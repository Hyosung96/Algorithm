
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int[][] from, to;
    public static int N, M;
    public static int cnt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        from = new int[N][M];
        to = new int[N][M];

        //기본 행렬 세팅
        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                from[i][j] =Integer.parseInt(str.substring(j,j+1));
            }
        }

        //바꿔야 할 행렬 세팅
        for(int i = 0 ; i <N; i ++) {
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++)
                to[i][j] = Integer.parseInt(str.substring(j,j+1));
        }

        cnt = 0;
        //탐색 시작(배열 범위 넘어가지않게 -2 범위까지만 수행)
        for(int i = 0 ; i < N-2; i++) {
            for(int j = 0 ; j < M-2; j++) {
                // i,j 가 다르다면 변환
                if(from[i][j] != to[i][j]) {
                    solve(i,j);
                    cnt++;
                }
            }
        }
        boolean chk = true;
        //행렬 동일한지 체크
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++)
                if(from[i][j] != to[i][j]) {
                    chk = false;
                    break;
                }
            if(!chk)
                break;
        }
        
        if(chk){
            System.out.println(cnt);
        }
        else System.out.println(-1);
    }
    public static void solve(int x, int y){
        for(int i=x; i<x+3; i++){
            for(int j=y; j<y+3; j++){
                if(from[i][j] == 0){
                    from[i][j] = 1;
                }
                else {
                    from[i][j] = 0;
                }
            }
        }
    }
}
