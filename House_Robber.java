import java.util.Arrays;

public class House_Robber {
    public static void main(String args[]){
        int nums[] = new int[]{2,7,9,3,1};
        int dp[] = new int[nums.length];
        Arrays.fill(dp, -1);
        System.out.println("Memo: " + memo(nums.length-1, nums, dp));
        Arrays.fill(dp, -1);
        System.out.println("Tabu: " + tabu(nums, dp));
        System.out.println("Fastest: " + fastest(nums));
    }

    public static int memo(int idx, int arr[], int dp[]){
        if(idx == 0){
            return arr[idx];
        }
        if(idx == -1){
            return 0;
        }
        if(dp[idx]!=-1){
            return dp[idx];
        }
        int pick = arr[idx]+memo(idx-2, arr, dp);
        int notpick = 0 + memo(idx-1, arr, dp);
        return dp[idx] = Math.max(pick, notpick);
    }

    public static int tabu(int arr[], int dp[]){
        dp[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            int take = arr[i];
            if(i>1){
                take+=dp[i-2];
            }
            int nottake = 0 + dp[i-1];
            dp[i] = Math.max(take, nottake);
        }
        return dp[arr.length-1];
    }

    public static int fastest(int arr[]){
        int prev1 = arr[0];
        int prev2 = 0;
        for(int i=1;i<arr.length;i++){
            int take = arr[i];
            if(i>1){
                take+=prev2;
            }
            int nottake = 0 + prev1;
            int curr = Math.max(take, nottake);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
