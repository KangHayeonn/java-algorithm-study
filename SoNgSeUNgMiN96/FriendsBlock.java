class Solution {
    static boolean[][] isDead;
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        int tempCounter;
        char[][] newBoard = new char[m][n];
        
        for(int i=0;i<m;i++){     //char로 변형
            newBoard[i] = board[i].toCharArray();
        }
        
        while(true){
            tempCounter=0;
            isDead = new boolean[m][n];
            for(int i=0;i<m-1;i++){     //검사
                for(int j=0;j<n-1;j++){
                    check(newBoard, i, j);
                }
            }
            for(int i=0;i<m;i++){     //검사
                for(int j=0;j<n;j++){
                    if(isDead[i][j]){
                        newBoard[i][j] = '0';
                        tempCounter++;
                    }
                }
            }
            
            for(int i=0;i<n;i++){       //열을 기준으로 위로 검사.
                for(int j=m-2;j>=0;j--){     //맨 아래+1블록부터 검사
                    if(newBoard[j][i]!='0'){        //0이 아닌 블록이라면
                        int tempJ = j+1;
                        if(newBoard[tempJ][i]!='0') continue;   //빈블록이아니면 취소
                        while(tempJ<m&&newBoard[tempJ][i]=='0'){
                            tempJ++;
                        }
                        newBoard[tempJ-1][i] = newBoard[j][i];      //블록 밀어주기.
                        newBoard[j][i] = '0';
                    }    
                }
            }
            
            answer += tempCounter;
            
            if(tempCounter==0) break;
        }
        
       
        
        return answer;
    }
    public static void check(char[][] board, int i, int j){
        if(board[i][j]!='0'&&board[i][j]==board[i+1][j]&&board[i+1][j]==board[i][j+1]&&board[i][j+1]==board[i+1][j+1]){
            isDead[i][j] = isDead[i+1][j] = isDead[i][j+1] = isDead[i+1][j+1] = true;
        }
    }
}
