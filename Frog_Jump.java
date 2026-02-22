// import java.util.*;

import java.util.Arrays;

public class Frog_Jump {
    public static void main(String args[]){
        int heights[] = {30, 20, 50, 10, 40};
        int n = heights.length;
        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        System.out.println("Memo: "+memo(n-1, dp, heights));
        Arrays.fill(dp, -1);
        System.out.println("Tabu: "+tabu(dp, heights));
    }

    public static int memo(int idx, int dp[], int heights[]){
        if(idx == 0){
            return 0;
        }
        if(dp[idx]!=-1){
            return dp[idx];
        }
        int left = memo(idx-1, dp, heights)+Math.abs(heights[idx]-heights[idx-1]);
        int right = Integer.MAX_VALUE;
        if(idx>1){
            right = memo(idx-2, dp, heights)+Math.abs(heights[idx]-heights[idx-2]);
        }
        return dp[idx] = Math.min(left, right);
    }

    public static int tabu(int dp[], int heights[]){
        dp[0] = 0;
        for(int i=1;i<heights.length;i++){
            int left = dp[i-1]+Math.abs(heights[i]-heights[i-1]);
            int right = Integer.MAX_VALUE;
            if(i>1){
                right = dp[i-2]+Math.abs(heights[i]-heights[i-2]);
            }
            dp[i] = Math.min(left, right);
        }
        return dp[heights.length-1];
    }
}
