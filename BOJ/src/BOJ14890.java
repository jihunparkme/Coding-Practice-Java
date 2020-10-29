import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890 {

	static int N, X, map[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(process());
	}

	private static int process() {

		int res = 0;

		for (int i = 0; i < N; i++) {
			int[] horizontal = new int[N];
			int[] vertical = new int[N];

			for (int j = 0; j < N; j++) {
				horizontal[j] = map[i][j];
				vertical[j] = map[j][i];
			}

			if (check(horizontal)) res++;
			if (check(vertical)) res++;
		}

		return res;
	}

	private static boolean check(int[] line) {

		int cntX = 0;
		int now = 0;
		int h = line[0];

		for (int i = 0; i < N; i++) {
			// 이전 지형과 높이가 같다면 pass
			if (h == line[i]) continue;

			// 높이의 차가 1보다 크다면 불가능
			if (Math.abs(h - line[i]) >= 2) return false;
			// 이전 지형보다 1 더 낮다면
			else if (h - line[i] == 1) {
				// 경사로를 높을 수 있는지 확인
				now = line[i];
				for (int ck = 0; ck < X; ck++) {
					// 경사로를 놓을 수 없다면
					if (i + ck >= N || now != line[i + ck]) return false;
					// 경사로를 놓았다고 체크
					line[i+ ck] = 10;
				}
				// 경사로 사용
				cntX++;
				// +X+1 부터 다시 확인
				i = i + X - 1;
				if (i >= N) break;
			}
			// 이전 지형보다 1 더 높다면
			else if (h - line[i] == -1) {
				// 경사로를 놓을 수 있는지 확인
				now = line[i];
				for (int ck = 1; ck <= X; ck++) {
					// 경사로를 높을 수 없다면
					if (i - ck < 0 || now - 1 != line[i - ck]) return false;
					// 경사로를 놓았다고 체크
					line[i - ck] = 10;
				}
				// 경사로 사용
				cntX++;
			}

			h = now;
		}

		return true;
	}

}
