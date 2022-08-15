import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static String seq1, seq2;
    static int[][] dp; //dp[i][j] = seq1 의 0 ~ i-1 , seq2 의 0 ~ j-1 에서 가장 긴 공통 수열의 길이

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        seq1 = br.readLine();
        seq2 = br.readLine();

        dp = new int[seq1.length()+1][seq2.length()+1];

        for(int i=0;i<seq1.length();i++) {
            for(int j=0;j<seq2.length();j++) {
                if(seq1.charAt(i) == seq2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                    answer = Math.max(dp[i+1][j+1], answer);
                }
            }
        }
        System.out.println(answer);
    }
}
