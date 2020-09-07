import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2105 {

	static int N, map[][], res, startX, startY;
	static boolean isAte[];
	// 우하, 좌하, 좌상, 우상
	static int[] dx = {1, 1, -1, -1}, dy = {1, -1, -1, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			res = -1;
			
			// 디저트 카페의 디저트 종류 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 사각형 모양을 만들 수 있는 최소의 범위만 확인
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					// 여기부터 디저트 카페 투어 시작
					isAte = new boolean[101];
					isAte[map[i][j]] = true;
					// 시작점
					startX = i;
					startY = j;
					
					go(i, j, -1, -1, 0, 0);
				}
			}
			
			// 디저트를 가장 많이 먹을 때의 디저트 수
			System.out.println("#" + tc + " " + res);
		}

	}

	/**
	 * 
	 * @param x	행 좌표
	 * @param y	열 좌표
	 * @param prevX	이전 행 좌표
	 * @param prevY	이전 열 좌표
	 * @param cnt	먹은 디저트 개수
	 * @param sd	이동 방향
	 */
	private static void go(int x, int y, int prevX, int prevY, int cnt, int sd) {

		// 대각선 방향으로 이동
		for (int d = sd; d < 4; d++) {
			int xx = x + dx[d];
			int yy = y+ dy[d];
			// 지역을 벗어나면 pass			
			if(xx < 0 || yy < 0 || xx >= N || yy >= N) continue;
			// 이전 카페로 되돌아갈 경우
			if(xx == prevX && yy == prevY) continue;
			// 시작점으로 도착할 경우
			if(xx == startX && yy == startY) {
				// 디저트를 먹은 최대 개수 갱신
				res = Math.max(res, cnt + 1);
				return;
			}
			// 이미 먹은 디저트일 경우 pass
			if(isAte[map[xx][yy]]) continue;
			
			// 디저트 냠냠
			isAte[map[xx][yy]] = true;
			go(xx, yy, x, y, cnt + 1, d);
			
			// 디저트 퉤퉤			
			isAte[map[xx][yy]] = false;
		}
		
	}

}

/*
대각선 방향으로 움직이고 사각형 모양을 그리며 출발한 카페로 돌아와야 한다.
	카페 투어를 하는 도중 해당 지역을 벗어나면 안 된다.
	
친구들은 같은 종류의 디저트를 다시 먹는 것을 싫어한다.
	카페 투어 중에 같은 숫자의 디저트를 팔고 있는 카페가 있으면 안 된다.
	
서로 다른 디저트를 먹으면서 사각형 모양을 그리며 다시 출발점으로 돌아오는 경우,
	디저트를 가장 많이 먹을 수 있는 경로를 찾고, 그 때의 디저트 수를 출력
	
디저트를 먹을 수 없는 경우 -1
*/
