
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int cnt = 0;
        while(A<B){

            String str = String.valueOf(B);

            if (str.charAt(str.length() - 1) != '1' && B % 2 != 0) {
                cnt = -1;
                break;
            }

            if(B%2==0){
                B /= 2;
            }
            else{
                B /= 10;
            }
            cnt++;
        }
        if(A!=B){
            System.out.println(-1);
        }
        else {
            System.out.println(cnt+1);
        }
    }
}
