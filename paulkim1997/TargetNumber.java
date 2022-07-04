class Solution {
    static int[] selected;
    static boolean[] visited;
    static int answer; //정답 배열 함수에 들고 다니기 귀찮아서 만든 전겨 변수
    static int targetN; //타겟 넘버를 함수에 들고 다니기 귀찮아서 만든 전역 변수
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        //초기화 코드
        visited = new boolean[numbers.length];
        selected = new int[numbers.length];
        targetN = target;

        //주어진 수들 중에서, 1개 고를 때, 2개 고를 때, ... , N개 고를 때
        for(int i=1;i<numbers.length;i++) {
            rec_func(i, 0, numbers, 0);
        }
        
        return answer;
    }
    
    //n개의 수 중에서 k개의 수를 순서 상관 있이 골라서 음수로 바꾸면 타겟 넘버가 되는지 체크하는 함수
    public static void rec_func(int k, int selectCount, int[] numbers, int index) {
      
        //선택 다 했으면
        if(selectCount == k) {
            //총합 구하는 방법
            //numbers 총합 + selectedCount총합 X -2
            //ex) 4,1,2,1 에서 1,1 이 선택됐으면, 4+1+2+3 + (1+1) x 2
            int targetNumber = 0;
            for(int i=0;i<numbers.length;i++) {
                targetNumber += numbers[i];
            }
            for(int i=0;i<selected.length;i++) {
                targetNumber -= selected[i];
                targetNumber -= selected[i];
            }
            if(targetNumber ==targetN) {
                answer++;
            }
            return;
        }
        
        //배열 돌면서 순서 상관 있이 선택하는 재귀함수 부분 (N과M 문제랑 동일)
        for(int i=selectCount;i<numbers.length;i++) {
            if(!visited[i] && i >= index) { //순서 제어 추가
                selected[selectCount] = numbers[i];
                visited[i] = true;
                rec_func(k, selectCount+1, numbers, i);
                selected[selectCount] = 0;
                visited[i] = false;
            }
        }
    }
}
