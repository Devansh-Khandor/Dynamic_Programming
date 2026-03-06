package DPonStrings;

import java.util.*;

public class PrintLCS {
    public static void main(String args[]){
        String text1 = "abcde";
        String text2 = "ace";
        int n = text1.length();
        int m = text2.length();
        int dp2[][] = new int[n+1][m+1];
        for(int row[]:dp2){
            Arrays.fill(row, -1);
        }
        System.out.println("Tabu: "+tabu(text1, text2, dp2));
    }

    public static String tabu(String text1, String text2, int dp[][]){
        int n = text1.length();
        int m = text2.length();

        for(int i=0;i<n+1;i++){
            dp[i][0] = 0;
        }
        for(int j=0;j<m+1;j++){
            dp[0][j] = 0;
        }

        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1]; 
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        int x = dp[n][m] - 1;
        char c[] = new char[x+1];
        int i = n;
        int j = m;
        while(i>0 && j>0){
            char c1 = text1.charAt(i-1);
            char c2 = text2.charAt(j-1);
            if(c1==c2){
                c[x] = c1;
                x--;
                i--;
                j--;
            }
            else if(dp[i-1][j]>dp[i][j-1]){
                i = i-1;
            }
            else{
                j = j-1;
            }
        }
        return Arrays.toString(c);
    }
}
