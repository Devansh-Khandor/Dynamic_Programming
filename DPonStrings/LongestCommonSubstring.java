package DPonStrings;

import java.util.*;

public class LongestCommonSubstring {
    public static void main(String args[]){
        String s1 = "ABCDGH";
        String s2 = "ACDGHR";
        int n = s1.length();
        int m = s2.length();
        int dp[][] = new int[n+1][m+1];
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        System.out.println(tabu(s1, s2, dp));
    }

    public static int tabu(String s1, String s2, int dp[][]){
        int n = s1.length();
        int m = s2.length();
        for(int i=0;i<n+1;i++){
            dp[i][0]=0;
        }
        for(int i=0;i<m+1;i++){
            dp[0][i]=0;
        }
        
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                char c1 = s1.charAt(i-1);
                char c2 = s2.charAt(j-1);
                if(c1==c2){
                    dp[i][j] = 1+dp[i-1][j-1];
                }
                else{
                    dp[i][j] = 0;
                }
            }
        }
        
        int max = 0;
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }
}
