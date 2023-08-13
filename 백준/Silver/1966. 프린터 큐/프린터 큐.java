import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0; i<T; i++){
            Deque<int[]> deque = new LinkedList<>(); // 초기 위치, 중요도 저장
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 문서 개수
            int M = Integer.parseInt(st.nextToken()); // 찾는 위치
            st = new StringTokenizer(br.readLine()); // 문서 입력
            int max = 0;
            for(int j=0; j<N; j++){
                int num = Integer.parseInt(st.nextToken());
                deque.add(new int[] {j, num});
                max = Math.max(max, num);
            }
            int cnt = 0;
            while (deque.size()>0){
                if(deque.getFirst()[1] == max){ // 첫번째 요소의 중요도가 가장 높을 경우
                    cnt++;
                    if(deque.getFirst()[0] == M){ // 해당 요소가 찾으려는 요소일 경우
                        deque.pollFirst();
                        System.out.println(cnt);
                        break;
                    }
                    else {
                        deque.pollFirst();
                    }
                    max = 0; // 최댓값 초기화
                    for (int[] element : deque) {
                        max = Math.max(max, element[1]);
                    }
                }
                else { //첫번째 요소의 중요도가 가장 높지 않을 경우
                    deque.addLast(deque.getFirst());
                    deque.pollFirst();
                }
            }

        }

    }

}