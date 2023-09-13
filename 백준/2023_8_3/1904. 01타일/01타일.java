import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // N=1일 때 : 1
        // N=2일 때 : 2
        // N=3일 때 : 3
        // N=4일 때 : 5
        // N=5일 때 : 8
        // 00001 00100 10000 11100 00111 10011 11001 11111
        // 피보나치 수열: a(n+2) = a(n-1) + a(n-2)
        // N < 1,000,000
        int MAX = 1000000;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] fb = new int[MAX+1];
        fb[1] = 1;
        fb[2] = 2;
        if(N>=3){
            for(int i=1; i<N-1; i++){
                fb[i+2] = (fb[i+1] + fb[i])%15746;
            }
        }
        System.out.println(fb[N]%15746);
    }
}
