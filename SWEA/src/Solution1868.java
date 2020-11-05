import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1868 {

	static int N;
	static char map[][];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= N; tc++) {
			
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			
			process(0);
			
			System.out.println("#" + tc + " " );
		}

	}

	private static void process(int idx) {
		
		if(idx > N * N) return;
		
		int x = idx / N;
		int y = idx % N;
		
		if(map[x][y])
		
	}

}
