import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ14698 {

	static final int MOD = 1_000_000_007;
	static int N;
	static PriorityQueue<Long> pq;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		
		pq = new PriorityQueue<>();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			
			pq.clear();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				pq.add(Long.parseLong((st.nextToken())));
			}
			
			sb.append(process() + "\n");
		}
		
		System.out.println(sb);
	}

	private static long process() {

		long cost = 1, mul = 0;
		// N 마리의 슬라임을 모두 합성해서 1마리의 슬라임으로.
		while(pq.size() > 1) {
			// 각 합성 단계마다 필요한 전기 에너지들을 모두 곱한 값이 비용
			mul = pq.poll() * pq.poll();
			cost = (cost * (mul % MOD)) % MOD;

			pq.add(mul);
		}
		
		return cost;
	}
	
}