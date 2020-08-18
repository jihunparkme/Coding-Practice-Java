import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1233 {

	static int T, N;
	static char node[];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = 10;

		for (int tc = 1; tc <= T; tc++) {
			int res = 1;
			N = Integer.parseInt(br.readLine());
			node = new char[N + 1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				st.nextToken();
				node[i] = st.nextToken().charAt(0);
				// 뒤에 입력값이 더 있다면 부모 노드
				if (st.hasMoreTokens()) {
					// 부모 노드에 숫자가 들어왔다면 불가능
					if (node[i] >= '0') res = 0;
				} else {
					// 리프 노드에 연산자가 들어왔다면 불가능
					if (node[i] < '0') res = 0;
				}
			}

			System.out.println("#" + tc + " " + res);
		}
	}
}