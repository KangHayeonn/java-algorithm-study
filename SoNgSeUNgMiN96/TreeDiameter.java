import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Child{  //자식의 번호와 간선
        int child, w;

        public Child(int child, int w) {
            this.child = child;
            this.w = w;
        }
    }
  
    static class Tree{  //각 트리의 노드(네이밍 실수)

        ArrayList<Child> childs;
        int parent;

        public Tree(int parent) {
            this.parent = parent;
            childs = new ArrayList<>();
        }
    }

    static int max=Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N= Integer.parseInt(br.readLine()),a,b,c;
        if(N==1) {
            System.out.println(0);
            return;
        }
        Tree[] trees = new Tree[N+1];
        
        
        for (int i = 1; i <N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if(trees[a]==null){ //root일 경우
                trees[a] = new Tree(0); //자신의 노드를 생성
            }
            trees[a].childs.add(new Child(b,c));    //자식을 추가.
            trees[b] = new Tree(a);     //자식은 나를 부모로 만든다.
        }
        getMaxRadius(trees,1);
        System.out.println(max);
    }

    private static int getMaxRadius(Tree[] trees, int i) {
        int childCount = trees[i].childs.size(), second,first;
        if(childCount>=2) {   //자식이 둘 이상?
            PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
            for (int j = 0; j < childCount; j++) {  //자식들의 w계산
                 pq.add(getMaxRadius(trees,trees[i].childs.get(j).child)+trees[i].childs.get(j).w); //해당자식의 값 더하기 지금 길이
            }
            first = pq.poll();
            second = pq.poll();
            max = Math.max(first+second,max);   //두개의 가장 긴 반지름으로 지름 갱신
            return first;   //자신을 포함한 긴 반지름 리턴
        }else if(childCount==1){
            int temp= getMaxRadius(trees,trees[i].childs.get(0).child)+trees[i].childs.get(0).w ; //반지름 리턴
            max = Math.max(temp,max); //자신과 리프를 이은 지름을 기준으로 갱신
            return temp;
        }else return 0; //자식이 없음.
    }

}
