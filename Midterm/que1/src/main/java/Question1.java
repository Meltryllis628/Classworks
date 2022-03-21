import java.util.ArrayList;

public class Question1 {

    public static void main(String[] args) {

        //output: 25
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(sumThreeAndSeven(values));

        // output: shnydys
        System.out.println( dropVowelsAndCase("SHINYDays")); //shnydys

        // output: [3, 0, 2, 2, 4, 6, 10]
        System.out.println(someSequence(0));
        // output: [3, 0, 2, 2]
        System.out.println(someSequence(1));
        System.out.println(someSequence(2));
        System.out.println(someSequence(3));
        System.out.println(someSequence(4));
        System.out.println(someSequence(5));
        System.out.println(someSequence(6));
        System.out.println(someSequence(7));
        System.out.println(someSequence(10000));
    }

    public static int sumThreeAndSeven( int[] values){
        int sum = 0;
        for(int i :values){
            if(i%3==0||i%7==0){
                sum += i;
            }
        }
        return sum;
    }

    public static String dropVowelsAndCase(String s){
        s = s.toLowerCase();
        String s1 = new String();
        for(int i = 0;i<s.length();i++){
            if(!(s.charAt(i)=='a'||s.charAt(i)=='e'||s.charAt(i)=='i'||s.charAt(i)=='o'||s.charAt(i)=='u')){
                s1+= s.charAt(i);
            }
        }
        return s1;

    }

    public static ArrayList<Integer> someSequence(int n){
        ArrayList<Integer> seq = new ArrayList<Integer>();
        seq.add(3);
        if(n == 0)return seq;
        seq.add(0);
        if(n == 1)return seq;
        seq.add(2);
        if(n == 2)return seq;
        for (int i =3;i<=n;i++){
            seq.add(seq.get(i-1)+seq.get(i-2));
        }
        return seq;
    }


}
