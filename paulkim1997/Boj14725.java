import java.util.*;
import java.io.*;

public class Boj14725 {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        TrieNode currentNode = new TrieNode();

        for(int i=0;i<N;i++) {
            String input = br.readLine();
            currentNode.insert(input);
        }

        print(currentNode, 0);
    }
  
    //재귀로 Trie 자료구조의 문자열 출력
    public static void print(TrieNode currentNode, int depth) {
        for(String key : currentNode.children.keySet()) {
            for(int i=0;i<depth;i++) {
                System.out.print("--");
            }
            System.out.println(key);
            print(currentNode.children.get(key), depth+1);
        }
    }
    
    //TrieNode 클래스, TreeMap으로 자식 노드들이 자동으로 사전순 정렬
    public static class TrieNode {
        TreeMap<String, TrieNode> children = new TreeMap<>();

        public void insert(String input) {
            TrieNode currentNode = this;
            String[] splitter = input.split(" ");
            String[] foods = new String[splitter.length - 1];
            for(int i=0;i<splitter.length-1;i++) {
                foods[i] = splitter[i+1];
            }
            for(String food : foods) {
                currentNode.children.putIfAbsent(food, new TrieNode());
                currentNode = currentNode.children.get(food);
            }
        }
    }
}
