import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16174 {

	static int N, map[][];
	static int[] dr = { 0, 1 }, dc = { 1, 0 };
	static boolean visited[][];
	static class State {
		int r, c;

		public State(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(dfs());
	}

	private static String dfs() {

		Queue<State> q = new LinkedList<>();
		visited[0][0] = true;
		q.add(new State(0, 0));

		while (!q.isEmpty()) {
			State now = q.poll();
			// 도착지에 도달!
			if(now.r == N -1 && now.c == N - 1) {
				return "HaruHaru";
			}
			int dist = map[now.r][now.c];
					
			for (int d = 0; d < 2; d++) {
				int rr = now.r + dr[d] * dist; 
				int cc = now.c + dc[d] * dist;
				// 범위를 벗어나면 pass
				if(rr < 0 || cc < 0 || rr >= N || cc >= N) continue;
				// 이미 도착한 곳이라면
				if(visited[rr][cc]) continue;
				
				visited[rr][cc] = true;
				q.add(new State(rr, cc));
			}
		}

		return "Hing";
	}

}
