import java.io.*;
import java.util.*;

public class Main {

    static int[] D;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    static final int INF = 50000001;

    //N개의 노드, M개의 간선
    //from, to, 간선의 값(여물)
    // 시작 노드 설정:1, 거리 설정:0
    // 우선순위 큐: 거리, 노드 저장
    // 초기 값으로는 무한을 설정
    // 테이블 먼저 입력받기
    // 더 거리비용(distance)이 낮은 값이 높은 우선순위를 가질 수 있어야함 NODE 클래스 구현

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        D = new int[N+1]; // 노드까지의 비용을 저장하는 배열

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Node>());
        }
        // 그래프 저장
        for(int i =0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()); // 시작노드
            int to = Integer.parseInt(st.nextToken()); // 인접노드
            int value = Integer.parseInt(st.nextToken()); // 간선 값
            graph.get(from).add(new Node(to, value)); // 그래프 저장 (from -> to)
            graph.get(to).add(new Node(from, value)); // 양방향 그래프이므로 (to -> from) 저장
        }

        Arrays.fill(D, INF); // 최대값으로 세팅

        dijkstra(1);

        // 모든 노드로 가기 위한 최단 거리를 출력

        System.out.println(D[N]); // 최종 노드까지의 최소비용 출력

    }
    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        //시작 노드까지의 거리는 0으로 설정하여 큐에 삽입
        pq.offer(new Node(start, 0));
        D[start] = 0;
        while (!pq.isEmpty()){ // 큐가 비어있지 않다면
            // 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
            Node node = pq.poll();
            int dist = node.getDistance(); // 현재 노드까지의 비용
            int now = node.getIndex(); // 현재 노드
            // 현재 노드가 이미 처리된 적 있는 노드라면 무시
            if(D[now] < dist) continue;

            // 현재 노드와 연결된 다른 인접한 노드들 확인
            for(int i =0; i<graph.get(now).size(); i++){ //graph.get(now) = 현재 노드

                int next_idx = graph.get(now).get(i).getIndex(); // 연결된 인접 노드
                int next_dis = D[now] + graph.get(now).get(i).getDistance(); //now에서 인접 노드로 연결된 거리
                if(next_dis < D[next_idx]) // 저장된 인접노드까지의 값보다 비용이 적다면?
                {
                    D[next_idx] = next_dis; // 갱신
                    pq.offer(new Node(next_idx, next_dis)); // 우선순위 큐 추가
                }
            }
        }
    }
}
class Node implements Comparable<Node>{
    private int index;
    private int distance;
    public Node(int index, int distance){
        this.index = index;
        this.distance = distance;
    }
    public int getIndex() {
        return index;
    }
    public int getDistance() {
        return distance;
    }
    @Override
    public int compareTo(Node other) { // 거리 비용이 짧은 것이 높은 우선 순위를 가지도록 설정
        if(this.distance < other.distance){
            return -1;
        }
        return 1;
    }
}
