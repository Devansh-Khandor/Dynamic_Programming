import java.util.*;

public class Fibonacci {
    @SuppressWarnings("resource")
    public static void main(String args[]){
        Scanner ob = new Scanner(System.in);
        System.out.println("Enter the number");
        int n = ob.nextInt();
        int dp[] = new int[n+1];
        for(int i=0;i<n+1;i++){
            dp[i] = -1;
        }
        System.out.println(fb_memo(n, dp));
        System.out.println(fb_tabu(n));
        System.out.println(fb_quickest(n));
    }

    public static int fb_memo(int n, int dp[]){
        if(n<=1){
            return n;
        }
        if(dp[n]!=-1){
            return dp[n];
        }
        else{
            dp[n] = fb_memo(n-1, dp)+fb_memo(n-2, dp);
            return dp[n];
        }
    }

    public static int fb_tabu(int n){
        int dp[] = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public static int fb_quickest(int n){
        int prev2 = 0;
        int prev1 = 1;
        for(int i=2;i<=n;i++){
            int curr = prev1+prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;

    }
}
