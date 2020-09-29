import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2589 {

	static int H, W, res;
	static char map[][];
	static boolean visited[][];
	static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
	static class Pos {
		int x, y, dis;

		public Pos(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		// 마을 정보 입력
		for (int i = 0; i < H; i++) {
			map[i] = br.readLine().toCharArray();
		}
		// 마을을 탐색하며 육지인 곳을 발견하면 이동해보자.
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j] == 'L') {
					go(i, j);
				}
			}
		}
		
		System.out.println(res); 
	}
	
	private static void go(int x, int y) {

		visited = new boolean[H][W];
		visited[x][y] = true;
				
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(x, y, 0));
		while(!q.isEmpty()) {
			Pos now = q.poll();
			// 최대 거리를 계속 갱신
			res = Math.max(res, now.dis);
			
			for (int d = 0; d < 4; d++) {
				int xx = now.x + dx[d];
				int yy = now.y + dy[d];
				// 범위를 벗어나면
				if(xx < 0 || xx >= H || yy < 0 || yy >= W) continue;
				// 물이면
				if(map[xx][yy] == 'W' || visited[xx][yy]) continue;

				visited[xx][yy] = true;
				q.add(new Pos(xx, yy, now.dis + 1));
			}
		}
		
	}

}
