import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18513_v2 {

	static int N, K, MAX = 100000000;
	static long res;
	static int[] dx = { -1, 1 };
	static boolean[] visitedUp, visitedDown;

	static class State implements Comparable<State> {
		int x;
		int dist;

		public State(int x, int dist) {
			this.x = x;
			this.dist = dist;
		}

		@Override
		public int compareTo(State o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 샘터
		K = Integer.parseInt(st.nextToken()); // 지을 집
		visitedUp = new boolean[100000000];
		visitedDown = new boolean[100000000];

		// 샘터 위치를 먼저 queue에 넣어주자.
		PriorityQueue<State> q = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			q.add(new State(tmp, 0));
			if(tmp < 0) visitedDown[tmp * -1] = true;
			else visitedUp[tmp] = true;
		}

		bfs(q);

		System.out.println(res);
	}

	private static void bfs(Queue<State> q) {
		
		int homeCnt = 0;
		while(!q.isEmpty()) {
			State now = q.poll();
			
			for (int d = 0; d < 2; d++) {
				int xx = now.x + dx[d];
				// 범위를 벗어날 경우 pass
				if(xx <= -MAX || xx >= MAX) continue;
				// 이미 집을 지은 곳이라면 pass
				if(xx < 0) {
					if(visitedDown[xx * -1]) continue;
				} else {
					if(visitedUp[xx]) continue;
				}

				// 여기에 집을 지어보자
				homeCnt++;
				res += now.dist + 1;
				// 집을 다 지었으면 
				if(homeCnt == K) return;
				
				if(xx < 0) visitedDown[xx * -1] = true;
				else visitedUp[xx] = true;
				
				q.add(new State(xx, now.dist + 1));
			}
		}
		
	}

}