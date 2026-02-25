package TwoDimensionalDP;

import java.util.Arrays;

public class Triangle {
    public static void main(String args[]){
        int arr[][] = new int[][]{{2},{3,4},{6,5,7},{4,1,8,3}};
        int m = arr.length;
        int dp[][] = new int[m][m];
        for(int row[]: dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Memo: "+memo(0, 0, dp, arr));
        for(int row[]: dp){
            Arrays.fill(row, -1);
        }
        System.out.println("Tabu: "+tabu(dp, arr));
        System.out.println("Fastest: "+fastest(arr));
    }

    public static int memo(int i, int j, int dp[][], int arr[][]){
        if(i==arr.length-1){
            return arr[i][j];
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int bottom = arr[i][j]+memo(i+1, j, dp, arr);
        int right = arr[i][j]+memo(i+1, j+1, dp, arr);
        return dp[i][j] = Math.min(bottom, right);
    }

    public static int tabu(int dp[][], int arr[][]){
        int n = arr.length;
        for(int j=0;j<n;j++){
            dp[n-1][j] = arr[n-1][j];
        }
        for(int i=n-2;i>=0;i--){
            for(int j=i;j>=0;j--){
                int bottom = arr[i][j]+dp[i+1][j];
                int right = arr[i][j]+dp[i+1][j+1];
                dp[i][j] = Math.min(bottom, right);
            }
        }
        return dp[0][0];
    }

    public static int fastest(int arr[][]){
        int n = arr.length;
        int dp[] = new int[n];
        for(int j=0;j<n;j++){
            dp[j] = arr[n-1][j];
        }
        for(int i=n-2;i>=0;i--){
            int temp[] = new int[n];
            for(int j=i;j>=0;j--){
                int bottom = arr[i][j]+dp[j];
                int right = arr[i][j]+dp[j+1];
                temp[j] = Math.min(bottom, right);
            }
            dp = temp;
        }
        return dp[0];
    }
}
