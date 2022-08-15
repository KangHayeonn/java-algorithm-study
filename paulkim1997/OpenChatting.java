import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        int commandCnt = 0;
        Map<String, String> nickNameInfo = new HashMap<>();

        for(int i = 0; i< record.length;i++) {
            String currentRecord = record[i];
            StringTokenizer st = new StringTokenizer(currentRecord);
            String command = st.nextToken();

            //닉네임 정보 끝까지 저장
            if(!"Leave".equals(command)) {
                String uid = st.nextToken();
                String nickname = st.nextToken();
                nickNameInfo.put(uid, nickname);
            }

            if(!"Change".equals(command))
                commandCnt++;
        }

        String[] answer = new String[commandCnt];
        int index = 0;

        for(int i=0;i< record.length;i++) {
            String currentRecord = record[i];
            StringTokenizer st = new StringTokenizer(currentRecord);
            String command = st.nextToken();

            if("Enter".equals(command)){
                String uid = st.nextToken();
                String msg = nickNameInfo.get(uid) + "님이 들어왔습니다.";
                answer[index] = msg;
                index++;
            }

            if("Leave".equals(command)) {
                String uid = st.nextToken();
                String msg = nickNameInfo.get(uid) + "님이 나갔습니다.";
                answer[index] = msg;
                index++;
            }
        }

        return answer;
    }
}
