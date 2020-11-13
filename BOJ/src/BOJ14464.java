import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ14464 {

	static int C, N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		int[] chickens = new int[C];
		for (int i = 0; i < C; i++) {
			chickens[i] = Integer.parseInt(br.readLine());
		}

		PriorityQueue<Cow> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			pq.add(new Cow(a, b));
		}
		
		Arrays.sort(chickens);
		System.out.println(process(chickens, pq));
	}


	private static int process(int[] chickens, PriorityQueue<Cow> pq) {
		
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			
			boolean isHelp = false;
			for (int i = 0; i < C; i++) {
				// 닭의 도움을 받아 소가 길을 건널 수 있다면
				if(chickens[i] >= pq.peek().s && chickens[i] <= pq.peek().e && chickens[i] > 0) {
					cnt++;
					pq.poll();
					isHelp = true;
					chickens[i] = -1;
					
					break;
				}
			}
			// 소가 도움을 받을 수 없을 경우
			if(!isHelp) pq.poll();
		}
		
		return cnt;
	}


	static class Cow implements Comparable<Cow> {
		int s, e;

		public Cow(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Cow o) {
			if(this.e != o.e) return Integer.compare(this.e, o.e); 
			else return Integer.compare(this.s, o.s);
		}

	}
}

/*

C * N 4억


*/