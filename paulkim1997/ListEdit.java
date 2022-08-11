import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        Stack<Integer> stack = new Stack<>();
        int size = n;
        

        for(int i=0;i<cmd.length;i++) {
            String input = cmd[i];

            StringTokenizer st = new StringTokenizer(input);
            String command = st.nextToken();

            if("U".equals(command)) {
                k -= Integer.parseInt(st.nextToken());
            } else if("D".equals(command)) {
                k += Integer.parseInt(st.nextToken());
            } else if("C".equals(command)) {
                stack.push(k);
                size--;
                //마지막 행 삭제하면
                if(k==size) k--;
            } else if("Z".equals(command)) {
                int removeNum = stack.pop();
                //k 이전 것이면 k한칸 밀림
                if(k >= removeNum) k++;
                size++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<size;i++) sb.append("O");
        
        while(!stack.empty()) {
            int removedNum = stack.pop();
            sb.insert(removedNum, 'X');
        }
        answer = sb.toString();
        return answer;
    }
}
