import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1244_v2 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] light = new int[110];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) 
			light[i] =  Integer.parseInt(st.nextToken());

		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {

			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			// 남학생
			if (gender == 1) {
				// 스위치 번호가 자기가 받은 수의 배수이면, 그 스위치의 상태를 바꾼다
				for (int j = num; j <= N; j += num) {
					light[j] = 1 - light[j];
				}
			}
			// 여학생
			else {
				int size = 1;
				light[num] = 1 - light[num];
				while (true) {
					// 스위치를 중심으로 좌우가 대칭이면서 그 구간에 속한 스위치의 상태를 모두 변경
					if (num - size <= 0 || num + size > N) break;
					if (light[num - size] == light[num + size]) {
						light[num - size] = 1 - light[num - size];
						light[num + size] = 1 - light[num + size];
						size++;
					} else {
						break;
					}
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			System.out.print(light[i]);
 
			if (i % 20 == 0) System.out.print("\n");
			else System.out.print(" ");
		}
	}

}
