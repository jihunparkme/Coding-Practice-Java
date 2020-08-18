import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3499 {

	static String card[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			sb = new StringBuilder();
			card = new String[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) 
				card[i] = st.nextToken();
			
			sb.append("#" + tc + " ");
			// 카드가 짝수개
			if(N%2==0) {
				for (int i = 0; i < N/2 ; i++) 
					sb.append(card[i] + " " + card[i + N/2] + " ");
			}
			// 카드가 홀수개라면, 마지막 남은 카드를 더 추가
			if(N%2 != 0) {
				for (int i = 0; i < N/2 ; i++) 
					sb.append(card[i] + " " + card[i + (N/2+1)] + " ");
				
				sb.append(card[N/2]);
			}
			
			System.out.println(sb);
		}
		
	}
	
}
