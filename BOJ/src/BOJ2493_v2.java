import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493_v2 {

	static int N, res[], towers[];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		res = new int[N + 1];
		towers = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			towers[i] = Integer.parseInt(st.nextToken());
		}

		solve();

		for (int i = 1; i <= N; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void solve() {
	
		Stack<Integer> st = new Stack<>();
		
		for (int i = N; i >= 1; i--) {
			// Stack이 비어있다면 push
			if(st.isEmpty()) st.push(i);
			else {
				// Stack이 비어있지 않고, i번째 타워가 stack의 top보다 크다면
				while(!st.isEmpty() && towers[i] >= towers[st.peek()]) {
					// stack의 top을 빼내고 stack top index에 i번을 저장
					res[st.pop()] = i;
				}
				// 동작이 끝나면 i번째 타워를 stack push
				st.push(i);
			}
			
		}
		
	}

}
