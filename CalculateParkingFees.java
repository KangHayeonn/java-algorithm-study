import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer;
        HashMap<String,Integer> result = new HashMap<>();   //누적시간.
        HashMap<String,Integer> parking = new HashMap<>();      //단위시간
        StringTokenizer st;
        ArrayList<String> keyArray = new ArrayList<>();
        String[] timeSp;
        String number;
        int in,out, time;
        for(String record: records){    //주차장에 차를 넣고 뺀다.
            st = new StringTokenizer(record);
            timeSp = st.nextToken().split(":");
            number = st.nextToken();
            
            if(parking.containsKey(number)){    //차가 있으면 출차
                
                in = parking.get(number);
                out = Integer.parseInt(timeSp[0])*60+Integer.parseInt(timeSp[1]);
                if(result.containsKey(number)){ //이미 왔던차라면 누적으로 더함.
                    result.put(number,out-in+result.get(number));
                }else{      //처음 정산된 차는 방금 시간 넣기, KeyArray에 키 추가(추후 정렬)
                    keyArray.add(number);
                    result.put(number,out-in);
                }
                parking.remove(number);
            }else{      //차가 없으면 주차.
                time = Integer.parseInt(timeSp[0])*60+Integer.parseInt(timeSp[1]); 
                parking.put(number,time);
            }
        }
        
        for(String key: parking.keySet()){  //아직 주차장에 남은차량을 정산한다.
            in = parking.get(key);
            out = 23*60+59;
            if(result.containsKey(key)){
                result.put(key,out-in+result.get(key));
            }else{
                keyArray.add(key);
                result.put(key,out-in);
            }
        }
        
        Collections.sort(keyArray,(s1,s2)->s1.compareTo(s2));       //차량번호기준 정렬
        answer = new int[keyArray.size()];
        for(int i=0;i<keyArray.size();i++){     //정렬된 차량을 정산한다.
            answer[i] = getCost(fees,result.get(keyArray.get(i)));
        }
        
        return answer;
    }
    public int getCost(int[] fees, int minutes){        //정산함수
        if(fees[0]>=minutes){//기본요금
            return fees[1];
        }
        return ((int)Math.ceil((minutes-fees[0])/(double)fees[2]))*fees[3]+fees[1];
    }
}
