import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static int N;


    public static void main(String[] args) throws IOException {
        // 카드 정렬하기

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());


        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i =0; i<N; i++){
            pq.add(Integer.parseInt(br.readLine()));
        }

        if(N == 1) { // 1묶음일 경우 예외처리
            System.out.println(0);
            System.exit(0);
        }

        int result = 0;

        while (pq.size() >= 2){ // 우선순위 큐 2개 이상일 경우, 합산 후 que add(우선순위 정렬)
            int A = pq.poll();
            int B = pq.poll();
            result += A+B;
            pq.add(A + B);
        }
        System.out.println(result);

    }
}
