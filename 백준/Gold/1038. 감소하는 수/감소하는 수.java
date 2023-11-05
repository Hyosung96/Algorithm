import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static int N;

    static ArrayList<Long> number;


    public static void main(String[] args) throws IOException {
        // 감소하는 수
        // 0
        // 1 10
        // 20 21 210
        // 30 31 310 32 320 321
        // 맨 앞자리 별 감소하는 수 세팅 후 마지막에 정렬 처리
        // 감소하는 수 최대값 : 9876543210
        // 부분 집합 뽑기 (1개일 경우, 2개일 경우, 3개일 경우) = 10C1 + 10C2 + 10C3 + ... + 10C10
        // 10 45 120 210 252 210 120 45 10 1
        // 10 55 175 385 637 847 967 1012 1022 1023
        // 모든 경우의 수 = 1023 -1 = 1022 (0부터 시작하므로)
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        if (N > 1022) {
            System.out.println(-1);
            System.exit(0);
        }

        number = new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            // 0 ~ 9로 시작하는 감소하는 수를 구함 (맨 앞자릿 수 세팅)
            dfs(1, i);
        }
        
        Collections.sort(number); // 순서 상관 없이 들어가있으므로 sort

        System.out.println(number.get(N));

    }
    public static void dfs(int depth, long num){ // depth: 자릿수, num: 숫자

        if(depth > 10) return; // 자릿수 최대 : 10

        number.add(num);

        for (int i = 0; i < 10; i++) {
            // 숫자의 제일 마지막자리와 비교하여 작은 수를 맨 뒤에 삽입
            // ex) 98 -> 980, 981, 982, ... , 987 까지 재귀 호출
            if (num % 10 > i) {
                // 기존 감소하는 수인 num에 * 10을 하여 자릿수를 높이고 작은 수를 맨뒤에 넣어줌
                // 자릿수 + 1
                dfs(depth + 1,(num * 10) + i);
            }
        }
    }
}