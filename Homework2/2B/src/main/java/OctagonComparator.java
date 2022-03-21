import java.util.Comparator;

//Modify the class header and implement any necessary methods
public class OctagonComparator implements Comparator<Octagon> {
    @Override
    public int compare(Octagon octagon, Octagon t1) {
        if ((octagon.getSide()-t1.getSide())%1 == 0){
            return (int)(octagon.getSide()-t1.getSide());
        }
        return (int)(((octagon.getSide()-t1.getSide())%1)/0.000000001);
    }
}
