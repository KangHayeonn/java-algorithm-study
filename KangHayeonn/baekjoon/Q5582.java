// 공통 부분 문자열 (백준 골드5)

import java.io.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		int[][] dp = new int[str2.length()+1][str1.length()+1]; // 이차원 배열
		int max = 0;
		
		for(int r=1; r<=str2.length(); r++) {     // 행
			for(int c=1; c<=str1.length(); c++) { // 열
				char c1 = str1.charAt(c-1);
				char c2 = str2.charAt(r-1);
				
				if(c1 == c2) {
					dp[r][c] = dp[r-1][c-1] + 1;
					max = Math.max(max, dp[r][c]);
				}
			}
		}
		
		System.out.println(max);
	}
}
