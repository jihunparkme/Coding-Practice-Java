import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14465 {

	static int N, K, B;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		int[] sum = new int[N + 1];
		boolean[] isBroken = new boolean[N + 1];
		for (int i = 0; i < B; i++) {
			isBroken[Integer.parseInt(br.readLine())] = true;
		}
		
		int cnt = 0;
		// K번째 횡단보도까지 망가진 횡단보도의 개수 count
		for (int i = 1; i <= K; i++) {
			if(isBroken[i]) cnt++;
		}
		sum[K] = cnt;
		int res = cnt;

		for (int i = K + 1; i <= N; i++) {
			int tmp = sum[i - 1];
			// 이번 횡단보도도 망가졌다면
			if(isBroken[i]) tmp++;
			// K번째 전에 망가진 횡단보도가 있었다면
			if(isBroken[i - K]) tmp--;
			
			sum[i] = tmp;
			res = Math.min(res, tmp);
		}
		
		System.out.println(res);
	}

}