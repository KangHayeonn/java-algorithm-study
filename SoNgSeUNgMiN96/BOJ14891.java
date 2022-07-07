import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14891 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] gears = new char [4][8];   //N극은 0 S극은 1
        String temp;
        StringTokenizer st;

        int []gearTop = new int[4]; //각 톱니의 탑부분을 저장한다. 좌측은 +6 우측은 +2를 하여 인덱싱함.
        int N, gearNum, direc,answer=0 ; //회전 수 N, 톱니번호 gearNum, 톱니방향 direc, 정답 answer
        for (int i = 0; i < 4; i++) {
            temp = br.readLine();
            gears[i] = temp.toCharArray();
        }
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            gearNum = Integer.parseInt(st.nextToken())-1;
            direc = Integer.parseInt(st.nextToken());
            spin(gears,gearNum,direc,gearTop,0);
        }
        int idx =0;
        for (int i = 1; i <= 8; i*=2) {
            answer += i*(gears[idx][(gearTop[idx])%8]-'0');
            idx++;
        }
        System.out.println(answer);
    }
    public static void spin(char[][] gears, int gearNum, int direc, int[] gearTop,int code){    //code 0 = 좌우가능! 1 = 좌만가능 2 = 우만가능
        if(gearNum<0||gearNum>3){   //범위 오바!
            return;
        }
        if(code!=1&&gearNum<3&&gears[gearNum][(gearTop[gearNum]+2)%8]!=gears[gearNum+1][(gearTop[gearNum+1]+6)%8]){ //3번 기어가 아닐경우 우측 검사!
            spin(gears,gearNum+1,-direc,gearTop,2);
        }
        if(code!=2&&gearNum>0&&gears[gearNum][(gearTop[gearNum]+6)%8]!=gears[gearNum-1][(gearTop[gearNum-1]+2)%8]){     //왼쪽검사
            spin(gears,gearNum-1,-direc,gearTop,1);
        }
        if(direc==1){       //시계방향 회전일경우!
            gearTop[gearNum] += 7; //시계방향 회전!
        }else{
            gearTop[gearNum]++; //반시계방향 회전!
        }
    }
}
