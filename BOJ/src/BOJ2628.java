import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2628 {

	static int C, R, K;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		// 절단선
		ArrayList<Integer> cutR = new ArrayList<>();
		ArrayList<Integer> cutC = new ArrayList<>();

		// 시작점과 끝 점을 추가
		cutR.add(0); cutR.add(R);
		cutC.add(0); cutC.add(C);

		// 잘라야하는 점선
		for (int i = 0; i < K; i++) {

			// 0:가로, 1:세로
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			// 세로, 가로 각 절단지점을 list에 추가
			if (d == 1)
				cutC.add(n);
			else
				cutR.add(n);
		}

		cutR.sort(null);
		cutC.sort(null);

		int maxR = 0, maxC = 0;
		// 가로로 절단된 종이 중 최대 높이 찾기
		for (int i = 1; i < cutR.size(); i++) {
			maxR = Math.max(maxR, cutR.get(i) - cutR.get(i - 1));
		}
		// 세로로 절단된 종이 중 최대 너비 찾기
		for (int i = 1; i < cutC.size(); i++) {
			maxC = Math.max(maxC, cutC.get(i) - cutC.get(i - 1));
		}
		// 각 최대 길이의 곱이 가장 큰 종이 조각의 넓이
		System.out.println(maxR * maxC);
	}

}
