import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ9077 {
     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         StringTokenizer st;
         char[][] map;
         int test_case = Integer.parseInt(br.readLine());

         for (int testNum = 1; testNum <= test_case; testNum++) {
             int N = Integer.parseInt(br.readLine());
             map = new char[10001][10001];
             int a,b, max=0;
             for (int i = 0; i < N; i++) {
                 st = new StringTokenizer(br.readLine());
                 a = Integer.parseInt(st.nextToken());
                 b = Integer.parseInt(st.nextToken());
                 for(int j=a-10;j<=a;j++){
                         for(int k=b-10;k<=b;k++) {
                             if (j < 0 || k < 0) continue;
                             max = Math.max(max,++map[j][k]);
                         }
                 }
             }
             bw.write(max+"\n");
         }
         bw.flush();
         bw.close();
     }
}
