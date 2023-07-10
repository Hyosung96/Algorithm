import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static LinkedList<Integer> deque = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        // N개의 원소를 포함하고 있는 양방향 큐
        // 3가지 연산
        // 1. 첫번째 원소 뽑아내기(Poll)
        // 2. 왼쪽으로 한칸 이동(Left)
        // 3. 오른쪽으로 한칸 이동(Right)
        // N: 큐의 크기, M: 수의 개수

        int[] pick = new int[M];

        for(int i=1; i<=N; i++){
            deque.add(i);
        }
        int cnt = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            pick[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++){
            if(check(pick[i])){ // 절반 보다 작을경우 left
                while (pick[i]!=deque.get(0)){
                    int tmp = deque.pollFirst();
                    deque.addLast(tmp);
                    cnt++;
                }
            }
            else {
                while (pick[i]!=deque.get(0)){
                    int tmp = deque.pollLast();
                    deque.addFirst(tmp);
                    cnt++;
                }
            }
            deque.poll();
        }

        System.out.println(cnt);

    }

    public static boolean check(int a) {

        int idx = deque.indexOf(a);
        return idx <= deque.size() / 2;
    }

}
