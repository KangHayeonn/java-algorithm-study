// 파일명 정렬 (프로그래머스 LEVEL2)
import java.util.*;

class Solution {
	public static class Type {
		String head, number, tail;
		int order;
		
		public Type(String head, String number, String tail, int order) {
			this.head = head;
			this.number = number;
			this.tail = tail;
			this.order = order;
		}
	}
	public String[] solution(String[] files) {
		PriorityQueue<Type> pq = new PriorityQueue<>((x, y) -> {
			String s1 = x.head.toLowerCase();
			String s2 = y.head.toLowerCase();
			if(s1.compareTo(s2) < 0) return -1;
			else if(s1.compareTo(s2) > 0) return 1;
			else {
				int n1 = Integer.parseInt(x.number);
				int n2 = Integer.parseInt(y.number);
				if(n1 != n2) return n1 - n2;
				else {
					return x.order - y.order;
				}
			}
		});
		
		int start, end, cnt;
		String head, number, tail="", str;
		
		for(int i=0; i<files.length; i++) {
			str = files[i];
			start = 0; // Number 시작점
			end = 0;   // Tail 시작점
			cnt = 0;
			
			for(int j=0; j<str.length(); j++) {
				char c = str.charAt(j);
				System.out.println(c);
				
				if(cnt > 5 && end == 0) end = j;
				if(!Character.isDigit(c) && start != 0 && end == 0) {
					System.out.println("들어오나 ? " + c);
					end = j;
				}
				if(Character.isDigit(c)) {
					if(start == 0) start = j;
					cnt += 1;
				}
			}
			head = str.substring(0, start);
			
			if(end == 0) {
				number = str.substring(start);
			} else {
				number = str.substring(start, end);
				tail = str.substring(end);
			}
			
			System.out.println(head + " " + number + " " + tail);
			pq.offer(new Type(head, number, tail, i));
		}

		String[] answer = new String[files.length];
		int idx = 0;
		
		while(!pq.isEmpty()) {
			Type t = pq.poll();
			str = t.head + t.number + t.tail;
			answer[idx++] = str; 
		}
		return answer;
	}
}
