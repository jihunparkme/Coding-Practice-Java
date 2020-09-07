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
				// 1이 적힌 칸
				if (map[i][j] == 1) oneCnt++;
			}
		}

		go(0, 0);

		System.out.println(res == 987654321 ? -1 : res);
	}

	/**
	 * 색종이를 붙일 수 있는 칸에 색종이를 붙여보자.
	 * @param idx	2차원 배열 index
	 * @param paperCnt	현재까지 사용한 색종이의 수
	 */
	private static void go(int idx, int paperCnt) {
		// 색종이를 최소로 사용한 경우보다 많이 사용한 경우 return
		if (res <= paperCnt) return;
		// 모두 탐색이 끝났을 때
		if (idx == N * N) {
			// 모든 1을 못 덮었다면
			if (oneCnt != 0) return;
			// 모든 1을 덮었다면
			// 사용한 색종이의 최소 개수 갱신
			res = Math.min(res, paperCnt);
			return;
		}

		int r = idx / N;
		int c = idx % N;

		// 색종이를 붙일수 있는 칸이면
		if (map[r][c] == 1) {
			// 다섯 종류의 색종이를 붙여보자
			for (int p = 5; p >= 1; p--) {
				// 해당 크기 색종이를 모두 사용했으면 pass
				if (paperState[p] == 0) continue;
				// 해당 크기 색종이로 붙일 수 있나 확인
				if (check(r, c, p)) {
					// 붙일 수 있다면 붙이고(oneCnt 감소)
					setPaper(r, c, p, true);
					paperState[p]--;
					
					// 다음 위치로
					go(idx + 1, paperCnt + 1);
					
					// 돌아오면 다시 스티꺼 떼어내기(oneCnt 증가)
					setPaper(r, c, p, false);
					paperState[p]++;
				}
			}
		} else {
			// 색종이를 붙일 수 없는 경우
			go(idx + 1, paperCnt);
		}

	}

	/**
	 * 실제 색종이를 붙이는 함수
	 * @param r	행 좌표
	 * @param c	열 좌표
	 * @param p	색종이 크기
	 * @param isPut	색종이를 붙일지, 떼어낼지
	 */
	private static void setPaper(int r, int c, int p, boolean isPut) {

		for (int i = 0; i < p; i++) {
			for (int j = 0; j < p; j++) {
				// 스티커를 붙일 경우
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

	/**
	 * 해당 색종이로 현재 칸에 붙일 수 있는지 확인
	 * @param r	행 좌표
	 * @param c	열 좌표
	 * @param p	색종이 크기
	 */
	private static boolean check(int r, int c, int p) {

		for (int i = 0; i < p; i++) {
			for (int j = 0; j < p; j++) {
				// 범위를 벗어날 경우
				if (r + i >= N || c + j >= N) return false;
				// 붙일 자리에 1이 없다면 붙일 수 없음
				if (map[r + i][c + j] != 1) return false;
			}
		}

		return true;
	}

}