// 순위 검색 (프로그래머스 LEVEL2)
import java.util.*;

class Solution {
    	static Map<String, ArrayList<Integer>> map = new HashMap<>();
	static ArrayList<Integer> arr;
    	public int[] solution(String[] info, String[] query) {
		String[] strArr;
		String str;
		
		for(int i=0; i<info.length; i++) {
			strArr = info[i].split(" ");
			combination("", 0, strArr);
		}
		
		List<String> list = new ArrayList<>(map.keySet());
		for(int i=0; i<list.size(); i++) {
			arr = map.get(list.get(i));
			Collections.sort(arr);
		}
		
		int[] answer = new int[query.length];
		for(int i=0; i<query.length; i++) {
			str = query[i].replaceAll(" and ", "");
			strArr = str.split(" ");
			answer[i] = binarySearch(strArr[0], Integer.parseInt(strArr[1]));
		}
		
		return answer;
	}
	// 조합
	public static void combination(String str, int depth, String[] strArr) {
		if(depth == 4) {
			if(!map.containsKey(str)) {
				arr = new ArrayList<Integer>();
				arr.add(Integer.parseInt(strArr[4]));
				map.put(str, arr);
			} else {
				map.get(str).add(Integer.parseInt(strArr[4]));
			}
			return;
		}
		
		combination(str+strArr[depth], depth+1, strArr);
		combination(str+"-", depth+1, strArr);
	}
	// 이분 탐색
	public static int binarySearch(String str, int num) {
		if(!map.containsKey(str)) return 0;
		
		arr = map.get(str);
		int start = 0, end = arr.size()-1, mid = 0;

		while(start <= end) {
			mid = (start + end) / 2;
			
			if(arr.get(mid) < num) {
				start = mid+1;
			} else {
				end = mid-1; 
			}
		}
		
		return arr.size() - start;
	}
}
