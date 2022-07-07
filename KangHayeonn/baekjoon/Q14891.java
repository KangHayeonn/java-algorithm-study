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
			isVisited = new boolean[4]; // 회전한 톱니바퀴의 번호를 체크해주기 위해
			
			// 회전 함수
			turnWheel(type-1, direction);
			/*
			** type = 0 -> 1번 톱니바퀴
			** type = 1 -> 2번 톱니바퀴
			** type = 2 -> 3번 톱니바퀴
			** type = 3 -> 4번 톱니바퀴
			**/
		}
		
		int total = 0;
		for(int i=0; i<4; i++) {
			int value = wheel[i][top[i]%8] - '0'; // 총 0~7의 값만 나오도록 하기 위해 %8을 나눠줌
			
			if(i==0 && value==1) total += 1;
			else if(i==1 && value==1) total += 2;
			else if(i==2 && value==1) total += 4;
			else if(i==3 && value==1) total += 8;
		}
		System.out.println(total);
	}
        // 톱니바퀴의 번호와 방향에 따라 재귀함수를 호출 (해당 번호의 톱니바퀴가 회전했다고 가정)
	public static void turnWheel(int type, int direction) {
		// 1~4번 톱니바퀴를 벗어나는 범위일 경우 return
        	if(type < 0 || type > 3) return;
		// 이미 회전한 톱니바퀴는 true로 변경
       		isVisited[type] = true;
		
        	// 제일 왼쪽에 있는 톱니바퀴일 경우(1번) -> 바로 오른쪽 옆 톱니바퀴만 체크(2번)
		if(type == 0 && !isVisited[type+1]) {
            		// +2일 경우 상대와 밎물리는 오른쪽 바퀴
            		// +6일 경우 상대와 밎물리는 왼쪽 바퀴
           		// 즉, 1번 톱니바퀴의 경우 자신의 오른쪽 2번바퀴와 2번 톱니바퀴의 6번바퀴를 비교 (동일한 극인지)
            		// 극이 다를 경우만 (!=) 옆 톱니바퀴를 현재 자신의 방향과 반대방향으로 회전
			if(wheel[type][(top[type]+2)%8] != wheel[type+1][(top[type+1]+6)%8]) {
				turnWheel(type+1, direction * (-1));
			}
		}
		
        	// 가운데에 있는 톱니바퀴의 경우(2번 & 3번)
        	// 자신을 기준으로 좌우 톱니바퀴를 체크함
		if((type > 0 && type < 3)) {
            		// 자신을 기준으로 오룬쪽
			if(!isVisited[type+1]) {
				if(wheel[type][(top[type]+2)%8] != wheel[type+1][(top[type+1]+6)%8]) {
					turnWheel(type+1, direction * (-1));
				}
			}
            		// 자신을 기준으로 왼쪽
			if(!isVisited[type-1]) {
				if(wheel[type][(top[type]+6)%8] != wheel[type-1][(top[type-1]+2)%8]) {
					turnWheel(type-1, direction * (-1));
				}
			}
		}
		
        	// 제일 오른쪽에 있는 톱니바퀴일 경우(4번) -> 바로 왼쪽 옆 톱니바퀴만 체크(3번)
		if(type == 3 && !isVisited[type-1]) {
			if(wheel[type][(top[type]+6)%8] != wheel[type-1][(top[type-1]+2)%8]) {
				turnWheel(type-1, direction * (-1));
			}
		}
		
		// 바퀴 회전 (시계방향 / 반시계방향)
		if(direction == 1) { // 시계방향
			top[type] += 7;
		} else if(direction == -1) { // 반시계방향
			top[type] += 1;
		}
		/*
		** 인덱싱을 이용해 회전을 가정! (가상 회전 구현)
		** 자신을 기준으로 8개의 톱니를 각각 0~7로 top부터 부여하였을 경우
		** 시계방향으로 돌경우 자기 자신의 처음 인덱싱 7에 있던 값이 top에 위치하게 됨 (따라서, +7)
		** 반시계방향으로 돌경우 자기 자신의 처음 인덱싱 1에 있던 값이 top에 위치하게 됨 (따라서, +1)                        
		*/
	}
}
