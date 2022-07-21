import java.util.*;

class Solution {
        public static class Type implements Comparable<Type> {
		int node, weight;
		
		public Type(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Type t) {
			return this.weight - t.weight;
		}
	}
	public int solution(int N, int[][] road, int K) {
		ArrayList<Type>[] graph = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int len = road.length;
		for(int i=0; i<len; i++) {
			int from = road[i][0];
			int to = road[i][1];
			int weight = road[i][2];
			
			graph[from].add(new Type(to, weight)); // 양방향
			graph[to].add(new Type(from, weight));
		}
		
		int[] stPath = new int[N+1]; // 1번 마을에서 시작했을 경우 최단경로를 저장하는 배열
		int INF = 50 * 500000;
		Arrays.fill(stPath, INF);		
		return dijkstra(graph, stPath, 1, K);
	}
	public static int dijkstra(ArrayList<Type>[] graph, int[] stPath, int start, int K) {
		PriorityQueue<Type> pq = new PriorityQueue<>();
		pq.offer(new Type(start, 0));
		stPath[start] = 0;
		
		while(!pq.isEmpty()) {
			Type t = pq.poll();
			int node = t.node;
			int weight = t.weight;
			
			for(Type x : graph[node]) {
				int gNode = x.node;
				int gWeight = x.weight;
				
				if(stPath[gNode] > weight + gWeight) {
					stPath[gNode] = weight + gWeight;
					pq.offer(new Type(gNode, weight + gWeight));
				}
			}
		}
		
		int len = stPath.length;
		int answer = 0;
		for(int i=1; i<len; i++) {
			if(stPath[i] <= K) {
				answer++;
			}
		}
		
		return answer;
	}
}
