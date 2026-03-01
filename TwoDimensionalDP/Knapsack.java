package TwoDimensionalDP;

import java.util.Arrays;

public class Knapsack {
    public static void main(String args[]){
        int val[] = {1, 2, 3};
        int wt[] = {4, 5, 1};
        int W = 4;
        int dp[][] = new int[val.length][W+1];
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Memo:"+memo(val.length-1, W, val, wt, dp));
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Tabulation:"+tabu(W, val, wt, dp));
        System.out.println("Fastest:"+fastest(W, val, wt));
    }

    public static int fastest(int w, int val[], int wt[]){
        int n = val.length;
        int dp[]= new int[w+1];
        for(int j=0;j<w+1;j++){
            if(wt[0]<=j){
                dp[j] = val[0];
            }
            else{
                dp[j] = 0;
            }
        }

        for(int i=1;i<n;i++){
            int temp[]= new int[w+1];
            temp[0] = 0;
            for(int j=0;j<w+1;j++){
                int nottake = dp[j];
                int take = Integer.MIN_VALUE;
                if(wt[i]<=j){
                    take = val[i]+dp[j-wt[i]];
                }
                temp[j] = Math.max(nottake, take);
            }
            dp=temp;
        }
        return dp[w];
    }

    public static int tabu(int w, int val[], int wt[], int dp[][]){
        int n = val.length;
        for(int j=0;j<w+1;j++){
            if(wt[0]<=j){
                dp[0][j] = val[0];
            }
            else{
                dp[0][j] = 0;
            }
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<w+1;j++){
                int nottake = dp[i-1][j];
                int take = Integer.MIN_VALUE;
                if(wt[i]<=j){
                    take = val[i]+dp[i-1][j-wt[i]];
                }
                dp[i][j] = Math.max(nottake, take);
            }
        }
        return dp[n-1][w];
    }

    public static int memo(int index, int w, int val[], int wt[], int dp[][]){
        if(index==0){
            if(wt[0]<=w){
                return val[0];
            }
            else{
                return 0;
            }
        }
        if(dp[index][w]!=-1){
            return dp[index][w];
        }
        int nottake = memo(index-1, w, val, wt, dp);
        int take = Integer.MIN_VALUE;
        if(wt[index]<=w){
            take = val[index]+memo(index-1, w-wt[index], val, wt, dp);
        }
        
        return dp[index][w]=Math.max(nottake, take);
    }
}
