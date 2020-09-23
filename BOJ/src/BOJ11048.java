import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11048 {

	static int R, C, map[][], dp[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R+1][C+1];
		dp = new int[R+1][C+1];

		// 미로 정보 입력
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// [r-1][c-1], [r-1][c], [r][c-1] 까지의 사탕 개수 + 현재 위치에 있는 사탕 개수
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				dp[r][c] = Math.max(dp[r-1][c-1] + map[r][c], 
						Math.max(dp[r-1][c] + map[r][c], dp[r][c-1] + map[r][c]));
			}
		}
		
		System.out.println(dp[R][C]);
	}

}
