import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {

	static int N, res[];
	static Stack<Tower> towers;
	static class Tower {
		int num, height;

		public Tower(int num, int height) {
			this.num = num;
			this.height = height;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		res = new int[N + 1];
		towers = new Stack<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			towers.add(new Tower(i, Integer.parseInt(st.nextToken())));
		}
		
		process();
		
		for (int i = 1; i <= N; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void process() {
		
		// 수신 타워를 찾지 못한 타워
		ArrayList<Tower> rest = new ArrayList<>();
		
		while(towers.size() != 1) {
			
			// 현재 타워 높이
			Tower now = towers.pop();
			
			// 왼쪽 타워에 신호를 보낼 수 있다면
			if(towers.peek().height > now.height) {
				// 신호가 닿은 왼쪽 타워의 번호를 저장
				res[now.num] = towers.peek().num;
				// 수신 타워를 찾지 못한 타워들도 확인
				if(!rest.isEmpty()) {
					for (int i = rest.size() - 1; i >= 0; i--) {
						Tower t = rest.get(i);
						if(towers.peek().height > t.height) {
							res[t.num] = towers.peek().num;
							rest.remove(i);
						}
					}
				}
			} 
			// 닿을 수 없다면
			else {
				rest.add(now);
			}
		}
	}

}
