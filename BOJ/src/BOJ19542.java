import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ19542 {

	static int N, S, D, dist[];
	static ArrayList<Integer> relation[];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 노드 개수
		S = Integer.parseInt(st.nextToken()); // 위치
		D = Integer.parseInt(st.nextToken()); // 힘
		relation = new ArrayList[N + 1];
		dist = new int[N + 1];
		for (int i = 0; i <= N; i++)
			relation[i] = new ArrayList<Integer>();

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			relation[a].add(b);
			relation[b].add(a);
		}

		dfs(S, 0);

		int res = 0;
		for (int i = 1; i <= N; i++) {
			if (i != S && dist[i] >= D) {
				res += 1;
			}
		}

		System.out.println(res * 2);
	}

	// 리프노드부터 몇 개의 자식을 가지고 있는지 cnt
	public static int dfs(int node, int pnt) {
		// 리프 노드는 자식이 없으므로 0
		if (node != S && relation[node].size() == 1) {
			return dist[node] = 0;
		}
		for (int i = 0; i < relation[node].size(); i++) {
			int next = relation[node].get(i);
			// 내 부모 노드면 pass
			if (next == pnt) continue;
			// 가장 길게 달린 자식노드 수를 저장.
			dist[node] = Math.max(dist[node], dfs(next, node) + 1);
		}
		return dist[node];
	}
}
