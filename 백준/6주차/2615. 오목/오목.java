import java.io.*;
import java.util.*;

public class Main {

    static int [][] cal = {{0,1}, {1,0}, {1,1}, {-1,1}};
    static int [][] num;
    static boolean complete = false;
    static int N = 19;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 줄 마다 1 혹은 2 찾는 반복문 (있을 경우 탐색 시작)
        // 탐색 함수 만들고
        // 가로줄 체크, 세로줄 체크 , 대각선 증감
        // 하고 5개가 연속될 경우 승리판정
        // 총 19X19
        // 세로축이 y축, 가로축이 x축으로 설정
        // 첫 시작좌표: (0,0)
        num = new int[N][N];
        StringTokenizer st;
        for(int i =0; i<N; i++){ // 오목판 저장
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int j =0; j<N; j++){ // 오목판 탐색 // 가장 왼쪽 위 값 출력해야하기 때문에 j부터 증가 // 탐색 방향: 위에서 아래
            for(int i=0; i<N; i++){
                if(num[i][j] == 1 || num[i][j] == 2){ // 바둑돌 있을 경우 탐색 시작
                    check(i, j);
                }
                if(complete){ // 승패 갈린 경우 종료
                    return;
                }
            }
        }
        System.out.println(0); // 승패 안갈릴 경우
    }
    public static void check(int y, int x){
        for(int i =0; i<4; i++){ //{0,1}: y증감, {1,0}: x증감, {1,1}: /대각선 증감, {-1,1}: \대각선 증감
            int cnt = 1; // 일치하는 바둑알 수
            int tx = x;
            int ty = y;

            while (true){ // 정방향 탐색
                tx += cal[i][0];
                ty += cal[i][1];
                if ( 0 <= tx && tx < N && 0 <= ty && ty < N) { // 해당 위치가 바둑판 안인지 체크
                    if(num[y][x] == num[ty][tx])cnt ++;
                    else {
                        break;
                    }
                } else break;
            }
            tx = x;
            ty = y;
            while( true) { // 반대방향 탐색
                tx -= cal[i][0];
                ty -= cal[i][1];
                if ( 0 <= tx && tx < N && 0 <= ty && ty < N) {
                    if(num[y][x] == num[ty][tx])cnt ++;
                    else break;

                } else break;
            }
            // 연속되는 바둑돌이 5개라면
            if (cnt == 5) {
                System.out.println(num[y][x]); // 이긴 돌 출력(흑, 백)
                System.out.println((y+1) + " " + (x+1)); // 인덱스 출력
                complete = true;
                return;
            }
        }
    }
}
