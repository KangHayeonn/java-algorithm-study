import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Node{
        int child,w;

        public Node(int child, int w) {
            this.child = child;
            this.w = w;
        }
    }

    static class Parent{
        int parent, min, max;

        public Parent(int parent, int min, int max) {
            this.parent = parent;
            this.min = min;
            this.max = max;
        }
    }


     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         StringTokenizer st;
         int N = Integer.parseInt(br.readLine()), M;        //노드 N, 명령 M
         int[] depth = new int[N + 1];
         boolean[] visited = new boolean[N + 1];
         int K = getK(N);
         Parent[][] parents = new Parent[N + 1][K+1];   //부모 정보와, max, min의 정보를 담고있다.
         Queue<Integer> bfs = new LinkedList<>();

         ArrayList<Node>[] tree = new ArrayList[N + 1];
         int A, B, C, curr, diff;
         Node next;

         for (int i = 1; i < tree.length; i++) tree[i] = new ArrayList<>(); //연결관계 초기화.

         for (int i = 1; i < N; i++) {      //N-1개 돈다.

             st = new StringTokenizer(br.readLine());
             A = Integer.parseInt(st.nextToken());
             B = Integer.parseInt(st.nextToken());
             C = Integer.parseInt(st.nextToken());

             tree[A].add(new Node(B, C));
             tree[B].add(new Node(A, C));
         }

         //parent 초기화
         for (int i = 0; i < N+1; i++) {
             for (int j = 0; j <= K; j++) {
                parents[i][j] = new Parent(0,Integer.MAX_VALUE,Integer.MIN_VALUE);
             }
         }

         bfs.add(1);    //1번 노드를 루트로 지정.
         visited[1] = true;

         while (bfs.size()>0){  //트리를 모두 그림.
             curr = bfs.poll();
             ArrayList<Node> currArray = tree[curr];

             for (int i = 0; i < currArray.size(); i++) {
                 next = currArray.get(i); //다음 노드
                 if(!visited[next.child]){  //방문한적이 없다면.
                     depth[next.child] = depth[curr] + 1;
                     visited[next.child] = true;
                     bfs.add(next.child);
                     parents[next.child][0].parent = curr;
                     parents[next.child][0].max = next.w;       //최대 최소도. 현재 부모와의 간선 거리만큼 초기화.
                     parents[next.child][0].min = next.w;
                 }
             }
         }

         //parent 값 초기화.
         for (int i = 1; i <=K; i++) {
             for (int j = 1; j <=N; j++) {
                 parents[j][i].max = Math.max(parents[j][i - 1].max, parents[parents[j][i - 1].parent][i - 1].max);    //부모테이블 max갱신.
                 parents[j][i].min = Math.min(parents[j][i - 1].min, parents[parents[j][i - 1].parent][i - 1].min);    //부모테이블 min갱신.
                 parents[j][i].parent = parents[parents[j][i-1].parent][i-1].parent;    //부모테이블 갱신.
             }
         }


         M = Integer.parseInt(br.readLine());
         for (int i = 0; i < M; i++) {
             st = new StringTokenizer(br.readLine());
             A = Integer.parseInt(st.nextToken());
             B = Integer.parseInt(st.nextToken());
             Parent pA = new Parent(parents[A][0].parent,Integer.MAX_VALUE,Integer.MIN_VALUE), pB = new Parent(parents[B][0].parent,Integer.MAX_VALUE,Integer.MIN_VALUE);
             //얘네는 최대 최소만 저장. parent 자체는 신경 쓰지 않음.

             if(depth[A]<depth[B]){     //무조건. B가 작은값이 오도록 바꿔준다.
                 int temp =B;
                 B = A;
                 A = temp;
             }
             diff = depth[A] - depth[B];

             for (int j = 0; diff>0; j++) {
                 if((diff&1)==1){   //값의 차이가 2진수로 해당한다면.
                     pA.min = Math.min(pA.min,parents[A][j].min);
                     pA.max = Math.max(pA.max,parents[A][j].max);
                     A = parents[A][j].parent;      //A 이동.
                 }
                 diff>>=1;
             }

             //깊이는 통일 되었음.

             while (A!=B) {
                 int j = 0;

                 for (j = 0; j < K; j++) {      //K점프 까지 탐색
                     if (parents[A][j].parent == parents[B][j].parent) break;
                 }

                 if (j != 0) {
                     j--;
                 }

                 pA.min = Math.min(pA.min, parents[A][j].min);
                 pA.max = Math.max(pA.max, parents[A][j].max);
                 pB.min = Math.min(pB.min, parents[B][j].min);
                 pB.max = Math.max(pB.max, parents[B][j].max);
                 A = parents[A][j].parent;
                 B = parents[B][j].parent;
             }
             bw.write(Math.min(pA.min,pB.min)+" "+Math.max(pA.max,pB.max)+"\n");
         }
         bw.flush();
    }

    public static int getK(int n){
        int i=0, temp=1;
        while (temp<n){
            temp*=2;
            i++;
        }
        return i-1;
    }
}
