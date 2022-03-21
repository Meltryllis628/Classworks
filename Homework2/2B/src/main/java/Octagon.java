import java.util.Comparator;
import java.util.Objects;

public class Octagon implements Comparable<Octagon>{
    private double side;
    public Octagon(double side){
        this.side = side;
    }
    public double getSide() {
        return side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Octagon octagon = (Octagon) o;
        return Double.compare(octagon.side, side) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side);
    }

    @Override
    public int compareTo(Octagon octagon) {
        if ((this.side-octagon.side)%1 == 0){
            return (int)(this.side-octagon.side);
        }
        return (int)(((this.side-octagon.side)%1)/0.000000001);
    }


}