import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution5656 {

	static int N, W, H, res, map[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			res = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(br.readLine()); // 쏠 수 있는 구슬 개수
			W = Integer.parseInt(br.readLine());
			H = Integer.parseInt(br.readLine());
			map = new int[H][W];
		
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(br.readLine());
				}
			}

			process(0);

			System.out.println("#" + tc + " " + "dd");
		}

	}

	private static void process(int cnt) {

		if(cnt == N) {
			// 남은 벽돌의 개수
		}
		
		// 맨 위에 있는 벽돌을 깨보자.
		for (int i = 0; i < H; i++) {
			
		}
		

		// 벽돌은 상하좌우(숫자 - 1)칸 만큼 같이 제거

		// 제거되는 범위 내에 있는 벽돌은 동시에 제거

		// 빈 공간이 있을 경우 벽돌은 밑으로 떨어짐

		

	}

}

/*
 * 1. 구슬은 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨트릴 수 있다.
 * 
 * 2. 벽돌은 숫자 1 ~ 9 로 표현되며, 구술이 명중한 벽돌은 상하좌우로 ( 벽돌에 적힌 숫자 - 1 ) 칸 만큼 같이 제거
 * 
 * 3. 제거되는 범위 내에 있는 벽돌은 동시에 제거
 */