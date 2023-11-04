import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static int N, max;

    static ArrayList<Integer> num;

    public static void main(String[] args) throws IOException {
        // 에너지 구슬의 개수 N
        // 에너지 구슬의 무게 W
        // 구슬 2개 남을 때까지 반복
        // 양 마지막 끝은 선택 불가능
        // 하나 선택하면 양옆의 값 곱셈하여 에너지 모음
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        num = new ArrayList<>(); // 에너지 구슬

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            num.add(Integer.parseInt(st.nextToken()));
        }

        max = 0;

        dfs(N, 0); // 구슬 개수(뽑을 때마다 -1), 에너지

        System.out.println(max);

    }
    public static void dfs(int length, int sum){
        if(length <= 2){ // 구슬 다 뽑았을 경우
            max = Math.max(max, sum);
            return;
        }
        for(int i=1; i<num.size()-1; i++){
            int n = num.get(i-1) * num.get(i+1); // 에너지 적립
            int tmp = num.remove(i); // 현재 위치 제거
            dfs(length-1, sum+n);
            num.add(i, tmp); // 복귀
        }
    }
}