class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int lr,lc,rr,rc, temp, min, idx=0;  
        int[][] map = new int[rows][columns];
        
        for(int i=0;i<rows;i++){            
            for(int j=0;j<columns;j++){
                map[i][j] = columns*i + j+ 1;
            }    
        }
        
        for(int[] querie : queries){    //쿼리 돌림.
            
            lr = querie[0]-1;
            lc = querie[1]-1;
            rr = querie[2]-1;
            rc = querie[3]-1;
            min = map[lr][lc];
            temp = min;
            
            for(int i=lr;i<rr;i++){     //좌측선
                map[i][lc] = map[i+1][lc]; 
                min = Math.min(min, map[i][lc]);
            }
            
            for(int i=lc;i<rc;i++){     //하단선
                map[rr][i] =map[rr][i+1]; 
                min = Math.min(min, map[rr][i]);
            }
            
            for(int i= rr; i>lr;i--){ //우측선
                map[i][rc] = map[i-1][rc];
                min = Math.min(min,map[i][rc]);
            }    
            
            for(int i=rc;i>lc;i--){//상단선
                map[lr][i] = map[lr][i-1];
                min = Math.min(min, map[lr][i]);
            }
            
            if(lc<rc){      //없어진거 
                map[lr][lc+1] = temp;
            }
            answer[idx++]=min;
        }
        
        return answer;
    }
}
