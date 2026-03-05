package DPonSubsequences;

import java.util.*;

public class CoinChangeII {

    public static void main(String args[]){
        int amount = 5;
        int coins[] = {1,2,5};
        int n = coins.length;
        int dp[][] = new int[n][amount+1];
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Memo: "+memo(n-1,amount, coins, dp));
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Tabu: "+tabu(amount, coins, dp));
        System.out.println("Fastest: "+fastest(amount, coins));
    }
    
    public static int fastest(int target, int coins[]){
        int n = coins.length;
        int dp[] = new int[target+1];
        dp[0] = 1;
        for(int i=0;i<target+1;i++){
            if(i%coins[0]==0){
                dp[i] = 1;
            }
            else{
                dp[i] = 0;
            }
        }

        for(int i=1;i<n;i++){
            int temp[] = new int[target+1];
            temp[0] = 1;
            for(int j=1;j<target+1;j++){
                
                int notpick = dp[j];
                int pick = 0;
                if(coins[i]<=j){
                    pick = temp[j-coins[i]];
                }
                temp[j] = notpick+pick;
            }
            dp = temp;
        }
        return dp[target];

    }

    public static int tabu(int target, int coins[], int dp[][]){
        int n = coins.length;
        for(int i=0;i<n;i++){
            dp[i][0] = 1;
        }
        for(int i=0;i<target+1;i++){
            if(i%coins[0]==0){
                dp[0][i] = 1;
            }
            else{
                dp[0][i] = 0;
            }
        }


        for(int i=1;i<n;i++){
            for(int j=1;j<target+1;j++){
                
                int notpick = dp[i-1][j];
                int pick = 0;
                if(coins[i]<=j){
                    pick = dp[i][j-coins[i]];
                }
                dp[i][j] = notpick+pick;
            }
        }
        return dp[n-1][target];
    }

    public static int memo(int index, int target, int coins[], int dp[][]){
        if(target == 0){
            return 1;
        }
        if(index==0){
            if(target%coins[0]==0){
                return 1;
            }
            else{
                return 0;
            }
        }
        if(dp[index][target]!=-1){
            return dp[index][target];
        }
        int notpick = memo(index-1, target, coins, dp);
        int pick = 0;
        if(coins[index]<=target){
            pick = memo(index, target-coins[index],coins, dp);
        }
        return dp[index][target] = notpick+pick;

    }
}
