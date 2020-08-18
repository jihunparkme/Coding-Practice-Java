import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1215 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;
		int size = 8;
		for (int tc = 1; tc <= T; tc++) {

			int res = 0;
			int N = Integer.parseInt(br.readLine());
			int halfN = N / 2;
			int map[][] = new int[size][size];

			for (int i = 0; i < size; i++) {
				String str = br.readLine();
				for (int j = 0; j < size; j++) {
					map[i][j] = str.charAt(j);
				}
			}

			for (int x = 0; x < size; x++) {
				for (int y = 0; y <= size - N; y++) {
					boolean h = true; // 가로
					boolean v = true; // 세로
					for (int k = 0; k < halfN; k++) {
						if (map[x][y + k] != map[x][y + N - k - 1])
							h = false;
						if (map[y + k][x] != map[y + N - k - 1][x])
							v = false;
					}
					if (h) res++;
					if (v) res++;
				}
			}

			System.out.println("#" + tc + " " + res);
		}

	}

}
