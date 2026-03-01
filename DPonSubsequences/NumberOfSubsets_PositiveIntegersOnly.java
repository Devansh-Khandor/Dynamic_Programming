package DPonSubsequences;

import java.util.*;

/*Given an array of positive integers and an integer target,
count the total number of subsets whose sum is equal to the target.

Each element in the array can be used at most once.

Return the number of such subsets. */

public class NumberOfSubsets_PositiveIntegersOnly {
    public static void main(String args[]){
        int arr[] = {5, 2, 3, 10, 6, 8};
        int target = 10;
        int n = arr.length;
        int dp[][] = new int[n][target+1];
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Memo: "+ memo(n-1, target, arr, dp));
        for(int row[]:dp){
            Arrays.fill(row, 0);
        }
        System.out.println("Tabu: "+ tabu(target, arr, dp));
        System.out.println("Fastest: "+ fastest(target, arr));
    }

    public static int memo(int index, int target, int arr[], int dp[][]){
        if(target == 0){
            return 1;
        }
        if(index == 0){
            return target == arr[0]?1:0;
        }
        if(dp[index][target]!=-1){
            return dp[index][target];
        }
        int nottake = memo(index-1, target, arr, dp);
        int take = 0;
        if(target>=arr[index]){
            take = memo(index-1, target-arr[index], arr, dp);
        }
        return dp[index][target]=take+nottake;
    }

    public static int tabu(int target, int arr[], int dp[][]){
        int n = arr.length;
        for(int i=0;i<n;i++){
            dp[i][0] = 1;
        }
        if(arr[0]<= target){
            dp[0][arr[0]] = 1;
        }
        
        for(int i=1;i<n;i++){
            for(int j=1;j<target+1;j++){
                int nottake = dp[i-1][j];
                int take = 0;
                if(j>=arr[i]){
                    take = dp[i-1][j-arr[i]];
                }
                dp[i][j] = take+nottake;
            }
        }
        return dp[n-1][target];
    }

    public static int fastest(int target, int arr[]){
        int n = arr.length;
        int dp[] = new int[target+1];
        dp[0] = 1;
        if(arr[0]<=target){
            dp[arr[0]] = 1;
        }

        for(int i=1;i<n;i++){
            int temp[] = new int[target+1];
            temp[0] = 1;
            for(int j=1;j<target+1;j++){
                int nottake = dp[j];
                int take = 0;
                if(j>=arr[i]){
                    take = dp[j-arr[i]];
                }
                temp[j] = take+nottake;
            }
            dp = temp;
        }

        return dp[target];

    }
}
