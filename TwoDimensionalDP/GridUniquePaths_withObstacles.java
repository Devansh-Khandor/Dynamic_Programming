package TwoDimensionalDP;

import java.util.Arrays;

public class GridUniquePaths_withObstacles {

    public static void main(String args[]){
        int arr[][] = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        int m = arr.length;
        int n = arr[0].length;
        if(arr[0][0] == 1){
            System.out.println(0);
            return;
        }
        int dp[][] = new int[m][n];
        for(int row[]: dp){
            Arrays.fill(row, -1);
        }
        System.out.println(memo(m-1, n-1, arr, dp));
    }

    public int uniquePathsWithObstacles(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        if(arr[0][0] == 1){
            return 0;
        }
        int dp[][] = new int[m][n];
        for(int row[]: dp){
            Arrays.fill(row, -1);
        }
        return memo(m-1, n-1, arr, dp);

    }

    public static int memo(int i, int j, int arr[][], int dp[][]){
        if(i==0 && j==0){
            return 1;
        }
        if(i<0 || j<0){
            return 0;
        }
        if(arr[i][j] == 1){
            return 0;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }

        int up = memo(i-1, j, arr, dp);
        int left = memo(i, j-1, arr, dp);
        return dp[i][j] = up+left;
    }
}
