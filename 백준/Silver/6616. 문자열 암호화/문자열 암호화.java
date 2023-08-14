import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if(N==0){
                break;
            }
            String s = br.readLine();
            s = s.replace(" ", ""); // 공백 제거

            if(N>=s.length()){ // N이 문자열보다 길 경우
                System.out.println(s.toUpperCase());
                continue; // 문자열 출력 후 다음 루프
            }
            Queue<Character> queue = new LinkedList<>();
            for(int i =0; i<s.length(); i++){
                queue.add(s.charAt(i));
            }
            char[] c = new char[s.length()];
            int idx = 0;

            while (true){
                if(queue.isEmpty()){ // 큐가 비었을 경우 결과값 출력
                    StringBuilder sb = new StringBuilder();
                    for(int i=0; i<c.length; i++){
                        sb.append(c[i]);
                    }
                    String result = sb.toString().toUpperCase();
                    System.out.println(result);
                    break;
                }
                if(c[idx] != 0){ // 넣을 위치에 이미 값이 들어있다면?
                    idx++; // 다음 위치로 이동
                    continue;
                }
                c[idx] = queue.peek(); // 큐의 첫번째 값 삽입 후 poll
                queue.poll();

                idx += N;
                if(idx >= c.length){  // 인덱스가 length보다 클 경우 초기화
                    idx = 0;
                }
            }
        }
    }
}