import java.util.ArrayList;

public class Permutation {
    private final String in;
    private ArrayList<String> a = new ArrayList<String>();
    // additional attribute if needed



    Permutation(final String str){
        in = str;
        // additional initialization if needed

    }

    public void permute(){
        // produce the permuted sequence of 'in' and store in 'a', recursively
        String str = "";
        ArrayList<Character> lst = new ArrayList<Character>();
        for(int i = 0;i<this.in.length();i++){
            lst.add((Character)this.in.charAt(i));
        }
        //System.out.println(lst);
        a = getPermutate(str,lst);
    }
    public static ArrayList<String> getPermutate(String str1,ArrayList<Character> lst){
        ArrayList<String> result = new ArrayList<String>();
        if(lst.isEmpty()){
            result.add(str1);
            //System.out.println(result);
            return result;
        }
        for(Character i:lst){
            String nstr = str1;
            ArrayList<Character> nlst = (ArrayList<Character>) lst.clone();
            nstr = nstr + i;
            nlst.remove(i);
            //System.out.println(nstr);
            //System.out.println(nlst);
            result.addAll(getPermutate(nstr,nlst));
            //System.out.println(result);
        }
        return result;
    }

    public ArrayList<String> getA(){
        return a;
    }

}
