import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;

    static boolean[][] board;

    static int cnt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        // depth 는 N까지

        board = new boolean[N][N]; // 체스판

        cnt = 0;
        dfs(0); // depth : 행


        System.out.println(cnt);

    }
    public static void dfs(int depth){ //depth = 행
        if(depth == N){
            cnt++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (queenCheck(depth, i)) {
                board[depth][i] = true;
                dfs(depth + 1);
                board[depth][i] = false;
            }
        }
    }

    public static boolean queenCheck(int x, int y){ //(1,1), (1,2)
        for (int i = x -1; i >= 0; i--) { // 현재 행 이전까지만 조회
            // 같은 열에 존재하는 경우
            if(board[i][y]) return false;
        }
        // 왼쪽 대각선 체크
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j]) return false;

        }
        // 오른쪽 대각선 체크
        for (int i = x - 1, j = y + 1; i >= 0 && j < N; i--, j++) {
            if (board[i][j]) return false;
        }
        return true;
    }
}
