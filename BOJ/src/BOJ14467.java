import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14467 {

	static int N, observe[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		observe = new int[N];
		Arrays.fill(observe, -1);
		
		int cnt = 0;
		for (int i = 0; i < N; i++) {
		
			st = new StringTokenizer(br.readLine());
			
			int c = Integer.parseInt(st.nextToken()) - 1;
			int p = Integer.parseInt(st.nextToken());
			
			// 처음 확인하는 Cow
			if(observe[c] == -1) observe[c] = p;
			// 이미 확인했던 Cow라면 길을 건넜는지 확인
			else if(observe[c] != p) {
				observe[c] = p;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
