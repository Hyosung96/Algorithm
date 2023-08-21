import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // mCn
        // 점화식: m!/n!(m-n)!
        // 팩토리얼 재귀 구현
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            BigInteger N = new BigInteger(st.nextToken());
            BigInteger M = new BigInteger(st.nextToken());
            BigInteger sub = M.subtract(N);
            N = recursion(N);
            M = recursion(M);
            sub = recursion(sub);
            System.out.println(M.divide(N.multiply(sub)));
        }
    }
    public static BigInteger recursion(BigInteger num){
        if(num.compareTo(BigInteger.ONE) < 1) return BigInteger.ONE;
        return num.multiply(recursion(num.subtract(BigInteger.ONE)));
    }
}
