import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ8983 {

	static int M, N, L, range[];
	static Animal[] animals;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); // 사대의 수 
		N = Integer.parseInt(st.nextToken()); // 동물의 수
		L = Integer.parseInt(st.nextToken()); // 사정거리
		range = new int[M];
		animals = new Animal[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			range[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			animals[i] = new Animal(a, b, false);
		}
		
		System.out.println(process());
	}
	
	private static int process() {
		
		int res = 0;
		// 동물의 위치를 정렬
		Arrays.sort(animals);

		// 사대에서 동물을 잡아보자.
		int idx = 0;
		for (int i = 0; i < M; i++) {
			// 현재 사대와 수평선에 가장 근접해 있는 동물의 인덱스
			idx = search(range[i]);
			res += shot(range[i], idx);
		}
		
		return res;
	}

	private static int shot(int pos, int idx) {

		int cnt = 0, size = 0;
		boolean left = false, right = false; // 각 방향의 탐색 끝
		
		while(!(left && right)) {
			
			int lf =  idx - size, rt = idx + size;
			if(lf < 0) left = true;
			if(rt >= N) right = true;
			
			// 왼쪽 확인
			if(!left) {
				int lfDist = Math.abs(pos - animals[lf].r) + animals[lf].c;
				// 동물이 사거리 안에 들고 이미 잡은 동물이 아니라면
				if(lfDist <= L && !animals[lf].catched) {
					cnt++;
					animals[lf].catched = true;
				}
			}
			
			// 오른쪽 확인
			if(!right) {
				int rtDist = Math.abs(pos - animals[rt].r) + animals[rt].c;
				// 동물이 사거리 안에 들고 이미 잡은 동물이 아니라면
				if(rtDist <= L && !animals[rt].catched) {
					cnt++;
					animals[rt].catched = true;
				}
			}
			
			size++;
		}
		
		return cnt;
	}

	private static int search(int find) {
		
		int start = 0, mid = 0, end = N;
		while(start <= end) {
			mid = (start + end) / 2;
			
			if(find < animals[mid].r) end = mid - 1;
			else if(find > animals[mid].r) start = mid + 1;
			else return mid;
		}
		
		return start;
	}

	static class Animal implements Comparable<Animal> {
		int r, c;
		boolean catched;
		
		public Animal(int r, int c, boolean catched) {
			this.r = r;
			this.c = c;
			this.catched = catched;
		}

		@Override
		public int compareTo(Animal o) {
			return Integer.compare(this.r, o.r);
		}
	}

}

/*
모든 동물과 모든 사대를 확인하는 방법은
O(M * N) = 10,000,000,000
Naive한 방법으로 해결하기 힘들다.



Row 기준으로 동물의 위치를 정렬
*/