// 표 편집 (프로그래머스 LEVEL3)
import java.util.*;

class Solution {
	public String solution(int n, int k, String[] cmd) {
		int currIdx = k; // 현재 위치
        	int lastIdx = n-1; // 마지막 행 인덱스
        	Stack<Integer> deleteStack = new Stack<>();
        
		String command;
		int x = 0;

		for(int i=0; i<cmd.length; i++) {
			String[] strArr = cmd[i].split(" ");
			command = strArr[0];

			if("U".equals(command)) {
				x = Integer.parseInt(strArr[1]);
				currIdx -= x;
			} else if("D".equals(command)) {
				x = Integer.parseInt(strArr[1]);
				currIdx += x;
			} else if("C".equals(command)) {
				deleteStack.add(currIdx);

				if(currIdx == lastIdx) currIdx -= 1;
				lastIdx -= 1;
			} else {
				if(deleteStack.pop() <= currIdx) currIdx += 1;
				lastIdx += 1;
			}
		}

		StringBuilder sb = new StringBuilder("");
		for(int i=0; i<=lastIdx; i++) {
			sb.append("O");
		}
        
		while(!deleteStack.isEmpty()) {
        			sb.insert(deleteStack.pop(), "X");
        		}
        		
		return sb.toString();
	}
}
