import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int L, C;

    private static ArrayList<String> result;

    private static boolean[] visited;

    private static HashMap<Character, Boolean> map;

    private static char[] words;

    private static int jaCnt, moCnt; // 자음, 모음 개수


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 3 ≤ L ≤ C ≤ 15 , 소문자, 중복 x
        L = Integer.parseInt(st.nextToken()); // 암호의 길이
        C = Integer.parseInt(st.nextToken()); // 암호에 들어갈 수 있는 문자 개수

        visited = new boolean[C];
        map = new HashMap<>(); // Hashmap에 모음들 저장
        map.put('a', true);
        map.put('e', true);
        map.put('i', true);
        map.put('o', true);
        map.put('u', true);

        result = new ArrayList<>();

        words = new char[C];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<C; i++){
            words[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(words);

        // 총 뎁스는 C(암호의 길이)
        dfs("",0); // 1부터 시작 // 최소 1개의 모음, 2개의 자음

        for(String s : result){
            System.out.println(s);
        }
    }

    private static void dfs(String word,int depth) {
        if(depth == L){
            for(int i=0; i<L; i++){
                check(word.charAt(i)); // 자음 모음 개수 체크
            }
            if(moCnt>=1 && jaCnt>=2){
                result.add(word);
            }
            moCnt = 0;
            jaCnt = 0; // 자음, 모음 초기화
            return;
        }
        for(int i = 0; i<C; i++){
            if(visited[i]){
                continue;
            }
            if(word.length()>0){
                if(words[i] < word.charAt(word.length()-1)){ // 들어오는 문자가 문자순인지 체크
                    continue;
                }
            }
            visited[i] = true;
            dfs(word + words[i], depth+1);
            visited[i] = false;
        }
    }

    public static void check(char c){
        if(map.containsKey(c)){
            moCnt++;
        }
        else {
            jaCnt++;
        }
    }
}