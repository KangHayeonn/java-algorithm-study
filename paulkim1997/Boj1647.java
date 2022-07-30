import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static ArrayList<Node>[] info;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        info = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i=1;i<=N;i++) {
            info[i] = new ArrayList<Node>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            //양방향
            info[from].add(new Node(to, cost));
            info[to].add(new Node(from, cost));
        }

        //Prim 알고리즘
        int selectedCnt = 0;
        int maxWeight = 0;
        int answer = 0;

        PriorityQueue<Node> q = new PriorityQueue<>();

        //1번 노드부터 탐색 시작
        q.add(new Node(1,0));

        while(!q.isEmpty()) {
            Node cur = q.poll();

            //이미 선택된 집이면 스킵
            if(visited[cur.next]) continue;

            visited[cur.next] = true;
            answer += cur.weight;
            maxWeight = Math.max(cur.weight, maxWeight);
            selectedCnt++;

            //N개 다 골랐으면 끝
            if(selectedCnt == N) break;

            for(Node n : info[cur.next]) {
                if(!visited[n.next]) {
                    q.add(n);
                }
            }
        }

        System.out.println(answer - maxWeight);
    }

    static class Node implements Comparable<Node>{
        private int weight;
        private int next;

        Node(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
