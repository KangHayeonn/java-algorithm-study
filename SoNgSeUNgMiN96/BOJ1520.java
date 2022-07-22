import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1520 {
    static int answer = 0,N,M;
    static int[] dx = {0,0,-1,1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] dp = new int[N][M];    //
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;      //모두  -1로 초기화.
            }
        }
        System.out.println(dfs(map, dp, 0, 0 ));
    }

    private static int dfs(int[][] map, int[][] dp, int i, int j) {
        if(dp[i][j]!=-1){    //이미 여기서부터 경로가 정해져있다면. <-이미 가본 블럭이라 똑같이 가 볼 필요 없음.     경로가 없는 길이라도 0으로 초기화되어있을것.
            answer += dp[i][j];
            return dp[i][j];
        }
        if(i==N-1&&j==M-1) {
            answer++;
            return 1;        //이경로는 한가지 방법이 있음.
        }

        int way = 0, tempX, tempY;
        for (int k = 0; k < 4; k++) {
            tempX = i + dx[k];
            tempY = j + dy[k];
            if (tempX >= 0 && tempX < N && tempY >= 0 && tempY < M) {   //범위 안이라면
                if (map[i][j] > map[tempX][tempY]) {    //현재가 내리막 길인가?
                    way += dfs(map, dp, tempX, tempY);
                }
            }
        }
        return dp[i][j] = way;
    }
}
