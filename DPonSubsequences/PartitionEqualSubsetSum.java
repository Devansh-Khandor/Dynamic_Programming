package DPonSubsequences;

import java.util.Arrays;

//Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

public class PartitionEqualSubsetSum {
    public static void main(String args[]){
        int nums[] = {1,5,11,5};
        int n = nums.length;
        int sum = 0;
        for(int i=0;i<n;i++){
            sum+=nums[i];
        }
        if(sum%2==1){
            System.out.println("false");
        }
        else{
            sum = sum/2;
            boolean dp[][] = new boolean[n][sum+1];
            for(boolean row[]:dp){
                Arrays.fill(row, false);
            }
            System.out.println(tabu(sum, nums, dp));
        }
    }

    public static boolean tabu(int target, int arr[], boolean dp[][]){
        int n = arr.length;
        for(int i=0;i<n;i++){
            dp[i][0] = true;
        }
        if(arr[0]<=target){
            dp[0][arr[0]]=true;
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<target+1;j++){
                boolean nottake = dp[i-1][j];
                boolean take = false;
                if(j>=arr[i]){
                    take = dp[i-1][j-arr[i]];
                }
                dp[i][j] = take||nottake;
            }
        }
        return dp[n-1][target];
    }
}
