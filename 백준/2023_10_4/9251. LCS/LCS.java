import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String A=br.readLine();
        String B=br.readLine();

        int[][] dp = new int[A.length()+1][B.length()+1];

        // a를 기준으로 b와 비교
        for(int i=1;i<=A.length();i++) { // a를 기준으로
            for(int j=1;j<=B.length();j++) { // b와 비교
                if(A.charAt(i-1) != B.charAt(j-1)) { // 다를 경우
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]); //점화식
                }else { // 같을 경우
                    dp[i][j] = dp[i-1][j-1]+1; // 점화식
                }
            }
        }
        System.out.println(dp[A.length()][B.length()]); // 마지막 요소 출력
    }
}
