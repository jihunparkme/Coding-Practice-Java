import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5014_bfs_func {

	static int F, S, G, U, D, res, dist[];
	static int max = 1000001;
	;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		F = Integer.parseInt(st.nextToken()); // 최고 층
		S = Integer.parseInt(st.nextToken()); // 강호 위치
		G = Integer.parseInt(st.nextToken()); // 회사 위치
		U = Integer.parseInt(st.nextToken()); // up
		D = Integer.parseInt(st.nextToken()); // down

		dist = new int[max];
		Arrays.fill(dist, -1);
		
		res = bfs();
		if(res == -1) System.out.println("use the stairs");
		else System.out.println(res);
	}
	
	public static int bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		int[] button = {U, -D};
		// 강호의 위치에서 시작
		dist[S] = 0;
		q.add(S);
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for (int i = 0; i < 2; i++) {
				int next = now + button[i];
				// 회사 위치 도착
				if(next == G) 
					return dist[now] + 1;
				// 불필요하게 더 올라가거나 내려가거나 이미 온 곳이면 pass
				if(next > F || next < 1 || dist[next] >= 0) continue;
				// 도착하지 않았을 경우
				dist[next] = dist[now] + 1;
				q.add(next);
			}
		}
		
		return -1;
	}
}