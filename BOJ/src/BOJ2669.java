import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2669 {

	public static void main(String[] args) throws IOException {	
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] map = new boolean[110][110];
		
		int res = 0;
		for (int c = 0; c < 4; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int sy = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			
			for (int i = sx; i < ex; i++) {
				for (int j = sy; j < ey; j++) {
					if(map[i][j]) continue;
					res++;
					map[i][j] = true;
				}
			}
		}
		
		System.out.println(res);
	}

}
