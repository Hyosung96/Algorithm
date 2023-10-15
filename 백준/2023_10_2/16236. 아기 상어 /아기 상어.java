import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, startRow, startCol, size, eatCnt, result;
    static int[][] board;
    static PriorityQueue<Node> pq;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {

        // 아기상어 초기 크기: 2
        // 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
        // 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.
        // 정렬 기준
        // 1. 이동 시간이 가장 짧은 물고기
        // 2. 거리가 같은 경우 위에 있는 먹이
        // 3. 같은 행에 있으면 가장 왼쪽에 있는 먹이
        // 물고기를 먹으면, 그 칸은 0으로
        // 최소 거리 탐색
        // BFS
        // queue, PriorityQueue 사용, 상어 위치 저장용, 먹이 위치 저장용(우선순위)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine()); // 행
        board = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 9){ // 상어위치: 시작점
                    startRow = i;
                    startCol = j;
                    board[i][j] = 0;
                }
            }
        }
        size = 2; // 상어 초기 사이즈 = 2
        eatCnt = 0; // 물고기 먹은 개수
        result = 0; // 결과값
        bfs();
    }

    public static void bfs(){

        // 먹이들의 위치를 저장하기 위한 우선순위큐
        // 정렬 기준
        // 1. 이동 시간이 가장 짧은 물고기
        // 2. 거리가 같은 경우 위에 있는 먹이
        // 3. 같은 행에 있으면 가장 왼쪽에 있는 먹이
        pq = new PriorityQueue<>((new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.time == o2.time) {
                    if (o1.row == o2.row) {
                        return o1.col - o2.col;
                    } else {
                        return o1.row - o2.row;
                    }
                } else {
                    return o1.time - o2.time;
                }
            }
        }));
        // BFS에 사용할 큐 (상어 정보 저장)
        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node(startRow, startCol, 0)); // 현재 상어의 위치

        while (true){
            visited = new boolean[N][N]; // 방문 체크 배열

            while (!queue.isEmpty()){ // 먹을 수 있는 물고기 탐색 BFS
                Node node = queue.poll(); // 현재 위치에 있는 상어 꺼내기
                int nowRow = node.row;
                int nowCol = node.col;
                int time = node.time;

                for(int i=0; i<4; i++){ //현재 위치에서 한칸 이동
                    int tx = nowRow + dx[i];
                    int ty = nowCol + dy[i];

                    // 범위 밖이거나 방문했을 경우, 상어보다 큰 물고기가 있을 경우 pass 다음 좌표 계산
                    if (tx < 0 || tx >= N || ty < 0 || ty >= N || visited[tx][ty] || board[tx][ty] > size) {
                        continue;
                    }

                    // 상어 > 물고기일 경우, 우선순위 큐에 먹이의 위치 저장
                    if(board[tx][ty] < size && board[tx][ty] != 0) {
                        pq.offer(new Node(tx, ty, time+1));
                    }

                    // 해당 좌표로 이동하기 위해 큐에 상어의 이동 시간 1증가 시켜서 저장
                    queue.offer(new Node(tx, ty, time+1));
                    // 해당 좌표 방문처리
                    visited[tx][ty] = true;
                }
            }
            // BFS가 종료되고 먹을 수 있는 먹이가 없다면
            if (pq.isEmpty()) {
                // 현재까지 저장된 결과 값 반환 후 종료
                System.out.println(result);
                return;
            }

            // 먹이 꺼내기
            Node node = pq.poll();
            eatCnt++; // 카운트 증가
            // 상어의 몸집이 먹이먹은 횟수와 동일하면
            if (size == eatCnt) {
                // 상어 몸집 1 증가
                size++;
                // 먹이먹은 횟수 0으로 초기화
                eatCnt = 0;
            }
            // 먹은 곳 좌표값 0으로 처리
            board[node.row][node.col] = 0;
            // 먹이를 먹기위해 해당 좌표까지 이동한 시간 결과값에 갱신
            result += node.time;
            // 큐에 먹이를 먹은 상어 저장 (이동거리: 0 초기화)
            queue.offer(new Node(node.row, node.col, 0));
            // 먹이를 저장한 우선순위 큐 비우기
            pq.clear();
        }
    }
}
class Node{ // 물고기 정보 저장(상어, 물고기)
    int row, col, time;

    public Node(int row, int col, int time){
        this.row = row;
        this.col = col;
        this.time = time;
    }

}
