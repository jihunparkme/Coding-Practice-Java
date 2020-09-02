import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class BOJ5397 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			Stack<Character> store = new Stack<>();
			Deque<Character> q = new LinkedList<>(); 
			char[] L = br.readLine().toCharArray();
			
			for (int i = 0; i < L.length; i++) {
				// 왼쪽 키
				if(L[i] == '<') {
					if(!q.isEmpty())
						store.push(q.pollLast());
					continue;
				}
				if(L[i] == '>') {
					if(!store.isEmpty())
						q.add(store.pop());
					continue;
				}
				if(L[i] == '-') {
					q.pollLast();
					continue;
				}
				q.add(L[i]);
			}

			while(!store.isEmpty()) q.add(store.pop());
			
			while(!q.isEmpty()) {
				sb.append(q.pollFirst());
			}
			System.out.println(sb);
			sb.setLength(0);
		}
		
	}
}
