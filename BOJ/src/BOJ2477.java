import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2477 {

	static int K, side[];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());
		side = new int[6];

		int maxR, maxC;
		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			Integer.parseInt(st.nextToken());
			side[i] = Integer.parseInt(st.nextToken());
		}

		int max = 0, idx = 0;
		for (int i = 0; i < 6; i++) {
			if (max < side[i] * side[(i + 1) % 6]) {
				max = side[i] * side[(i + 1) % 6];
				idx = i;
			}
		}

		int small = side[(idx + 3) % 6] * side[(idx + 4) % 6];
		System.out.println((max - small) * K);

	}

}
