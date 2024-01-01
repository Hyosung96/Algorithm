import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] board;
    static int[][] visit;

    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        // 미로
        // 0: 검은방, 1: 흰방, 검은방 진입 불가
        // 윗줄 맨 왼쪽은 무조건 1(시작지점)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        visit = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(str[j]);
                visit[i][j] = Integer.MAX_VALUE; // 이동 수 저장 배열
            }
        }

        bfs(0,0);

        System.out.println(visit[N-1][N-1]);
    }

    public static void bfs(int y, int x) {

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(y, x));
        visit[0][0] = 0; // 시작 값은 0

        while (!queue.isEmpty()) {

            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                // 배열 밖이면 continue
                if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                
                // 옮기기 전의 값보다 옮긴 값에 이미 더 작은 값이 들어있으면 continue
                if(visit[ny][nx] <= visit[node.y][node.x]) continue;

                if (board[ny][nx] == 1) { // 이동할 수 있는 곳이면 visit은 이전값 넣어줌
                    queue.add(new Node(ny, nx));
                    visit[ny][nx] = visit[node.y][node.x];
                }
                else { // 이동할 수 없는 벽이면, 값을 +1
                    queue.add(new Node(ny, nx));
                    visit[ny][nx] = visit[node.y][node.x] + 1;
                }
            }

        }

    }

    static class Node {
        int y;
        int x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
