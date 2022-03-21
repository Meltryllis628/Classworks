import java.sql.Array;
import java.lang.System;

public class Palindrome {
    public static void main(String[] args) {
        String inputString ="123321";
        if (isPalindrome(inputString.toCharArray())) {
            System.out.println(inputString + " is a palindrome.");
        } else {
            System.out.println(inputString + " is not a palindrome.");

        }
    }
    public static boolean isPalindrome (char[] S) {
        if(S.length<=1){
            return true;
        }
        if(S[0] == S[S.length-1]){
            char[] S0 = new char[S.length-2];
            for (int i = 0;i<S.length-2;i++){
                S0[i] = S[1 + i];
            }
            return isPalindrome(S0);
        }
        else{return false;}
    }
}
