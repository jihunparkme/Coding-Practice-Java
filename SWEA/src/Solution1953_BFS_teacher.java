
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1953_BFS_teacher {

	static int N, M, R, C, L, map[][];
	static boolean visited[][];
	static int[] dr = {-1, 0, 0, 1}, dc = {0, -1, 1, 0};
	// 구조물 타입이 연결된 방향
	// 상,좌,우,하 : 0,1,2,3
	static String[] type = { 
			null, 
			"0312", // 1: 상하좌우
			"03", // 2:상하
			"12", // 3:좌우
			"02", // 4:상우
			"32", // 5:하우
			"31", // 6:하좌
			"01" // 7:상좌
	};

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(in.readLine().trim());
			N = Integer.parseInt(st.nextToken()); // 지하 터널의 크기
			M = Integer.parseInt(st.nextToken()); 
			R = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑의 위치
			C = Integer.parseInt(st.nextToken()); 
			L = Integer.parseInt(st.nextToken()); // 소요된 시간
			
			map = new int[N][M];
			visited = new boolean[N][M];

			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(in.readLine().trim());
				
				for (int j = 0; j < M; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.println("#" + tc + " " + process());
		}
	}

	private static int process() {
		
		// 시작 위치도 시간에 포함 : cnt = res = 1
		int res = 1, time = 1, size = 0;
		
		Queue<Point> queue = new LinkedList<>();
		// 시작 위치
		queue.add(new Point(R, C));
		visited[R][C] = true;
		
		String info = null;
		while (time++ < L) {
			// 1시간 동안 이루어지는 동작
			size = queue.size();
			
			while (size-- > 0) {
				// 현재 위치
				Point now = queue.poll();
				// 현재 위치에서 이동할 수 있는 방향 정보
				info = type[map[now.x][now.y]];

				for (int d = 0; d < info.length(); d++) {
					// 연결된 방향
					int dir = info.charAt(d) - '0';
					// 연결된 다음 좌표
					int rr = now.x + dr[dir];
					int cc = now.y + dc[dir];
					// 범위를 벗어나거나 구조물이 없는 곳이라면 pass
					if(rr < 0 || rr >= N || cc < 0 || cc >= M || map[rr][cc] == 0) continue;
					// 다음 좌표의 구조물이 현재 방향과 연결되어있고 방문하지 않은 곳이면
					if(type[map[rr][cc]].contains(Integer.toString(3 - dir)) && !visited[rr][cc]) {
						queue.add(new Point(rr, cc));
						visited[rr][cc] = true;
						res++;
					}
				}
			}
		}
		
		return res;
	}
}