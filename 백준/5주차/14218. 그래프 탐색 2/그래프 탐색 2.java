import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int MAX = 1001;
        int[][] connect = new int[n + 1][n + 1]; //노드 간의 연결 정보를 저장하기 위한 배열
        int[] distance = new int[n + 1]; //시작 노드로부터의 최단 거리를 저장하기 위한 배열
        for (int i = 1; i <= n; i++) {
            distance[i] = MAX;
        }
        // 시작 노드로부터의 거리를 0으로 설정
        distance[1] = 0;

        // 간선 정보 입력받아 connect 배열에 저장
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            connect[a][b] = 1;
            connect[b][a] = 1;
        }

        // 테스트 케이스의 개수 입력
        int t = Integer.parseInt(br.readLine());

        // 각 테스트 케이스에 대한 처리
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            connect[a][b] = 1;
            connect[b][a] = 1;

            // BFS를 사용하여 최단 거리 계산
            bfs(connect, distance, n);

            // 결과 출력
            for (int j = 1; j <= n; j++) {
                if (distance[j] == MAX) {
                    System.out.print(-1 + " ");
                } else {
                    System.out.print(distance[j] + " ");
                }
            }
            // 한 테스트 케이스의 결과 출력이 끝나면 줄 바꿈
            System.out.println();
        }
    }
    // BFS를 통한 최단 거리 계산
    public static void bfs(int[][] connect, int[] distance, int n) {
        boolean[] visited = new boolean[n + 1];
        Queue<int[]> queue = new ArrayDeque<>();
        //시작 노드 설정: 1번 노드
        queue.add(new int[]{1, 0});

        while (!queue.isEmpty()) {
            int[] nodePath = queue.poll();
            int node = nodePath[0];
            int path = nodePath[1];

            // 더 작은 거리가 발견되면 갱신
            if (distance[node] > path) {
                distance[node] = path;
            }

            visited[node] = true;

            for (int i = 1; i <= n; i++) {
                // 연결된 노드 중 방문하지 않은 노드 탐색
                if (connect[node][i] == 1 && !visited[i]) {
                    queue.add(new int[]{i, path + 1}); //노트, 거리 값 세팅
                    visited[i] = true;
                }
            }
        }
    }
}
