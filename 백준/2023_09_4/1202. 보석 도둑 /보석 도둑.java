import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        // N: 보석 수, K = 가방 수
        // 하나의 가방에 하나의 보석
        // (M,V) 무게, 가격
        // 훔칠 수 있는 보석의 최대 가격
        // (1 ≤ N, K ≤ 300,000)
        // (0 ≤ Mi, Vi ≤ 1,000,000)
        // (1 ≤ Ci ≤ 100,000,000) -- 가방에 수용가능한 최대 무게

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 보석 개수
        int K = Integer.parseInt(st.nextToken()); // 가방 개수


        ArrayList<Gem> list = new ArrayList<>(); // 보석 무게, 값 저장, [0]: 무게, [1]: 값

        for(int i=0; i<N; i++){ // 보석 무게, 값 저장 (무게 가벼운 순서로 저장, 같을 경우 값이 비싼 순으로 저장)
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            Gem node = new Gem(weight, value);
            list.add(node);
        }
        NodeComparator nodeComparator = new NodeComparator();

        list.sort(nodeComparator);

        int[] bag = new int[K]; // 가방 무게 저장

        for(int i=0; i<K; i++){
            bag[i] = Integer.parseInt(br.readLine()); // 가방 무게
        }
        Arrays.sort(bag); // 가방 무게 정렬

        long valueSum = 0; // 보석 값 합산

        // 우선순위 큐는 항상 가격이 내림차순 정렬되도록 설정.
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        int idx = 0;

        for(int i=0; i<K; i++){
            if(list.isEmpty()) break; // 보석이 없을 경우

            while (true){
                if(idx>=N) break; // 보석 개수 다 차면 break
                Gem gem = list.get(idx);

                if(bag[i] < gem.weight) break; // 가방 무게보다 보석이 무거울 경우 루프 종료
                // 보석의 무게가 가방보다 작으면 최소 힙에 저장
                pq.add(gem.value);
                idx++; // 보석 수 증가

            }

            if (!pq.isEmpty()) {
                valueSum += pq.poll();
            }

        }
        System.out.println(valueSum);
    }
}

class Gem {
    int weight;
    int value;

    public Gem(int weight, int value){
        this.weight = weight;
        this.value = value;
    }
}
// 우선순위 Comparator
class NodeComparator implements Comparator<Gem> {
    @Override
    public int compare(Gem o1, Gem o2) {
        if (o1.weight == o2.weight) { // 무게가 같을 경우?
            return o2.value - o1.value; // 더 높은 값 (내림차순 정렬)
        } else { // 무게가 다를 경우
            return o1.weight - o2.weight; // 더 낮은 무게 (오름차순 정렬)
        }
    }
}

