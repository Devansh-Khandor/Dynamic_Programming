package TwoDimensionalDP;

import java.util.Arrays;

public class MinPathSum {
    public static void main(String args[]){
        int grid[][] = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        int m = grid.length;
        int n = grid[0].length;
        int dp[][] = new int[m][n];
        for(int row[]: dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Memoization: "+ memo(m-1, n-1, grid, dp));
    }

    public static int memo(int i, int j, int grid[][], int dp[][]){
        if(i==0 && j==0){
            return grid[i][j];
        }
        if(i<0 || j<0){
            return Integer.MAX_VALUE;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int up = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;
        if(i>0)up = grid[i][j]+memo(i-1, j, grid, dp);
        if(j>0)left = grid[i][j]+memo(i, j-1, grid, dp);

        return dp[i][j] = Math.min(up, left);
    }
}
