package DPonStrings;

import java.util.*;

public class LongestPalindromicSubsequence {
    public static void main(String args[]){
        String s1 = "bbabcbcab";
        StringBuilder stringBuilder = new StringBuilder(s1);
        stringBuilder.reverse();
        String s2 = stringBuilder.toString();
        int n = s1.length();
        int dp[][] = new int[n][n];
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Memo: "+memo(n-1, n-1, s1, s2, dp));

        int dp2[][] = new int[n+1][n+1];
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Tabu: "+tabu(s1, s2, dp2));
    }

    public static int memo(int idx1, int idx2, String s1, String s2, int dp[][]){
        if(idx1<0 || idx2<0){
            return 0;
        }
        if(s1.charAt(idx1)==s2.charAt(idx2)){
            return dp[idx1][idx2] = 1+memo(idx1-1, idx2-1, s1, s2, dp);
        }
        return dp[idx1][idx2] = Math.max(memo(idx1-1, idx2, s1, s2, dp), memo(idx1, idx2-1, s1, s2, dp));
    }

    public static int tabu(String s1, String s2, int dp[][]){
        int n = s1.length();
        for(int i=0;i<n+1;i++){
            dp[i][0] = 0;
        }
        for(int i=0;i<n+1;i++){
            dp[0][i] = 0;
        }

        for(int i=1;i<n+1;i++){
            for(int j=1;j<n+1;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j] = 1+dp[i-1][j-1]; 
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][n];
    }


}
