import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20159 {

	static int N, cards[];
	
	public static void main(String[] args) throws IOException {
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		cards = new int[N];		

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(process());
	}

	private static int process() {

		// 밑장은 최대 한 번 뺄 수 있음
		int[][] score = new int[2][N/2];
		int first = cards[N - 1];
		
		score[0][0] = cards[0];
		score[1][0] = cards[0];
		int idx = 1;
		for (int i = 1; i < N; i++) {
			// 상대에게 패를 분배
			if(i % 2 != 0) {
				// 첫 분배에서 밑장을 뺄 경우
				first += cards[i];	
				
				continue;
			}
			
			// 밑장을 안 뺄 경우
			score[0][idx] = score[0][idx - 1] + cards[i]; 
			
			// 밑장을 뺄 경우
			score[1][idx] = score[0][idx - 1] + cards[N - 1];
			
			idx++;
		}
		
		return Math.max(first - cards[N - 1], Math.max(score[0][N/2 - 1], score[1][N/2 - 1]));
	}

}
