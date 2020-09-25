import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOj10157_v2 {

	static int[] dx = { 1, 0, -1, 0 }, dy = { 0, 1, 0, -1 };
	static int C, R ,K, resX, resY;
	static boolean[][] check;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		check = new boolean[R][C];
		
		int res = go();
		if(res == -1) System.out.println('0');
		else System.out.println(resX + " " + resY);
	}

	private static int go() {
		// 시작점
		// dir(방향) 하:0, 우:1, 상:2, 좌: 3
		int x = -1, y = 0, dir = 0;
		for (int i = 1; i <= R*C; i++) {
			int xx = x + dx[dir];
			int yy = y + dy[dir];
			// 범위 넘어가거나 이미 확인했으면 방향 전환
			if(xx < 0 || xx >= R || yy < 0 || yy >= C || check[xx][yy]) {
				dir = (dir + 1) % 4;
				i--;
				continue;
			}
			x = xx;
			y = yy;
			check[x][y] = true;
			if(i == K) {
				resX = y + 1;
				resY = x + 1;
				return i;
			}
		}
		
		return -1;
	}

}
