package DPonStrings;

import java.util.*;
/*Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings. */

public class LongestComonSubsequence {
    public static void main(String args[]){
        String text1 = "abcde";
        String text2 = "ace";
        int n = text1.length();
        int m = text2.length();
        int dp[][] = new int[n][m];
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Memo: "+memo(n-1, m-1, text1, text2, dp));
        int dp2[][] = new int[n+1][m+1];
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Tabu: "+tabu(text1, text2, dp2));
        System.out.println("Fastest: "+fastest(text1, text2));

    }

    public static int recc(int idx1, int idx2, String text1, String text2){
        if(idx1<0 || idx2<0){
            return 0;
        }
        if(text1.charAt(idx1)==text2.charAt(idx2)){
            return 1 + recc(idx1-1, idx2-1, text1, text2);
        }
        else{
            return Math.max(recc(idx1-1, idx2, text1, text2), recc(idx1, idx2-1, text1, text2));
        }
    }

    public static int fastest(String text1, String text2){
        int n = text1.length();
        int m = text2.length();
        int dp[] = new int[m+1];
        for(int j=0;j<m+1;j++){
            dp[j] = 0;
        }
        for(int i=1;i<n+1;i++){
            int temp[] = new int[m+1];
            for(int j=1;j<m+1;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    temp[j] = 1 + dp[j-1]; 
                }
                else{
                    temp[j]=Math.max(dp[j], temp[j-1]);
                }
            }
            dp = temp;
        }

        return dp[m];

    }

    public static int tabu(String text1, String text2, int dp[][]){
        int n = text1.length();
        int m = text2.length();

        for(int i=0;i<n+1;i++){
            dp[i][0] = 0;
        }
        for(int j=0;j<m+1;j++){
            dp[0][j] = 0;
        }

        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1]; 
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[n][m];
    }

    public static int memo(int idx1, int idx2, String text1, String text2, int dp[][]){
        if(idx1<0 || idx2<0){
            return 0;
        }
        if(dp[idx1][idx2]!=-1){
            return dp[idx1][idx2];
        }
        if(text1.charAt(idx1)==text2.charAt(idx2)){
            return dp[idx1][idx2] = 1 + memo(idx1-1, idx2-1, text1, text2, dp);
        }
        else{
            return dp[idx1][idx2] = Math.max(memo(idx1-1, idx2, text1, text2, dp), memo(idx1, idx2-1, text1, text2, dp));
        }
    }
}
