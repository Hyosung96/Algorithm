import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 강의 수

        PriorityQueue<Lecture> lectures = new PriorityQueue<>(); // 강의 저장

        //입력받기
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(no, start, end)); // 강의 저장 (시작순서 순)
        }

        //종료시간을 기준으로 하는 방을 정렬
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        rooms.add(lectures.poll().end); // 첫 강의실 세팅

        Main: while(!lectures.isEmpty()) {
            int roomsCnt = rooms.size();
            Lecture nextLecture = lectures.poll(); // 신규 강의

            for(int i = 0; i < roomsCnt; i++) {
                //강의하고 있는 강의실에서 강의를 할 수 있다면
                if(rooms.peek() <= nextLecture.start ) { // 현재 진행 강의의 end 보다 다음 강의의 start가 클 경우
                    rooms.poll();   //현재 강의실에서의 강의를 제거
                    rooms.add(nextLecture.end);  //다음 강의의 종료시간 넣기
                    continue Main; // 다음 강의로 이동
                }
            }
            rooms.add(nextLecture.end); //다음 강의의 종료시간 넣기
        }

        System.out.println(rooms.size());

    }

    private static class Lecture implements Comparable<Lecture> {
        int no, start, end;

        public Lecture(int no, int start, int end) {
            this.no = no;
            this.start = start;
            this.end = end;
        }
        // 시작시간 순 정렬
        @Override
        public int compareTo(Lecture o) {
            return this.start - o.start;
        }
    }
}