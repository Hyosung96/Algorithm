import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        // 저울
        // 누적합
        // 다음 값 ≤ 누적합 + 1을 만족할 시 무게의 전체합 이하의 무게 측정 가능
        // a1=1이고, a(n+1) > S(n)+1일 때 측정할 수 없는 최소 양의 정수 = S(n)+1

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N= Integer.parseInt(br.readLine()); // 추 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] weight = new int[N];

        for(int i =0; i<N; i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(weight);

        int sum = 0;
        for (int i=0; i<N; i++) {
            if (sum + 1 < weight[i]) {
                break;
            }
            sum += weight[i];
        }

        System.out.println(sum + 1);

    }

}
