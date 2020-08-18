import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1931 {

	static class info implements Comparable<info>{
		int start, end;

		public info(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(info o) {
			// 종료 시간이 같다면
			// 시작 시간 기준으로 정렬
			// 다르다면, 종료시간 기준으로 정렬
			if(this.end == o.end)
				return Integer.compare(this.start, o.start);
			else 
				return Integer.compare(this.end, o.end);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		info[] timeLst= new info[N]; 
		// 회의 정보
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			timeLst[i] = new info(Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()));
		}
			
		// 종료시간 기준 정렬
		Arrays.sort(timeLst);
		
		int cnt = 0;
		int now = 0;
		for(info t : timeLst) {
			// 시작 시간이 현재시간보다 이르다면 pass
			if(t.start < now) continue;
			now = t.end;	// 현재 시간을 종료 시간으로 갱신
			cnt++;
		}
		
		System.out.println(cnt);
	}

}
