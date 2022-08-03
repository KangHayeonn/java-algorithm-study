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
		
		boolean chk;
		String head, number, tail, str;

		for(int i=0; i<files.length; i++) {
            		String[] strArr = files[i].split("[0-9]");
			str = files[i];
            		head = strArr[0];
            		number = "";
            		tail="";
           			chk = false;
            
			for(int j=head.length(); j<str.length(); j++) {
				char c = str.charAt(j);
                
                			if(!Character.isDigit(c)) chk = true;
                
                			if(Character.isDigit(c) && number.length() < 5 && !chk) {
                    				number += c;
                			} else {
                    				tail += c;
                			}
                
			}
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
