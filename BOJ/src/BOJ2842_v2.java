import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2842_v2 {

	static int N, P;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 음의 수
		P = Integer.parseInt(st.nextToken()); // 프렛의 수
		// 기타 줄 세팅
		int[][] line = new int[7][P];
		int[] tops = new int[7];
		for (int i = 1; i <= 6; i++) {
			tops[i] = -1;
		}
		
		// 어떤 음을 쳐볼까?
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 프렛 누르기
			if(tops[a] == -1 || line[a][tops[a]] < b) {
				// 이미 음을 누르고 있다면
				if(tops[a] >= 0 && line[a][tops[a]] == b) continue;
				
				line[a][++tops[a]] = b;
				cnt++;
				
				continue;
			}
			
			// 프렛 떼기
			while(tops[a] >= 0 && line[a][tops[a]] > b) {
				tops[a]--;
				cnt++;
			}
			// 이미 음을 누르고 있다면
			if(tops[a] >= 0 && line[a][tops[a]] == b) continue;
			// 새로 음을 눌러야 하면
			line[a][++tops[a]] = b;
			cnt++;
		}
		
		System.out.println(cnt);

	}

}
