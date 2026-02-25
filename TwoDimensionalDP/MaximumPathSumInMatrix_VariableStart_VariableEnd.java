package TwoDimensionalDP;

import java.util.Arrays;

public class MaximumPathSumInMatrix_VariableStart_VariableEnd {

    public static void main(String args[]){
        int mat[][] = new int[][]{{3, 6, 1}, {2, 3, 4}, {5, 5, 1}};
        int m = mat.length;
        int n = mat[0].length;
        int max = Integer.MIN_VALUE;
        int dp[][] = new int[m][n];
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        for(int j=0;j<n;j++){
            max = Math.max(max, memo(0, j, dp, mat));
        }
        System.out.println("Memo: "+max);
        for(int row[]:dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Tabu: "+tabu(dp, mat));
        System.out.println("Fastest: "+fastest(mat));
    }

    public static int memo(int i, int j, int dp[][], int arr[][]){
        int n = arr.length;
        int m = arr[0].length;
        if(i==n-1){
            return arr[i][j];
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int left = Integer.MIN_VALUE;
        int right = Integer.MIN_VALUE;
        if(j>0){left = arr[i][j]+memo(i+1, j-1, dp, arr);}
        int bottom = arr[i][j]+memo(i+1, j, dp, arr);
        if(j<m-1){right = arr[i][j]+memo(i+1, j+1, dp, arr);}
        return dp[i][j] = Math.max(left, Math.max(bottom, right));
    }
    
    public static int tabu(int dp[][], int arr[][]){
        int n = arr.length;
        int m = arr[0].length;
        for(int j=0;j<m;j++){
            dp[n-1][j] = arr[n-1][j];
        }
        
        for(int i=n-2;i>=0;i--){
            for(int j=0;j<m;j++){
                int left = Integer.MIN_VALUE;
                int right = Integer.MIN_VALUE;
                if(j>0){left = arr[i][j]+dp[i+1][j-1];}
                int bottom = arr[i][j]+dp[i+1][j];
                if(j<m-1){right = arr[i][j]+dp[i+1][j+1];}
                dp[i][j] = Math.max(left, Math.max(bottom, right));
            }
        }
        
        int max = Integer.MIN_VALUE;
        for(int j=0;j<m;j++){
            max = Math.max(max, dp[0][j]);
        }
        return max;
    }
    
    public static int fastest(int arr[][]){
        int n = arr.length;
        int m = arr[0].length;
        int dp[] = new int[m];
        for(int j=0;j<m;j++){
            dp[j] = arr[n-1][j];
        }
        
        for(int i=n-2;i>=0;i--){
            int temp[] = new int[m];
            for(int j=0;j<m;j++){
                int left = Integer.MIN_VALUE;
                int right = Integer.MIN_VALUE;
                if(j>0){left = arr[i][j]+dp[j-1];}
                int bottom = arr[i][j]+dp[j];
                if(j<m-1){right = arr[i][j]+dp[j+1];}
                temp[j] = Math.max(left, Math.max(bottom, right));
            }
            dp = temp;
        }
        
        int max = Integer.MIN_VALUE;
        for(int j=0;j<m;j++){
            max = Math.max(max, dp[j]);
        }
        return max;
    }
}
