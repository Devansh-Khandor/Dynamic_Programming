package TwoDimensionalDP;

import java.util.*;

public class GridUniquePaths {
    public static void main(String args[]){
        int m = 3;
        int n = 3;
        System.out.println("Recursion: "+ recursion(m-1, n-1));
        int dp[][] = new int[m][n];
        for(int row[]: dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Memoization: "+ memo(m-1, n-1,dp));
        for(int row[]: dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Tabulation: "+ tabu(m, n, dp));
        System.out.println("Fastest: "+ fastest(m, n));
    }

    public static int recursion(int i, int j){
        if(i==0 && j==0){
            return 1;
        }
        if(i<0 || j<0){
            return 0;
        }
        int up = recursion(i-1, j);
        int left = recursion(i, j-1);
        return up+left;
    }

    public static int memo(int i, int j, int dp[][]){
        if(i==0 && j==0){
            return 1;
        }
        if(i<0 || j<0){
            return 0;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }

        int up = memo(i-1, j, dp);
        int left = memo(i, j-1, dp);
        return dp[i][j] = up+left;
    }

    public static int tabu(int m, int n, int dp[][]){
        dp[0][0] = 1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0){continue;}
                int up = 0;
                int left = 0;
                if(i>0)up=dp[i-1][j];
                if(j>0)left =dp[i][j-1];
                dp[i][j] = up+left;
            }
        }
        return dp[m-1][n-1];

    }

    public static int fastest(int m, int n){
        int dp[] = new int[n];
        for(int i=0;i<m;i++){
            int temp[] =  new int[n];
            for(int j=0;j<n;j++){
                if(i==0 && j==0){
                    temp[j] = 1;
                    continue;
                }
                int up = 0;
                int left = 0;
                if(i>0)up=dp[j];
                if(j>0)left =temp[j-1];
                temp[j] = up+left;
            }
            dp = temp;
        }
        return dp[n-1];
    }
}
