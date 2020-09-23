import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1244 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		boolean[] light = new boolean[110];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if (tmp == 1) light[i] = true;
			else light[i] = false;
		}

		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {

			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			// 남학생
			if (gender == 1) {
				// 스위치 번호가 자기가 받은 수의 배수이면, 그 스위치의 상태를 바꾼다
				for (int j = num; j <= N; j += num) {
					light[j] = !light[j];
				}
			}
			// 여학생
			else {
				light[num] = !light[num];
				for (int j = num - 1, k = num + 1; (j >= 1 && k <= N && light[j] == light[k]); j--, k++) {
					light[j] = !light[j];
					light[k] = !light[k];
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			if (light[i]) System.out.print("1");
			else System.out.print("0");
 
			if (i % 20 == 0) System.out.print("\n");
			else System.out.print(" ");
		}
	}

}
