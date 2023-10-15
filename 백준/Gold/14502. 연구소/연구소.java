import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static String[][] board;
    static String[][] virusBoard;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int result;

    public static void main(String[] args) throws IOException {

        // 벽 3개 세워야함
        // 3개 세운 후 안전 영역 최댓값 리턴
        // dfs로 벽세우기(depth: 세운 벽 개수)
        // bfs로 벽세운 곳에서 안전영역 최댓값 체크
        // 풀탐색

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // (3<=N<=8)
        M = Integer.parseInt(st.nextToken()); // (3<=M<=8)

        board = new String[N][M];

        for(int i =0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                board[i][j] = st.nextToken();
            }
        }
        result = 0;
        dfs(0);

        System.out.println(result);

    }
    public static void dfs(int depth){ // 벽 세팅 용 dfs
        if(depth == 3){ // 개수 계산
            bfs();
            return;
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(board[i][j].equals("0")){
                    board[i][j] = "1";
                    dfs(depth+1);
                    board[i][j] = "0";
                }
            }
        }
    }
    public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        virusBoard = new String[N][M];
        // 벽이 세개 세워질 때마다 bfs를 실행해야하므로 벽이 3개 세워진 board 복사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                virusBoard[i][j] = board[i][j];
                if (virusBoard[i][j].equals("2")) { 
                    queue.add(new int[]{i, j}); // 감염지역 queue add
                }
            }
        }
        while (!queue.isEmpty()) { //감염지역과 붙어있는 모든 안전 영역 감염처리
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];

            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];

                if (tx >= 0 && tx < N && ty >= 0 && ty < M && virusBoard[tx][ty].equals("0")) { // board 안이고, 0일 경우 감염처리
                    virusBoard[tx][ty] = "2";
                    queue.add(new int[]{tx, ty}); // 감염지역 queue add
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (virusBoard[i][j].equals("0")) { // 안전영역 체크
                    cnt++;
                }
            }
        }
        result = Math.max(result, cnt); // 안전역역 최대값 갱신
    }
}

