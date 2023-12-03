import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 마지막으로 설치한 곳 + 최대거리로 공유기 c개를 설치
        // (설치할 곳 - 마지막으로 설치한 곳) <= 설치거리일 경우 설치가능 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 집의 개수
        int C = Integer.parseInt(st.nextToken());  // 공유기의 개수
        int[] home = new int[N];

        for (int i = 0; i < N; ++i) {
            home[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(home);   // 정렬

        int min = 1;
        int max = home[N-1] - home[0]; // 가장 큰 거리 값
        int d = 0; // 설치 거리
        int ans = 0; // 설치 거리 최대값 저장
        // 1 2 4 8 9

        while (min <= max) {
            int mid = (min + max) / 2;
            int start = home[0];
            int count = 1;  // 공유기 설치 개수 저장
            for (int i = 0; i < N; i++) {
                d = home[i] - start;  // 집마다 거리 체크
                if (d >= mid) {  // 공유기 설치 가능한지 여부 체크
                    count++;
                    start = home[i]; // 설치하고, 여기 집 부터 다시 거리 체크
                }
            }

            if (count >= C) {
                ans = mid; // 최대값 저장
                min = mid + 1;  // 설치거리 세팅 증가
            } else {
                max = mid - 1; // 설치거리 세팅 감소
            }
        }
        System.out.println(ans);
    }
}