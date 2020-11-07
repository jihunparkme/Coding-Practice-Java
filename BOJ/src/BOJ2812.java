import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2812 {

	static int N, K;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		String num = br.readLine();
		Stack<Integer> res = process(num);
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < N - K; i++) {
			sb.append(res.get(i));
		}
		System.out.println(sb);
	}

	private static Stack<Integer> process(String num) {

		int cnt = 0; // 지운 숫자의 개수
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < N; i++) {
			// 현재 숫자
			int now = num.charAt(i) - '0';
			// 아직 숫자를 더 지울 수 있고, stack의 top보다 지금 숫자보다 더 크다면 
			while (cnt < K && !stack.isEmpty() && stack.peek() < now) {
				// top에 있는 숫자를 지워주자.
				stack.pop();
				cnt++;
			}
			// 새로운 숫자를 추가
			stack.add(now);
		}

		return stack;
	}

}
