import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    private static int[][] board;

    private static ArrayList<int[]> zero;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 0인 위치를 저장하는 리스트
        // 0의 위치들 체크하고, 순서대로 들어갈 수 있는 값들 넣어주기 (다음 depth)
        // check 배열 만들어주고, 해당 위치에 들어갈 수 있는 값 체크

        board = new int[9][9];

        zero = new ArrayList<>();
        for(int i = 0; i<9; i++){
            String s = br.readLine();
            for(int j=0; j<9; j++){
                board[i][j]= Integer.parseInt(String.valueOf(s.charAt(j)));
                if(board[i][j] == 0){
                    zero.add(new int[]{i,j}); // 인덱스 저장
                }
            }
        }
        dfs(0);
    }

    private static void dfs(int depth) {
        if(depth == zero.size()){ // 0인 칸 다 채웠을 경우
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.exit(0); // 종료
        }
        int[] tmp = zero.get(depth);
        int row = tmp[0]; //행
        int col = tmp[1]; //열

        boolean[] numCheck = new boolean[10]; // 1~9 중 들어갈 수 있는 숫자 체크

        for(int i=0; i<9; i++){ //가로체크, 0이 아닌 요소들 체크
            if(board[row][i]!=0) numCheck[board[row][i]] = true;
        }
        for(int i=0; i<9; i++){ //세로체크, 0이 아닌 요소들 체크
            if(board[i][col]!=0) numCheck[board[i][col]] = true;
        }
        int startRow = (row/3) *3; // 3x3 네모의 첫 값 세팅
        int startCol = (col/3) *3;

        for(int i=startRow; i<startRow+3; i++){ // 네모 체크(3x3)
            for(int j=startCol; j<startCol+3; j++){
                if(board[i][j]!=0) numCheck[board[i][j]] = true;
            }
        }
        for(int i=1; i<10; i++){ //해당 위치에 들어갈 수 있는 값 체크 후 세팅, 다음 dfs 탐색
            if(!numCheck[i]) {
                board[row][col] = i;
                dfs(depth+1);
                board[row][col] = 0;
            }
        }
    }
}