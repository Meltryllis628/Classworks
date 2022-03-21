package piano;

import java.util.ArrayList;
import java.lang.System;

import javax.sound.midi.MidiUnavailableException;

import midi.Instrument;
import midi.Midi;
import music.NoteEvent;
import music.Pitch;

public class PianoMachine {
	
	private Midi midi;
	private Instrument instrument = Instrument.PIANO;
	private int position = 0;
	private ArrayList<Pitch> playing = new ArrayList<Pitch>();
	private boolean isRecording = false;
	private ArrayList<NoteEvent> recordingList = new ArrayList<NoteEvent>();
	/**
	 * constructor for PianoMachine.
	 * 
	 * initialize midi device and any other state that we're storing.
	 */
    public PianoMachine() {
    	try {
            midi = Midi.getInstance();
            instrument =  Instrument.PIANO;
            position = 0;
            isRecording = false;
            playing = new ArrayList<Pitch>();
            recordingList = new ArrayList<NoteEvent>();
        } catch (MidiUnavailableException e1) {
            System.err.println("Could not initialize midi device");
            e1.printStackTrace();
            return;
        }
    }
    
    //TODO write method spec
    public void beginNote(Pitch rawPitch) {
        Pitch pitch = rawPitch.transpose(12*position);
        if(!playing.contains(pitch)) {
            midi.beginNote(pitch.toMidiFrequency(), instrument);
            playing.add(pitch);
        }
        if(isRecording){
            NoteEvent noteEvent = new NoteEvent(pitch,System.currentTimeMillis(),instrument, NoteEvent.Kind.start);
            recordingList.add(noteEvent);
        }
    }
    
    //TODO write method spec
    public void endNote(Pitch rawPitch) {
        Pitch pitch = rawPitch.transpose(12*position);
        if(playing.contains(pitch)) {
            midi.endNote(pitch.toMidiFrequency(), instrument);
            playing.remove(pitch);
        }
        if(isRecording){
            NoteEvent noteEvent = new NoteEvent(pitch,System.currentTimeMillis(),instrument, NoteEvent.Kind.stop);
            recordingList.add(noteEvent);
        }
    }
    
    //TODO write method spec
    public void changeInstrument() {
        instrument = instrument.next();
    }
    
    //TODO write method spec
    public void shiftUp() {
        if(position<2){
            position += 1;
        }
        else{
            position = 2;
        }
    }
    
    //TODO write method spec
    public void shiftDown() {
        if(position>-2){
            position -= 1;
        }
        else{
            position = -2;
        }
    }
    
    //TODO write method spec
    public boolean toggleRecording() {
    	if(!isRecording){
    	    recordingList.clear();
    	    isRecording = true;
    	    return true;
        }
    	else{
            isRecording = false;
            return false;
        }
    }
    
    //TODO write method spec
    public void playback() {
        long last = recordingList.get(0).getTime();
        for (NoteEvent note : recordingList) {
            if (note.getKind().equals(NoteEvent.Kind.start)) {
                Midi.rest((int) (note.getTime() - last) / 10);
                midi.beginNote(note.getPitch().toMidiFrequency(), note.getInstr());
                last = note.getTime();
            } else if (note.getKind().equals(NoteEvent.Kind.stop)) {
                Midi.rest((int) (note.getTime() - last) / 10);
                midi.endNote(note.getPitch().toMidiFrequency(), note.getInstr());
                last = note.getTime();
            }
        }
    }
}
