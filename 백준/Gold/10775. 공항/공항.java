import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine()); // 게이트 수
        int P = Integer.parseInt(br.readLine()); // 비행기 수

        int[] parent = new int[G + 1]; // 각 게이트의 부모 게이트를 저장할 배열
        for (int i = 1; i <= G; i++) {
            parent[i] = i; // 초기화: 각 게이트는 자신을 부모로 갖음
        }

        int cnt = 0;

        for (int i = 0; i < P; i++) {
            int n = Integer.parseInt(br.readLine());
            int dockingGate = find(parent, n);

            if (dockingGate != 0) {
                union(parent, dockingGate, dockingGate - 1); // 현재 게이트와 바로 아래 게이트를 합침
                cnt++;
            } else {
                // 도킹할 수 없는 경우 종료
                break;
            }
        }
        System.out.println(cnt);
    }

    private static int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent, parent[x]); // 경로 압축
    }

    private static void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a != b) {
            parent[a] = b;
        }
    }
}
