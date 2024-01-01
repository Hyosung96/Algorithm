import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static int N,M;
    static int[][] board;

    static int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dy = {0, 1, 0, -1};

    static int clearCnt = 1; // 첫위치는 무조건 청소

    public static void main(String[] args) throws Exception {
        // 로봇청소기
        // 둘째 줄에는? 로봇청소기 첫 좌표, 바라보는 방향(0:위, 1:오른쪽, 2:아래, 3:왼쪽) // 북 동 남 서,
        // 청소되지 않은칸 : 0, 벽: 1, 청소한 칸 : 2로 세팅

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int op = Integer.parseInt(st.nextToken()); // 방향값


        for(int i =0; i<N; i++){ // 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clean(x, y, op);

        System.out.println(clearCnt);

    }

    public static void clean(int x, int y, int op){
        board[x][y] = 2; // 청소 처리

        for(int i=0; i<4; i++){
            op = (op+3)%4; // 반시계 방향으로 90도 회전
            int nx = x + dx[op];
            int ny = y + dy[op];
            if (0 <= nx && nx < N && 0 <= ny && ny < M && board[nx][ny] == 0){ //바라보는 곳이 빈곳일 경우? 전진
                clearCnt++;
                clean(nx, ny, op);
                return;
            }
        }
        // 4방향에 빈 곳 없을 경우? 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진
        int back = (op + 2) % 4; //반대 방향으로 세팅 (방향 변경X, 후진)
        int bx = x + dx[back];
        int by = y + dy[back];
        if(bx >= 0 && by >= 0 && bx < N && by < M && board[bx][by] != 1) { // 벽이 아닐경우 이동 가능
            clean(bx, by, op); //후진이니까 바라보는 방향은 유지
        }

    }

}