import java.util.*;

class Solution {
    static int[][] board;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};
        answer = new int[queries.length];

        board = new int[rows+1][columns+1];
        int num = 1;
        for(int i=1;i<= rows;i++) {
            for(int j=1;j<= columns;j++) {
                board[i][j] = num;
                num++;
            }
        }

        for(int i=0;i< queries.length;i++) {
            int[] query = queries[i];
            answer[i] = rotateArray(query);
        }

        return answer;
    }
    
    public int rotateArray(int[] query) {
        int leftTopX = query[0];
        int leftTopY = query[1];
        int rightBottomX = query[2];
        int rightBottomY = query[3];
        int rightTopX = query[0];
        int rightTopY = query[3];
        int leftBottomX = query[2];
        int leftBottomY = query[1];

        int minNum = Integer.MAX_VALUE;

        int[] top = new int[rightTopY - leftTopY];
        int[] right = new int[rightBottomX - rightTopX];
        int[] bottom = new int[rightBottomY - leftBottomY];
        int[] left = new int[leftBottomX - leftTopX];

        //각 부분 배열 저장
        int index = 0;
        for(int i=leftTopY;i<rightTopY;i++) {
            top[index] = board[rightTopX][i];
            minNum = Math.min(top[index], minNum);
            index++;
        }

        index = 0;
        for(int i=rightTopX;i<rightBottomX;i++) {
            right[index] = board[i][rightTopY];
            minNum = Math.min(right[index], minNum);
            index++;
        }

        index = 0;
        for(int i=leftBottomY+1;i<=rightBottomY;i++) {
            bottom[index] = board[leftBottomX][i];
            minNum = Math.min(bottom[index], minNum);
            index++;
        }

        index = 0;
        for(int i=leftTopX+1;i<=leftBottomX;i++) {
            left[index] = board[i][leftTopY];
            minNum = Math.min(left[index], minNum);
            index++;
        }

        //대체하기
        index = 0;
        for(int i=leftTopY+1;i<=rightTopY;i++) {
            board[rightTopX][i] = top[index];
            index++;
        }

        index = 0;
        for(int i=rightTopX+1;i<=rightBottomX;i++) {
            board[i][rightTopY] = right[index];
            index++;
        }

        index = 0;
        for(int i=leftBottomY;i<rightBottomY;i++) {
            board[leftBottomX][i] = bottom[index];
            index++;
        }

        index = 0;
        for(int i=rightTopX;i<leftBottomX;i++) {
            board[i][leftTopY] = left[index];
            index++;
        }

        return minNum;
    }
}
