import java.io.*;
import java.util.Stack;


public class Main {

    public static void main(String[] args) throws IOException {

        Stack<Character> stk = new Stack<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        int result = 0;
        int num = 1;

        for(int i = 0; i<str.length(); i++){
            switch (str.charAt(i)) {

                case '(':
                    stk.push('(');
                    num *= 2;
                    break;

                case '[':
                    stk.push('[');
                    num *= 3;
                    break;

                case ')':
                    if (stk.isEmpty() || stk.peek() != '(') {
                        result = 0;
                        System.out.println(result);
                        return;
                    }

                    if (str.charAt(i-1) == '(') {
                        result += num;
                    }
                    stk.pop();
                    num /= 2;
                    break;

                case ']':
                    if (stk.isEmpty() || stk.peek() != '[') {
                        result = 0;
                        System.out.println(result);
                        return;
                    }

                    if ((str.charAt(i-1) == '[')){
                        result += num;
                    }
                    stk.pop();
                    num /= 3;
                    break;
            }
        }
        if(!stk.isEmpty()){
            result = 0;
        }
        System.out.println(result);

    }

}