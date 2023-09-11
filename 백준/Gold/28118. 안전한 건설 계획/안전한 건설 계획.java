import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int cost;

    public static void main(String[] args) throws IOException {

        // N개의 기둥, M개의 빔으로 구성
        // 모든 기둥 연결까지의 최소비용

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 기둥 개수
        int M = Integer.parseInt(st.nextToken()); // 빔 개수

        int lineCount = M;


        int[][] num = new int[N+1][N+1]; // 간선 정보 저장

        for(int i =0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            num[from][to] = 1;
            num[to][from] = 1; // 간선 정보 저장
        }


        cost = 0; // 비용 계산

        while (lineCount != (N*(N-1)/2)){ // 비용 없이 요소 추가

            int previousLineCount = lineCount;

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j || num[i][j] == 1) {
                        continue;
                    }

                    // 무료로 연결할 수 있는가를 검증해보자
                    // (1, 3) 이 무료로 연결되려면, (n, 1) 과 (n, 3)이 연결되어 있어야 한다(단, n은 != 1, != 3)
                    for (int k = 1; k <= N; k++) {
                        if (num[k][i] == 1 && num[k][j] == 1) {
                            num[i][j] = 1;
                            num[j][i] = 1;
                            lineCount++;
                            break;
                        }
                    }
                }
            }
            if(previousLineCount == lineCount){ // 비용 없이 간선 추가된 것이 없을 경우
                Break: for(int i= 1; i<=N; i++){
                    for(int j=1; j<=N; j++){
                        if(i==j) continue;
                        if(num[i][j] ==0){
                            num[i][j] =1;
                            num[j][i] =1;
                            lineCount++;
                            cost++;

                            break Break;
                        }

                    }
                }

            }

        }

        System.out.println(cost);
    }
}
