import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ17280 {

	static int T, N, M;
	static int passenger[];
	static ArrayList<Driver> drivers;
	
	static class Driver implements Comparable<Driver>{
		int start, end;

		public Driver(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Driver o) {
			if(this.start != o.start) 
				return Integer.compare(this.start, o.start);
			else
				return Integer.compare(this.end, o.end);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int res = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			passenger = new int[N];
			drivers = new ArrayList<>();
			// 승객 정보
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) 
				passenger[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(passenger);
			
			// 드라이버 정보
			PriorityQueue<Driver> pq = new PriorityQueue<>();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				drivers.add(new Driver(start, end));
				pq.add(drivers.get(i));
			}
			/*
			x = [10, 20, 30]
			y = [2,  8, 25]
			z = [18, 8, 35]
			*/
			int pIdx = 0;
			while(!pq.isEmpty()) {
				Driver now = pq.poll();
				if(pIdx >= N) break;
				for (int i = pIdx; i < N; i++) {
					// 가장 가까운 거리를 가는 승객조차 태우지 못 할 경우 pass
					if(now.end < passenger[i]) break;
					if(now.start > passenger[i]) continue;
					// 승객을 태울 수 있을 경우
					if(now.start <= passenger[i] && now.end >= passenger[i]) {
						res++;
						pIdx = i + 1;
						break;
					}
				}
			}
			
			System.out.println(res);
		}
	}

}
