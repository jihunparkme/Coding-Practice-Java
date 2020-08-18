import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178_bfs {

	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	static int N, M, map[][], dp[][];
	static public class pos{
		int x;
		int y;
		public pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+2][M+2];
		dp = new int[N+2][M+2];
		
		for (int x = 1; x <= N; x++) {
			String str = br.readLine();
			for (int y = 0; y < M; y++) {
				map[x][y+1] = (str.charAt(y)) - '0';
			}
		}
		
		Queue<pos> q = new LinkedList<>();
		q.offer(new pos(1, 1));
		// 시작점은 1
		dp[1][1] = 1;
		while(!q.isEmpty()) {
			pos tmp = q.poll();
			// 도착 지점에 도달하면 종료
			if(tmp.x == N && tmp.y == M) break;
			// 인접한 좌표에 길이 있는지 확인
			for (int i = 0; i < 4; i++) {
				int xx = tmp.x + dx[i];
				int yy = tmp.y + dy[i];
				// 이미 왔던 길이라면 pass
				if(dp[xx][yy] > 1) continue;
				// 벽이면 pass
				if(map[xx][yy] == 0) continue;
				// 이전 길까지의 칸 수 + 1
				dp[xx][yy] = dp[tmp.x][tmp.y]+1;
				q.offer(new pos(xx, yy));
			}
		}
		
		System.out.println(dp[N][M]);
	}
}

// 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸
// DFS : 경로의 수
// BFS : 최단 거리