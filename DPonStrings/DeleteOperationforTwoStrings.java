package DPonStrings;

import java.util.Arrays;

/*Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string. */

public class DeleteOperationforTwoStrings {
    public static void main(String args[]){
        String s1 = "sea";
        String s2 = "eat";
        int n = s1.length();
        int m = s2.length();
        int dp[][] = new int[n][m];
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        System.out.println(n+m-(2*memo(n-1, m-1, s1, s2, dp)));
    }

    public static int memo(int idx1, int idx2, String s1, String s2, int dp[][]){
        if(idx1<0 || idx2<0){
            return 0;
        }
        if(dp[idx1][idx2]!=-1){
            return dp[idx1][idx2];
        }
        if(s1.charAt(idx1)==s2.charAt(idx2)){
            return dp[idx1][idx2] = 1+memo(idx1-1, idx2-1, s1, s2, dp);
        }
        return dp[idx1][idx2] = Math.max(memo(idx1-1, idx2, s1, s2, dp), memo(idx1, idx2-1, s1, s2, dp));
    }
}
