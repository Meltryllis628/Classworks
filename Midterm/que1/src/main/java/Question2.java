public class Question2 {

    public static void main(String[] args) {
        System.out.println(tail("apple"));
        //outputs: 1 and 2
        System.out.println( distance( "aqua", "") );
        System.out.println( distance( "raaaa", "b") );
    }
    public static int min3(int a,int b,int c){
        int d = Math.min(a,b);
        int e = Math.min(d,c);
        return e;
    }

    public static int distance( String a, String b) {
        if(a.length()==0||b.length()==0){
            return a.length()+b.length();
        }
        if(a.charAt(0)==b.charAt(0)){
            return distance(tail(a),tail(b));
        }
        else{
            return 1+min3(distance(tail(a),tail(b)),distance(a,tail(b)),distance(tail(a),b));
        }
    }

    public static String tail(String s){
        return s.substring(1);
    }
}
