import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        // 연산1: 문자열의 뒤에 A 추가
        // 연산2: 문자열을 뒤집고 뒤에 B 추가
        // S -> T 가능하다면 1, 불가능하면 0
        // dfs 시간초과
        // T -> S 역방향
        // T의 마지막 요소가 A일 경우? A삭제
        // T의 마지막 요소가 B일 경우? B삭제 후 reverse

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder S = new StringBuilder(br.readLine());
        StringBuilder T = new StringBuilder(br.readLine());

        while (S.length()<T.length()){
            if(T.charAt(T.length()-1) == 'A'){ // 마지막 요소 A일 경우 삭제
                T.deleteCharAt(T.length()-1);
            }
            else if(T.charAt(T.length()-1) == 'B'){
                T.deleteCharAt(T.length()-1);
                T.reverse();
            }
        }
        if(S.toString().equals(T.toString())) System.out.println(1);
        else System.out.println(0);
    }
}