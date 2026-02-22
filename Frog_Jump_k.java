import java.util.Arrays;

public class Frog_Jump_k {
    public static void main(String args[]){
        int heights[] = {30, 20, 50, 10, 40};
        int n = heights.length;
        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        System.out.println("Memo: "+memo(n-1, dp, heights, 5));
        Arrays.fill(dp, -1);
        System.out.println("Tabu: "+tabu(dp, heights, 5));
    }

    public static int memo(int idx, int dp[], int heights[], int k){
        if(idx == 0){
            return 0;
        }
        if(dp[idx]!=-1){
            return dp[idx];
        }
        int min_steps = Integer.MAX_VALUE;
        for(int j=1;j<=k;j++){
            if(idx-j>=0){
                int jump = memo(idx-j, dp, heights, k)+Math.abs(heights[idx]-heights[idx-j]);
                min_steps = Math.min(min_steps,jump);
            }
        }
        return dp[idx] = min_steps;
    }

    public static int tabu(int dp[], int heights[], int k){
        dp[0] = 0;
        for(int i=1;i<heights.length;i++){
            int min_steps = Integer.MAX_VALUE;
            for(int j=1;j<=k;j++){
                if(i-j>=0){
                    int jump = dp[i-j]+Math.abs(heights[i]-heights[i-j]);
                    min_steps = Math.min(min_steps,jump);
                }
            }
            dp[i] = min_steps;

        }
        return dp[heights.length-1];
    }
}
