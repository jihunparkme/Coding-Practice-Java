import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1204 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		int N = 1000;
	
		for (int tc = 1; tc <= T; tc++) {
			
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int score[] = new int[101];
			// 점수 입력
			for (int i = 0; i < N; i++) 
				score[Integer.parseInt(st.nextToken())]++;
			// 최빈수 찾기
			int maxMode = 0;
			int maxScore = 0;
			for (int i = 1; i < 100; i++) {
				// 최빈수가 여러개일 경우 가장 큰 점수를 출력
				if(maxMode <= score[i]) {
					maxMode = score[i];
					maxScore = i;
				}
			}
			
			System.out.println("#" + tc + " " + maxScore);
		}
	}
}
