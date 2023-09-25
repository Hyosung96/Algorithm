import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;

    static List<int[]> chickenList;

    static List<int[]> homeList;

    static boolean[] open;

    static int answer;


    public static void main(String[] args) throws IOException {

        //(r1, c1)과 (r2, c2) 사이의 거리는 |r1-r2| + |c1-c2|
        //치킨 거리는 집과 가장 가까운 치킨집 사이의 거리
        //도시의 치킨 거리는 모든 집의 치킨 거리의 합
        //0은 빈 칸, 1은 집, 2는 치킨집
        //(2 ≤ N ≤ 50), (1 ≤ M ≤ 13)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행/열 개수

        M = Integer.parseInt(st.nextToken()); // 치킨집 최대 개수

        chickenList = new ArrayList<>(); // 치킨집 인덱스 저장

        homeList = new ArrayList<>(); // 집 인덱스 저장

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int num = Integer.parseInt(st.nextToken()); // 굳이 board 세팅 X, 집, 치킨 리스트로 저장
                if(num == 1){
                    homeList.add(new int[]{i,j}); // 집 인덱스 저장
                }
                if(num == 2){
                    chickenList.add(new int[]{i,j}); // 치킨집 인덱스 저장
                }
            }
        }

        open = new boolean[chickenList.size()];

        answer = Integer.MAX_VALUE; // 최초값: 거리 최대값 세팅

        dfs(0,0); // 모든 치킨 집 탐색(M개 선정), 중복 체크 제거를 위해 idx 추가

        System.out.println(answer);

    }
    public static void dfs(int idx, int depth){

        if (depth == M) {
            int allDist = 0; // 치킨거리

            for (int i = 0; i < homeList.size(); i++) {
                int dist = 100; // 최초값: 거리 최댓값 세팅

                // 어떤 집과 치킨집 중 open한 치킨집의 모든 거리를 비교한다.
                // 그 중, 최소 거리를 구한다.
                for (int j = 0; j < chickenList.size(); j++) { // 거리 계산: |r1-r2| + |c1-c2|
                    if (open[j]) {
                        dist = Math.min(dist,
                                Math.abs(homeList.get(i)[0] - chickenList.get(j)[0])
                                        + Math.abs(homeList.get(i)[1] - chickenList.get(j)[1]));
                    }
                }
                allDist += dist; // 최소거리를 치킨거리에 포함시킴
            }
            answer = Math.min(answer, allDist); // 가장 적은 치킨거리로 세팅
            return;
        }

        // 백트래킹
        for (int i = idx; i < chickenList.size(); i++) {
            open[i] = true;
            dfs(i+1, depth + 1); // idx= 지나쳤던 곳 패스, depth = 치킨 집 수
            open[i] = false;
        }

    }
}
