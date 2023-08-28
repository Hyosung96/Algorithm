import java.io.*;
import java.util.*;

public class Main {

    static int[] dp; // 위치가 n일 때의 최소 이동거리
    static HashMap<Integer, List<int[]>> map; // key : 도착지점, value : (출발지점,이동거리)

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            if (!map.containsKey(to)) { // 도착지점이 map에 없었을 경우
                map.put(to, new ArrayList<>()); // value 초기화
            }
            map.get(to).add(new int[]{from,distance});
        }

        dp = new int[D+1]; // dp 초기화

        for(int i = 1; i<=D; i++){  // 위치 1부터 시작하여 D위치까지 최소 이동거리를 채움
            int d = dp[i-1]+1; // 기본값 : (현재 위치-1)에서의 최소 이동거리 + 1

            if (map.containsKey(i)) {
                for(int[] distance : map.get(i)){ // distance 배열 [0]: from값, [1]: 거리값
                    // 도착위치가 i인 지름길들을 조회해서 최소 이동거리 갱신
                    d = Math.min(d, dp[distance[0]]+distance[1]); // 시작 지점의 dp 값과 거리값 더한 값 <-> 현재 위치에서의 최소 이동거리 비교
                }
            }

            dp[i] = d;
        }

        System.out.println(dp[D]);
    }
}