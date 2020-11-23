import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573 {
	
	static int N, M, cnt, map[][];
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Queue<Info> q = new LinkedList<>();
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0) q.add(new Info(i, j, map[i][j])); 
			}
		}
		
		System.out.println(process(q));
	}
	
	private static int process(Queue<Info> q) {

		int time = 0, size = 0;
		int[][] prevMap = new int[N][M];
		while(!q.isEmpty()) {
			
			++time;
			size = q.size();
			// 이전 map 상태를 활용하기 위해 copy
			copy(prevMap, map);
			
			while(size-- > 0) {
				Info now = q.poll();
				int cnt = 0;
				
				// 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 녹는다
				for (int d = 0; d < 4; d++) {
					int rr = now.r + dr[d];
					int cc = now.c + dc[d];
					if(rr < 0 || rr >= N || cc < 0 || cc >= M) continue;
					if(prevMap[rr][cc] == 0) cnt++;
				}
				int nxth = now.h - cnt;
				// 빙산이 남았다면
				if(nxth > 0) {
					q.add(new Info(now.r, now.c, nxth));
					map[now.r][now.c] = nxth;
				} else {
					// 빙산이 모두 녹았다면
					map[now.r][now.c] = 0;
				}
			}
			
			// 빙산이 몇 덩어리로 분리되었는지
			cnt = 0;
			visited = new boolean[N][M];
			for (Info i : q) {
				if(visited[i.r][i.c]) continue;
				cntLump(i.r, i.c);
				cnt++;
			}
			// 빙산이 두 덩어리 이상으로 분리되었다면
			if( cnt >= 2) return time;
		}
		// 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않을 경우
		return 0;
	}

	private static void cntLump(int r, int c) {
		
		visited[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int rr = r + dr[d];
			int cc = c + dc[d];
			// 범위를 벗어날 경우
			if(rr < 0 || rr >= N || cc < 0 || cc >= M) continue;
			// 이미 방문한 영역이거나 바다일 경우
			if(visited[rr][cc] || map[rr][cc] == 0) continue;
			
			cntLump(rr, cc);
		}
		
	}

	private static void copy(int[][] prevMap, int[][] map) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				prevMap[i][j] = map[i][j];
			}
		}
		
	}

	static class Info {
		int r, c, h;

		public Info(int r, int c, int h) {
			this.r = r;
			this.c = c;
			this.h = h;
		}
		
	}
}
