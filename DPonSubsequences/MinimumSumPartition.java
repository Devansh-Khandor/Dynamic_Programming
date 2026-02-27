package DPonSubsequences;

import java.util.Arrays;

//Given an array arr[]  containing non-negative integers, the task is to divide it into two sets set1 and set2 such that the absolute difference between their sums is minimum and find the minimum difference.

public class MinimumSumPartition {
    public static void main(String args[]){

        int nums[] = {1, 6, 11, 5};

        int n = nums.length;
        int sum = 0;
        for(int i=0;i<n;i++){
            sum+=nums[i];
        }
        boolean dp[][] = new boolean[n][sum+1];
        for(boolean row[]:dp){
            Arrays.fill(row, false);
        }
        System.out.println(tabu(sum, nums, dp));

    }

    public static int tabu(int target, int arr[], boolean dp[][]){
        int n = arr.length;
        for(int i=0;i<n;i++){
            dp[i][0] = true;
        }
        if(arr[0]<=target && arr[0]>=0){
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

        int min = Integer.MAX_VALUE;
        for(int i=0;i<=target/2;i++){
            if(dp[n-1][i]==true){
                min = Math.min(min, Math.abs((target-i)-i));
            }
        }

        return min;
    }
}
