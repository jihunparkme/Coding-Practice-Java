import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2563 {
	
	static int K, cnt;
	static boolean map[][];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		map = new boolean[110][110];
		int x, y, dstX, dstY;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken()); 
			x = Integer.parseInt(st.nextToken());
			// 크기가 10인 정자각형
			dstX = x + 10;
			dstY = y + 10;
			
			for (int xx = x; xx < dstX; xx++) {
				for (int yy = y; yy < dstY; yy++) {
					if(map[xx][yy]) continue;
					cnt++;
					map[xx][yy] = true;
				}
			}
		}
		
		System.out.println(cnt);
	}
	
}
