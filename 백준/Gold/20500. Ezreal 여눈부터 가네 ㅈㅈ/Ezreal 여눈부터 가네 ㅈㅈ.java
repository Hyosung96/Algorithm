import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class Main {

    static int N;
    static int cnt;
    static int MOD;

    public static void main(String[] args) throws Exception {
        // 1,5로 구성된 N자리 숫자 중에서 15의 배수의 수
        // 1,5로 구성된 숫자의 조합
        // CNT: 0 1 1 3 5 11 21 43 85 171
        // IDX: 1 2 3 4 5 6 7 8 9 10
        // 규칙:
        // IDX 홀수일때는? 해당 IDX 까지의 누적합
        // IDX 짝수일때는? 해당 IDX 까지의 누적합 +1
        // 결과값 1000000007 로 나누기

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        MOD = 1000000007;

        cnt =0;

        BigInteger[] dp = new BigInteger[N+2];

        dp[1] = BigInteger.valueOf(0);
        dp[2] = BigInteger.valueOf(1);

        for(int i = 3; i <= N; i++) {
            dp[i] = BigInteger.ZERO;
            for(int j=2; j < i; j++){
                dp[i] = dp[i].add(dp[j]);
            }
            if(i%2 == 0){ // 짝수일 경우
                dp[i] = dp[i].add(BigInteger.valueOf(1));
            }
        }

        System.out.println(dp[N].remainder(BigInteger.valueOf(MOD)));

    }

}