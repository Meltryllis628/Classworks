import java.util.Objects;

public class BallotPaper implements Comparable<BallotPaper>
{

    private String firstChoice;
    private String secondChoice;

    BallotPaper(String firstChoice, String secondChoice){
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
    }
    public String getFirstChoice() {
        return firstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public void transferVotes() {
        firstChoice = secondChoice;
        secondChoice = null;
    }

    @Override
    public String toString() {
        return "1:" + firstChoice +" 2:" + secondChoice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BallotPaper that = (BallotPaper) o;
        return (Objects.equals(firstChoice, that.firstChoice) && Objects.equals(secondChoice, that.secondChoice));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstChoice, secondChoice);
    }

    @Override
    public int compareTo(BallotPaper ballotPaper) {
        return this.firstChoice.compareTo(ballotPaper.firstChoice);
    }
}
