// 오픈채팅방 (프로그래머스 LEVEL2)

import java.util.*;

class Solution {
	public String[] solution(String[] record) {
		Map<String, String> map = new HashMap<>();
		ArrayList<Type> log = new ArrayList<>();
		
		String[] strArr;
		String input, id, nickname;
		for(int i=0; i<record.length; i++) {
			strArr = record[i].split(" ");
			input = strArr[0];
			id = strArr[1];
			nickname = strArr.length > 2 ? strArr[2] : "";
			
			if("Enter".equals(input)) {
				map.put(id, nickname);
				log.add(new Type(id, 1));
			} else if("Leave".equals(input)) {
				log.add(new Type(id, -1));
			} else {
				map.put(id, nickname);
			}
		}
		
		String[] answer = new String[log.size()];
		String output;
		for(int i=0; i<log.size(); i++) {
			Type t = log.get(i);
			
			if(t.input == 1) {
				output = "님이 들어왔습니다.";
			} else {
				output = "님이 나갔습니다.";
			}
			
			answer[i] = map.get(t.id) + output;
		}
		
		return answer; 
	}
	public class Type {
		String id; 
		int input; // 1 : Enter, -1 : Leave
		
		public Type(String id, int input) {
			this.id = id;
			this.input = input;
		}
	}
}
