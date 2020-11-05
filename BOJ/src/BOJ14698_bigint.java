import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import jdk.jfr.Unsigned;

public class BOJ14698_bigint {

	static final int MOD = 1_000_000_007;
	static int N;
	static PriorityQueue<Long> pq[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		pq = new PriorityQueue[2];
		pq[0] = new PriorityQueue<>();
		pq[1] = new PriorityQueue<>();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			
			pq[0].clear();
			pq[1].clear();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				pq[0].add(Long.parseLong(st.nextToken()));
			}
			
			System.out.println(process());
		}
		
	}

	private static BigInteger process() {
		
		if(pq[0].size() == 1) return BigInteger.ONE;
		
		BigInteger cost = BigInteger.ONE;
		long a =0, b = 0, r = 0;
		int idx = 0;
		
		// N 마리의 슬라임을 모두 합성해서 1마리의 슬라임으로.
		while(true) {
			
			while(!pq[idx].isEmpty()) {
				a = pq[idx].poll();
				if(pq[idx].isEmpty()) {
					pq[idx ^ 1].add(a);
					break;
				}
				b = pq[idx].poll();
				
				// 각 합성 단계마다 필요한 전기 에너지들을 모두 곱한 값이 비용
				r = a * b;
				cost = cost.multiply(BigInteger.valueOf(r));
				pq[idx ^ 1].add(r);
			}
			
			idx ^= 1;
			if(pq[idx].size() == 1) return cost.mod(BigInteger.valueOf(MOD));
		}
	}
	
}
