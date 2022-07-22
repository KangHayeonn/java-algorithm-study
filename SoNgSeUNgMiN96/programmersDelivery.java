import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

    static class Node{
        int next, w;

        public Node(int next, int w) {
            this.next = next;
            this.w = w;
        }
    }

    public static int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[] dist = new int[N+1];
        int INF = Integer.MAX_VALUE-10001;      //오버플로우가 나지 않는 선에서의 큰 값을 정한다.

        ArrayList<Node>[] graph = new ArrayList[N+1];

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.w-b.w);       //여기서의 Node에는 현재 노드까지의 누적된 거리의 합의 최솟값,과 현재 노드를 담는다.

        for (int i = 1; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;      //자신을 기준으로 INF로 먼저 초기화 시켜둔다.
        }
        dist[1] = 0;    //1번마을 0으로 초기화.

        for (int[] ints : road) {   //각 경로그래프를 양방향으로 그려준다.
            graph[ints[0]].add(new Node(ints[1], ints[2]));
            graph[ints[1]].add(new Node(ints[0], ints[2]));
        }

        pq.offer(new Node(1,0));        //1번마을을 추가한다. 1번마을까지 cost 0

        while (pq.size()>0){
            Node curr = pq.poll();

            for (Node node : graph[curr.next]) {        //방문한 마을에서 이어진 마을들을 검사하고, 해당 마을 중 1번마을에서 가는 거리의 비용이 짧아지는경우 갱신한다.
                if(dist[node.next]> curr.w+ node.w){        //갱신되어 더 짧게갈 수 있는 마을이 있다면 그 마을을 다시 pq에 넣는다.
                    dist[node.next] = curr.w+ node.w;
                    pq.offer(new Node(node.next, dist[node.next]));
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if(dist[i]<=K) answer++;
        }

        return answer;
    }
}
