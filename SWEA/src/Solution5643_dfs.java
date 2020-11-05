import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5643_dfs {

	static int N, M, adj[][];
	static int gtCnt, ltCnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine()); // 학생 수 : 정점 수
			M = Integer.parseInt(br.readLine()); // 관계 수 : 간선 수

			adj = new int[N + 1][N + 1];
			int i, j;
			for (int m = 1; m <= M; m++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				i = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				
				adj[i][j] = 1;
			}

			int res = 0;
			for (int k = 1; k <= N; k++) {
				
				gtCnt = ltCnt = 0;
				
				gtDFS(k, new boolean[N + 1]);
				ltDFS(k, new boolean[N + 1]);
				
				if(gtCnt + ltCnt == N - 1) res++;
			}
			
			System.out.println("#" + tc + " " + res);
		}

	}
	/**
	 * 자신보다 큰 학생을 따라 탐색
	 * @param n 현재 기준이 되는 학생번호
	 * @param visited 현재까지 확인한 친구 체크
	 */
	private static void gtDFS(int n, boolean[] visited) { 

		visited[n] = true;
		
		for (int i = 1; i <= N; i++) {
			// 나보다 크고 확인하지 않은 치구
			if(adj[n][i] == 1 && !visited[i]) {
				gtCnt++;
				gtDFS(i, visited);
			}
		}

	}

	/**
	 * 자신보다 작은 학생을 따라 탐색
	 * @param n 현재 기준이 되는 학생번호
	 * @param visited 현재까지 확인한 친구 체크
	 */
	private static void ltDFS(int n, boolean[] visited) { 

		visited[n] = true;
		
		for (int i = 1; i <= N; i++) {
			// 나보다 작고 확인하지 않은 친구
			if(adj[i][n] == 1 && !visited[i]) {
				ltCnt++;
				ltDFS(i, visited);
			}
		}

	}
}
