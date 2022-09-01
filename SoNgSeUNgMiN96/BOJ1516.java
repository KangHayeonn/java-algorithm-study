import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static class Node{
        int t;
        ArrayList<Integer> childs;
        public Node(int t, ArrayList<Integer> childs) {
            this.t = t;
            this.childs = childs;
        }
    }
     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         StringTokenizer st;


         int N = Integer.parseInt(br.readLine());
         Node[] nodes = new Node[N+1];
         int[] answer = new int[N+1];
         boolean[] visited = new boolean[N+1];



         for (int i = 1; i <= N; i++) {
             st = new StringTokenizer(br.readLine());
             int t = Integer.parseInt(st.nextToken());
             int child;
             nodes[i] = new Node(t, new ArrayList<>());

             while ((child = Integer.parseInt(st.nextToken()))!=-1){
                 nodes[i].childs.add(child);    //자식으로 추가
                 visited[child] = true;     //이미 꼭대기가 아님.
             }
         }
         for (int i = 1; i < visited.length; i++) {
             if (!visited[i]) {     //꼭대기만 검사
                dfs(nodes,i,answer);
             }
         }

         for (int i = 1; i < answer.length; i++) {
             bw.write(answer[i]+"\n");
         }
         bw.flush();
         bw.close();
     }

    private static int dfs(Node[] nodes, int curr, int[] answer) {

        int max = 0;
        if(answer[curr]!=0) return answer[curr];

        for(int i=0;i<nodes[curr].childs.size();i++){
            int child = nodes[curr].childs.get(i);
            int next = dfs(nodes,child, answer);        //자식들의 최댓값과 비교
            max = Math.max(next,max);
        }

        return answer[curr] =  max + nodes[curr].t;
    }
}
