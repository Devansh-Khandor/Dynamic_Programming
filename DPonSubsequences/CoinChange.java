package DPonSubsequences;

import java.util.Arrays;

public class CoinChange {
    public static void main(String args[]){
        int coins[] = {1,2,5};
        int amount = 11;
        int n = coins.length;
        int dp[][] = new int[n][amount+1];
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        int res = memo(n-1, amount, coins, dp);
        if(res==Integer.MAX_VALUE){
            System.out.println("-1");
        }
        else{
            System.out.println("Memo:"+res);
        }

        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        int res2 = tabu(amount, coins, dp);
        if(res2==Integer.MAX_VALUE){
            System.out.println("-1");
        }
        else{
            System.out.println("Tabu:"+res2);
        }
        int res3 = fastest(coins, amount);
        if(res3==Integer.MAX_VALUE){
            System.out.println("-1");
        }
        else{
            System.out.println("Tabu:"+res3);
        }

    }

    public static int fastest(int coins[], int target){
        int n = coins.length;
        int dp[] = new int[target+1];
        for(int i=0;i<target+1;i++){
            if(i%coins[0]==0){
                dp[i] = i/coins[0];
            }
            else{
                dp[i] = Integer.MAX_VALUE;
            }
        }

        for(int i=1;i<n;i++){
            int temp[] = new int[target+1];

            for(int j=0;j<target+1;j++){
                int notpick = dp[j];
                int pick = Integer.MAX_VALUE;
                if(coins[i]<=j){
                    int res = temp[j-coins[i]];
                    if(res!=Integer.MAX_VALUE){
                        pick = 1+res;
                    }
                }
                temp[j] = Math.min(pick, notpick);
            }
            dp = temp;
        }

        return dp[target];
    }

    public static int tabu(int target, int coins[], int dp[][]){
        int n = coins.length;
        for(int i=0;i<target+1;i++){
            if(i%coins[0]==0){
                dp[0][i] = i/coins[0];
            }
            else{
                dp[0][i] = Integer.MAX_VALUE;
            }
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<target+1;j++){
                int notpick = dp[i-1][j];
                int pick = Integer.MAX_VALUE;
                if(coins[i]<=j){
                    int res = dp[i][j-coins[i]];
                    if(res!=Integer.MAX_VALUE){
                        pick = 1+res;
                    }
                }
                dp[i][j] = Math.min(pick, notpick);
            }
        }

        return dp[n-1][target];
    }

    public static int memo(int index,int target, int coins[], int dp[][]){
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
        int notpick = memo(index-1, target, coins, dp);
        int pick = Integer.MAX_VALUE;
        if(coins[index]<=target){
            int res = memo(index, target-coins[index], coins, dp);
            if(res!=Integer.MAX_VALUE){
                pick = 1+res;
            }
        }
        return dp[index][target] = Math.min(notpick, pick);
    }
}
