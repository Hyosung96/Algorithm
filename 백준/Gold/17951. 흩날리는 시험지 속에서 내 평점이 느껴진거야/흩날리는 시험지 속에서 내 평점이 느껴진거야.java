import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 시험지
        // 순차적으로 합산하여 그룹 나누기
        // 그룹의 합의 최솟값의 최대 출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 시험지 개수
        int K = Integer.parseInt(st.nextToken());  // 그룹 수
        int[] score = new int[N]; // 점수 저장

        int sum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            score[i] = Integer.parseInt(st.nextToken());
            sum += score[i];
        }

        int min = 1;
        int max = sum; // 점수 최대치
        int ans = 0; // 점수 최대값 저장

        while (min <= max) {
            int mid = (min + max) / 2;
            int count = 0;  // 그룹 개수 저장
            int tmp = 0; // 시험지 그룹 점수 저장
            for (int i = 0; i < N; i++) {
                tmp += score[i];  // 시험지 그룹 점수 저장
                if (tmp >= mid) {  // 시험지 그룹 점수 > 기준 값일 경우
                    count++;
                    tmp = 0; // 그룹 생성하고, 그룹 점수 초기화
                }
            }

            if (count >= K) {
                ans = mid; // 최대값 저장
                min = mid + 1;  // 점수 기준값 증가
            } else {
                max = mid - 1; // 점수 기준값 감소
            }
        }
        System.out.println(ans);
    }
}