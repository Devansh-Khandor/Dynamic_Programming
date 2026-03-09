package DPonStrings;

import java.util.Arrays;

public class MinInsertionsToMakePalindrome {
    public static void main(String args[]){
        String s1 = "mbadm";
        StringBuilder stringBuilder = new StringBuilder(s1);
        stringBuilder.reverse();
        String s2 = stringBuilder.toString();
        int n = s1.length();
        int dp[][] = new int[n][n];
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        System.out.println(n-memo(n-1, n-1, s1, s2, dp));
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
