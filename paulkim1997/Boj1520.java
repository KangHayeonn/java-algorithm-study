import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static int[][] board, dp;
    static int answer;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        dp = new int[N][M];

        //입력
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //dp 배열 -1로 초기화
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                dp[i][j] = -1;
            }
        }

        dfs(0,0);
        System.out.println(dp[0][0]);
    }

    public static int dfs(int i, int j) {
        //도착 지점 도달
        if(i==N-1 && j==M-1) {
            return 1;
        }

        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        dp[i][j] = 0;
        for(int dir=0;dir<4;dir++) {
            int nx = i + dx[dir];
            int ny = j + dy[dir];
            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if(board[nx][ny] < board[i][j]) {
                dp[i][j] += dfs(nx, ny);
            }
        }
        return dp[i][j];
    }
}
