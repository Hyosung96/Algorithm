import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        HashMap<String, String> map = new HashMap<>();

        for(int i =0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String place = st.nextToken();
            String startTime = st.nextToken();
            String endTime = st.nextToken();

            if (map.containsKey(name)){ // 이름 이미 있을 경우 패스
                continue;
            }
            else {
                map.put(name, place+","+startTime+","+endTime); // , : 구분자
            }
        }
        HashMap<String, List<int[]>> placeMap = new HashMap<>(); // 장소 값 저장

        // placeMap 초기화
        for(String s : map.keySet()){
            String place = map.get(s).split(",")[0];
            placeMap.put(place, new ArrayList<>());
        }

        for(String s : map.keySet()){
            String place = map.get(s).split(",")[0];
            int startTime = Integer.parseInt(map.get(s).split(",")[1]);
            int endTime = Integer.parseInt(map.get(s).split(",")[2]);

            placeMap.get(place).add(new int[]{startTime, endTime}); // [1, 4], [2, 3] ...
        }

        HashMap<String, Integer> placeCntMax = new HashMap<>();

        for(int i=1; i<50000; i++){ // 탐색하며 장소별 최대 값 갱신
            for(String s : placeMap.keySet()){
                int cnt = 0;
                List<int[]> placeList = placeMap.get(s);


                for(int j=0; j<placeList.size(); j++){ // 해당 장소로 저장된 값들 중 현재 범위 안에 들어가는 요소 탐색
                    if(placeList.get(j)[0] <=i &&  placeList.get(j)[1] >i){
                        cnt++;
                    }
                }
                // placeCntMax에 해당 키가 있는지 확인하고 없으면 초기값 0으로 설정
                if (!placeCntMax.containsKey(s)) {
                    placeCntMax.put(s, 0);
                }
                placeCntMax.put(s, Math.max(placeCntMax.get(s), cnt)); // 장소별 최대값 갱신
            }
        }

        String maxKey = "";
        int maxValue = 0;
        for (Map.Entry<String, Integer> entry : placeCntMax.entrySet()) { //시간이 제일 많이 겹친 장소 탐색, 여러개일 경우 사전 순서
            if (entry.getValue() > maxValue || (entry.getValue() == maxValue && entry.getKey().compareTo(maxKey) < 0)) {
                maxKey = entry.getKey();
                maxValue = entry.getValue();
            }
        }
        List<int[]> maxKeyPlaceList = placeMap.get(maxKey);
        List<Integer> range = new ArrayList<>();
        boolean isMax = false;
        for(int i=0; i<50000; i++){ // 제일 많이 겹친 장소의 범위 탐색
            int cnt = 0;
            for(int j= 0; j<maxKeyPlaceList.size(); j++){ // cnt 세팅
                if(maxKeyPlaceList.get(j)[0] <=i &&  maxKeyPlaceList.get(j)[1] >=i){ // 체크할 때와 다르게 End 지점을 포함
                    cnt++;
                }
            }
            if(isMax && cnt!= maxValue){ // 겹치는 구간 끝났을 경우 종료
                System.out.println(maxKey + " " + range.get(0) + " " + range.get(range.size()-1));
                return;
            }
            if(cnt == maxValue){
                range.add(i);
                isMax = true;
            }
        }
    }
}
