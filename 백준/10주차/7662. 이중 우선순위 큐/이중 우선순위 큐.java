import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        // 낮은 숫자가 우선순위..
        // 최댓값 최솟값 삭제 시 요소가 둘 이상일 경우 하나만 삭제됨

        int T = Integer.parseInt(br.readLine());

        for(int i =0; i<T; i++){
            // T: 테스트 케이스
            TreeMap<Long, Integer> treeMap = new TreeMap<>();
            int k = Integer.parseInt(br.readLine());
            for(int j=0; j<k; j++){ // 입력
                st = new StringTokenizer(br.readLine());
                String oper = st.nextToken();
                long num = Long.parseLong(st.nextToken());

                if(oper.equals("I")){ // 삽입 연산
                    treeMap.put(num, treeMap.getOrDefault(num, 0) +1); // (num, cnt) 저장
                }
                else{ // 삭제 연산
                    if(treeMap.isEmpty()) continue; // 비어 있을 경우? 다음 연산으로
                    if(num == -1){
                        long min = treeMap.firstKey();
                        if(treeMap.get(min) == 1){ // 요소가 하나만 있을 경우 삭제
                            treeMap.remove(min);
                        }
                        else treeMap.replace(min, treeMap.get(min) -1); // 하나 이상일 경우 카운트 -1
                    }
                    else {
                        long max = treeMap.lastKey();
                        if(treeMap.get(max) == 1){
                            treeMap.remove(max);
                        }
                        else treeMap.replace(max, treeMap.get(max) -1);
                    }
                }
            }
            if(treeMap.isEmpty()){
                System.out.println("EMPTY");
            }
            else {
                System.out.println(treeMap.lastKey() +" " + treeMap.firstKey());
            }
        }

    }
}
