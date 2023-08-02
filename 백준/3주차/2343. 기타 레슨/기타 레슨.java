import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int left, right;
    static int[] num;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // left, mid, right를 어떻게 잡을 것인가
        // right: 전체 더한 값
        // left: 요소 중 최대값
        // 최솟값(요소 중 최대값), 극단적인 끝값(요소를 전부 더한 값)
        // 블루레이의 크기 세팅 : 최솟값 + 최댓값/2

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[N];

        st = new StringTokenizer(br.readLine());
        left = 0;
        right = 0;

        for(int i = 0; i<N; i++){
            num[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left, num[i]);
            right += num[i];
        }
        solve();

        System.out.println(left);
    }

    public static void solve() {
        while (left<=right){
            int cnt =0;
            int mid = (left+right)/2;
            int tempSum = 0;
            for(int i=0; i<N; i++){
                if (tempSum + num[i] > mid) {
                    tempSum = 0;
                    cnt += 1; // 블루레이 개수 1 증가
                }
                tempSum += num[i];
            }
            if (tempSum > 0) cnt += 1; // 블루레이 크기보다 작아서 증가시키지 못했을 경우

            // 블루레이 개수 체크
            if (cnt <= M) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }

        }

    }

}
