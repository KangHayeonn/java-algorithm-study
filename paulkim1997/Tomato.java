import java.util.*;
import java.io.*;

public class Main {
    //좌우, 위 보려는 dx, dy, dz
    static int[] dx = {1,-1,0,0,0,0};
    static int[] dy = {0,0,1,-1,0,0};
    static int[] dz = {0,0,0,0,1,-1};
    static int N,M,H;
    static int[][][] board;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H][M][N];
        
        //정보 입력 
        for(int i=0;i<H;i++) {
            for(int j=0;j<M;j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=0;k<N;k++) {
                    board[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
        int maxNum = 1;
        Queue<Integer> q = new LinkedList<>();

        //BFS 시작 하기전에 토마토 위치 정보 모두 추가
        for(int i=0;i<H;i++) {
            for(int j=0;j<M;j++) {
                for(int k=0;k<N;k++) {
                    if(board[i][j][k] == 1) {
                        q.add(i); q.add(j); q.add(k);
                    }
                }
            }
        }
        //저장된 토마토 위치들로 BFS 시작
        while(!q.isEmpty()) {
            int curZ = q.poll();
            int curY = q.poll();
            int curX = q.poll();

            for(int dir=0;dir<6;dir++) {
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];
                int nz = curZ + dz[dir];

                if(nx < 0 || ny < 0 || nz < 0 || nx >= N || ny >= M || nz >= H) continue;

                if(board[nz][ny][nx] == 0) {
                    q.add(nz);
                    q.add(ny);
                    q.add(nx);
                    board[nz][ny][nx] = board[curZ][curY][curX] + 1;
                    //탐색하면서 제일 큰 숫자 오래걸리는거니 maxNum 업데이트
                    maxNum = Math.max(maxNum, board[nz][ny][nx]);
                }
            }
        }
        //마무리 탐색하면서 안익은 토마토 있으면 -1 출력
        for(int i=0;i<H;i++) {
            for(int j=0;j<M;j++) {
                for(int k=0;k<N;k++) {
                    if(board[i][j][k] == 0) {
                        System.out.println("-1");
                        return;
                    }
                }
            }
        }
        System.out.println(maxNum-1);
    }
}
