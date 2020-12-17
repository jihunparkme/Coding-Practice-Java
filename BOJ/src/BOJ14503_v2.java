import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14503_v2 {

	static int N, M, map[][], cnt;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1}; // 북동남서
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int Rr = Integer.parseInt(st.nextToken());
		int Rc = Integer.parseInt(st.nextToken());
		int Rd = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		process(Rr, Rc, Rd);
		System.out.println(cnt);
	}

	private static void process(int r, int c, int dir) {
		
		// 벽일 경우
		if(map[r][c] == 1) return;
		// 1. 현재 위치를 청소
		else if(map[r][c] == 0) {
			// 청소한 곳은 2로 체크
			map[r][c] = 2;
			cnt++;
		}

		int rr = 0, cc = 0;
		for (int d = 0; d < 4; d++) {
			dir = (dir + 3) % 4;
			rr = r + dr[dir];
			cc = c + dc[dir];
			// 이미 청소했거나 벽일 경우
			if(map[rr][cc] > 0) continue;
			// 아직 청소하지 않은 공간이 존재한다면, 
			// 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행
			process(rr, cc, dir);
			return;
		}
		
		// 네 방향 모두 청소가 이미 되어있거나 벽인 경우
		// 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아감
		process(r - dr[dir], c - dc[dir], dir);
		return;
	}

}