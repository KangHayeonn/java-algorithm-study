import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569 {
    static class Tomato{
        int z,x,y, depth;

        public Tomato(int z, int x, int y, int depth) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int[] dx = {1, -1, 0, 0, 0, 0}, dy = {0, 0, -1, 1, 0, 0}, dz = {0, 0, 0, 0, 1, -1};
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), H = Integer.parseInt(st.nextToken());
        int[][][] box = new int[H][M][N];
        int day=-1, tomatoCount=0;  //덜 익은 토마토를 세어준다.
        Queue<Tomato> bfs = new LinkedList<>();

        for (int i = 0; i < H; i++) {       //토마토 입력받기
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    if(box[i][j][k]==1){    //토마토 추가.
                        bfs.add(new Tomato(i,j,k,0));
                    }else if(box[i][j][k]==0){    //덜익은 토마토 세어주기.
                        tomatoCount++;
                    }
                }
            }
        }

        if(tomatoCount==0){  //익은 토마토가 하나도 없는 경우.
            System.out.println(0);
            return;
        }

        bfsLoop : while (bfs.size()>0){
            Tomato curr = bfs.poll();
            int tempx, tempy,tempz;

            for (int i = 0; i < 6; i++) {
                tempx = curr.x+ dx[i];
                tempy = curr.y+ dy[i];
                tempz = curr.z+ dz[i];
                if (tempx >= 0 && tempx < M && tempy < N && tempy >= 0 && tempz >= 0 && tempz < H) {
                    if(box[tempz][tempx][tempy]==0){        //해당 부분이 덜익은 토마토인경우
                        box[tempz][tempx][tempy] = 1;
                        bfs.add(new Tomato(tempz,tempx,tempy,curr.depth+1));
                        tomatoCount--;
                        if(tomatoCount==0){
                            day = curr.depth+1;
                            break bfsLoop;
                        }
                    }
                }
            }
        }
        System.out.println(day);
     }
}
