class Solution {
    static String[][] arr;
    static boolean[][] marker;
    static int M,N;
    static int answer;
    
    public int solution(int m, int n, String[] board) {
        answer = 0;
        N = n;
        M = m;

        arr = new String[m][n];

        //String 배열 -> String[][] 로 한 단어씩
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                arr[i][j] = String.valueOf(board[i].charAt(j));

            }
        }
        
        //1. 현재 터트릴 블록이 있으면
        //2. marker에다가 터트릴 블록 위치 마킹 (블록이 A~Z)
        //3. 마킹된 위치 dd로 삭제하면서 정답에 계속 추가
        //4. 밑으로 밀어주기
        while(findArea(m,n)) {
            marker = new boolean[m][n];
            mark();
            delete();
            pushDown();
        }
        System.out.println(answer);
        return answer;
    }

    //현재 상태에 깰 블록 존재하는지 확인
    public boolean findArea(int m, int n) {
        //0,0 ~ 5,5 까지 있으면 4,4까지 검사
        for(int i=0;i<m-1;i++) {
            for(int j=0;j<n-1;j++) {
                if(arr[i][j].equals("dd")) continue;
                if(check(i,j,arr[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    //현재 블록 기준 4개가 똑같은지 체크
    public boolean check(int a, int b, String current) {
        for(int i=0;i<2;i++) {
            for(int j=0;j<2;j++) {
                if(!arr[a+i][b+j].equals(current))
                    return false;
            }
        }
        return true;
    }

    //없앨 블록 마크
    public void mark() {
        for(int i=0;i<M-1;i++) {
            for(int j=0;j<N-1;j++) {
                //이미 마킹한 곳이면 스킵
                if(arr[i][j].equals("dd")) continue;
              
                if(check(i,j,arr[i][j])) {
                    for(int a=i;a<i+2;a++) {
                        for(int b=j;b<j+2;b++) {
                            marker[a][b] = true;
                        }
                    }
                }
            }
        }
    }

    //없앨 블록 dd로 변경
    public void delete() {
        for(int i=0;i<M;i++) {
            for(int j=0;j<N;j++) {
                if(marker[i][j] == true) {
                    arr[i][j] = "dd"; 
                    //정답 + 1
                    answer++;
                }
            }
        }
    }

    //블록 떨구기
    //밑에서부터 탐색하면서 dd면 무시하고 아니면 dd 아닐때까지 내리기
    public void pushDown() {
        for(int i=0;i<N;i++) {
            for(int j=M-2;j>=0;j--) {
                if("dd".equals(arr[j][i])) continue;

                while("dd".equals(arr[j+1][i])) {
                    arr[j+1][i] = arr[j][i];
                    arr[j][i] = "dd";
                    j++;
                    if(j==M-1) break;
                }
            }
        }
    }
}
