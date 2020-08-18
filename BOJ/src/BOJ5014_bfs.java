import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5014_bfs {

	static int F, S, G, U, D;
	static int max = 1000001;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		F = Integer.parseInt(st.nextToken()); // 최고 층
		S = Integer.parseInt(st.nextToken()); // 강호 위치
		G = Integer.parseInt(st.nextToken()); // 회사 위치
		U = Integer.parseInt(st.nextToken()); // up
		D = Integer.parseInt(st.nextToken()); // down

		int res = -1;
		int[] button = {U, -D};
		int[] dist = new int[max];
		// 방문 확인을 위해 -1 로 초기화
		Arrays.fill(dist, -1);
		Queue<Integer> q = new LinkedList<Integer>();
		// 강호의 위치에서 시작
		dist[S] = 0;
		q.add(S);
		while(!q.isEmpty()) {
			int now = q.poll();
			// 회사 층 도착 혹은
			// 시작층과 도착층이 같을 경우
			if(now == G) {
				res = dist[now];
				break;
			}
			for (int i = 0; i < 2; i++) {
				int next = now + button[i];
				// 최고층을 넘어가거나 1층보다 내려가거나 이미 방문한 층이면 pass
				if(next > F || next < 1 || dist[next] >= 0) continue;
				// 아직 층에 도착하지 않았을 경우
				dist[next] = dist[now] + 1;
				q.add(next);
			}
		}
		if(res == -1) System.out.println("use the stairs");
		else System.out.println(res);
	}
}