import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13459 {

	static int N, M;
	static char map[][];
	static Point red, blue;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 'B') blue = new Point(i, j);
				else if(map[i][j] == 'R') red = new Point(i, j);
			}
		}
		
		// 첫 동작
		boolean isSuccess = false;
		for (int i = 0; i < 4; i++) {
			// 성공했다면
			if(process(i, 0)) {
				isSuccess = true;
				break;
			}
		}

		if(isSuccess) System.out.println("1");
		else System.out.println("0");
	}

	private static boolean process(int dir, int cnt) {
		
		// 10번을 넘어가면 실패
		if(cnt >= 10) {
			return false;
		}
		
		Point tmpRed = new Point(red.r, red.c);
		Point tmpBlue = new Point(blue.r, blue.c);
		boolean isRMove = false, isBMove = false;
		
		// 이 방향으로 기울여보자. (빨강이 먼저 움직여야만 하는 경우)
		// 왼쪽으로 기우는데 빨강이 왼쪽에 더 가까이
		if((dir == 3 && red.c < blue.c) || 
		// 오른쪽으로 기우는데 빨강이 오른쪽에 더 가까이
			(dir == 1 && red.c > blue.c) ||
		// 위쪽으로 기우는데 빨강이 위쪽에 더 가까이
			(dir == 0 && red.r < blue.r) ||
		// 아래쪽으로 기우는데 빨강이 아래쪽에 더 가까이
			(dir == 2 && red.r > blue.r)) {
			isRMove = lean(red, dir, 'B');
			isBMove = lean(blue, dir, 'R');
		} else {
			isBMove = lean(blue, dir, 'R');
			isRMove = lean(red, dir, 'B');
		}
		
		// 구술의 위치가 바뀌었다면
		if(isRMove) {
			map[red.r][red.c] = 'R';
			map[tmpRed.r][tmpRed.c] = '.';
		}
		if(isBMove) {
			map[blue.r][blue.c] = 'B';
			map[tmpBlue.r][tmpBlue.c] = '.';
		}
		
//		System.out.println("방향 :" +  dir);
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		// 빨간 구술이 구멍에 빠지면 성공
		if(map[red.r][red.c] == 'O') return true;
		// 파란 구술이 구멍에 빠지면 실패
		if(map[blue.r][blue.c] == 'O') return false;
		// 더이상 구술이 움직이지 않는다면
		if(!isRMove && !isBMove) return false;
		
		for (int d = 0; d < 4; d++) {
			if(d == dir) continue;
			
			if(process(d, cnt + 1)) return true;
		}
		
		// 나올 때 원래 위치로 되돌리기
		map[red.r][red.c] = '.';
		map[blue.r][blue.c] = '.';
		map[tmpRed.r][tmpRed.c] = 'R';
		map[tmpBlue.r][tmpBlue.c] = 'B';
		
		red = new Point(tmpRed.r, tmpRed.c);
		blue = new Point(tmpBlue.r, tmpBlue.c);
		
		return false;		
	}
	
	private static boolean lean(Point marble, int d, char opp) {
		
		int rr = marble.r;
		int cc = marble.c;
		
		while(true) {
			rr += dr[d];
			cc += dc[d];
			// 범위를 벗어나거나 벽이 있거나 상대 돌이 있을 경우
			if(rr < 0 || cc < 0 || rr >= N || cc >= M || 
					map[rr][cc] == '#' || map[rr][cc] == opp) {
				rr -= dr[d];
				cc -= dc[d];
				
				break;		
			}
			// 구멍이 있을 경우
			if(map[rr][cc] == 'O') {
				marble.r = rr;
				marble.c = cc;
				return true;
			}
		}
		
		// 구술의 위치가 바뀌었다면
		if(marble.r != rr || marble.c != cc) {
			marble.r = rr;
			marble.c = cc;
			return true;
		} else {
			return false;
		}
		
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
}
