import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static boolean isPossible;

    static boolean[] visited;

    static int[][] board;

    public static void main(String[] args) throws IOException {
        // 여행계획
        // N개의 도시
        // 여행 계획에 속한 도시 M개
        // 연결 그래프 입력받고
        // 마지막 줄에는 여행 계획

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N= Integer.parseInt(br.readLine()); // 도시 개수
        M= Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시 개수

        board = new int[N+1][N+1];
        visited = new boolean[N+1]; // 방문 배열

        StringTokenizer st;

        for(int i =1; i<=N; i++){ // 시작 인덱스 : 1, 연결 그래프 입력받기
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] travel = new int[M+1]; // 여행 계획 도시 삽입
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=M ; i++){
            travel[i] = Integer.parseInt(st.nextToken());
        }

        isPossible = true;

        dfs(travel[1]); // 연결 정보 체크

        for (int t : travel) {
            if (t > 0 && !visited[t]) { // 방문 불가능 한 경우? false
                isPossible = false;
                break;
            }
        }

        if(isPossible) System.out.println("YES");
        else System.out.println("NO");

    }

    public static void dfs(int fromCity){
        visited[fromCity] = true;

        for(int i=1; i<=N; i++){
            if(board[fromCity][i] == 1 && !visited[i]){
                dfs(i);
            }
        }
    }
}
