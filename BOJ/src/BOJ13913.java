import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13913 {

	static int N, K, path[];
	static int[] dx = { -1, 1, 2 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치
		path = new int[100001]; // 이동 경로
		
		// 수빈이와 동생이 같은 위치
		if(N == K) { 
			sb.append(0 + "\n" + N);
		} 
		// 수빈이가 동생보다 뒤에 있을 경우
		else if (N > K) {
			sb.append(N - K + "\n");
			for (int i = N; i >= K; i--) {
				sb.append(i + " ");
			}
		} 
		// 수빈이가 동생보다 앞에 있을 경우
		else {
			sb.append(process() + "\n");
									
			List<Integer> res = new LinkedList<>();
			// 동생 위치부터 수빈이 위치까지 되돌아가자.
			res.add(K);
			while(path[K] != 0) {
				res.add(path[K]);
				K = path[K];
			}
			// 수빈이의 위치가 0일 경우 예외처리
			if(N == 0) res.add(0);
			
			for (int i = res.size() - 1; i >= 0; i--)
				sb.append(res.get(i) + " ");
		}
		
		System.out.println(sb);
	}

	private static int process() {

		Queue<Subin> q = new LinkedList<>();
		int[] dist = new int[100001];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		q.add(new Subin(N, 0));
		dist[N] = 0;
		
		int nxt = 0;
		while(!q.isEmpty()) {
			Subin now = q.poll();
			
			for (int i = 0; i < 3; i++) {
				if(i == 2) nxt = now.x * dx[i];
				else nxt = now.x + dx[i];
				
				// 범위를 벗어나거나 멀리 돌아올 경우
				if(nxt < 0 || nxt > 100000 || now.time + 1 >= dist[nxt]) continue;
				path[nxt] = now.x;
				dist[nxt] = dist[now.x] + 1;
				q.add(new Subin(nxt, now.time + 1));
			}
		}
		
		return dist[K];
	}

	static class Subin {
		int x, time;

		public Subin(int x, int time) {
			this.x = x;
			this.time = time;
		}
		
	}
}
