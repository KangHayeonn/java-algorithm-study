import java.util.*;
import java.io.*;

public class Main {
	static int firstIdx = 0;
	static int answer = 0;
	// static int[][] tree;
	static ArrayList<Type> tree[];
	static int n;
	public static class Type {
		int node, weight;
		public Type(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer("");
		
		// tree = new int[n+1][n+1]; // 메모리 초과
		tree = new ArrayList[n+1];
		
		for(int i=0; i<=n; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			tree[from].add(new Type(to, weight));
			tree[to].add(new Type(from, weight));
		}
		
		boolean[] isVisited = new boolean[n+1];
		isVisited[1] = true;
		dfs(1, 0, isVisited); // 부모에서 가장 긴 노드 찾기
		
		isVisited = new boolean[n+1];
		isVisited[firstIdx] = true;
		dfs(firstIdx, 0, isVisited);
		
		System.out.println(answer);
	}
	public static void dfs(int idx, int count, boolean[] isVisited) {
		if(answer < count) {
			answer = count;
			firstIdx = idx;
		}
		
		for(Type t: tree[idx]) {
			if(!isVisited[t.node]) {
				isVisited[t.node] = true;
				dfs(t.node, count + t.weight, isVisited);
			}
		}
	}
}
