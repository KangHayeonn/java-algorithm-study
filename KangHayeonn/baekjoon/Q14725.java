// 개미굴 (백준 골드3)
import java.io.*;
import java.util.*;

public class Main {
	static BufferedWriter bw;
	public static class Node {
		Map<String, Node> childNodes = new TreeMap<>();
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Node rootNode = new Node();
		String s;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			Node curr = rootNode;
			
			for(int j=0; j<K; j++) {
				s = st.nextToken();
				
				if(!curr.childNodes.containsKey(s)) curr.childNodes.put(s, new Node());
				
				curr = curr.childNodes.get(s);
			}
		}
		
		print(rootNode, "");
		bw.flush();
		bw.close();
	}
	public static void print(Node node, String bar) throws IOException {
		Object[] key = node.childNodes.keySet().toArray();
		
		for(Object o : key) {
			bw.write(bar+o+"\n");
			print(node.childNodes.get(o), bar+"--");
		}
	}
}
