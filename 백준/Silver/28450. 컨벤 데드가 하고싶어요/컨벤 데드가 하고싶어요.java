import java.io.*;
import java.util.*;

public class Main {

    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        // DP 바텀업
        // 행, 열의 마지막 라인일 경우 분기
        // 아닐 경우 밑, 오른쪽 인덱스 중 값이 작은 것 += 연산
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        dp = new long[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                dp[i][j] = Long.parseLong(st.nextToken());
            }
        }

        int H = Integer.parseInt(br.readLine());

        for (int i = N - 1; i >= 0; i--) {
            //바텀업 메모이제이션, 점화식은..
            //DP(i,j) = 도착지 부터 0,0 인덱스까지의 최적의 해
            //점화식: DP(i,j) += min(DP(i+1, j), DP(i, j+1))
            for (int j = M - 1; j >= 0; j--) {
                if (i == N - 1 && j == M - 1) {
                    // 시작점은 패스
                    continue;
                }
                if (i == N - 1) { // 아래 마지막 라인일 경우
                    dp[i][j] += dp[i][j + 1];
                } else if (j == M - 1) { // 오른쪽 마지막 라인일 경우
                    dp[i][j] += dp[i + 1][j];
                } else {
                    dp[i][j] += Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        if (dp[0][0] > H) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            System.out.println(dp[0][0]);
        }
    }
}