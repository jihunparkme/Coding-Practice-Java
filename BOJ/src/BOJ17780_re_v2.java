import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17780_re_v2 {

	static int N, K, map[][];
	static int dir[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static ArrayList<Integer> s[][];
	static piece num[];

	public static class piece {
		int x;
		int y;
		int d;

		public piece(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 체스판 크기
		K = Integer.parseInt(st.nextToken()); // 말의 개수
		map = new int[N + 1][N + 1];
		num = new piece[K];
		s = new ArrayList[N + 1][N + 1];
		// 체스판 정보 입력
		// 0은 흰색, 1은 빨간색, 2는 파란색
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				s[i][j] = new ArrayList<Integer>();
			}
		}
		// 말 정보 입력
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 행
			int y = Integer.parseInt(st.nextToken()); // 열
			int d = Integer.parseInt(st.nextToken()); // 이동방향
			if (d == 1) {
	            d = 1;
	         } else if (d == 2) {
	            d = 3;
	         } else if (d == 3) {
	            d = 0;
	         } else if (d == 4) {
	            d = 2;
	         }
			// 같은 칸에 말이 두 개 이상 있는 경우는 입력으로 주어지지 않는다.
			num[i] = new piece(x, y, d);
			s[x][y].add(i);
		}

		System.out.println(start());
	}

	private static int start() {

		int time = 1;

		while (time <= 1000) {
			for (int i = 0; i < K; i++) {
				piece next = num[i];
				int x = next.x;
				int y = next.y;
				
				// 말이 가장 아래에 있지 않다면 pass
				if (s[next.x][next.y].get(0) != i)
					continue;
				
				// 말이 이동
				int x1 = next.x + dir[next.d][0];
				int y1 = next.y + dir[next.d][1];
				
				// 다음 칸이 체스판을 벗어나는 경우 or 파란색일 경우
				if (x1 < 1 || x1 > N || y1 < 1 || y1 > N || map[x1][y1] == 2) {
					// k번 말의 이동 방향을 반대로 하고 한 칸 이동
					next.d = (next.d + 2) % 4; 
					x1 = next.x + dir[next.d][0];
					y1 = next.y + dir[next.d][1];
					
					// 방향을 반대로 한 후에 이동하려는 칸이 체스판을 벗어나는 경우 or 파란색일 경우
					if (x1 < 1 || x1 > N || y1 < 1 || y1 > N || map[x1][y1] == 2) {
						// 이동하지 않고 방향만 반대로 바꾼다.
						next.d = (next.d + 2) % 4;
					} else if (map[x1][y1] == 0) {
						if(!move(next, x1, y1)) 
							return time;
					} else {
						// 방향을 반대로 한 후에 이동하려는 칸이 빨간칸일 경우
						if(!reverse(next, x1, y1)) 
							return time;
					}
				} // 다음 칸이 흰색칸일 경우 그냥 이동
				else if (map[x1][y1] == 0) {
					if(!move(next, x1, y1)) 
						return time;
				} // 다음 칸이 빨간칸일 경우
				else {
					if(!reverse(next, x1, y1)) 
						return time;
				}
			}
			
			time++;
		}
		
		return -1;
	}
	
	private static boolean reverse(piece next, int x1, int y1) {
		
		int x = next.x;
		int y = next.y;
		
		while (s[x][y].size() != 0) {
			int nextnum = s[next.x][next.y].remove(s[next.x][next.y].size() - 1);
			num[nextnum].x = x1;
			num[nextnum].y = y1;
			s[x1][y1].add(nextnum);
		}
		if (s[x1][y1].size() >= 4) {
			return false;
		}
		
		return true;
	}
	
	static private boolean move(piece next, int x1, int y1) {
		
		// 방향을 반대로 한 후에 이동하려는 칸이 흰색칸일 경우
		Stack<Integer> ne = new Stack<Integer>();
		while (s[next.x][next.y].size() != 0) {
			ne.add(s[next.x][next.y].remove(s[next.x][next.y].size() - 1));
		}
		while (!ne.isEmpty()) {
			int nextnum = ne.pop();
			num[nextnum].x = x1;
			num[nextnum].y = y1;
			s[x1][y1].add(nextnum);
		}
		if (s[x1][y1].size() >= 4) {
			return false;
		}
		
		return true;
	}
}
