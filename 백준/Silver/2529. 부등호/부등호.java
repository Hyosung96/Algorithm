import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int k;
    private static boolean[] isVisited;
    private static String[] sign;
    private static ArrayList<String> result;

    private static int[] number;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine()); // 부등호 문자 개수
        sign = new String[k]; // 부등호 저장
        isVisited = new boolean[10]; // 0~9 숫자방문여부 (중복숫자불가)
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            sign[i] = st.nextToken();
        }
        result = new ArrayList<>(); // 부등호 관계를 만족하는 모든 값 저장
        number = new int[k+1]; // 부등호 관계를 만족하는 값 임시 저장
        dfs(0);
        System.out.println(result.get(result.size() - 1)); //최댓값
        System.out.println(result.get(0)); //최솟값
    }

    private static void dfs(int depth) { // depth = 깊이 저장
        if (depth == k + 1) { // 마지막 depth일 경우 값 저장 후 이전 depth로 return
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<=k; i++){
                sb.append(number[i]);
            }
            result.add(String.valueOf(sb));
            return;
        }
        for (int i = 0; i <= 9; i++) {
            if (depth == 0 || !isVisited[i] && check(number[depth - 1], i, sign[depth - 1])) { //첫진입 통과 || 숫자 비교(부등호)
                isVisited[i] = true;
                number[depth] = i;
                dfs(depth + 1);
                isVisited[i] = false;
                number[depth] = 0; // 해당 depth 초기화
            }
        }
    }

    private static boolean check(int a, int b, String c) {
        if (c.equals("<")) return a < b;
        else return a > b;
    }
}
