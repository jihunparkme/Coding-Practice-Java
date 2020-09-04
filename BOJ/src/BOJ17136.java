import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17136 {

	static int N = 10, oneCnt, map[][];
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
				if (map[i][j] == 1) oneCnt++;
			}
		}

		go(0, 0);

		System.out.println(res == 987654321 ? (oneCnt == 0 ? 0 : -1) : res);
	}

	private static void go(int idx, int paperCnt) {
		// 구해진 값보다 색종이를 더 많이 사용했을 경우 return
		if (res <= paperCnt) return;
		// 모두 탐색이 끝났을 때
		if (idx == N * N) {
			// 모든 1을 못 덮었다면
			if (oneCnt != 0) return;
			// 모든 1을 덮었다면
			// 색종이의 최소 개수 갱신
			res = Math.min(res, paperCnt);
			return;
		}

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
					setPaper(r, c, p, true);
					paperState[p]--;

					// 다음 위치로
					go(idx + 1, paperCnt + 1);

					// 돌아오면 다시 스티꺼 떼어내기((oneCnt 증가)
					setPaper(r, c, p, false);
					paperState[p]++;
				}
			}
		} else {
			// 색종이를 붙일 수 없는 경우
			go(idx + 1, paperCnt);
		}

	}

	private static void setPaper(int r, int c, int p, boolean isPut) {

		for (int i = 0; i < p; i++) {
			for (int j = 0; j < p; j++) {
				// 스티커를 떼어낼 용도라면
				if (isPut) {
					// 색종이를 붙이고
					map[r + i][c + j] = 0;
					// 전체 1 개수 감소
					oneCnt--;
				} else {
					// 색종이를 떼어내고
					map[r + i][c + j] = 1;
					// 전체 1 개수 다시 증가
					oneCnt++;
				}
			}
		}

	}

	private static boolean check(int r, int c, int p) {

		for (int i = 0; i < p; i++) {
			for (int j = 0; j < p; j++) {
				// 범위를 벗어날 경우
				if (r + i >= N || c + j >= N)
					return false;
				// 붙일 자리에 1이 없다면 붙일 수 없음
				if (map[r + i][c + j] != 1)
					return false;
			}
		}

		return true;
	}

}