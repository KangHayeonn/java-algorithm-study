import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2437 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()), answer = 0;
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if(answer>=arr[i]-1) answer += arr[i];
            else break;
        }
        System.out.println(answer+1);
    }
}
