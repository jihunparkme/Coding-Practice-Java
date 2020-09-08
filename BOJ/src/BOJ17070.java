import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070 {

	static int N, res, map[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		// 집 상태 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 파이프를 밀어보자.
		// 가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지
		push(0, 1, 0);

		System.out.println(res);
	}

	/**
	 * 파이프 밀기
	 * @param x	행 좌표
	 * @param y	열 좌표
	 * @param nd	0: 가로, 1: 세로, 2: 우하대각선
	 */
	private static void push(int x, int y, int nd) {

		// 도착지에 도달
		if(x == N - 1 && y == N - 1) {
			res++;
			return;
		}
		
		// 파이프가 가로로 놓여진 경우
		if(nd == 0) {
			// 범위를 넘지 않고, 벽이 없을 경우 우로 이동하거나
			if(y + 1 < N && map[x][y + 1] == 0)
				push(x, y + 1, 0);
			
			// 범위를 넘지 않고, 벽이 없을 경우 우하대각선으로 이동하거나
			if(x + 1 < N && y + 1 < N) {
				if(map[x+1][y] == 0 && map[x+1][y+1] == 0 && map[x][y+1] == 0)
					push(x + 1, y + 1, 2);
			}
		}
		// 파이프가 세로로 놓여진 경우
		else if(nd == 1) {
			// 범위를 넘지 않고, 벽이 없을 경우 하로 이동하거나
			if(x + 1 < N && map[x+1][y] == 0)
				push(x + 1, y, 1);
			// 범위를 넘지 않고, 벽이 없을 경우 우하대각선으로 이동하거나
			if(x + 1 < N && y + 1 < N) {
				if(map[x+1][y] == 0 && map[x+1][y+1] == 0 && map[x][y+1] == 0)
					push(x + 1, y + 1, 2);
			}
		}
		// 파이프가 우하대각선으로 놓여진 경우
		else if(nd == 2) {
			// 범위를 넘지 않고, 벽이 없을 경우 우로 이동하거나
			if(y + 1 < N && map[x][y + 1] == 0)
				push(x, y + 1, 0);
			// 범위를 넘지 않고, 벽이 없을 경우 하로 이동하거나
			if(x + 1 < N && map[x+1][y] == 0)
				push(x + 1, y, 1);			
			// 범위를 넘지 않고, 벽이 없을 경우 우하대각선으로 이동하거나
			if(x + 1 < N && y + 1 < N) {
				if(map[x+1][y] == 0 && map[x+1][y+1] == 0 && map[x][y+1] == 0)
					push(x + 1, y + 1, 2);
			}		
	
		}

	}

}
