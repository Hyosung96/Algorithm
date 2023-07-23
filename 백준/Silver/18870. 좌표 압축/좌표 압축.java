import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] num = new int[N];
        int[] sort = new int[N];

        for(int i=0; i<N; i++){
            int number = Integer.parseInt(st.nextToken());
            num[i] = number;
            sort[i] = number;
        }

        Arrays.sort(sort);

        int rank = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i =0; i<N; i++){
            if(i!=0){
                if(!map.containsKey(sort[i])){
                    map.put(sort[i], rank);
                    rank++;
                }
            }
            else{
                map.put(sort[i], rank);
                rank++;
            }
        }

        for(int i =0; i<N; i++){
            sb.append(map.get(num[i])).append(" ");
        }
        bw.write(String.valueOf(sb));
        bw.flush();
    }

}
