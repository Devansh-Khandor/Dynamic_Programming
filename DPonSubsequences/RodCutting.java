package DPonSubsequences;

import java.util.*;

/*Given a rod of length n inches and an array price[], where price[i] denotes the value of a piece of length i. Your task is to determine the maximum value obtainable by cutting up the rod and selling the pieces.

Note: n = size of price, and price[] is 1-indexed array. */

public class RodCutting {
    public static void main(String args[]){
        int price[] = {1, 5, 8, 9, 10, 17, 17, 20};
        int n = price.length;
        int dp[][] = new int[n][n+1];
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Memo: "+memo(n-1, n, price, dp));
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Tabu: "+tabu(n, price, dp));
        System.out.println("Fastest: "+ fastest(n, price));
    }

    public static int fastest(int n, int price[]){
        
        int dp[] = new int[n+1];
        for(int i=0;i<n+1;i++){
            dp[i] = price[0]*i;
        }
        dp[0] = 0;
        for(int i=1;i<n;i++){
            int temp[] = new int[n+1];
            temp[0] = 0;
            for(int j=1;j<n+1;j++){
                int notpick = dp[j];
                int pick = Integer.MIN_VALUE;
                if(j-(i+1)>=0){
                    pick = price[i]+temp[j-(i+1)];
                }
                temp[j] = Math.max(notpick, pick);
            }
            dp = temp;
        }
        return dp[n];
    }
    
    public static int tabu(int n, int price[], int dp[][]){
        for(int i=0;i<n+1;i++){
            dp[0][i] = price[0]*i;
        }
        for(int i=0;i<n;i++){
            dp[i][0] = 0;
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<n+1;j++){
                int notpick = dp[i-1][j];
                int pick = Integer.MIN_VALUE;
                if(j-(i+1)>=0){
                    pick = price[i]+dp[i][j-(i+1)];
                }
                dp[i][j] = Math.max(notpick, pick);
            }
        }
        return dp[n-1][n];
    }
    
    public static int memo(int index, int n, int price[], int dp[][]){
        if(index==0){
            return price[0]*n;
        }
        
        if(n==0){
            return 0;
        }
        
        if(dp[index][n]!=-1){
            return dp[index][n];
        }
        
        int notpick = memo(index-1, n, price, dp);
        int pick = Integer.MIN_VALUE;
        if(n-(index+1)>=0){
            pick = price[index]+memo(index, n-(index+1), price, dp);
        }
        return dp[index][n] = Math.max(notpick, pick);
    }
}
