import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] arr; //Graph
    static int[] inDegree; //진입 차수 배열
    static int[] buildTime; //건물 짓는 시간
    static int[] waitingTime; //각 건물별 기다리는 총 시간 (건설 시간 제외)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            arr[i] = new ArrayList<>();
        }

        inDegree = new int[N+1];
        buildTime = new int[N+1];
        waitingTime = new int[N+1];

        for(int i=1;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            //소요시간 먼저
            buildTime[i] = Integer.parseInt(st.nextToken());

            //-1 나올떄까지 입력
            while(true) {
                int num = Integer.parseInt(st.nextToken());

                if(num == -1) break;

                arr[num].add(i);
                inDegree[i]++;
            }
        }

        //위상 정렬 진행
        topologySort();

        for(int i=1;i<=N;i++) {
            System.out.println(waitingTime[i] + buildTime[i]);
        }
    }

    public static void topologySort() {
        Queue<Integer> Q = new LinkedList<>();

        //진입 차수가 0인 것 큐에 삽입
        for(int i=1;i<=N;i++) {
            if(inDegree[i] == 0)
                Q.offer(i);
        }

        while(!Q.isEmpty()) {
            int currentNode = Q.poll();

            for(int nextNode : arr[currentNode]) {
                inDegree[nextNode]--;

                //다음 건물을 짓기 위해선, 현재 건물의 총 시간(대기 + 건설)만큼 기다려야 함
                //다른 탐색과정에서 현재 건물을 짓기 위해 waitingTime이 저장되어있으면, 둘 중 큰 값으로!
                waitingTime[nextNode] = Math.max(waitingTime[nextNode], waitingTime[currentNode] + buildTime[currentNode]);

                //진입 차수 0됐으면 큐에 삽입
                if(inDegree[nextNode] == 0) {
                    Q.offer(nextNode);
                }
            }
        }

    }

}
