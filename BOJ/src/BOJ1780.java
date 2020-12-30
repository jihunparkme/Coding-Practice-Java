import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1780 {

	static int N, map[][], cnt[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		cnt = new int[3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) + 1;
			}
		}
		
		process(0, 0, N);
		for (int i = 0; i < 3; i++) {
			System.out.println(cnt[i]);
		}
	}

	private static void process(int r, int c, int size) {
		
		// 1. 모두 같은 수로 되어 있다면
		if(isAllSame(r, c, size)) {
			cnt[map[r][c]]++;
			
			return;
		} 
		
		// (1)이 아닌 경우 size를 3등분
		int newSize = size / 3;
		for (int i = r; i < r + size; i += newSize) {
			for (int j = c; j < c + size; j += newSize) {
				process(i, j, newSize);
			}
		}
	}

	private static boolean isAllSame(int r, int c, int size) {
		
		// 모두 같은 수로 되어 있는지 확인
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if(map[r][c] != map[i][j]) return false;
			}
		}
		
		return true;
	}

}
