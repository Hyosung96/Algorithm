import java.io.*;

public class Main {

    static int N;

    static int[] num;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        num = new int[N+1];
        visited = new boolean[N+1];

        dfs(1);
    }

    public static void dfs(int depth){
        if(depth == N+1){
            for(int i =1; i<N+1; i++){
                System.out.print(num[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i=1; i<N+1; i++){
            if (visited[i]){
                continue;
            }
            num[depth] = i;
            visited[i] = true;
            dfs(depth+1);
            visited[i] = false;
        }

    }

}
