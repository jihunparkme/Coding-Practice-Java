import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3109 {

	static int R, C, res;
	static int[] dr = { -1, 0, 1 };
	static char[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		setPipe();
		System.out.println(res);
	}

	private static void setPipe() {
		// 0열의 나기부씨 식당의 모든 행의 위치에서 파이프 놓기 시작
		for (int r = 0; r < map.length; r++) {
			visited[r][0] = true;
			go(r, 0);
		}
	}

	// 현 위치에서 오른쪽대각선위, 오른쪽, 오른쪽대각선아래 순서로 파이프 연결 시도
	private static boolean go(int r, int c) {
		// 나야나씨 식당까지 파이트가 연결되었다면
		if(c == C-1) {
			res++;
			// 성공 결과를 리턴
			return true;
		}
		
		int nr, nc = c + 1;
		for (int d = 0; d < 3; d++) {
			nr = r + dr[d];
			// 범위를 벗어나거나 이미 파이프가 놓여있거나, 건물이 있을 경우
			if (nr < 0 || nr >= R || visited[nr][nc] || map[nr][nc] == 'x')
				continue;
			
			// 파이프 놓기
			visited[nr][nc] = true;
			// 다음 칸으로 이동 후 진행 결과가 끝까지 연결 가능했다면
			// 현 위치에서 다른 방향으로 파이프 놓기 시도를 중단하고 이전 위치로 돌아감
			if(go(nr, nc)) return true; 
		}
		// 파이프가 제대로 설치되지 않았다면
		return false;
	}

}