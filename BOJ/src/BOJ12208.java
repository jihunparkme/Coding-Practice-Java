import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12208 {

	static int N, map[][];
	static int dir;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			String d = st.nextToken();
			// right, up, left, down;
			if (d.equals("right"))
				dir = 0;
			else if (d.equals("up"))
				dir = 1;
			else if (d.equals("left"))
				dir = 2;
			else
				dir = 3;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			process();

			System.out.println("Case #" + tc + ":");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
	}

	private static void process() {

		for (int i = 0; i < dir; i++)
			rotate90();

		start();
		if (dir != 0) {
			for (int i = 0; i < 4 - dir; i++) {
				rotate90();
			}
		}
	}

	private static void start() {

		for (int i = 0; i < N; i++) {
			// 당기기
			poll(i);
			// 합치기
			for (int j = N - 1; j > 0; j--) {
				if (map[i][j] == map[i][j - 1]) {
					map[i][j] = map[i][j] * 2;
					map[i][j - 1] = 0;
				}
			}
			// 당기기
			poll(i);
		}

	}

	private static void poll(int i) {
		
		for (int j = N - 1; j > 0; j--) {
			if(map[i][j] == 0) {
				int idx = j - 1;
				while(idx > 0 && map[i][idx] == 0) {
					idx--;
				}
				map[i][j] = map[i][idx];
				map[i][idx] = 0;
			}
		}
		
	}

	private static void rotate90() {

		int[][] tmpMap = new int[N][N];
		// 배열 복사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
		// 회전
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[j][N - 1 - i] = tmpMap[i][j];
			}
		}
	}

}
