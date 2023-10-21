import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        // 농구 경기 48분 동안 진행
        // 골이 들어갈 때 마다 골이 들어간 시간과 팀을 적음
        // N: 골이 들어간 횟수
        // 득점한 팀의 정보(1or2), 득점시간
        // 각 팀의 이기고 있던 시간 출력 (첫째줄 1팀, 둘째줄 2팀) (MM:SS)
        // 분 정보를 초로 환산해서 계산

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        HashMap<Integer, String> timeMap = new HashMap<>(); // 득점 시간, 팀 저장
        HashMap<String, Integer> scoreMap = new HashMap<>(); // 팀, 득점 저장
        HashMap<String, Integer> resultMap = new HashMap<>(); // 팀, 이긴 시간 저장

        for(int i =0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String team = st.nextToken();
            String time = st.nextToken();
            int m = Integer.parseInt(time.split(":")[0]);// 분
            int s = Integer.parseInt(time.split(":")[1]);// 초

            timeMap.put((m*60)+s, team); // 초로 변환해서 저장
        }
        scoreMap.put("1", 0);
        scoreMap.put("2", 0); // 초기 득점 정보 저장
        resultMap.put("1", 0);
        resultMap.put("2", 0);
        for(int i=0; i<2880; i++){ // 48분 초 환산: 2880
            if(timeMap.containsKey(i)){ // 해당 시간에 득점 정보 있을 경우? 득점처리
                String team = timeMap.get(i);
                scoreMap.replace(team, scoreMap.get(team)+1);
            }
            if(scoreMap.get("1") > scoreMap.get("2")){
                resultMap.replace("1", resultMap.get("1")+1);
            }
            else if(scoreMap.get("2") > scoreMap.get("1")) {
                resultMap.replace("2", resultMap.get("2")+1);
            }
        }
        System.out.printf("%02d"+":"+"%02d"+"\n", (resultMap.get("1")/60), (resultMap.get("1")%60)); // 시간 환산하여 출력
        System.out.printf("%02d"+":"+"%02d", (resultMap.get("2")/60), (resultMap.get("2")%60));
    }
}
