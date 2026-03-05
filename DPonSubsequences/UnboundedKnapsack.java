package DPonSubsequences;

import java.util.Arrays;

public class UnboundedKnapsack {
    public static void main(String args[]){
        int val[] = {1, 1};
        int wt[] = {2, 1};
        int capacity = 3;
        int n = wt.length;
        int dp[][] = new int[n][capacity+1];
        for(int row[]: dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Memo: "+ memo(n-1, capacity, val, wt, dp));
        for(int row[]: dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Tabu: "+ tabu(capacity, val, wt, dp));
        System.out.println("Fastest:"+fastest(capacity, val, wt));
    }

    public static int fastest(int w, int val[], int wt[]){
        int n = wt.length;
        int dp[] = new int[w+1];
        for(int i=0;i<w+1;i++){
            if(wt[0]<=i){
                dp[i] = (i/wt[0])*val[0];
            }
            else{
                dp[i] = 0;
            }
        }
        
        for(int i=1;i<n;i++){
            int temp[] = new int[w+1];
            for(int j=0;j<w+1;j++){
                int nottake = dp[j]; 
                int take = Integer.MIN_VALUE;
                if(wt[i]<=j){
                    take = val[i]+temp[j-wt[i]];
                }
                temp[j] = Math.max(take, nottake);
            }
            dp = temp;
        }
        return dp[w];
        
    }
    
    public static int tabu(int w, int val[], int wt[], int dp[][]){
        int n = wt.length;
        for(int i=0;i<w+1;i++){
            if(wt[0]<=i){
                dp[0][i] = (i/wt[0])*val[0];
            }
            else{
                dp[0][i] = 0;
            }
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<w+1;j++){
                int nottake = dp[i-1][j]; 
                int take = Integer.MIN_VALUE;
                if(wt[i]<=j){
                    take = val[i]+dp[i][j-wt[i]];
                }
                dp[i][j] = Math.max(take, nottake);
            }
        }
        return dp[n-1][w];
        
    }
    
    public static int memo(int index, int w, int val[], int wt[], int dp[][]){
        if(index==0){
           if(wt[0]<=w){
                return (w/wt[0])*val[0];
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
            take = val[index]+memo(index, w-wt[index], val, wt, dp);
        }
        
        return dp[index][w]=Math.max(nottake, take);
    }
}
