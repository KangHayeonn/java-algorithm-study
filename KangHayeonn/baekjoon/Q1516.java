// 게임 개발 (백준 골드3)
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		ArrayList<Integer>[] arr = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		
		int x, weight;
		int[] cutOfLink = new int[N+1]; // 후행 정점에 대한 간선의 수
		int[] weightArr = new int[N+1];
		ArrayList<Integer> numArr;
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			weight = Integer.parseInt(st.nextToken());
			numArr = new ArrayList<>();
			
			while(true) {
				x = Integer.parseInt(st.nextToken());
				if(x == -1) break;
				numArr.add(x);
			}
			for(Integer n : numArr) {
				arr[n].add(i);
				cutOfLink[i]++;
			}
			weightArr[i] = weight;
		}

		TopologicalSort(arr, cutOfLink, weightArr, N);
		
	}
	public static void TopologicalSort(ArrayList<Integer>[] arr, int[] cutOfLink, int[] weightArr, int N) {
		Queue<Integer> q = new LinkedList<>();
		int[] result = new int[N+1];
		
		// 초기 : 선행 정점을 가지지 않는 정점을 큐에 삽입
		for(int i=1; i<N+1; i++) {
			if(cutOfLink[i]==0) {
				q.offer(i);
				result[i] = weightArr[i];
			}
		}
		
		for(int i=1; i<N+1; i++) {
			Integer t = q.poll();
			
			for(Integer nextT : arr[t]) {
				cutOfLink[nextT]--;
				
				result[nextT] = Math.max(result[nextT], result[t]+weightArr[nextT]);
				
				if(cutOfLink[nextT] == 0) {
					q.offer((nextT));
				}
			}
		}
		
		for(int i=1; i<N+1; i++) {
			System.out.println(result[i]);
		}
	}
}
