// 도로 네트워크 (백준 플래티넘4)
import java.io.*;
import java.util.*;

public class Main {
	static int N, d, min, max;
	static ArrayList<City>[] tree;
	static int[] depthArr;
	static int[][] parentArr, minLengthArr, maxLengthArr;
	public static class City {
		int city, weight;
		public City (int city, int weight){
			this.city = city;
			this.weight = weight;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
	
		tree = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			tree[i] = new ArrayList<City>();
		}
		
		int from, to, weight;
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			
			tree[from].add(new City(to, weight));
			tree[to].add(new City(from, weight));
		}
		
		d = getMaxDepth();
		
		depthArr = new int[N+1];
		parentArr = new int[d+1][N+1];
		minLengthArr = new int[d+1][N+1];
		maxLengthArr = new int[d+1][N+1];
		
		init(1, 0, 1);
		getParent();
		
		int K = Integer.parseInt(br.readLine()), a, b;
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			LCA(a, b);
			bw.write(min + " " + max + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	public static int getMaxDepth() {
		int x = 1;
		int t = 0;
		
		while(x <= N) {
			x *= 2;
			t += 1;
		}
		
		return t-1;
	}
	public static void init(int curr, int d, int parent) {
		depthArr[curr] = d;
		
		for(City c : tree[curr]) {
			if(c.city != parent) {
				init(c.city, d+1, curr);
				parentArr[0][c.city] = curr;
				minLengthArr[0][c.city] = c.weight;
				maxLengthArr[0][c.city] = c.weight;
			}
		}
	}
	public static void getParent() {
		for(int r=1; r<d+1; r++) {
			for(int c=1; c<N+1; c++) {
				parentArr[r][c] = parentArr[r-1][parentArr[r-1][c]];
				minLengthArr[r][c] = Math.min(minLengthArr[r-1][c], minLengthArr[r-1][parentArr[r-1][c]]);
				maxLengthArr[r][c] = Math.max(maxLengthArr[r-1][c], maxLengthArr[r-1][parentArr[r-1][c]]);
			}
		}
	}
	public static void LCA(int a, int b) {
		min = 1000000;
		max = -1;
		
		int depthA = depthArr[a];
		int depthB = depthArr[b];
		
		if(depthA < depthB) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		for(int i=d; i>=0; i--) {
			if(Math.pow(2, i) <= depthArr[a] - depthArr[b]) {
				min = Math.min(minLengthArr[i][a], min);
				max = Math.max(maxLengthArr[i][a], max);
				
				a = parentArr[i][a];
			}
		}
		
		if(a == b) return;
		
		for(int i=d; i>=0; i--) {
			if(parentArr[i][a] != parentArr[i][b]) {								
				min = Math.min(min, Math.min(minLengthArr[i][a], minLengthArr[i][b]));
				max = Math.max(max, Math.max(maxLengthArr[i][a], maxLengthArr[i][b]));
				
				a = parentArr[i][a];
				b = parentArr[i][b];
			}
		}
		
		// 동일한 깊이에서 시작할 경우 초기값이 그대로 유지되는 문제때문에 추가
		min = Math.min(min, Math.min(minLengthArr[0][a], minLengthArr[0][b]));
		max = Math.max(max, Math.max(maxLengthArr[0][a], maxLengthArr[0][b]));
		
		return;
	}
}
