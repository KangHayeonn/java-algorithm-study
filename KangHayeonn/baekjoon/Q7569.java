// 백준 토마토 골드5

import java.io.*;
import java.util.*;

public class Main {
	static int[][] d = {
			{1, 0, 0}, {-1, 0, 0}, 
			{0, 1, 0}, {0, -1, 0}, 
			{0, 0, 1}, {0, 0, -1}
		}; // 동 서 남 북 상 하 (x, y, z)
	static int[][][] map;
	static int M, N, H;
	static Queue<Type> q = new LinkedList<>();
	public static class Type {
		int x, y, z;
		public Type(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][N][M]; // 상자
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<M; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if(map[i][j][k] == 1) {
						q.offer(new Type(k, j, i)); // 익은 토마토를 먼저 좌표로 저장
					}
				}
			}
		}
		bfs();
		System.out.print(countDay());
	}
	public static void bfs() {
		boolean[][][] isVisited = new boolean[H][N][M];
		
		while(!q.isEmpty()) {
			Type t = q.poll();
			isVisited[t.z][t.y][t.x] = true;
			
			for(int i=0; i<6; i++) {
				int nx = t.x + d[i][0];
				int ny = t.y + d[i][1];
				int nz = t.z + d[i][2];
				
				if(nx < 0 || ny < 0 || nz < 0 ||
					nx >= M || ny >= N || nz >= H) continue;
				
				if(!isVisited[nz][ny][nx] && map[nz][ny][nx] == 0) {
					q.add(new Type(nx, ny, nz));
					isVisited[nz][ny][nx] = true;
					map[nz][ny][nx] = map[t.z][t.y][t.x] + 1; // 익은 날짜 계산
				}
			}
		}
	}
	public static int countDay() {
		int max = 0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					int check = map[i][j][k];
					
					if(check == 0) return -1; // 안 익은 토마토가 있을 경우
					if(max < check) max = check; // 최대 일수로 교환
				}
			}
		}
		
		return (max == 0) ? max : max-1; 
	}
}
