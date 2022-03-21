import java.util.Objects;

//Modify the class header and implement any necessary methods
public class Octagon01 implements Comparable<Octagon01>{
    private double side;
    public Octagon01(double side){
        this.side = side;
    }
    public double getSide() {
        return side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Octagon01 octagon = (Octagon01) o;
        return Double.compare(octagon.side, side) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side);
    }

    @Override
    public int compareTo(Octagon01 octagon) {
        if ((this.side-octagon.side)%1 == 0){
            return (int)(this.side-octagon.side);
        }
        return (int)(((this.side-octagon.side)%1)/0.000000001);
    }


}