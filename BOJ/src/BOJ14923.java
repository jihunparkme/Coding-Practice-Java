import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14923 {

	static int N, M, Hx, Hy, Ex, Ey;
	static int[][] map;
	static int[] dr = {0, 1, 0, -1}, dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		// 홍익이 위치
		st = new StringTokenizer(br.readLine());
		Hx = Integer.parseInt(st.nextToken()) - 1;
		Hy = Integer.parseInt(st.nextToken()) - 1;
		// 탈출 위치
		st = new StringTokenizer(br.readLine());
		Ex = Integer.parseInt(st.nextToken()) - 1;
		Ey = Integer.parseInt(st.nextToken()) - 1;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		System.out.println(process());
	}

	private static int process() {
		
		int time = 0;
		// [지팡이를 사용한 횟수][세로][가로]
		boolean[][][] visited = new boolean[2][N][M];
		Queue<Hong> q = new LinkedList<>();
		
		q.add(new Hong(Hx, Hy, 0));
		visited[0][Hx][Hy] = true;
		
		while(!q.isEmpty()) {
			
			++time;
			int size = q.size();
			while(size-- > 0) {
				
				Hong now = q.poll();
				// 4방탐색
				for (int d = 0; d < 4; d++) {
					int rr = now.r + dr[d];
					int cc = now.c + dc[d];
					
					// 범위를 벗어날 경우
					if(rr < 0 || cc < 0 || rr >= N || cc >= M) continue;
					
					// 벽이라면
					if(map[rr][cc] == 1) {
						// 지팡이를 사용할 수 있고 방문하지 않았을 경우
						if(now.cnt == 0 && !visited[now.cnt + 1][rr][cc]) {
							// 이동할 곳이 탈출 위치라면
							if(rr == Ex && cc == Ey) return time;
							
							q.add(new Hong(rr, cc, now.cnt + 1));
							visited[now.cnt + 1][rr][cc] = true;
						} 
					}
					// 벽이 아닐 경우
					else {
						// 방문하지 않았을 경우
						if(!visited[now.cnt][rr][cc]) {
							// 이동할 곳이 탈출 위치라면
							if(rr == Ex && cc == Ey) return time;
							
							q.add(new Hong(rr, cc, now.cnt));
							visited[now.cnt][rr][cc] = true;
						}
					}
				}
			}
		}
		
		// 탈출 할 수 없다면
		return -1;
	}

	static class Hong {
		int r, c, cnt;

		public Hong(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		
	}
}
