
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 처음 위치는 무작위로 정해짐 (1/N)
        // 10분에 한번씩 이동
        // 25 0.6, 25 1.0, 25 0.3, 25 0.7 1.0 0.4
        // 향하는 간선의 확률 배열 하나 만들고
        // to 매장의 존재 확률 = (from 매장 존재 확률 x to 간선의 확률) / 4 * 100

        // 0.15 0.25 0.075, 0.525

        // 0.6, 1.0, 0.3, 2.1
        // 해당 위치에 있을 확률 / 4 x 향하는 간선의 확률의 합
        // 4를 나누는 이유는? -> 간선의 모든 확률의 합=4
        // 백분율이기에 나중에 100 곱해줘야함

        int time = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        double[][] node = new double[4][4];    //매장 간 이동 확률 저장
        double[] line = new double[4];    //각 매장에 존재할 확률
        double[] tmp;   // 계산용 확률
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char from = st.nextToken().charAt(0);
            char to = st.nextToken().charAt(0);
            double per = Double.parseDouble(st.nextToken());

            node[from - 65][to - 65] = per;    //각 매장 이동 확률 저장
            line[to - 65] += per;        //첫 10분 이동할 계산 저장, 각 노드를 향하는 확률의 합
        }
        //n × 10분 동안 이동 거리 탐색
        for (int i = 1; i < time; i++) {
            tmp = new double[4];
            for (int j = 0; j < 4; j++) {
                if (line[j] == 0) continue;    // 시작 간선 존재하지 않을 경우
                for (int k = 0; k < 4; k++) {
                    if (node[j][k] == 0) continue;    //해당 매장에 있을 확률 0일 경우
                    tmp[k] += line[j] * node[j][k]; // 도착 노드의 확률 세팅
                }
            }
            //계산한 값 확률 복사 (tmp -> line)
            System.arraycopy(tmp, 0, line, 0, 4);
        }

        //탐색 종료 후 얻은 계산값을 통해 확률 구하기
        for (int i = 0; i < 4; i++) {
            System.out.println((line[i] / 4) * 100);
        }


    }
}
