import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9372_v2 {

	static int N, M;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 국가의 수
			M = Integer.parseInt(st.nextToken());	// 비행기의 종류
			
			// 비행기편 입력
			for (int i = 0; i < M; i++) {
				br.readLine();
			}
			
			System.out.println(N-1);
		}
	}

}
