import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17471 {

	static int N, res = 987654321, population[];
	static ArrayList<Integer>[] adj;
	static boolean selected[];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		population = new int[N + 1];
		adj = new ArrayList[N + 1];
		// 인구 정보 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			adj[i] = new ArrayList<>();
		}
		// 인접한 구역 정보 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for (int j = 0; j < size; j++) {
				adj[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		selected = new boolean[N + 1];
		selectA(1);

		System.out.println(res == 987654321 ? -1 : res);

	}

	private static void selectA(int idx) {
		// 모두 선택했다면
		if (idx == N + 1) {
			// 두 선거구 모두 연결되었는지 확인
			if (check()) {
				// 연결되었다면
				int sumA = 0, sumB = 0;
				for (int i = 1; i <= N; i++) {
					if (selected[i]) sumA += population[i];
					else sumB += population[i];
				}

				res = Math.min(res, Math.abs(sumA - sumB));
			}
			
			return;
		}

		// 이 구역을 A선거구로 선택
		selected[idx] = true;
		selectA(idx + 1);
		// 이 구역을 A선거구로 미선택
		selected[idx] = false;
		selectA(idx + 1);

	}

	private static boolean check() {
		boolean[] visited2 = new boolean[N + 1];

		int areaA = -1;
		// A선거구 구역 한 곳을 찾고
		for (int i = 1; i <= N; i++) {
			if (selected[i]) {
				areaA = i;
				break;
			}
		}
		int areaB = -1;
		// B선거구 구역 한 곳을 찾고
		for (int i = 1; i <= N; i++) {
			if (!selected[i]) {
				areaB = i;
				break;
			}
		}

		// 선거구에 포함된 구역이 없다면
		if (areaA == -1 || areaB == -1)
			return false;

		Queue<Integer> q = new LinkedList<>();
		// 먼저 A 선거구에 포함된 구역들이 모두 연결되었는지 확인
		q.add(areaA);
		visited2[areaA] = true;
		while (!q.isEmpty()) {
			int now = q.poll();
			// 현재 구역과 연결된 구역들을 확인
			for (int i = 0; i < adj[now].size(); i++) {
				// 이미 확인한 곳이면 pass
				if (visited2[adj[now].get(i)]) continue;
				// A 구역이 아니면 pass
				if (!selected[adj[now].get(i)]) continue;
				visited2[adj[now].get(i)] = true;
				q.add(adj[now].get(i));
			}
		}
		// B 선거구에 포함된 구역들이 모두 연결되었는지 확인
		q.add(areaB);
		visited2[areaB] = true;
		while (!q.isEmpty()) {
			int now = q.poll();
			// 현재 구역과 연결된 구역들을 확인
			for (int i = 0; i < adj[now].size(); i++) {
				// 이미 확인한 곳이면 pass
				if (visited2[adj[now].get(i)])
					continue;
				// B 구역이 아니면 pass
				if (selected[adj[now].get(i)])
					continue;
				visited2[adj[now].get(i)] = true;
				q.add(adj[now].get(i));
			}
		}

		// 한 곳이라도 연결되지 않았다면
		for (int i = 1; i <= N; i++) {
			if (!visited2[i])
				return false;
		}

		return true;
	}

}
