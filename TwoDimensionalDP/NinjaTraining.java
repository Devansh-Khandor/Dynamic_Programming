package TwoDimensionalDP;
import java.util.*;

public class NinjaTraining {
    public static void main(String args[]){
        int arr[][] = new int[][]{{2, 1, 3}, {3, 4, 6}, {10, 1, 6}, {8, 3, 7}};
        int dp[][] = new int[arr.length][4];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        System.out.println("Memo: "+ memo(arr.length-1, 3, dp, arr));
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        System.out.println("Tabu: "+ tabu(dp, arr));

        System.out.println("Fastest: "+ fastest(arr));

    }

    public static int memo(int day, int last, int dp[][], int arr[][]){
        if(day == 0){
            int maxi = 0;
            for(int i=0;i<=2;i++){
                if(i!=last){
                    int points = arr[0][i];
                    maxi = Math.max(points, maxi);
                }
            }
            return maxi;
        }
        if(dp[day][last]!=-1)return dp[day][last];

        int maxi = 0;
        for(int i=0;i<=2;i++){
            if(i!=last){
                int points = arr[day][i] + memo(day-1, i, dp, arr);
                maxi = Math.max(maxi, points);
            }
        }
        return dp[day][last] = maxi;
    }

    public static int tabu(int dp[][], int arr[][]){
        dp[0][0] = Math.max(arr[0][1], arr[0][2]); 
        dp[0][1] = Math.max(arr[0][0], arr[0][2]); 
        dp[0][2] = Math.max(arr[0][0], arr[0][1]); 
        dp[0][3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));
        for(int day=1;day<arr.length;day++){
            for(int last = 0;last<4;last++){
                int maxi = 0;
                for(int i=0;i<=2;i++){
                    if(i!=last){
                        int points = arr[day][i] + dp[day-1][i];
                        maxi = Math.max(maxi, points);
                    }
                }
                dp[day][last] = maxi;
            }
        }
        return dp[arr.length-1][3];
    }

    public static int fastest(int arr[][]){
        int prev[] = new int[4];
        prev[0]= Math.max(arr[0][1], arr[0][2]); 
        prev[1]= Math.max(arr[0][0], arr[0][2]); 
        prev[2]= Math.max(arr[0][0], arr[0][1]); 
        prev[3]= Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));
        for(int day=1;day<arr.length;day++){
            int temp[] = new int[4];
            for(int last = 0;last<4;last++){
                int maxi = 0;
                for(int i=0;i<=2;i++){
                    if(i!=last){
                        int points = arr[day][i] + prev[i];
                        maxi = Math.max(maxi, points);
                    }
                }
                temp[last] = maxi;
            }
            prev = temp;
        }
        return prev[3];
    }
}
