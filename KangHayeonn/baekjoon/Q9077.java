// 지뢰제거 (백준 Q9077)

import java.io.*;
import java.util.*;

public class Main {
	static int x, y;
	static int[][] map;
	public static class Type {
		int x, y;
		public Type(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer("");
		ArrayList<Type> arr;
		ArrayList<Type> reset; // 지뢰의 위치
		map = new int[10001][10001];
		int N;
		
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine()); // 지뢰 개수
			arr = new ArrayList<>();
			reset = new ArrayList<>();
			// map = new int[10001][10001]; // 메모리 초과?
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken()); // 지뢰 x-좌표
				y = Integer.parseInt(st.nextToken()); // 지뢰 y-좌표
				
				arr.add(new Type(x, y));
				map[y][x] = 1;
				reset.add(new Type(x, y));
			}
			
			int max = 0;
			for(Type t : arr) {
				int n = check(t.x, t.y);
				if(max < n) max = n;
			}
			
			int count = lastCheck(); // 예외처리 (0 & 10000 부분에 꼭지점)
			if(max < count) max = count;
			
			for(Type t : reset) {
				map[t.y][t.x] = 0;
			}
			
			sb.append(max).append("\n");
		}
		System.out.println(sb);
	}
	public static int check(int x, int y) {
		int[] result = new int[4];
		
		// 정사각형의 '좌측 상단'의 꼭지점에 지뢰가 위치할 경우
		for(int r=y; r<=y+10; r++) {
			for(int c=x; c<=x+10; c++) {
				if(r > 10000 || c > 10000) continue;
				if(map[r][c] == 1) result[0] += 1;
			}
		}
		// 정사각형의 '좌측 하단'의 꼭지점에 지뢰가 위치할 경우
		for(int r=y; r>=y-10; r--) {
			for(int c=x; c<=x+10; c++) {
				if(r < 0 || c > 10000) continue;
				if(map[r][c] == 1) result[1] += 1;
			}
		}
		// 정사각형의 '우측 상단'의 꼭지점에 지뢰가 위치할 경우
		for(int r=y; r<=y+10; r++) {
			for(int c=x; c>=x-10; c--) {
				if(r > 10000 || c < 0) continue;
				if(map[r][c] == 1) result[2] += 1;
			}
		}
		// 정사각형의 '우측 하단'의 꼭지점에 지뢰가 위치할 경우
		for(int r=y; r>=y-10; r--) {
			for(int c=x; c>=x-10; c--) {
				if(r < 0 || c < 0) continue;
				if(map[r][c] == 1) result[3] += 1;
			}
		}
		
		Arrays.sort(result); // 하나의 지뢰를 기준으로 정사각형을 잡았을 경우 최대값을 구함	
		return result[3];
	}
	// 0과 10000 부분에 꼭지점이 위치할 경우의 예외처리
	public static int lastCheck() {
		int[] result = new int[4];
		
		for(int r=0; r<=10; r++) {
			for(int c=0; c<=10; c++) {
				if(map[r][c] == 1) result[0] += 1;
			}
		}
		for(int r=9990; r<=10000; r++) {
			for(int c=9990; c<=10000; c++) {
				if(map[r][c] == 1) result[0] += 1;
			}
		}
		for(int r=0; r<=10; r++) {
			for(int c=9990; c<=10000; c++) {
				if(map[r][c] == 1) result[0] += 1;
			}
		}
		for(int r=9990; r<=10000; r++) {
			for(int c=0; c<=10; c++) {
				if(map[r][c] == 1) result[0] += 1;
			}
		}
		
		Arrays.sort(result);
		return result[3];
	}
}
