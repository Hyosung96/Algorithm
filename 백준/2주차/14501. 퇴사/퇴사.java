import java.io.*;
import java.util.StringTokenizer;


public class Main {

    static int N;
    static int[] T;
    static int[] P;

    public static void main(String[] args) throws IOException {

        //N+1일 퇴사
        //상담을 완료하는데 걸리는 기간 Ti
        //상담을 했을 때 받을 수 있는 금액 Pi
        //N일에 얻는 최대 수익 계산
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        T = new int[N+1];
        P = new int[N+1];


        for (int i =1; i<=N; i++){
            String s= br.readLine();
            st = new StringTokenizer(s);
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;

        for(int i=1; i<=N; i++){//시작 일 지정 (첫날이 아니라 이후부터 시작할 수 있음)
            sum = Math.max(sum, solve(i+T[i], P[i]));
        }
        System.out.println(sum);

    }
    public static int solve(int day, int value){
        if(day > N+1) return 0;

        int tmp = value;

        for(int i=day; i <= N; i++){//상담 후 일자부터 N+1까지 상담 이득 비교
            tmp = Math.max(tmp, solve(i+T[i], value +P[i]));
        }
        return tmp;
    }

}
