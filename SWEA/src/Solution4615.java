import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution4615 {
	// 시계방향
	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken()); // 돌을 놓는 횟수
			int board[][] = new int[N + 1][N + 1];
			// 초기 세팅
			board[N / 2][N / 2] = 2;
			board[N / 2 + 1][N / 2 + 1] = 2;
			board[N / 2 + 1][N / 2] = 1;
			board[N / 2][N / 2 + 1] = 1;

			for (int i = 0; i < M; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st2.nextToken());
				int y = Integer.parseInt(st2.nextToken());
				int rock = Integer.parseInt(st2.nextToken());

				board[x][y] = rock;
				for (int d = 0; d < 8; d++) {
					int xx = x + dx[d];
					int yy = y + dy[d];
					// 해당 방향으로 같은색 돌이 나올 때까지 탐색
					boolean existSame = false;
					while(true) {
						if(xx < 1 || yy < 1 || xx > N || yy > N) break;
						if(board[xx][yy] == 0) break;
						if(board[xx][yy] == rock) {
							existSame = true;
							break;
						}
						xx += dx[d];
						yy += dy[d];
					}
					while(existSame) {
						xx -= dx[d];
						yy -= dy[d];
						board[xx][yy] = rock;
						
						if(xx == x && yy == y) break;
					}
				}	
//				for (int tst = 0; tst <= N; tst++) {
//					System.out.println(Arrays.toString(board[tst]));
//				}
//				System.out.println("\n\n");
			}
			
			int cntB = 0, cntW = 0;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(board[i][j] == 1) cntB++;
					else if(board[i][j] == 2) cntW++;
				}
			}

			System.out.println("#" + tc + " " + cntB + " " + cntW);
		}

	}

}
// 1: 흑돌, 2: 백돌