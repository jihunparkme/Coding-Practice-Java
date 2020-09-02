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

		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 기준점
		int res = 987654321;
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

	// 기준점과 d1,d2를 받아서 영역을 검사한다.
	public static int cal(int[][] map, int x, int y, int d1, int d2) {
		boolean[][] isArea5 = new boolean[N + 1][N + 1];
		// 5번영역을 isArea5에 true로 칠한다.
		// 해당 행의 마름모 가로 크기
		int size = 1;
		// 시작 열
		int nc = y;
		for (int r = 0; r <= d1 + d2 ; r++) {
			// 현재줄 현재행부터 size만큼 오른쪽으로 가며 true로 칠한다.
			for (int c = 0; c < size; c++) {
				isArea5[x + r][nc + c] = true;
			}
			// 아직 행이 d1만큼 내려오지 않았다면 출발열은 한칸 왼쪽으로 가고
			if (r < d1) nc--;
			else nc++;
			// d1보다 더 많이 내려왔다면 출발열을 한칸 오른쪽으로 보낸다.
			if (r < d1 && r < d2)
				// 마름모가 커지는 중이면 size += 2
				size += 2;
			else if (r >= d1 && r >= d2)
				// 마름모가 작아지는 중이면 size -= 2
				size -= 2;
		}

		int[] sum = new int[6]; // 1~5번 총합을 저장할 배열
		// 모든 맵을 검사하면서
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				
				// 5번영역이 아니면서 각 1,2,3,4번 영역에 맞는 조건이라면 자신의 위치에 더한다.
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
		
		// 정렬해서 젤큰거에서 젤 작은거 빼서 리턴
		Arrays.sort(sum);
		return sum[5] - sum[1];
	}

}
