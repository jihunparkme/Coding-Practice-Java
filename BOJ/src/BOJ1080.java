import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1080 {

	static int N, M, A[][], B[][];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N][M];
		B = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				A[i][j] = tmp.charAt(j) - '0';
			}
		}
		
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				B[i][j] = tmp.charAt(j) - '0';
			}
		}
		
		int res = go();
		System.out.println(isSame() ? res : -1);
	}

	private static int go() {

		int cnt = 0;
		
		for (int i = 0; i <= N - 3; i++) {
			for (int j = 0; j <= M - 3; j++) {
				if(A[i][j] != B[i][j]) {
					turn(i, j);
					cnt++;
				}
			}
		}
		
		return cnt;
	}

	private static boolean isSame() {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(A[i][j] != B[i][j]) return false;
			}
		}
		
		return true;
	}

	private static void turn(int x, int y) {
		
		for (int i = x; i < x + 3; i++) {
			for (int j = y; j < y + 3; j++) {
				// 0 -> 1, 1 -> 0
				A[i][j] ^= 1;
			}
		}
		
	}

}
