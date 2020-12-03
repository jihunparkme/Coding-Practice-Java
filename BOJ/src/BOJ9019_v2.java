import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9019_v2 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			sb.append(process(A, B) + "\n");
		}

		System.out.println(sb.toString());
	}

	static char[] oper = {'D', 'S', 'L', 'R'};
	private static StringBuilder process(int A, int B) {

		boolean[] checked = new boolean[10000];
		char[] operations = new char[10000];
		int[] before = new int[10000];
		Queue<Integer> q = new LinkedList<>();
		
		q.add(A);
		
		while(!q.isEmpty()) {
			
			int now = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int res = command(now, i);
				// 이미 확인한 숫자가 아닐 경우
				if(!checked[res]) {
					checked[res] = true; // 방문 체크
					q.add(res); // 큐에 추가
					before[res] = now; // 현재 결과 index에 명령을 수행하기 전 숫자를 저장
					operations[res] = oper[i]; // 현재 결과 index에 어떤 명령을 수행했는지 저장
				}
				// 숫자 B가 만들어졌을 경우
				if(res == B) {
					int tmp = B;
					StringBuilder sb = new StringBuilder();
					// before 배열에 저장된 operation 값을 tracking
					while(tmp != A) {
						sb.insert(0, operations[tmp]);
						tmp = before[tmp];
					}
					
					return sb;
				}
			}
		}
		
		return null;
	}

	private static int command(int now, int comd) {
		
		switch(comd) {
		// D 는 n을 두 배로
		case 0: return (now * 2) % 10000;
		// S 는 n에서 1 을 뺀 결과. n이 0 이라면 9999 가 대신 레지스터에 저장된다.
		case 1: return now == 0 ? 9999 : now - 1;
		// L 은 n의 각 자릿수를 왼편으로 회전(d2, d3, d4, d1)
		case 2: return (now % 1000) * 10 + (now / 1000);
		// R 은 n의 각 자릿수를 오른편으로 회전(d4, d1, d2, d3)
		case 3: return (now % 10) * 1000 + (now / 10);
		}
		
		return 0;
	}

}
