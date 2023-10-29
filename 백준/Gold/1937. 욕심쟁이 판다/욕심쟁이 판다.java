import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int[][] board;
    static int[][] dp;

    static int N;

    public static void main(String[] args) throws IOException {
        // 대나무 시작 위치마다 메모이제이션
        // dfs + dp 하면 될듯
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        board = new int[N][N]; // 대나무 양 저장
        dp = new int[N][N]; // 각 시작 위치에서의 최대 이동 값 저장

        StringTokenizer st;

        for(int i =0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        for(int i =0; i<N; i++){
            for(int j =0; j<N; j++){
                ans = Math.max(ans, dfs(i, j));
            }
        }

        System.out.println(ans);

    }
    public static int dfs(int x, int y){
        if(dp[x][y] != 0) return dp[x][y]; // 이전에 방문했다면 Pass

        dp[x][y] = 1; // 시작 값: 1

        for(int i=0; i<4; i++){
            int tx = x + dx[i];
            int ty = y + dy[i];
            if(tx<0 || tx>=N || ty<0 || ty>=N) continue;

            if(board[tx][ty] > board[x][y]){
                // dp 배열 최신화, 각 방향마다 최대값 탐색(dp[x][y]에 저장
                dp[x][y] = Math.max(dp[x][y],dfs(tx,ty)+1); // 한칸 더 전진할 때 마다 +1, dfs(dfs(dfs(1)+1)+1)...
            }
        }
        return dp[x][y];
    }

}
