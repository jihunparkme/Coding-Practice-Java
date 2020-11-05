import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author iccack
 */

public class Solution5643_floyd_warshall {

	static int INF = 987654321;
	static int N, M, res = 0, adj[][], manCount[];
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			
			N = sc.nextInt();
			M = sc.nextInt();
			adj = new int[N + 1][N + 1];
			// 초기는 무한대
			for (int i = 0; i <= N; i++) {
				Arrays.fill(adj[i], INF);
			}
			// 행의 학생이 열의 학생보다 키가 작다.
			for (int i = 0; i < M; i++) {
				adj[sc.nextInt()][sc.nextInt()] = 1; 
			}
			// 서로 관련 있는 사람들을 알수있는 것 변경하기
			for (int k = 1; k <= N; k++) { // 경유지
				for (int i = 1; i <= N; i++) { // 출발지
					for (int j = 1; j <= N; j++) { // 도착지
						if (adj[i][j] > adj[i][k] + adj[k][j]) {
							adj[i][j] = adj[i][k] + adj[k][j];
						}
					}
				}
			}

			int[] isKnows = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (adj[i][j] != INF) {
						isKnows[i]++;
						isKnows[j]++;
					}
				}
			}
			
			res = 0;
			for (int i = 1; i <= N; i++) {
				if (isKnows[i] == N - 1) {
					res++;
				}
			}

			System.out.println("#" + t + " " + res);
		}
	}
}