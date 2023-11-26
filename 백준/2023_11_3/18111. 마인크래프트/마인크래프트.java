import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, B;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        // 마인크래프트
        // 쌓기 연산 : 1초
        // 파기 연산 : 2초
        // 걸리는 시간과, 땅의 높이 출력
        // 쌓기 연산 가능한지 먼저 체크 후 파기 연산
        // 최소, 최대 높이 저장 후
        // 각 높이 마다 만들어 지는 시간 저장

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N= Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());
        B= Integer.parseInt(st.nextToken()); // 인벤토리 블럭 개수

        board = new int[N][M];

        int max = 0;
        int min = 256;

        for(int i =0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(max<board[i][j]) max = board[i][j]; // 최대높이 갱신
                if(min>board[i][j]) min = board[i][j]; // 최소높이 갱신
            }
        }
        int result = Integer.MAX_VALUE; // 작업 시간
        int height = 0; // 지정된 높이
        Main: for(int i = min; i<=max; i++){ // 세팅할 블럭개수 지정
            int blockCnt = B;
            int workCnt = 0;
            for(int j=0; j<N; j++){
                for(int k=0; k<M; k++){
                    if(board[j][k] < i){ // 지정 높이 보다 현재 위치가 더 낮을 경우
                        int diff = (i-board[j][k]); // 높이 차이
                        workCnt += diff; // 쌓기연산
                        blockCnt -= diff;
                    }
                    else if(board[j][k] > i){ // 지정 높이 보다 현재 위치가 더 높을 경우
                        int diff = (board[j][k] - i); // 높이 차이
                        workCnt += (diff*2); //땅파기 연산
                        blockCnt += diff;
                    }
                }
            }

            if(blockCnt < 0) continue Main; // 가지고 있는 블럭 부족할 경우

            if(result == workCnt){ // 작업 시간 같을 경우, 블럭 높은 것 저장
                if(height < i){
                    height = i; // 해당 높이 저장
                }
            }
            else if(result > workCnt){
                result = workCnt; // 작업 최소시간 갱신
                height = i; // 해당 높이 저장
            }
        }
        System.out.print(result + " " + height);
    }
}
