import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2635 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int res = 0;
		int cnt, idx = 0, pprev, prev, now;
		for (int i = 0; i <= N; i++) {
			
			pprev = N;
			prev = i;
			cnt = 2;
			
			while(true) {
				now = pprev - prev;
				// 음의 정수가 만들어지면
				if(now < 0) {
					if(cnt > res) {
						res = cnt;
						idx = i;
					}
					break;
				}
				
				pprev = prev;
				prev = now;
				cnt++;
			}
		}

		System.out.println(res);
		pprev = N;
		prev = idx;
		
		System.out.print(N + " " + idx + " ");
		while(true) {
			now = pprev - prev;
			// 음의 정수가 만들어지면
			if(now < 0) {
				break;
			}
			
			System.out.print(now + " ");
			pprev = prev;
			prev = now;
		}
	}

}
