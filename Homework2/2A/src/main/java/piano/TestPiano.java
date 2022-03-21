package piano;

import javax.sound.midi.MidiUnavailableException;

import midi.Midi;
import music.Pitch;

/**
 * Created by ngaiman_cheung on 17/10/16.
 */
public class TestPiano {

    public static void main(String[] args) {

        try {
            Midi midi;
            PianoMachine pm;
            midi = Midi.getInstance();
            midi.clearHistory();
            pm = new PianoMachine();
            pm.toggleRecording();

            pm.beginNote(new Pitch(0));
            Midi.rest(50);
            pm.endNote(new Pitch(0));

            pm.changeInstrument();
            Midi.rest(10);
            pm.changeInstrument();

            pm.beginNote(new Pitch(2));
            Midi.rest(50);
            pm.endNote(new Pitch(2));

            pm.shiftUp();
            Midi.rest(10);
            pm.beginNote(new Pitch(4));
            Midi.rest(50);
            pm.endNote(new Pitch(4));

            pm.shiftDown();
            pm.shiftDown();
            Midi.rest(10);
            pm.beginNote(new Pitch(5));
            Midi.rest(50);
            pm.endNote(new Pitch(5));
            pm.toggleRecording();
            System.out.println(midi.history());
            midi.clearHistory();
            pm.playback();


            System.out.println(midi.history());
            midi.clearHistory();
            new TestQ1Hw().test();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }



    }

}