import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
      
        //Key: 차번호, Information 객체: (입차 시간, 누적 주차 시간)
        Map<String, Information> info = new HashMap<>();

        for(String s : records) {
            String[] splitter = s.split(" ");
            //입차 정보
            String time = splitter[0];
            String carNo = splitter[1];
            String command = splitter[2];
          
            //입차
            if("IN".equals(command)) {
                //기존에 입차한 이력 있는 차량은 입차 시간과 기존 주차 시간 기록
                if(info.containsKey(carNo)) {
                    int pastParkedTime = info.get(carNo).getParkedTime();
                    Information parkingInfo = new Information(time, pastParkedTime);
                    info.put(carNo, parkingInfo);
                } else{
                    //기존에 입차한 기록 없는 차는 입차 시간과 0분 기록
                    Information inAndParkedTime = new Information(time, 0);
                    info.put(carNo, inAndParkedTime);
                }
            }

            //출차
            if("OUT".equals(command)) {
                //입차 기록 가져옴
                Information inAndParkedTime = info.get(carNo);
                //주차 시간 누적합
                int currentParkedTime = inAndParkedTime.getParkedTime();
                //입차 시간 -> 분으로 변환 (시간 x 60 + 분)
                String inTime = inAndParkedTime.getInTime();
                String [] timeSplitter = inTime.split(":");
                String inTimeHH = timeSplitter[0];
                String inTimeMM = timeSplitter[1];
                int inTimeInMin = Integer.parseInt(inTimeHH)*60 + Integer.parseInt(inTimeMM);
                
                //출차 시간 -> 분으로 변환 (시간 x 60 + 분)
                timeSplitter = time.split(":");
                String outTimeHH = timeSplitter[0];
                String outTimeMM = timeSplitter[1];
                int outTimeInMin = Integer.parseInt(outTimeHH)*60 + Integer.parseInt(outTimeMM);
                
                //주차 시간 = 출차 시간 - 입차 시간
                int parkedTime = outTimeInMin - inTimeInMin;
                
                //기존 주차 시간과 같이 합 저장
                Information calcTimeAndFee = new Information("",currentParkedTime + parkedTime);
                info.put(carNo, calcTimeAndFee);
            }
        }

        //미출차 차량 23:59분까지 요금 계산
        for(Map.Entry<String, Information> m : info.entrySet()) {
            String carNum = m.getKey();
            Information finalInfo = m.getValue();
            //입차 시간이 "" 이 아니면, 입차하고 나간적 없는 차량
            if(finalInfo.getInTime() != "") {
                //23:59 = 1439분으로 주차시간 계산해서 동일하게 저장
                int outTimeInMin = 1439;
                String [] timeSplitter = finalInfo.getInTime().split(":");
                String inTimeHH = timeSplitter[0];
                String inTimeMM = timeSplitter[1];
                int inTimeInMin = Integer.parseInt(inTimeHH)*60 + Integer.parseInt(inTimeMM);
                int parkedTime = outTimeInMin - inTimeInMin;
                Information calcTimeAndFee = new Information("", finalInfo.getParkedTime() + parkedTime);
                info.put(carNum, calcTimeAndFee);
            }
        }

        answer = new int[info.size()];

        //차 번호 순 정렬
        Object[] mapKey = info.keySet().toArray();
        Arrays.sort(mapKey);
      
        //answer 배열에 넣을 index 지정
        int index = 0;     
        
        //차 번호 순으로 정렬한 키 배열 돌면서, info Map에서 꺼내서 요금 계산
        for(Object keyVal : mapKey) {
            Information finalInfo = info.get(keyVal);
            int finalFee = 0;
            //기본 시간 초과 시
            if(finalInfo.getParkedTime() > fees[0]) {
                int banOlLim = (int)Math.ceil((double) (finalInfo.getParkedTime()-fees[0])/fees[2]);
                finalFee = fees[1] + banOlLim*fees[3];
            } else { //기본 시간 초과 안하면 기본 시간 금액
                finalFee = fees[1];
            }
            answer[index] = finalFee;
            index++;
        }

        return answer;
    }
    
    //입차 시간, 누적 주차 시간 저장하는 클래스
    public static class Information {
        int parkedTime;
        String inTime;

        public Information(String inTime, int parkedTime) {
            this.inTime = inTime;
            this.parkedTime = parkedTime;
        }

        public int getParkedTime() {
            return this.parkedTime;
        }

        public String getInTime() {
            return this.inTime;
        }
    }
}
