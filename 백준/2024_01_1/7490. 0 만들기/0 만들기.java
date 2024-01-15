import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static int N;


    public static void main(String[] args) throws Exception {
        // 0만들기
        // (3 <= N <= 9)
        // ' ', +, - , 연산 3개
        // '', +, - 아스키 순
        // dfs 3개 돌려서 모든 경우의 수

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int i =0; i<T; i++){
            N = Integer.parseInt(br.readLine());

            dfs(1,"1");
            System.out.println();
        }

    }
    public static void dfs(int num, String str){
        if(num == N) {
            result(str);
            return;
        }

        dfs(num+1,  str + " "  + (num+1)); // 자릿수 올리기
        dfs(num+1,  str + "+"  + (num+1)); // 덧셈
        dfs(num+1,  str + "-"  + (num+1)); // 뺄셈
    }

    public static void result(String result) {
        String str = result.replaceAll(" ", ""); // 공백 제거
        StringTokenizer st = new StringTokenizer(str, "-+", true); // 구분자 2개 사용(-, +)
        int sum = Integer.parseInt(st.nextToken()); // 첫번째 요소(숫자) ex. 1, 12, 123...

        while (st.hasMoreTokens()){
            String s = st.nextToken();
            if(s.equals("+")) { // 구분자 +일 경우
                sum += Integer.parseInt(st.nextToken());
            }else { // 구분자 -일 경우
                sum -= Integer.parseInt(st.nextToken());
            }
        }
        if(sum == 0){ // 결과 0일경우 출력
            System.out.println(result);
        }
    }
}
