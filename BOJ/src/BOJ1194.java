import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1194 {

	static int N, M;
	static char map[][];
	static boolean visited[][][];
	static State minsik;
	static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };

	static class State {
		// x좌표, y좌표, 현재까지 이동거리, 보유하고 있는 키 정보
		int x, y, dist, key;
		public State(int x, int y, int dist, int key) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.key = key;
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		map = new char[N][M];
		// a~f(6가지)까지 들고있는 열쇠의 종류에 따른 visited 처리
		visited = new boolean[N][M][1<<6];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				// 민식이 위치를 발견했다면
				if(map[i][j] == '0') {
					minsik = new State(i,j,0,0);
				}
			}
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<State> q = new LinkedList<>();
		// 초기 민식이 위치
		q.add(minsik);
		visited[minsik.x][minsik.y][0] = true;
		
		while(!q.isEmpty()) {
			State now = q.poll();
			
			// 탈출구에 도착했을 경우
			if(map[now.x][now.y] == '1') 
				return now.dist;
			
			// 사방으로 이동
			for (int d = 0; d < 4; d++) {
				int xx = now.x + dx[d];
				int yy = now.y + dy[d];
				int curKey = now.key;
				
				// 범위를 넘어갈 경우 pass
				if(xx < 0 || yy < 0 || xx >= N || yy >= M) continue;
				// 이미 방문한 곳이라면 pass
				if(visited[xx][yy][now.key]) continue;
				// 이동할 수 없을 경우(벽) pass
				if(map[xx][yy] == '#') continue;	
				// 이미 왔던 곳이라면
				if(visited[xx][yy][curKey]) continue;
				
				// 열쇠가 있을 경우
				if(map[xx][yy] >= 'a' && map[xx][yy] <= 'f') {
					// 키를 집고 상태를 변경
					curKey |= (1<<map[xx][yy]-'a');
				}
				// 문이 있을 경우
				if(map[xx][yy] >= 'A' && map[xx][yy] <= 'F') {
					// 나에게 해당 키가 없다면 pass
					if((curKey & (1 << (map[xx][yy] - 'A'))) == 0) continue;
				}
				// 그냥 빈 곳일 경우, 그냥 아래 처리 수행
				// 현재 정보를 queue에 add
				q.add(new State(xx, yy, now.dist + 1, curKey));
				// 방문처리
				visited[xx][yy][curKey] = true;
			}
		}
		// 미로를 탈출할 수 없다면
		return -1;
	}
}

/*
-- 빈 곳 : 언제나 이동할 수 있다. ('.‘로 표시됨)
---벽 : 절대 이동할 수 없다. (‘#’)
-- 열쇠 : 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 집는다. (a - f)
--- 문 : 대응하는 열쇠가 있을 때만 이동할 수 있다. (A - F)
---민식이의 현재 위치 : 빈 곳이고, 민식이가 현재 서 있는 곳이다. (숫자 0)
---출구 : 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다. (숫자 1)
 
visited[50]50][1<<6]
a:000001
b:000010
b:000011
...
 */