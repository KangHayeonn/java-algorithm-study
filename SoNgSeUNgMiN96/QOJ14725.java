import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class QOJ14725 {

    static class Trie{
        int depth=0;    //층수 저장.
        String self = "";   //문자열 저장.
        HashMap<String, Trie> hashMap = new HashMap<>();     //자식 매치의 해시맵
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        Trie root = new Trie();   //루트 생성
        Trie cursor;     //Try cursor 생성.

        String fees = "";
        int K;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            cursor = root;

            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            for (int j = 0; j < K; j++) {
                fees = st.nextToken();

                if(!cursor.hashMap.containsKey(fees)){   //없으면 추가
                    cursor.hashMap.put(fees, new Trie());
                }

                cursor.hashMap.get(fees).depth = cursor.depth + 1;      //깊이를 하나씩 늘려줌.
                cursor = cursor.hashMap.get(fees);      //커서를 다음 포인트로 이동.
                cursor.self = fees;     //
            }
        }

        dfs(root, bw);
        bw.flush();
    }

    private static void dfs(Trie cursor, BufferedWriter bw) throws Exception{
        int idx =0;
        String[] keySet = new String[cursor.hashMap.size()];  //루트를 기준으로 keySet 만큼만 순회를 돌린다.

        for(String key : cursor.hashMap.keySet()){
            keySet[idx++] = key;
        }

        Arrays.sort(keySet);

        for(String key : keySet){
            for (int i = 0; i < cursor.depth; i++) {
                bw.write("--");
            }
            bw.write(key+"\n");
            dfs(cursor.hashMap.get(key),bw);
        }
    }
}
