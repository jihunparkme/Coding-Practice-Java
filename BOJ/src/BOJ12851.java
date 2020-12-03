import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12851 {

	static int N, K, min;
	static int[] dx = { -1, 1, 2 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치

		if(N == K) System.out.println(0 + "\n" + 1);
		else {
			min = Integer.MAX_VALUE;
			int cnt = process();
			
			System.out.println(min + "\n" + cnt);
		}
	}

	private static int process() {

		int cnt = 0;
		Queue<Subin> q = new LinkedList<>();
		int[] visited = new int[100001];
		
		Arrays.fill(visited, Integer.MAX_VALUE);
		q.add(new Subin(N, 0));
		
		int nxt = 0;
		while(!q.isEmpty()) {
			
			Subin now = q.poll();
			
			for (int i = 0; i < 3; i++) {
				if(i == 2) nxt = now.x * dx[i];
				else nxt = now.x + dx[i];
				// 범위를 벗어나거나 이미 확인한 점
				if(nxt < 0 || nxt > 100000 || now.time + 1 > visited[nxt]) continue;
				
				// 동생을 찾았을 경우
				if(nxt == K && now.time + 1 <= min) {
					// 도착 시간과 같은 경우
					if(now.time + 1 == min) cnt++;
					// 도착 시간보다 작을 경우 cnt 초기화
					else {
						min = now.time + 1;
						cnt = 1;
					}
				}
				else {
					q.add(new Subin(nxt, now.time + 1));
					visited[nxt] = now.time + 1;
				}
			}
		}
		
		return cnt;
	}

	static class Subin {
		int x, time;

		public Subin(int x, int time) {
			this.x = x;
			this.time = time;
		}
		
	}
}
