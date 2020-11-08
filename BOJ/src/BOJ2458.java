import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2458 {

	static int N, M, taller[], shorter[];
	static ArrayList<Integer>[] adj;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		taller = new int[N + 1];
		shorter = new int[N + 1];
		adj = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj[a].add(b);
		}
		
		for (int i = 1; i <= N; i++) {
			process(i, i, new boolean[N + 1]);
		}
		
		int res = 0;
		for (int i = 1; i <= N; i++) {
			if(taller[i] + shorter[i] == N - 1) res++;
		}
		
		System.out.println(res);
	}

	private static void process(int start, int now, boolean[] visited) {
		
		visited[now] = true;
		
		for (Integer std : adj[now]) {
			if(visited[std]) continue;
			taller[start]++;
			shorter[std]++;
		
			process(start, std, visited);
		}
	}
}
