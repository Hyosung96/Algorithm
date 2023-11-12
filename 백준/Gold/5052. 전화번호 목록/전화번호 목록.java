import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    static int N, T;
    
    public static void main(String[] args) throws IOException {
        // 전화번호 목록
        // 문자열 정렬 (숫자 작은 순X, 문자열 순)
        // 문자열 포함 여부 체크

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T= Integer.parseInt(br.readLine());

        Main: for(int i =0; i<T; i++){
            N = Integer.parseInt(br.readLine());
            TreeSet<String> phone = new TreeSet<>();

            for(int j=0; j<N; j++){
                phone.add(br.readLine());
            }

            // ex) 111 121 1220 1225 123 123450 125 (문자열 정렬)
            for(int j=0; j<N-1; j++){ // 문자열 정렬이 된 상태이므로, 첫 요소와 다음요소만 비교
                String A = phone.pollFirst();
                String B = phone.first();
                if(B.startsWith(A)){ // 다음요소가 이전 요소로 시작하는지 체크
                    System.out.println("NO");
                    continue Main; // 다음 테스트 케이스로 이동
                }
            }
            System.out.println("YES"); // 포함 요소 없을 경우 YES
        }
    }
}
