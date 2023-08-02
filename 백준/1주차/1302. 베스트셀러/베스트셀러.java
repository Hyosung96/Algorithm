import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        String result = "";

        int max = 0;

        HashMap<String, Integer> map = new HashMap<>();

        for(int i =0; i<num; i++){
            String s = br.readLine();

            int cnt = 0;

            if(map.containsKey(s)){
                cnt = map.get(s);
                cnt++;
                map.replace(s, cnt);
            }
            else {
                cnt++;
                map.put(s, cnt);
            }
        }

        for (String a : map.keySet()){
            if(map.get(a) == max && result.compareTo(a) > 0){
                result = a;
            }
            else if(map.get(a) > max){
                result = a;
                max = map.get(a);
            }
        }

        System.out.println(result);

    }

}
