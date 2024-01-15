import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws Exception {
        // AC
        // R연산 : 배열의 수 순서 뒤집기
        // D연산 : 첫번째 수 버리기

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        
        Main: for(int i=0; i<T; i++){
            String oper = br.readLine();
            int N = Integer.parseInt(br.readLine());
            String[] num = new String[N];
            boolean isReverse = false; // 뒤집기 상태

            String str = br.readLine();
            num = str.replace("[", "").replace("]", "").split(",");
            Deque<String> deque = new LinkedList<>();
            for(int j=0; j<num.length; j++){
                deque.add(num[j]);
            }

            for(int j=0; j<oper.length(); j++){
                if(oper.charAt(j) == 'R'){ // 뒤집기 연산
                    isReverse = !isReverse;
                }
                else if(oper.charAt(j) == 'D'){
                    if(deque.size() == 0 || N == 0) { // 요소가 없는데 삭제연산일 경우
                        System.out.println("error");
                        continue Main;
                    }
                    if(isReverse) deque.pollLast(); //뒤집힌 상태일 경우? 마지막 요소 제거
                    else deque.pollFirst(); //정상 상태일 경우? 처음 요소 제거
                }
            }
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            if(isReverse){
                while (!deque.isEmpty()){
                    if(deque.size()==1){ // 마지막 요소일 경우 , 안붙임
                        sb.append(deque.pollLast());
                    }
                    else {
                        sb.append(deque.pollLast()).append(",");
                    }
                }
            }
            else{
                while (!deque.isEmpty()){
                    if(deque.size()==1){ // 마지막 요소일 경우 , 안붙임
                        sb.append(deque.pollFirst());
                    }
                    else {
                        sb.append(deque.pollFirst()).append(",");
                    }
                }
            }
            sb.append("]");
            System.out.println(sb);
        }
    }

}
