public class House_Robber_II {

    public static void main(String args[]){
        int nums[] = new int[]{1,2,3,1};
        int n = nums.length;
        if(n==1){
            System.out.println(nums[0]);
            return;
        }
        int arr1[] = new int[n-1];
        int arr2[] = new int[n-1];
        for(int i=0;i<n-1;i++){
            arr1[i] = nums[i];
        }
        for(int i=1;i<n;i++){
            arr2[i-1] = nums[i];
        }
        System.out.println(Math.max(helper(arr1), helper(arr2)));
    }

    public static int helper(int arr[]){
        int prev1 = arr[0];
        int prev2 = 0;
        for(int i=1;i<arr.length;i++){
            int take = arr[i];
            if(i>1){
                take+=prev2;
            }
            int nottake = 0+prev1;
            int curr = Math.max(take, nottake);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    } 
}
