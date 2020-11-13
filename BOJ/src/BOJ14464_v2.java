import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ14464_v2 {

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

		Cow[] cows = new Cow[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			cows[i] = new Cow(a, b);
		}

		Arrays.sort(chickens);
		Arrays.sort(cows);
		
		System.out.println(process(chickens, cows));
	}

	private static int process(int[] chickens, Cow[] cows) {

		int cnt = 0, cIdx = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 0; i < C; i++) {
			// 닭이 도울 수 있는 소를 찾아보자.
			while(cIdx < N && cows[cIdx].s <= chickens[i]) {
				// 도울 수 있는 소의 end time을 queue에 추가
				pq.add(cows[cIdx++].e);
			}
			
			// 닭이 도울 수 없는 소는 보내주자.
			while(!pq.isEmpty() && pq.peek() < chickens[i]) {
				pq.poll();
			}
			
			// pq에 소가 남아있다면 길을 건널 수 있게 도와주자.
			if(!pq.isEmpty()) {
				cnt++;
				pq.poll();
			}
		}

		return cnt;
	}

	static class Cow implements Comparable<Cow>{
		int s, e;

		public Cow(int s, int e) {
			this.s = s;
			this.e = e;
		}
		
		@Override
		public int compareTo(Cow o) {
			return Integer.compare(this.s, o.s);
		}
	}
}