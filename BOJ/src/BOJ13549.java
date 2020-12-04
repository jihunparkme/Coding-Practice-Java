import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13549 {

	static int N, K;
	static int[] dx = { -1, 1, 2 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치

		if(N == K) System.out.println(0);
		else {
			System.out.println(process());
		}
	}

	private static int process() {

		Queue<Subin> q = new LinkedList<>();
		int[] dist = new int[100001];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		q.add(new Subin(N, 0));
		dist[N] = 0;
		
		int nxt = 0, flag = 0;;
		while(!q.isEmpty()) {
			Subin now = q.poll();
			
			for (int i = 0; i < 3; i++) {
				if(i == 2) {
					nxt = now.x * dx[i];
					flag = 0;
				}
				else {
					nxt = now.x + dx[i];
					flag = 1;
				}
				
				// 범위를 벗어나거나 이미 확인한 점
				if(nxt < 0 || nxt > 100000 || now.time + flag > dist[nxt]) continue;
				if(dist[nxt] > now.time + flag) {
					dist[nxt] = dist[now.x] + flag;
					q.add(new Subin(nxt, now.time + flag));
				}
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
