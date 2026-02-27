package DPonSubsequences;

import java.util.*;

//Question: Given an array of positive integers arr[] and a value sum, determine if there is a subset of arr[] with sum equal to given sum. 

public class SubsetSum {
    public static void main(String args[]){
        int arr[] = {3, 34, 4, 12, 5, 2};
        int sum = 9;

        // int dp[][] = new int[arr.length][sum+1];
        // for(int row[]:dp){
        //     Arrays.fill(row, -1);
        // }
        // System.out.println("Memo: "+ memo(arr.length-1, sum, arr, dp));
        boolean dp[][] = new boolean[arr.length][sum+1];
        for(boolean row[]:dp){
            Arrays.fill(row, false);
        }
        System.out.println("Tabu: "+ tabu(sum, arr, dp));
        System.out.println("Fastest: "+fastest(sum, arr));
    }

    public static boolean memo(int index, int target, int arr[], int dp[][]){
        if(target == 0){
            return true;
        }
        if(index==0){
            return arr[0]==target;
        }
        if(dp[index][target]!=-1){
            return dp[index][target]==1;
        }
        boolean nottake = memo(index-1, target, arr, dp);
        boolean take = false;
        if(target>=arr[index]){
            take = memo(index-1, target-arr[index], arr, dp);
        }

        dp[index][target] = take||nottake?1:0;
        return take||nottake;
    }

    public static boolean tabu(int target, int arr[], boolean dp[][]){
        int n = arr.length;
        for(int i=0;i<n;i++){
            dp[i][0] = true;
        }
        if(arr[0]<=target){
            dp[0][arr[0]] = true;
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

    public static boolean fastest(int target, int arr[]){
        int n = arr.length;
        boolean dp[] = new boolean[target+1];
        dp[0] = true;
        if(arr[0]<=target){
            dp[arr[0]] = true;
        }
        for(int i=1;i<n;i++){
            boolean temp[] = new boolean[target+1];
            temp[0] = true;
            for(int j=1;j<target+1;j++){
                boolean nottake = dp[j];
                boolean take = false;
                if(j>=arr[i]){
                    take = dp[j-arr[i]];
                }
                temp[j] = take||nottake;
            }
            dp = temp;
        }
        return dp[target];
    }
}
