import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17090 {

	static int N, M, map[][], isPosb[][];
	static int[] dr= {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 세로 
		M = Integer.parseInt(st.nextToken()); // 가로
		map = new int[N][M];
		isPosb = new int[N][M]; // 1: 탈출 가능, -1: 탈출 불가능, 0: 미확인
		
		for (int i = 0; i < N; i++) {
			char tmp[] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				char now = tmp[j];
				// 문자에 번호 지정
				if(now == 'U') map[i][j] = 0;
				else if(now == 'R') map[i][j] = 1;
				else if(now == 'D') map[i][j] = 2;
				else map[i][j] = 3;
			}
		}
		
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int tmp = process(i, j, map[i][j]);
				// 탈출 불가능한 칸(-1)일 경우 0으로
				res += tmp < 0 ? 0 : tmp;
			}
		}
		
		System.out.println(res);
	}

	private static int process(int r, int c, int dir) {

		// 이동할 다음 칸을 기준으로
		int rr = r + dr[dir];
		int cc = c + dc[dir];
		
		// 범위를 벗어날 경우 탈출 성공 (DP에 1을 저장하고 return)
		if(rr < 0 || cc < 0 || rr >= N || cc >= M) return isPosb[r][c] = 1;
		// 이미 확인한 구역일 경우 (DP에 저장된 값을 return)
		else if(isPosb[rr][cc] != 0) return isPosb[rr][cc];
		
		// 우선 불가능으로 체크
		isPosb[r][c] = -1;
		
		// 다음 칸으로 이동 (탈출 가능할 경우 1 저장 후 return)
		// 자기 자신으로 돌아오거나 탈출 불가능한 칸을 거치면 -1 저장 후 return)
		return isPosb[r][c] = process(rr, cc, map[rr][cc]);
	}

}
