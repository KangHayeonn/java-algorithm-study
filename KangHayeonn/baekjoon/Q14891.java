// 백준 톱니바퀴 (골드5)

import java.io.*;
import java.util.*;

public class Main {
	static char[][] wheel = new char[4][8];
	static int[] top = new int[4];
	static boolean[] isVisited;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		for(int i=0; i<4; i++) {
			wheel[i] = (br.readLine()).toCharArray();
		}
		
		int K = Integer.parseInt(br.readLine()); // 회전횟수
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			isVisited = new boolean[4];
			
			// 회전 함수
			turnWheel(type-1, direction);
		}
		
		int total = 0;
		for(int i=0; i<4; i++) {
			int value = wheel[i][top[i]%8] - '0';
			
			if(i==0 && value==1) total += 1;
			else if(i==1 && value==1) total += 2;
			else if(i==2 && value==1) total += 4;
			else if(i==3 && value==1) total += 8;
		}
		System.out.println(total);
	}
	public static void turnWheel(int type, int direction) {
		if(type < 0 || type > 3) return;
		isVisited[type] = true;
		
		if(type == 0 && !isVisited[type+1]) {
			if(wheel[type][(top[type]+2)%8] != wheel[type+1][(top[type+1]+6)%8]) {
				turnWheel(type+1, direction * (-1));
			}
		}
		if((type > 0 && type < 3)) {
			if(!isVisited[type+1]) {
				if(wheel[type][(top[type]+2)%8] != wheel[type+1][(top[type+1]+6)%8]) {
					turnWheel(type+1, direction * (-1));
				}
			}
			if(!isVisited[type-1]) {
				if(wheel[type][(top[type]+6)%8] != wheel[type-1][(top[type-1]+2)%8]) {
					turnWheel(type-1, direction * (-1));
				}
			}
		}
		if(type == 3 && !isVisited[type-1]) {
			if(wheel[type][(top[type]+6)%8] != wheel[type-1][(top[type-1]+2)%8]) {
				turnWheel(type-1, direction * (-1));
			}
		}
		
		// 바퀴 회전 (시계방향 / 반시계방향)
		if(direction == 1) {
			top[type] += 7;
		} else if(direction == -1) {
			top[type] += 1;
		}
	}
}
