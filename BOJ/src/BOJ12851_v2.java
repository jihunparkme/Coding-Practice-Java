import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12851_v2 {

	static int N, K;
	static int[] dx = { -1, 1, 2 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치

		if(N == K) System.out.println(0 + "\n" + 1);
		else {
			int[] res = process();
			
			System.out.println(res[0] + "\n" + res[1]);
		}
	}

	private static int[] process() {

		Queue<Subin> q = new LinkedList<>();
		int[] dist = new int[100001];
		int[] cnt = new int[100001];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		q.add(new Subin(N, 0));
		dist[N] = 0;
		cnt[N] = 1;
		
		int nxt = 0;
		while(!q.isEmpty()) {
			Subin now = q.poll();
			
			for (int i = 0; i < 3; i++) {
				if(i == 2) nxt = now.x * dx[i];
				else nxt = now.x + dx[i];
				// 범위를 벗어나거나 이미 확인한 점
				if(nxt < 0 || nxt > 100000 || now.time + 1 > dist[nxt]) continue;
				// 동생을 찾았을 경우
				if(dist[nxt] == now.time + 1) {
					cnt[nxt] += cnt[now.x];
				} else {
					dist[nxt] = dist[now.x] + 1;
					cnt[nxt] += cnt[now.x];
					q.add(new Subin(nxt, now.time + 1));
				}
			}
		}
		
		int[] res = { dist[K], cnt[K] };
		
		return res;
	}

	static class Subin {
		int x, time;

		public Subin(int x, int time) {
			this.x = x;
			this.time = time;
		}
		
	}
}
