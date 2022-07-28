// 양궁대회 (프로그래머스 LEVEL2)
import java.util.*;

class Solution {
	static int[] lionArr, ansArr;
	static int max = Integer.MIN_VALUE;
	public int[] solution(int n, int[] info) {
		lionArr = new int[11];
		ansArr = new int[11];
		dfs(1, n, info);
		
		if(!check(ansArr)) return new int[]{-1}; 
		return ansArr;
	}
	public void dfs(int count, int n, int[] info) {
		if(count == n+1) {
			int apeach=0, lion=0;
			for(int i=0; i<11; i++) {
				if(info[i] == 0 && lionArr[i] ==0) continue;
				if(info[i] >= lionArr[i]) apeach += 10-i;
				else lion += 10-i;
			}
			if(apeach < lion) {
				if(lion - apeach >= max) // 같을 때를 포함해야 더 작은 값이 계속 갱신됨
                {
					max = lion - apeach;
					for(int j=0; j<11; j++) {
						ansArr[j] = lionArr[j];
					}
                }
			}
			return;
		}
		for(int i=0; i<11; i++) {
			if(lionArr[i] > info[i]) continue;
			lionArr[i]+=1;
			dfs(count+1, n, info);
			lionArr[i]-=1;
		}
	}
	public boolean check(int[] arr) {
		for(int i=0; i<arr.length; i++) {
			if(arr[i] != 0) return true;
		}
		return false;
	}
}
