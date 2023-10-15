import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {

        // A 그룹 B 그룹
        // A의 요소가 B보다 큰 경우의 수
        // T: 테스트 케이스, N: A 그룹 개수, M: B그룹 개수
        // (1 ≤ N, M ≤ 20,000)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for(int i =0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            // 8 7 3 1 1
            // 6 3 1
            // B그룹을 정렬하고, A그룹에서 비교할 때 만약 B그룹의 값이 더 큰 경우? 탐색 종료

            int N = Integer.parseInt(st.nextToken()); // A그룹 개수
            int M = Integer.parseInt(st.nextToken()); // B그룹 개수

            st = new StringTokenizer(br.readLine());

            int[] A = new int[N];
            for(int j =0; j<N; j++){
                A[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            int[] B = new int[M];
            for(int j =0; j<M; j++){
                B[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(B); // B그룹 정렬

            int cnt = 0;

            for(int j=0; j<N; j++){
                for (int k = 0; k < M; k++) {
                    if(A[j] > B[k]) cnt++;
                    if(A[j] <= B[k]) break; // B그룹의 요소가 더 클경우 루프 종료
                }
            }
            System.out.println(cnt);
        }
    }
}
