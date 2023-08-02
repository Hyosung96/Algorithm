import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, Integer> map = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        double sum = 0;
        int[] num = new int[N];
        
        for(int i=0; i<N; i++){
            num[i] = Integer.parseInt(br.readLine());
            sum += num[i];
            if(map.containsKey(num[i])){
                map.replace(num[i], map.get(num[i])+1);
            }
            else{
                map.put(num[i], 1);
            }
        }
        Arrays.sort(num);

        //산술평균 출력
        System.out.println(Math.round(sum/N));
        //중앙값 출력
        System.out.println(num[N/2]);
        //최빈값 출력
        int max = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i =0; i<N; i++){
            max = Math.max(max, map.get(num[i]));
        }
        for(int i =0; i<N; i++){
            int a = map.get(num[i]);
            if(a == max){
                if(i!=0){
                    if(num[i-1] != num[i]){ //중복 값 제외
                        list.add(num[i]);
                    }
                }
                else {
                    list.add(num[i]);
                }
            }
        }

        if(list.size()>1){
            System.out.println(list.get(1));
        }
        else {
            System.out.println(list.get(0));
        }
        //범위 출력
        System.out.print(num[N-1] - num[0]);

    }

}
