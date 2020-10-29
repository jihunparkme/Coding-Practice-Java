import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ6198 {

	static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] building = new int[N];
		for (int i = 0; i < N; i++) {
			building[i] = Integer.parseInt(br.readLine());
		}

		System.out.println(process(building));
	}

	private static long process(int[] building) {
		
		long res = 0;
		Stack<Integer> stack = new Stack<>();
		// 1. 초기 빌딩 정보를 넣고 시작
		stack.add(building[0]);
		for (int i = 1; i < N; i++) {
			// 2. top 보다 작으면 push
			if (building[i] < stack.peek()) {
				stack.add(building[i]);
				res += stack.size() - 1;
			} else {
				while(!stack.isEmpty() && building[i] >= stack.peek())
					stack.pop();
				stack.add(building[i]);
				res += stack.size() - 1;
			}
		}
		
		return res;		
	}

}
