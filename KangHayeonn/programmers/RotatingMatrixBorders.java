// 행렬 테두리 회전하기 (프로그래머스 LEVEL2)
class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] map = new int[rows][columns];
		
		int num = 1;
		for(int r=0; r<rows; r++) {
			for(int c=0; c<columns; c++) {
				map[r][c] = num;
				num += 1;
			}
		}
		
		int[] answer = new int[queries.length];
		int idx = 0, r1, c1, r2, c2, temp, min;
		for(int i=0; i<queries.length; i++) {
			r1 = queries[i][0]-1;
			c1 = queries[i][1]-1;
			r2 = queries[i][2]-1;
			c2 = queries[i][3]-1;
			
			temp = map[r1][c1];
			min = temp;
			
			for(int r = r1; r < r2; r++){ // ↑
	            map[r][c1] = map[r+1][c1];
	            min = Math.min(min, map[r][c1]);
	        }
	        for(int c = c1; c < c2; c++){ // ←
	        	map[r2][c] = map[r2][c+1];
	            min = Math.min(min, map[r2][c]);
	        }
	        for(int r = r2; r > r1; r--){ // ↓
	        	map[r][c2] = map[r-1][c2];
	            min = Math.min(min, map[r][c2]);
	        }
	        for(int c = c2; c > c1; c--){ // →
	        	map[r1][c] = map[r1][c-1];
	            min = Math.min(min, map[r1][c]);
	        }
	        map[r1][c1+1] = temp;
	        
	        answer[idx++] = min;
		}
		
		return answer;
    }
}
