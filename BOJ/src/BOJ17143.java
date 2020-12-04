import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17143 {

	static int R, C, M, map[][], king;
	static Shark[] sharks;
	static int[] dr = {-1, 0, 0, 1}, dc = {0, -1, 1, 0}; // 상, 좌, 우, 하
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); // 세로
		C = Integer.parseInt(st.nextToken()); // 가로
		M = Integer.parseInt(st.nextToken()); // 상어의 수
		map = new int[R + 1][C + 1];
		sharks = new Shark[M + 1];
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()); 
			int c = Integer.parseInt(st.nextToken()); 
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()); // 이동방향
			int z = Integer.parseInt(st.nextToken()); // 크기
			
			if(d == 1) d = 0;
			else if(d == 2) d = 3;
			else if(d == 3) d = 2;
			else d = 1;
			
			map[r][c] = i;
			sharks[i] = new Shark(r, c, s, d, z);
		}
		
		System.out.println(process());
	}

	private static int process() {
		
		int res = 0;
		
		// 1. 낚시왕이 오른쪽으로 한 칸 이동
		while(king++ < C) {
			// 2. 낚시왕이 상어를 잡는다.
			res += fishing();
			
			// 3. 상어가 이동
			move();
		}
		
		return res;
	}

	private static void move() {

		// 새로운 맵에 정보를 저장
		int[][] newMap = new int[R + 1][C + 1];
		
		for (int i = 1; i <= M; i++) {
			// 잡힌 상어는 pass
			if(sharks[i] == null) continue;
			
			Shark now = sharks[i];
			int turn = 1, r = now.r, c = now.c;
			for (int m = 0; m < now.s; m++) {
				r += turn * dr[now.d]; 
				c += turn * dc[now.d];
				// 범위를 벗어날 경우
				if(r < 1 || r > R || c < 1 || c > C) {
					// 방향을 반대로 하고
					turn *= -1;
					// 이전 칸으로 이동
					r += (turn * dr[now.d]) * 2; 
					c += (turn * dc[now.d]) * 2;
				}
			}
			
			// 상어의 방향이 바뀐 상태라면
			if(turn == -1) {
				sharks[i].d = 3 - sharks[i].d; 
			}
			
			// 이동한 칸에 다른 상어가 있을 경우
			if(newMap[r][c] > 0) {
				// 현재 상어보다 더 큰 상어일 경우 잡아먹힌다..
				if(sharks[newMap[r][c]].z > now.z) sharks[i] = null;
				// 현재 상어가 더 클 경우
				else {
					sharks[i].r = r;
					sharks[i].c = c;
					// 해당 칸에 있던 상어를 잡아먹자.
					sharks[newMap[r][c]] = null;
					newMap[r][c] = i;
				}
			}
			// 이동한 칸이 비었을 경우
			else {
				sharks[i].r = r;
				sharks[i].c = c;
				newMap[r][c] = i;
			}
		}
		
		copy(map, newMap);
	}

	private static void copy(int[][] dest, int[][] src) {
		
		for (int i = 0; i <= R; i++) {
			for (int j = 0; j <= C; j++) {
				dest[i][j] = src[i][j];
			}
		}
		
	}

	private static int fishing() {

		// 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡자.
		for (int i = 1; i <= R; i++) {
			if(map[i][king] > 0) {
				int size = sharks[map[i][king]].z; 
				// 잡힌 상어의 정보는 삭제
				sharks[map[i][king]] = null;
				map[i][king] = 0;
				
				return size;
			}
		}
		
		return 0;
	}

	static class Shark {
		// 세로, 가로, 속력, 이동방향, 크기
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		
	}
}
