import java.util.*;
import java.io.*;

public class Boj14891 {
    static char[][] arr;
    static int k;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //톱니바퀴 정보 저장하는 배열 + 톱니바퀴 정보 저장
        arr = new char[4][8];
        for(int i=0;i<4;i++) {
            String input = br.readLine();
            for(int j=0;j<input.length();j++) {
                arr[i][j] = input.charAt(j);
            }
        }

        //회전 횟수 k
        k = Integer.parseInt(br.readLine());

        //회전 정보, number = 톱니바퀴 번호, direction = 1: 시계 방향, -1: 반시계 방향
        for(int i=0;i<k;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            roll(number, direction);
        }

        int answer = 0;

        //총합 구하는 부분
        for(int i=0;i<4;i++) {
            switch (i) {
                case 0:
                    if(arr[0][0]== '1') answer += 1;
                    break;
                case 1:
                    if(arr[1][0]== '1') answer += 2;
                    break;
                case 2:
                    if(arr[2][0]== '1') answer += 4;
                    break;
                case 3:
                    if(arr[3][0]== '1') answer += 8;
                    break;
                default:
                    break;
            }
        }

        System.out.println(answer);
    }

    public static void roll(int number, int direction) {
        switch (number) {
            //1번 톱니바퀴 회전
            case 1:
                //오른쪽의 2번 톱니바퀴 확인
                if(!checkRight(number)) {
                    //오른쪽의 3번 톱니바퀴 확인
                    if(!checkRight(number+1)) {
                        //마지막 4번 확인
                        if(!checkRight(number+2)) {
                            //4번 회전
                            rollWithDirections(number+3, direction * -1);
                        }
                        //3번 회전
                        rollWithDirections(number+2, direction);
                    }
                    //2번 회전
                    rollWithDirections(number+1, direction * -1);
                }
                //1번 회전
                rollWithDirections(number, direction);
                break;
            //2번 톱니바퀴 회전
            case 2:
                //왼쪽의 1번 확인 (단독!)
                if(!checkLeft(number)) {
                    rollWithDirections(number - 1, direction * -1);
                }

                //오른쪽의 3번 확인
                if(!checkRight(number)) {
                    //오른쪽의 4번 확인
                    if(!checkRight(number+1)) {
                        //4번 회전
                        rollWithDirections(number+2, direction);
                    }
                    //3번 회전
                    rollWithDirections(number +1, direction * -1);
                }
                //2번 회전
                rollWithDirections(number, direction);
                break;
            //3번 톱니바퀴 회전
            case 3:
                //오른쪽의 4번 확인 (단독!)
                if(!checkRight(number)) {
                    rollWithDirections(number+1, direction * -1);
                }

                //왼쪽의 2번 확인
                if(!checkLeft(number)) {
                    //왼쪽의 1번 확인
                    if(!checkLeft(number-1)) {
                        //1번 회전
                        rollWithDirections(number-2, direction);
                    }
                    //2번 회전
                    rollWithDirections(number-1, direction*-1);
                }
                //3번 회전
                rollWithDirections(number, direction);
                break;
            //4번 톱니바퀴 회전
            case 4:
                //왼쪽의 3번 확인
                if (!checkLeft(number)){
                    //왼쪽의 2번 확인
                    if(!checkLeft(number-1)) {
                        //왼쪽의 1번 확인
                        if(!checkLeft(number-2)) {
                            //1번 회전
                            rollWithDirections(number-3, direction*-1);
                        }
                        //2번 회전
                        rollWithDirections(number-2, direction);
                    }
                    //3번 회전
                    rollWithDirections(number-1, direction*-1);
                }
                //4번 회전
                rollWithDirections(number, direction);
                break;
            default:
                break;
        }
    }

    //왼쪽과 톱니바퀴와 맞물린 극이 같은 극인지?
    public static boolean checkLeft(int number) {
        if(arr[number-1][6] == arr[number-2][2]) {
            return true;
        }
        return false;
    }

    //오른쪽과 톱니바퀴와 맞물린 극이 같은 극인지?
    public static boolean checkRight(int number) {
        if(arr[number-1][2] == arr[number][6]) {
            return true;
        }
        return false;
    }

    //톱니바퀴 번호, 방향을 가지고 해당 톱니바퀴 회전 시킴
    public static void rollWithDirections(int number, int direction) {
        //반시계 방향 회전
        if(direction == -1) {
            char temp = arr[number-1][0];
            for(int i=1;i<8;i++) {
                arr[number-1][i-1] = arr[number-1][i];
            }
            arr[number-1][7] = temp;
        } else { //시계 방향 회전
            char temp = arr[number-1][7];
            for(int i=7;i>0;i--) {
                arr[number-1][i] = arr[number-1][i-1];
            }
            arr[number-1][0] = temp;
        }
    }
}
