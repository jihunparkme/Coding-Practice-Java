import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3109_v2 {

	static int R, C, res;
	static char map[][];
	static boolean visited[][];
	static int[] dr = { -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++)
			map[i] = br.readLine().toCharArray();

		// 근처 빵집으로부터 가스관 연결을 시도해보자.(0행 ~ R-1행)
		for (int i = 0; i < R; i++) {
			visited[i][0] = true;
			setPipe(i, 0);
		}
		
		System.out.println(res);
	}

	// 가스관 연결
	// 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선 순서로 연결 시도
	private static boolean setPipe(int r, int c) {
		// 원웅이네 빵집까지 파이프가 연결되었다면
		if(c == C-1) {
			res++;
			return true;
		}
		
		int rr, cc = c + 1;
		for (int d = 0; d < 3; d++) {
			rr = r + dr[d];
			// row 범위가 넘어가면 pass
			if(rr < 0 || rr >= R) continue;
			// 건물이 있거나 이미 파이프가 설치되(려던 시도가 있)었다면 pass
			if(map[rr][cc] == 'x' || visited[rr][cc]) continue;
			// 해당 공간에 파이프를 설치할 수 있다면 설치해보자.
			visited[rr][cc] = true;
			// 해당 방향으로 파이프가 제대로 설치되었다면 더이상 다른 방향을 확인하 필요가 없음
			if(setPipe(rr, cc)) return true;
		}
		// 파이프를 제대로 설치하지 못했을 경우
		return false;
	}

}
