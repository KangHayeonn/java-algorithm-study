import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int answer;
    static boolean[] isVisited;
    static ArrayList<Node>[] info;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
        //노드 개수 입력
        N = Integer.parseInt(br.readLine());
        
        //간선 간의 연결 정보 저장
        info = new ArrayList[N+1];

        for(int i=1;i<=N;i++) {
            info[i] = new ArrayList<Node>();
        }

        //노드 정보 입력
        for(int i=0;i<N-1;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            //양방향 그래프로 저장
            info[from].add(new Node(to,w));
            info[to].add(new Node(from, w));
        }

        answer = 0;
        //전체 노드 탐색하면서 최대 거리 찾기
        //1번 노드 부터 N번 노드 까지 for문으로 탐색
        for(int i=1;i<=N;i++) {
            isVisited = new boolean[N+1];
            isVisited[i] = true;
            dfs(i, 0);
        }

        System.out.println(answer);
    }
  
    //
    public static void dfs(int nodeNum, int weight) {
        //현재 위치에서 갈 수 있는 노드들 탐색
        for(Node node : info[nodeNum]) {
            if(!isVisited[node.nodeNum]) { //방문한적 없으면
                isVisited[node.nodeNum] = true; // 방문처리
                dfs(node.nodeNum, node.weight + weight); //해당 노드에서 더 탐색 (재귀)
            }
        }
        //탐색 종료하면 최대거리 업데이트
        answer = Math.max(weight, answer);
    }

    public static class Node {
        int nodeNum; //노드 숫자
        int weight; //가중치

        public Node(int nodeNum, int weight) {
            this.nodeNum = nodeNum;
            this.weight = weight;
        }
    }
}
