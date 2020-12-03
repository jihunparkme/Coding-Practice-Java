import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9019 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			String res = process(A, B);
			sb.append(res + "\n");
		}

		System.out.println(sb);
	}

	static String[] oper = {"D", "S", "L", "R"};
	private static String process(int A, int B) {

		Queue<Info> q = new LinkedList<>();
		boolean[] checked = new boolean[10000];
		q.add(new Info(A, ""));
		
		while(!q.isEmpty()) {
			
			Info now = q.poll();
			if(now.n == B) return now.comd;
			
			for (int i = 0; i < 4; i++) {
				int res = command(now, i);
				
				if(!checked[res]) {
					checked[res] = true;
					q.add(new Info(res, now.comd + oper[i]));
				}
			}
		}
		
		return "";
	}

	private static int command(Info now, int comd) {
		
		switch(comd) {
		// D 는 n을 두 배로
		case 0: return (now.n * 2) % 10000;
		// S 는 n에서 1 을 뺀 결과. n이 0 이라면 9999 가 대신 레지스터에 저장된다.
		case 1: return now.n == 0 ? 9999 : now.n - 1;
		// L 은 n의 각 자릿수를 왼편으로 회전(d2, d3, d4, d1)
		case 2: return (now.n % 1000) * 10 + (now.n / 1000);
		// R 은 n의 각 자릿수를 오른편으로 회전(d4, d1, d2, d3)
		case 3: return (now.n % 10) * 1000 + (now.n / 10);
		}
		
		return 0;
	}

	static class Info {
		int n;
		String comd;
		
		public Info(int n, String comd) {
			this.n = n;
			this.comd = comd;
		}

	}
}
