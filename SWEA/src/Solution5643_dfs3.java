import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution5643_dfs3 {

	static int N, M, adj[][];
	static int[] gtCnt, ltCnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine()); // 학생 수 : 정점 수
			M = Integer.parseInt(br.readLine()); // 관계 수 : 간선 수

			adj = new int[N + 1][N + 1];
			// idx번 학생보다 키가 큰 친구의 수와 작은 친구의 수를 각각 저장
			gtCnt = new int[N + 1];
			ltCnt = new int[N + 1];
			
			int i, j;
			for (int m = 1; m <= M; m++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				i = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				
				adj[i][j] = 1;
			}

			int res = 0;
			for (int k = 1; k <= N; k++) {
				dfs(k, k, new boolean[N + 1]);
			}
			// 자신보다 크거나 작은 친구들이 N-1 명이라면
			for (int k = 1; k <= N; k++) {
				if(gtCnt[k] + ltCnt[k] == N - 1) res++;
			}
			
			System.out.println("#" + tc + " " + res);
		}

	}

	/**
	 * src보다 큰 학생을 따라 탐색하며 src 자신이 누구보다 크고/작으지 판단
	 * @param src 출발 학생 번호
	 * @param cur 현재 학생 번호
	 * @param visited 방문 체크
	 */
	private static void dfs(int src, int cur, boolean[] visited) { 

		visited[cur] = true; // src < cur
		
		for (int i = 1; i <= N; i++) {
			// 나보다 크고 확인하지 않은 친구
			if(adj[cur][i] == 1 && !visited[i]) { // src < cur < i
				gtCnt[src]++;
				ltCnt[i]++;
				dfs(src, i, visited);
			}
		}

	}
	
}
