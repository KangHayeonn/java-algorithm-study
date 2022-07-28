import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1647 {

    static class Load{
        int a,b,w;

        public Load(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }

    static int[] parent;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), max = Integer.MIN_VALUE, answer = 0;
        int a, b, c;
        parent = new int[N+1];
        for (int i = 0; i < parent.length; i++) parent[i] = i;

        PriorityQueue<Load> pq = new PriorityQueue<>((L1, L2) -> L1.w - L2.w);     //간선의 비용이 적은것부터 시작

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            pq.offer(new Load(a,b,c));
        }

        while (pq.size()>0){
            Load curr = pq.poll();
            int aParent = findParent(curr.a);
            int bParent = findParent(curr.b);

            if(aParent>bParent){    //a의 부모가 무조건 낮은 숫자로 만들어준다.
                int temp = aParent;
                aParent = bParent;
                bParent = temp;
            }
            if(aParent!=bParent){
                parent[bParent] = aParent;
                max = Math.max(max, curr.w);
                answer += curr.w;
            }
        }
        System.out.println(answer - max);
    }

    private static int findParent(int a) {
        if(parent[a]==a) return a;
        return parent[a] = findParent(parent[a]);
    }
}
