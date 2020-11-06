import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution1868_bfs {

	static int N, res, mCnt[][];
	static char map[][];
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 }, dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			res = 0;
			map = new char[N][N];
			mCnt = new int[N][N];
			// 지뢰밭 입력
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}

			// 주변 지뢰 개수 세기
			countMine();

			// 주변에 지뢰가 없는 곳부터 눌러주자.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 주변에 지뢰가 없고 현 위치가 지뢰가 아니라면
					if(mCnt[i][j] == 0 && map[i][j] != '*') {
						click(i, j);
						res++;
					}
				}
			}
			
			// 아직 눌리지 않은 곳이 있다면 눌러주자.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 주변에 지뢰가 있지만 현 위지가 지뢰가 아니라면
					if(mCnt[i][j] > 0 && map[i][j] != '*') {
						res++;
					}
				}
			}
			
			System.out.println("#" + tc + " " + res);
		}

	}

	private static void click(int x, int y) {

		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		// 방문 체크
		mCnt[x][y] = -1;
		
		while(!q.isEmpty()) {
			
			Point now = q.poll();
			// 주변 탐색
			for (int d = 0; d < 8; d++) {
				int xx = now.x + dx[d];
				int yy = now.y + dy[d];
				// 범위를 벗어거나 이미 눌린 곳이거나 지뢰라면 pass
				if(xx < 0 || xx >= N || yy < 0 || yy >= N 
						|| mCnt[xx][yy] == -1 || map[xx][yy] == '*') continue;
				// 주변 좌표도 지뢰 count가 0이라면 queue에 추가해주자.
				if(mCnt[xx][yy] == 0) {
					q.add(new Point(xx, yy));
				}
				// 방문 체크
				mCnt[xx][yy] = -1;
			}
		}
		
	}
	
	private static void countMine() {
		
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				// 클릭할 수 있는 곳이라면 주변에 지뢰가 몇개 있는지 세보자.
				if(map[x][y] == '.') {
					int cnt = 0;
					for (int d = 0; d < 8; d++) {
						int xx = x + dx[d];
						int yy = y + dy[d];
						// 범위를 벗어거나 지뢰가 아니면 pass
						if(xx < 0 || xx >= N || yy < 0 || yy >= N || map[xx][yy] != '*') continue;
						
						cnt++;
					}
					
					mCnt[x][y] = cnt;
				}
			}
		}
		
	}

}