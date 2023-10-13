import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        // 햄버거, 사람 HashMap으로 저장
        // (IDX, H/P)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 글자 길이
        int K = Integer.parseInt(st.nextToken()); // 범위 (햄버거 선택할 수 있는)

        String str = br.readLine(); // 전체 문자열

        HashMap<Integer, Character> map = new HashMap<>(); // 햄버거, 사람 저장 <IDX, H/P>

        for(int i =0; i<N; i++){
            map.put(i, str.charAt(i));
        }

        int cnt = 0;

        Main: for(int i =0; i<N; i++){
            if(map.containsKey(i)){
                if(map.get(i).equals('P')){ // 사람일 경우 주변 햄버거 탐색
                    if(i<K){ // 현재 위치가 탐색 범위보다 작을 경우? 0부터 탐색
                        for(int j=0; j<i; j++){ // 뒤로 K칸 탐색
                            if(map.containsKey(j)){
                                if(map.get(j).equals('H')){
                                    map.remove(j);
                                    cnt++;
                                    continue Main;
                                }
                            }
                        }
                    }
                    else {
                        for(int j=i-K; j<i; j++){ // 뒤로 K칸 탐색
                            if(map.containsKey(j)){
                                if(map.get(j).equals('H')){
                                    map.remove(j);
                                    cnt++;
                                    continue Main;
                                }
                            }
                        }
                    }
                    for(int j=i+1; j<=i+K; j++){ // 앞으로 K칸 탐색
                        if(map.containsKey(j)){
                            if(map.get(j).equals('H')){
                                map.remove(j);
                                cnt++;
                                continue Main;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(cnt);

    }
}


