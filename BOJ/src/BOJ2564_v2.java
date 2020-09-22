import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2564_v2 {

	static int R, C, K, res;
	static int[] stores;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken()); // 가로
		R = Integer.parseInt(st.nextToken()); // 세로
		K = Integer.parseInt(br.readLine()); // 상점의 개수
		stores = new int[K + 1];

		int a = 0, b = 0;
		for (int i = 0; i <= K; i++) {
			st = new StringTokenizer(br.readLine());

			// 첫째 수는 상점이 위치한 방향
			// 1 : 북쪽, 2 : 남쪽, 3 : 서쪽, 4 : 동쪽
			a = Integer.parseInt(st.nextToken());
			// 둘째 수는 상점이 블록의 북쪽 또는 남쪽에 위치한 경우 블록의 왼쪽 경계로부터의 거리
			// 상점이 블록의 동쪽 또는 서쪽에 위치한 경우 블록의 위쪽 경계로부터의 거리
			b = Integer.parseInt(st.nextToken());

			if(a == 1) stores[i] = b;
			else if(a == 4) stores[i] = C + b;
			else if(a == 2) stores[i] = C + R + (C - b);
			else stores[i] = C * 2 + R + (R - b);
		}
		
		for (int i = 0; i < K; i++) {
			int path = Math.abs(stores[i] - stores[K]);
			res += Math.min(2 * (R + C) - path, path);
		}

		System.out.println(res);
	}
}