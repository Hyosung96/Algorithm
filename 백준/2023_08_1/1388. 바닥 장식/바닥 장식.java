
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static String[][] wood;
    public static int N, M;
    public static int cnt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        wood = new String[N][M];

        //판자 행렬 세팅
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                wood[i][j] = str.substring(j, j + 1);
            }
        }

        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (wood[i][j].equals("-")) {//가로 나무판자 탐색
                    if(j==M-1){ // 탐색 위치가 마지막일 경우
                        cnt++;
                    }
                    else{
                        if (wood[i][j+1].equals("|")) { // 탐색 위치의 다음 칸이 세로 판자일 경우
                            cnt++;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (wood[j][i].equals("|")) {//세로 나무판자 탐색
                    if(j==N-1){ // 탐색 위치가 마지막일 경우
                        cnt++;
                    }
                    else{
                        if (wood[j+1][i].equals("-")) { // 탐색 위치의 다음 열이 가로 판자일 경우
                            cnt++;
                        }
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}
