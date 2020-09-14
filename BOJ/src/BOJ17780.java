import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17780 {

	static int N, K, map[][], place[][][], piece[][], floor[];
	static int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());	// 체스판 크기
		K = Integer.parseInt(st.nextToken());	// 말의 개수
		map = new int[N][N];
		place = new int[5][N][N];	// 말 위치 정보 1층~4층
		piece = new int[K + 1][3];	// 말 정보, 행,열,방향
		floor = new int[K + 1];	// 말이 몇 층에 있는지
		// 체스판 정보 입력
		// 0은 흰색, 1은 빨간색, 2는 파란색
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 말 정보 입력
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			piece[i][0] =  Integer.parseInt(st.nextToken()) - 1; // 행
			piece[i][1] =  Integer.parseInt(st.nextToken()) - 1; // 열
			piece[i][2] =  Integer.parseInt(st.nextToken()) - 1; // 이동 방향
			// 같은 칸에 말이 두 개 이상 있는 경우는 입력으로 주어지지 않는다.
			floor[i] = 1;
			place[1][piece[i][0]][piece[i][1]] = i;
		}
		
		int turn = 0;
		out: while(true) {
			++turn;
			if(turn >= 1000) {
				turn = -1;
				break out;
			}
			
			// 턴 한 번은 1번 말부터 K번 말까지 순서대로 이동
			for (int k = 1; k <= K; k++) {
				// 말이 가장 아래에 있지 않다면 pass
				if(floor[k] != 1) continue;
				
				int x = piece[k][0];
				int y = piece[k][1];
				
				// k번 말이 이동
				int xx = x + dx[piece[k][2]];
				int yy = y + dy[piece[k][2]];
		
				// 체스판을 벗어나는 경우 or 파란색일 경우
				if(xx < 0 || yy < 0 || xx >= N || yy >= N || map[xx][yy] == 2) {
					// k번 말의 이동 방향을 반대로 하고 한 칸 이동
					if(piece[k][2] < 2) 
						piece[k][2] = piece[k][2] == 0 ? 1 : 0; 
					else 
						piece[k][2] = piece[k][2] == 2 ? 3 : 2;
					
					xx = x + dx[piece[k][2]];
					yy = y + dy[piece[k][2]];
					
					// 방향을 반대로 한 후에 이동하려는 칸이 파란색인 경우에는 
					if(xx < 0 || yy < 0 || xx >= N || yy >= N || map[xx][yy] == 2) {
						// 이동하지 않고 방향만 반대로 바꾼다.
						if(piece[k][2] < 2) 
							piece[k][2] = piece[k][2] == 0 ? 1 : 0; 
						else 
							piece[k][2] = piece[k][2] == 2 ? 3 : 2;
						
						continue;
					}
					// 빨간색인경우
					if(map[xx][yy] == 1) {
						reverse(x, y);
					}
					
					if(!move(xx, yy, k)) break out;
				}
				// 흰색인 경우에는 그 칸으로 이동
				else if(map[xx][yy] == 0) {
					
					if(!move(xx, yy, k)) break out;
					
				} 
				else if(map[xx][yy] == 1) {
					// 빨간색인 경우에는 이동한 후에 
					// A번 말과 그 위에 있는 모든 말의 쌓여있는 순서를 반대로 바꾼다
					// 말이 몇 개나 쌓였나 확인
					reverse(x, y);
					
					// 이동시키자
					if(!move(xx, yy, k)) break out;
				}

			}
			
		}

		System.out.println(turn);
	}
	
	private static void reverse(int x, int y) {
		
		int idx = 1;
		while(true) {
			if(place[idx][x][y] != 0) idx++;
			else break;
		}
		// 순서를 바꾸고
		idx--;
		if(idx == 2) {
			floor[place[1][x][y]] = 2;
			floor[place[2][x][y]] = 1;
			
			int tmp = place[1][x][y];
			place[1][x][y] = place[2][x][y];
			place[2][x][y] = tmp;
		} else if(idx == 3) {
			floor[place[1][x][y]] = 3;
			floor[place[3][x][y]] = 1;
			
			int tmp = place[1][x][y];
			place[1][x][y] = place[3][x][y];
			place[3][x][y] = tmp;
		}

	}
	
	static private boolean move(int xx, int yy, int k) {
		
		int x = piece[k][0];
		int y = piece[k][1];
		
		// 칸에 말이 이미 있는 경우에는 가장 위에 A번 말을 올려놓는다.
		if(place[1][xx][yy] != 0) {
			// 몇 층에 말을 올릴 수 있는지 확인
			int idx = 2;
			while(true) {
				if(place[idx][xx][yy] != 0) idx++;
				else break;
			}
			// idx 층에 말을 올릴 수 있음.
			// 말이 4개 이상 쌓이는 순간 게임이 종료
			if(idx == 4) return false;
			
			// 그렇지 않을 경우 말을 쌓자(1층~3층까지 확인)
			for (int i = 1; i <= 3; i++) {
				// 말이 있다면 쌓자
				if(place[i][x][y] != 0) {
					floor[place[i][x][y]] = idx;
					
					piece[place[i][x][y]][0] = xx;
					piece[place[i][x][y]][1] = yy;
					
					place[idx++][xx][yy] = place[i][x][y];
					place[i][x][y] = 0;

					if(idx > 4) return false;
				}
				else break;
			}
		} else {
			// 칸에 말이 없을 경우 말을 위치시키자.
			int idx = 1;
			// 그렇지 않을 경우 말을 쌓자(1층~3층까지 확인)
			for (int i = 1; i <= 3; i++) {
				// 말이 있다면 쌓자
				if(place[i][x][y] != 0) {
					floor[place[i][x][y]] = idx;
					
					piece[place[i][x][y]][0] = xx;
					piece[place[i][x][y]][1] = yy;
					
					place[idx++][xx][yy] = place[i][x][y];
					place[i][x][y] = 0;
				}
				else break;
			}
		}

		return true;
	}

}
