import java.io.*;
import java.util.StringTokenizer;

public class Main {


    static int N;
    static int[] num;
    static int[] oper;

    static int max;
    static int min;


    public static void main(String[] args) throws IOException {

        // [+, -, x, /]

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        num = new int[N+1];
        st = new StringTokenizer(br.readLine(), " ");

        for(int i=0; i<N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        oper = new int[4];

        st = new StringTokenizer(br.readLine(), " ");

        for(int i =0; i<4; i++){
            oper[i] = Integer.parseInt(st.nextToken());
        }
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        solve(1, num[0]); // 0 depth : num[0] 고정, 1 depth 부터 시작
        System.out.println(max);
        System.out.println(min);
    }

    public static void solve(int depth, int n) {
        if(depth == N){
            max = Math.max(max, n);
            min = Math.min(min, n);
            return;
        }

        for(int i=0; i<4; i++){
            if(oper[i] != 0) {
                oper[i]--;
                switch (i) {
                    case 0:
                        solve( depth + 1, n + num[depth]); break;
                    case 1:
                        solve( depth + 1, n - num[depth]); break;
                    case 2:
                        solve( depth + 1, n * num[depth]); break;
                    case 3:
                        solve( depth + 1, n / num[depth]); break;
                }
                oper[i]++;
            }
        }

    }

}
