import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1463 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] res = new int[N + 10];

		res[1] = 0;
		res[2] = 1;

		for (int i = 3; i <= N; i++) {
			res[i] = res[i - 1] + 1; // 1을 뺄 경우
			// 2로 나눌 경우
			if (i % 2 == 0) res[i] = Math.min(res[i], res[i / 2] + 1);
			// 3으로 나눌 경우
			if (i % 3 == 0) res[i] = Math.min(res[i], res[i / 3] + 1);
		}
		
		System.out.println(res[N]);
	}

}
