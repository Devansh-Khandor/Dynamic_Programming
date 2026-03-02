package DPonSubsequences;

import java.util.*;

/*You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.*/

public class MinimumCoins {
    public static void main(String args[]){
        int coins[] = {1,2,5};
        int amount = 11;
        int n = coins.length;
        int dp[][] = new int[n][amount+1];
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        int res = fastest(amount, coins);
        if(res==Integer.MAX_VALUE){
            System.out.println("-1");
        }
        else{
            System.out.println(res);
        }
    }

    public static int fastest(int target, int coins[]){
        int dp[] = new int[target+1];
        for(int i=0;i<target+1;i++){
            if(i%coins[0]==0){
                dp[i]= i/coins[0];
            }
            else{
                dp[i] = Integer.MAX_VALUE;
            }
        }

        for(int i=1;i<coins.length;i++){
            int temp[] = new int[target+1];
            for(int j=0;j<target+1;j++){
                int nottake = dp[j];
                int take = Integer.MAX_VALUE;
                if(coins[i]<=j){
                    int res = temp[j-coins[i]];
                    if(res!=Integer.MAX_VALUE){
                        take = 1+res;
                    }
                }
                temp[j] = Math.min(take, nottake);
            }
            dp = temp;
        }

        return dp[target];
    }

    public static int tabu(int target, int coins[], int dp[][]){
        for(int i=0;i<target+1;i++){
            if(i%coins[0]==0){
                dp[0][i]= i/coins[0];
            }
            else{
                dp[0][i] = Integer.MAX_VALUE;
            }
        }

        for(int i=1;i<coins.length;i++){
            for(int j=0;j<target+1;j++){
                int nottake = dp[i-1][j];
                int take = Integer.MAX_VALUE;
                if(coins[i]<=j){
                    int res = dp[i][j-coins[i]];
                    if(res!=Integer.MAX_VALUE){
                        take = 1+res;
                    }
                }
                dp[i][j] = Math.min(take, nottake);
            }
        }
        return dp[coins.length-1][target];
    }

    public static int memo(int index, int target, int coins[], int dp[][]){
        if(index==0){
            if(target%coins[0]==0){
                return target/coins[0];
            }
            else{
                return Integer.MAX_VALUE;
            }
        }
        if(dp[index][target]!=-1){
            return dp[index][target];
        }

        int nottake = memo(index-1, target, coins, dp);
        int take = Integer.MAX_VALUE;
        if(coins[index]<=target){
            int res = memo(index, target - coins[index], coins, dp);
            if(res != Integer.MAX_VALUE){
                take = 1 + res;
            }
        }
        return dp[index][target] = Math.min(take, nottake);
    }
}
