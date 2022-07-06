import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, String> entranceHours = new HashMap<>(); // 차량번호 : 입차시간
		Map<String, Integer> useHours = new HashMap<>();     // 차량번호 : 이용시간(분)
		
		int basicHour = fees[0]; // 기본 시간(분)
		int basicFee = fees[1];  // 기본 요금(원)
		int unitTime = fees[2];  // 단위 시간(분)
		int unitFee = fees[3];   // 단위 요금(원)
        String[] record, inTimeList, outTimeList;
        String time, carNum, isIn, inTime;
		
		int recordsLen = records.length, inTimeToMinutes, outTimeToMinutes, useTime;
        
		for(int i=0; i<recordsLen; i++) {
			record = records[i].split(" ");
			time = record[0];   // 입차/출차 시간
			carNum = record[1]; // 차량번호 
			isIn = record[2];   // IN/OUT
			
			if(entranceHours.containsKey(carNum)) {
				// 차량이 존재할 경우
				if("OUT".equals(isIn)) {
					inTime = entranceHours.get(carNum); // 입차시간
					// 출차시간 - 입차시간 계산 (분으로 돌려서 계산할 예정)
					inTimeList = inTime.split(":");
					inTimeToMinutes = Integer.parseInt(inTimeList[0])*60 + Integer.parseInt(inTimeList[1]);
					outTimeList = time.split(":");
					outTimeToMinutes = Integer.parseInt(outTimeList[0])*60 + Integer.parseInt(outTimeList[1]);
					
					useTime = outTimeToMinutes - inTimeToMinutes; //  사용시간
					if(useHours.containsKey(carNum)) {
						useHours.put(carNum, useHours.get(carNum) + useTime);
					} else {
						useHours.put(carNum, useTime);
					}
					entranceHours.remove(carNum);
				}
			} else {
				// 차량이 존재하지 않을 경우
				if("IN".equals(isIn)) {
					entranceHours.put(carNum, time);
				}
			}
		}
		
		for(String key : entranceHours.keySet()) {
			inTimeList = entranceHours.get(key).split(":");
			inTimeToMinutes = Integer.parseInt(inTimeList[0])*60 + Integer.parseInt(inTimeList[1]);
			outTimeToMinutes = (23*60 + 59); // 23:59
			
			useTime = outTimeToMinutes - inTimeToMinutes; //  사용시간
			if(useHours.containsKey(key)) {
				useHours.put(key, useHours.get(key) + useTime);
			} else {
				useHours.put(key, useTime);
			}
			entranceHours.remove(key);
		}
		
		SortedSet<String> carSet = new TreeSet<>();
		Map<String, Integer> totalFee = new HashMap<>();
        
		for(String key : useHours.keySet()) {
			useTime = useHours.get(key);
			
			if(useTime <= basicHour) {
				totalFee.put(key, basicFee);
			} else {
				int fee = basicFee + (int)Math.ceil((float)(useTime-basicHour)/unitTime) * unitFee;
				totalFee.put(key, fee);
			}
			
			carSet.add(key);
		}
		
		int idx = 0;
		int[] answer = new int[carSet.size()];
		for(String s : carSet) {
			answer[idx++] = totalFee.get(s);
		}
		
		
        return answer;
    }
}
