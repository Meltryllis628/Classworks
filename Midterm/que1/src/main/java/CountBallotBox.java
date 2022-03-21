
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class CountBallotBox {

    private ArrayList<BallotPaper> ballots;
    public CountBallotBox(){
        ballots = new ArrayList<BallotPaper>();
    }


    public void addBallot(BallotPaper ballotPaper) {
        ballots.add(ballotPaper);
    }

    private void sortBallots(){
        Collections.sort(ballots);
    }

    public int getVotesFor(String candidate) {
        int count = 0;
        for (BallotPaper ballot :ballots){
            if(Objects.equals(ballot.getFirstChoice(),candidate)){
                count += 1;
            }
        }
        return count;
    }

    public void eliminateCandidate(String candidate) {
        for (BallotPaper ballot :ballots){
            if(Objects.equals(ballot.getFirstChoice(),candidate)){
                ballot.transferVotes();
            }
        }
    }

    public void transferCandidate(BallotPaper ballotPaper, int i) {
        int count = 0;
        for (BallotPaper ballot :ballots){
            if(Objects.equals(ballot,ballotPaper)&&count<i){
                count++;
                ballot.transferVotes();
            }
        }
    }

    @Override
    public String toString() {
        sortBallots();
        String result = new String();
        for (BallotPaper ballot :ballots){
            result += (ballot.toString()+'\n');
        }
        return result;
    }
}
