import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // 랜선 자르기
        // 만들 랜선 N개
        // K개의 랜선은 길이 제각각
        // N개의 랜선을 모두 같은 길이로 만들기
        // N개보다 많이 만들어도 N개로 만든 것으로 취급 됨
        // 이분 탐색으로 랜선 몇개 나오는지 체크
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        long max = 0;

        long [] arr= new long[K];
        for(int i=0; i<K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]); // 최댓값 세팅
        }

        long half = 0;
        long min = 1; // 0으로 할 경우 에러남

        while(min <= max) {
            half = (min + max)/2; // 세팅할 랜선 길이
            long count = 0;

            for(int i =0; i<arr.length; i++) {
                count += arr[i] / half; // 각 랜선마다 몇개씩 랜선 나오는지 체크
            }

            // 원하는 랜선 갯수 보다 잘라진 랜선 수가 적을경우
            if(count < N) {
                max = half-1;
            }
            // 원하는 랜선 갯수 보다 잘라진 랜선 수가 많을경우
            else {
                min = half+1;
            }
        }

        System.out.println(max);
    }
}