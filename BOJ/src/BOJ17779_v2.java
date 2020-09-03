import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17779_v2 {

	static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int res = 987654321;
		/**
		 * 1. 기준에 맞는 x, y, d1, d2 선정. 
		 */
		// 기준점
		for (int x = 1; x < N; x++) {
			for (int y = 1; y < N; y++) {

				// 경계의 길이
				for (int d1 = 1; d1 < N; d1++) {
					for (int d2 = 1; d2 < N; d2++) {

						// 범위 필터링 1 ≤ x < x+d1+d2 ≤ N
						if (x + d1 + d2 <= N) {
							// 범위 필터링 1 ≤ y-d1 < y < y+d2 ≤ N
							if (1 <= y - d1 && y + d2 <= N) {
								// 시도해볼 수 있는 상태면 5번 선거구를 만들어보자.
								res = Math.min(res, cal(map, x, y, d1, d2));
							}
						}
					}
				}
			}
		}

		System.out.println(res);
	}

	// 기준점과 d1,d2를 받아서 영역을 검사
	public static int cal(int[][] map, int x, int y, int d1, int d2) {
		/**
		 * 2. 경계선을 만들면서 5번 선거구 구역 체크 
		 */
		// 5번 선거구 구역을 표시하기 위한 배열 준비
		boolean[][] isArea5 = new boolean[N + 1][N + 1];
		// 해당 행의 마름모 가로 크기는 1부터 시작(1, 3, 5, ,.. 3, 1)
		int size = 1;
		// 시작 열
		int nc = y;
		// 마름모의 높이는 (x + 0) ~ (x + d1 +d2)
		for (int r = 0; r <= d1 + d2 ; r++) {
			// 현재 행에서 size만큼 오른쪽으로 가며 5번 선거구 구역을 true로 체크
			for (int c = 0; c < size; c++) {
				isArea5[x + r][nc + c] = true;
			}
			// 시작열 정해주기
			// 행이 d1만큼 내려오지 않았다면 시작열은 한칸 왼쪽으로
			if (r < d1) nc--;
			// d1보다 더 많이 내려왔다면 시작열을 한칸 오른쪽으로
			else nc++;
			
			// 마름모가 커지는 중이면 
			if (r < d1 && r < d2) size += 2;
			// 마름모가 작아지는 중이면 
			else if (r >= d1 && r >= d2) size -= 2;
		}

		/**
		 * 3. 모든 격자를 탐색하면서 각 선거구 기준에 포함되는 곳에 해당하는 구역의 인구수 누적 
		 */
		int[] sum = new int[6]; // 1~5번 선거구의 인구 총합을 저장할 배열
		// 모든 맵을 검사하면서
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				// 5번 선거구 영역일 경우
				if(isArea5[r][c]) sum[5] += map[r][c];
				// 1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
				else if(1 <= r && r < x+d1 && 1 <= c && c <= y) sum[1] += map[r][c];
				// 2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
				else if(1 <= r && r <= x+d2 && y < c && c <= N) sum[2] += map[r][c];
				// 3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
				else if(x+d1 <= r && r <= N && 1 <= c && c < y-d1+d2) sum[3] += map[r][c];
				// 4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
				else sum[4] += map[r][c];
			}
		}
		
		/**
		 * 4. 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이를 구한 후 최솟값을 갱신
		 */
		// 정렬 후 
		Arrays.sort(sum);
		// 가장 많은 선거구와 가장 적은 선거구의 차이 구하기
		return sum[5] - sum[1];
	}

}
