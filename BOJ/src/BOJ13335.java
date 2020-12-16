import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13335 {

	static int n, w, L, trucks[], bridge[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 다리를 건너는 트럭의 수
		w = Integer.parseInt(st.nextToken()); // 다리의 길이
		L = Integer.parseInt(st.nextToken()); // 다리의 최대하중
		
		bridge = new int[w];
		trucks = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			trucks[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(process());
	}

	private static int process() {
		
		int time = 0, weight = 0, idx = 0;
		// 모든 트럭이 다리를 건널 때까지
		while(true) {
			// 다리 위의 트럭이 이동
			int reach = move();
			// 다리를 건넌 트럭이 있다면
			if(reach != 0 ) weight -= reach;
			// 다리에 대기중인 트럭이 올라올 수 있다면
			if(idx < n && weight + trucks[idx] <= L) {
				bridge[w - 1] = trucks[idx];
				weight += trucks[idx];
				idx++;
			}
			
			time++;
			
			if(idx == n && weight == 0) return time;
		}
	}

	private static int move() {
		
		int tmp = bridge[0];
		// 다리 위의 트럭을 이동
		for (int i = 1; i < w; i++) {
			bridge[i - 1] = bridge[i]; 
		}
		bridge[w - 1] = 0;
		
		return tmp;
	}
	
}