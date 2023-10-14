
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] num = new int[N];
        for(int i=0; i<N; i++){
            num[i] = Integer.parseInt(br.readLine());
        }
        int cnt = 0;
        for(int i=N-1; i>0; i--){
            if(num[i]<=num[i-1]){
                while (num[i]<=num[i-1]){
                    num[i-1]--;
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

}
