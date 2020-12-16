import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14499_v2 {
	
	static int N, M, x, y, K, map[][];
	static int[] dr = {0, 0, 0, -1, 1}, dc = {0, 1, -1, 0, 0}; // 동서북남
	/* dice figure
	 *      b
	 *   l (u) r
	 *      f
	 *      d      t
	 */
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		x = Integer.parseInt(st.nextToken()); // 주사위 x
		y = Integer.parseInt(st.nextToken()); // 주사위 y
		K = Integer.parseInt(st.nextToken()); // 명령의 개수
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		int u, l, r, f, b, d, t;
		u = l = r = f = b = d = t =0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int command = Integer.parseInt(st.nextToken());
			// 주사위를 굴리고
			int xx = x + dr[command];
			int yy = y + dc[command];
			// 주사위가 바깥으로 나갈 경우 
			if(xx < 0 || yy < 0 || xx >= N || yy >=M) continue;
			// 주사위 위치의 변화
			switch(command) {
			case 1: t = u; u = l; l = d; d = r; r = t; // 동
				break;
			case 2: t = u; u = r; r = d; d = l; l = t; // 서
				break;
			case 3: t = u; u = f; f = d; d = b; b = t; // 북
				break;
			case 4: t = u; u = b; b = d; d = f; f = t; // 남
				break;
			}
			// 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사 
			if(map[xx][yy] == 0) map[xx][yy] = d;
			// 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0
			else {
				d = map[xx][yy];
				map[xx][yy] = 0;
			}
			x = xx;
			y = yy;
			
			sb.append(u + "\n");
		}
		
		System.out.println(sb);
	}
	
}