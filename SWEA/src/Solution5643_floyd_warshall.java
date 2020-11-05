import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

// 5643. [Professional] 키 순서
// 백준 2458번
public class Solution5643_floyd_warshall {
	static int N;
	static int M;
	static int[][] maps;
	static boolean[] v;
	static int result = 0;
	static int[] manCount;
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("input_5643.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			maps = new int[N + 1][N + 1];
			for (int i = 0; i <= N; i++) {
				Arrays.fill(maps[i], INF);
			}
			for (int i = 0; i < M; i++) {
				maps[sc.nextInt()][sc.nextInt()] = 1; // 행의 학생이 열의 학생보다 키가 작다.
			}
			// 서로 관련 있는 사람들을 알수있는것 변경하기(프로이드 와샬 알고리즘)
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (maps[i][j] > maps[i][k] + maps[k][j]) {
							maps[i][j] = maps[i][k] + maps[k][j];
						}
					}
				}
			}

			int[] isKnows = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (maps[i][j] != INF) {
						isKnows[i]++;
						isKnows[j]++;
					}
				}
			}
			result = 0;
			for (int i = 1; i <= N; i++) {
				if (isKnows[i] == N - 1) {
					result++;
				}
			}

			System.out.println("#" + t + " " + result);

		}
	}
}