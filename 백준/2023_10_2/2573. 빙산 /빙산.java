import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] board, nextBoard;
    static boolean[][] visited;


    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};

    public static void main(String[] args) throws IOException {

        // 빙산
        // N = 행의 개수
        // M = 열의 개수
        // dfs 덩어리 개수 체크(0이 아닌 영역 방문 체크)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        board = new int[N][M];
        nextBoard = new int [N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;
                nextBoard[i][j] = num;
            }
        }
        int year = 0;
        Break: while (true){
            visited = new boolean[N][M]; // 방문 배열
            int zeroCnt = 0; // 0 개수 체크
            int checkCnt = 0; // 덩어리 개수
            for(int i=1; i<N-1; i++){ // 빙산 분리되어 있는지 체크, board 동기화(=nextBoard)
                for(int j=1; j<M-1; j++){
                    int num = nextBoard[i][j];
                    board[i][j] = num;
                    if(nextBoard[i][j] == 0) zeroCnt++; // 0의 개수 체크
                    if(nextBoard[i][j] != 0 && !visited[i][j]) { // 0이 아니면서 방문하지 않은 곳일 경우
                        check(i,j);
                        checkCnt++;
                        if(checkCnt > 1) break Break; // 덩어리 1개 이상일 경우 break
                    }
                }
            }
            for(int i=1; i<N-1; i++){ // 주변 바다 체크 후 감소처리
                for(int j=1; j<M-1; j++){
                    int reduceCnt = 0;
                    if(nextBoard[i][j] != 0) {
                        if(board[i][j-1] == 0) reduceCnt++; // 좌
                        if(board[i][j+1] == 0) reduceCnt++; // 우
                        if(board[i-1][j] == 0) reduceCnt++; // 상
                        if(board[i+1][j] == 0) reduceCnt++; // 하
                        nextBoard[i][j] -= reduceCnt;
                        if(nextBoard[i][j] < 0) nextBoard[i][j] = 0; // 0보다 작아질 경우 0처리
                   }
                }
            }
            year++; // 연수 증가
            if(zeroCnt == (N-2)*(M-2)) { //0개수 체크
                System.out.println(0);
                return;
            }
        }
        System.out.println(year);
    }
    public static void check(int x, int y){
        visited[x][y] = true;
        for(int i=0; i<4; i++){
            int tx = x;
            int ty = y;
            while (true){
                tx += dx[i];
                ty += dy[i];
                if (tx < 0 || ty < 0 || tx >= N || ty >= M) { // 해당 위치가 board 밖인지 체크
                    break;
                }
                else {
                    if (nextBoard[tx][ty] == 0){ // 해당 위치의 요소가 0일 경우 break
                        break;
                    }
                    if (nextBoard[tx][ty] != 0 && !visited[tx][ty]) { // 0이 아니고 방문하지 않은 곳일 경우 방문처리, 탐색
                        check(tx, ty);
                    }
                }
            }
        }
    }
}

