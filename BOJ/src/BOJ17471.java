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
	static boolean isAreaA[];

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

		isAreaA = new boolean[N + 1];
		selectA(1);

		System.out.println(res == 987654321 ? -1 : res);

	}

	/**
	 * 부분집합으로 두 개의 선거구 나누기
	 * @param idx	구역 index
	 */
	private static void selectA(int idx) {
		// A선거구에 해당하는 구역을 모두 선택했다면
		if (idx == N + 1) {
			// 각 선거구에 포함된 구역이 모두 연결되었는지 확인
			if (check()) {
				// 연결되었다면
				int sumA = 0, sumB = 0;
				for (int i = 1; i <= N; i++) {
					if (isAreaA[i]) sumA += population[i];
					else sumB += population[i];
				}

				res = Math.min(res, Math.abs(sumA - sumB));
			}
			
			return;
		}

		// 이 구역을 A선거구로 선택
		isAreaA[idx] = true;
		selectA(idx + 1);
		// 이 구역을 A선거구로 미선택
		isAreaA[idx] = false;
		selectA(idx + 1);

	}

	/*
	 * 각 선거구에 포함된 구역이 모두 연결되었는지 확인
	 */
	private static boolean check() {
		boolean[] visited = new boolean[N + 1];

		int areaA = -1;
		// A선거구 구역에 포함된 한 구역을 찾기
		for (int i = 1; i <= N; i++) {
			if (isAreaA[i]) {
				areaA = i;
				break;
			}
		}
		int areaB = -1;
		// B선거구 구역에 포함된 한 구역을 찾기
		for (int i = 1; i <= N; i++) {
			if (!isAreaA[i]) {
				areaB = i;
				break;
			}
		}

		// 선거구에 포함된 구역이 없다면 false
		// 각 구역은 두 선거구 중 하나에 포함
		if (areaA == -1 || areaB == -1) return false;

		Queue<Integer> q = new LinkedList<>();
		// 먼저 A 선거구에 포함된 구역들이 모두 연결되었는지 확인
		q.add(areaA);
		visited[areaA] = true;
		while (!q.isEmpty()) {
			int now = q.poll();
			// 현재 구역과 연결된 구역들을 확인
			for (int i = 0; i < adj[now].size(); i++) {
				// 이미 확인한 곳이면 pass
				if (visited[adj[now].get(i)]) continue;
				// A 구역이 아니면 pass
				if (!isAreaA[adj[now].get(i)]) continue;
				visited[adj[now].get(i)] = true;
				q.add(adj[now].get(i));
			}
		}
		// B 선거구에 포함된 구역들이 모두 연결되었는지 확인
		q.add(areaB);
		visited[areaB] = true;
		while (!q.isEmpty()) {
			int now = q.poll();
			// 현재 구역과 연결된 구역들을 확인
			for (int i = 0; i < adj[now].size(); i++) {
				// 이미 확인한 곳이면 pass
				if (visited[adj[now].get(i)]) continue;
				// B 구역이 아니면 pass
				if (isAreaA[adj[now].get(i)]) continue;
				visited[adj[now].get(i)] = true;
				q.add(adj[now].get(i));
			}
		}

		// 한 곳이라도 연결되지 않았다면
		for (int i = 1; i <= N; i++) {
			if (!visited[i])
				return false;
		}

		return true;
	}

}
