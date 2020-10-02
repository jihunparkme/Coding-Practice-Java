import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14466 {

	static int N, K, R;
	static Bridge bridges[];
	static boolean[][] isCow;
	static class Bridge {
		int sX, sY, eX, eY;

		public Bridge(int sX, int sY, int eX, int eY) {
			super();
			this.sX = sX;
			this.sY = sY;
			this.eX = eX;
			this.eY = eY;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		isCow = new boolean[N][N];
		bridges = new Bridge[R];
		
		// 다리 정보 입력
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			bridges[i] = new Bridge(a, b, c, d);
		}
		
		// 소 위치 입력
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			isCow[a][b] = true;
		}
		
		System.out.println(go());
	}

	private static int go() {
		
		int cnt = 0;
		
		for (int i = 0; i < R; i++) {
			int sx = bridges[i].sX;
			int sy = bridges[i].sY;
			int ex = bridges[i].eX;
			int ey = bridges[i].eY;
			
			if(isCow[sx][sy] && isCow[ex][ey]) cnt++;
		}
		
		return cnt;
	}

}
