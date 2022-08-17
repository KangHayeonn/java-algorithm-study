// 베스트앨범 (프로그래머스 LEVEL3)
import java.util.*;
import java.util.Map.Entry;

class Solution {
	public static class Song {
		String genre;
		int idx, play, count; // idx: 고유번호, play: 재생횟수, count: 속한 횟수
		
		public Song(String genre, int idx, int play, int count) {
			this.genre = genre;
			this.idx = idx;
			this.play = play;
			this.count = count;
		}
	}
    	public int[] solution(String[] genres, int[] plays) {
		Map<String, Integer> map = new HashMap<>();
		PriorityQueue<Song> pq = new PriorityQueue<>((o1, o2) -> {
			if(o1.count != o2.count) return o2.count - o1.count;
			else {
				if(o1.play != o2.play) return o2.play - o1.play;
				else return o1.idx - o2.idx;
			}
		});
		
		String str="";
		int count=0;
		
		for(int i=0; i<genres.length; i++) {
			str = genres[i];
			
			map.put(str, map.getOrDefault(str, 0) + plays[i]);
		}
		
		for(int i=0; i<genres.length; i++) {
			str = genres[i];
			count = map.get(str);
			
			pq.offer(new Song(str, i, plays[i], count));
		}
		
		count = 0;
		str = "";
		ArrayList<Integer> arr = new ArrayList<>();
		
		while(!pq.isEmpty()) {
			Song s = pq.poll();
			
			if(str.equals(s.genre)) {
				count += 1;
				if(count > 2) continue;
			}
			else {
				str = s.genre;
				count = 1;
			}
			
			arr.add(s.idx);
		}
		
		int[] answer = arr.stream()          // Stream<Integer>을 반환함
				.mapToInt(Integer::intValue) // Integer의 intValue() 메서드를 참조해서 값타입인 int로 언박싱함
				.toArray();                  // IntStream의 원소를 배열로 변환함
		return answer;
	}
}
