import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ18513 {

	static int N, K, MAX = 100000000;
	static int[] dx = { -1, 1 };
	static Set<Integer> visited;

	static class State {
		int x;
		int dist;

		public State(int x, int dist) {
			this.x = x;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 샘터
		K = Integer.parseInt(st.nextToken()); // 지을 집
		visited = new HashSet<>();

		// 샘터 위치를 먼저 queue에 넣어주자.
		Queue<State> q = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			q.add(new State(tmp, 0));
			visited.add(tmp);
		}
		// 샘터를 중심으로 집을 지어보자.
		System.out.println(bfs(q));
	}

	private static long bfs(Queue<State> q) {
		long res = 0;
		int homeCnt = 0;
		
		out:while(!q.isEmpty()) {
			// 현재 좌표
			State now = q.poll();
			// 주변 땅을 봐볼까?
			for (int d = 0; d < 2; d++) {
				int xx = now.x + dx[d];
				// 범위를 벗어날 경우 pass
				if(xx <= -MAX || xx >= MAX) continue;
				// 이미 집을 지은 곳이라면 pass
				if(visited.contains(xx)) continue;

				// 여기에 집을 지어보자
				homeCnt++;
				res += now.dist + 1;
				// 집을 다 지었으면 
				if(homeCnt == K) break out;
				
				visited.add(xx);
				q.add(new State(xx, now.dist + 1));
			}
		}
		
		return res;
	}

}