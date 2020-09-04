import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17136_v2 {

	static int N = 10, map[][];
	// 색종이의 크기는 1×1, 2×2, 3×3, 4×4, 5×5로 총 다섯 종류, 각 종류의 색종이는 5개씩
	static int paperState[] = { 0, 5, 5, 5, 5, 5 };
	static int res = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		go(0, 0);

		System.out.println(res == 987654321 ? -1 : res);
	}

	private static void go(int idx, int paperCnt) {

		// 모두 탐색이 끝났을 때
		if (idx == N * N) {
			// 색종이의 최소 개수 갱신
			res = Math.min(res, paperCnt);
			return;
		} else if (res < paperCnt) {
			// 색종이를 최소로 사용한 경우보다 
			// 현재 더 많이 사용한 경우 더이상 확인할 필요가 없음
			return;
		}
		else {
			int r = idx / N;
			int c = idx % N;

			// 색종이를 붙일수 있는 칸이면
			if (map[r][c] == 1) {
				// 1~5까지의 색종이를 붙여볼 것이다.
				for (int p = 5; p >= 1; p--) {
					// 해당 크기 색종이를 모두 사용했으면 pass
					if (paperState[p] == 0)
						continue;
					// 해당 크기 색종이로 붙일 수 있나 확인
					if (check(r, c, p)) {

						// 붙일 수 있다면 붙이고(oneCnt 감소)
						setPaper(r, c, p, 0);
						paperState[p]--;

						// 다음 위치로
						go(idx + 1, paperCnt + 1);

						// 돌아오면 다시 스티꺼 떼어내기((oneCnt 증가)
						setPaper(r, c, p, 1);
						paperState[p]++;
					}
				}
			} else {
				// 색종이를 붙일 수 없는 경우
				go(idx + 1, paperCnt);
			}
		}
	}

	private static void setPaper(int r, int c, int p, int put) {

		for (int i = 0; i < p; i++) {
			for (int j = 0; j < p; j++) {
				// 스티커를 붙이거나 떼어내거나
				map[r + i][c + j] = put;
			}
		}

	}

	private static boolean check(int r, int c, int p) {

		// 범위를 벗어날 경우
		if (r + p >= N || c + p >= N) return false;
		
		for (int i = 0; i < p; i++) {
			for (int j = 0; j < p; j++) {
				// 붙일 자리에 1이 없다면 붙일 수 없음
				if (map[r + i][c + j] != 1) return false;
			}
		}

		return true;
	}

}