class Solution {
	static char[][] map;
	static int answer = 0;
	static int[][] d = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
	public int solution(int m, int n, String[] board) {
		map = new char[m][n];
		for(int i=0; i<m; i++) {
			map[i] = board[i].toCharArray();
		}
		
		// 게임 진행
		while(true) {
			if(!checkBlock(m, n)) break; // 더 이상 깰 블록이 없으면 break
			deleteBlock(m, n);
		}
		return answer;
	}
	public static boolean checkBlock(int m, int n) {
		boolean[][] isChecked = new boolean[m][n];
		int nr, nc, count=0;
		
		for(int i=0; i<m-1; i++) {
			for(int j=0; j<n-1; j++) {
				if(map[i][j] == '-') continue; // 삭제된 블록일 경우 continue
				if(!fourBlock(i, j)) continue;
				else {
					for(int k=0; k<4; k++) {
						nr = i + d[k][0];
						nc = j + d[k][1];
						if(nr >= m || nc >= n) continue;
						if(!isChecked[nr][nc]) {
							count += 1;
							isChecked[nr][nc] = true;
						}
					}
				}
			}
		}
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(isChecked[i][j]) map[i][j] = '-';
			}
		}
		
		answer += count;
		return (count > 0) ? true : false;
	}
	public static boolean fourBlock(int r, int c) {
		int check = map[r][c];
		
		for(int i=r; i<r+2; i++) {
			for(int j=c; j<c+2; j++) {
				if(map[i][j] != check) return false;
			}
		}
		return true;
	}
	public static void deleteBlock(int m, int n) {
		for(int i=m-1; i>=0; i--) {   // 행
			for(int j=0; j<n; j++) {  // 열
				if(map[i][j] == '-') {
					for(int r = i - 1 ; r >= 0 ; r--) {
						if(map[r][j] != '-') {
							map[i][j] = map[r][j];
							map[r][j] = '-';
							break;
						}
					}
				}
			}
		}
	}
}
