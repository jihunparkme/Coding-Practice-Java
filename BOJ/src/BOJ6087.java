import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ6087 {

	static int W, H;
	static char map[][];
	static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
	static class State implements Comparable<State> {
		int x, y;
		int dir; // 동,서,남,북
		int cnt;
		
		public State(int x, int y, int dir, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(State o) {
			return Integer.compare(this.cnt, o.cnt);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		// 시작 레이저 확인
		boolean isStart = false;
		State start = null, end = null;
		// 지도 정보 입력
		for (int i = 0; i < H; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				// 레이저가 있다면
				if(map[i][j] == 'C') {
					// 시작점이 정해지지 않았다면
					if(!isStart) {
						start = new State(i, j, -1, 0);
						isStart = true;
					}
					else end = new State(i, j, 0, 0);
				}
			}
		}
		
		System.out.println(go(start, end));
	}

	private static int go(State start, State end) {

		PriorityQueue<State> pQ = new PriorityQueue<>();
		int[][] visited = new int[H][W];
		for (int i = 0; i < H; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		// 시작점부터 출발
		pQ.add(start);
		visited[start.x][start.y] = 0;
		
		while(!pQ.isEmpty()) {
			
			State now = pQ.poll();
			// 도착 지점에 도달
			if(now.x == end.x && now.y == end.y) return now.cnt;
			
			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int xx = now.x + dx[d];
				int yy = now.y + dy[d];
				// 범위를 벗어날 경우
				if(xx < 0 || yy < 0 || xx >= H || yy >= W) continue;
				// 벽일 경우
				if(map[xx][yy] == '*') continue;
				// 이미 방문
				if(visited[xx][yy] <= now.cnt) continue;
				
				// 시작점일 경우
				if(now.dir == -1) {
					pQ.add(new State(xx, yy, d, 0));
					visited[xx][yy] = 0;
					continue;
				}
				
				// 지금 가고 있는 방향과 같은 방향일 경우
				if(now.dir == d) {
					pQ.add(new State(xx, yy, d, now.cnt));
					visited[xx][yy] = now.cnt;
				} else {
					// 다른 방향일 경우(방향이 꺽일 때)
					pQ.add(new State(xx, yy, d, now.cnt + 1));
					visited[xx][yy] = now.cnt + 1;
				}
			} 
		}
	
		return 0;
	}

}
