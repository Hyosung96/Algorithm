import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[][] isVisit;

    static int[][] area;

    static int[] dx;
    static int[] dy;

    static int N;


    public static void main(String[] args) throws IOException {

        // 침수 높이를 탐색하며 가장 많은 영역 체크
        // 인접한 위치는 같은 영역으로 취급
        // 침수되지 않은 영역의 수

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 행/열 개수

        area = new int[N][N];

        // {1,0}, {-1,0}, {0,1}, {0, -1)

        dx = new int[]{1, -1, 0, 0}; // 상하좌우 이동 값
        dy = new int[]{0, 0, 1, -1};

        StringTokenizer st;

        for(int i =0; i<N; i++){ // 위치 별 침수 높이 입력
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<N; j++){
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 높이 1이상 100 이하
        // 방문 체크 하면서 하면 될듯?

        int maxCnt = 0;
        for(int i= 0; i<100; i++){ // 침수 높이 1부터 100까지
            isVisit = new boolean[N][N]; // 침수 높이마다 방문 배열 초기화
            int cnt = 0;
            for(int j =0; j<N; j++){ // 침수 높이 i일 때의 안전 영역 탐색
                for(int k =0; k<N; k++){
                    if(!isVisit[j][k] && area[j][k] > i){ // 해당 위치가 침수 한계보다 높다면? -> 안전영역
                        cnt++;
                        dfs(i,j,k);
                    }
                }
                maxCnt = Math.max(maxCnt, cnt);
            }
        }
        System.out.println(maxCnt);

    }
    public static void dfs(int h, int x, int y){ //침수 높이, 인덱스 값
        isVisit[x][y] = true;
        for(int i=0; i<4; i++){
            int tx = x+dx[i];
            int ty = y+dy[i];

            if(tx<0 || ty<0 || tx>N-1 || ty >N-1) continue; // area 안에 있는지 체크
            if(isVisit[tx][ty]) continue;

            if(area[tx][ty] >h){ // 해당 위치가 안전 영역일 경우? 한칸 더 감
                dfs(h,tx,ty);
            }
        }

    }
}
