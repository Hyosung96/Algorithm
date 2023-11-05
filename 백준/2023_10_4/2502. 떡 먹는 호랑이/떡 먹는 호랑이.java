import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // 피보나치 수열
        // 완탐 + DP
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken()); // 날짜
        int N = Integer.parseInt(st.nextToken()); // 떡 개수
        int[] dp = new int[D];

        Break: for(int i =1; i<N; i++){
            for(int j=i+1; j<N; j++){
                dp[0] = i; // 첫째날 세팅
                dp[1] = j; // 둘째날 세팅
                for(int k=2; k<D; k++){
                    dp[k] = dp[k-1] + dp[k-2];
                }
                if(dp[D-1] == N){
                    System.out.println(i); // 첫날 떡 개수
                    System.out.println(j); // 둘째날 떡 개수
                    break Break;
                }
            }
        }
    }
}
