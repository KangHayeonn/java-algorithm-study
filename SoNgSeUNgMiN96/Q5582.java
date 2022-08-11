import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Q5582 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        String A, B;

        A = br.readLine();
        B = br.readLine();
        int aLength = A.length(), bLength = B.length();


        boolean[][] dp = new boolean[bLength][aLength];

        for (int i = 0; i < bLength; i++) {
            for (int j = 0; j < aLength; j++) {
                if(A.charAt(j)==B.charAt(i)) {
                    dp[i][j] = true;
                }
            }
        }


        for (int i = 0; i < aLength + bLength -1; i++) {
            int count =0;
            int j =0, k=0;

            if(i<bLength){
                j = bLength - i - 1;
                k = 0;
            }else{
                k = i - bLength + 1;
                j = 0;
            }

            while (k<aLength&&j<bLength){
                count =0;
                while (j<bLength&&k<aLength&&dp[j][k]){
                    count++;
                    j++;
                    k++;
                }
                answer = Math.max(answer,count);
                j++;
                k++;
            }
        }
        System.out.println(answer);
    }
}
