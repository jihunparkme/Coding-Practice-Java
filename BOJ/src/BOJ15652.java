import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15652 {

	static int N, M;
	static int arr[];
	static int sel[];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		sel = new int[M];
		for (int i = 0; i < N; i++)
			arr[i] = i + 1;

		process(1, 0);
		System.out.println(sb);
	}

	public static void process(int idx, int cnt) {
		if (cnt == M) {
			int x = 0;
			out: for (int j = 0; j < N; j++) {
				sb.append(sel[x++] + " ");
				if (x == M)
					break out;
			}

			sb.append('\n');
			return;
		}
		// 중복조합이므로 자기 자신idx부터 자연수를 고를 수 있다.
		for (int i = idx - 1; i < N; i++) {
			// 중복이 허용되므로 해당 자연수가 사용되었는지 확인할 필요가 없다.
			sel[cnt] = arr[i];
			process(i + 1, cnt + 1);
		}
	}
}