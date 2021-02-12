import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1966 {

	static int N, M, num;
	static Queue<Docx> q;
	static class Docx {
		int idx, w;
		public Docx(int idx, int w) {
			this.idx = idx;
			this.w = w;
		}
		
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		while(tc-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			q = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				q.add(new Docx(i, Integer.parseInt(st.nextToken())));
			}
			
			System.out.println(findOrder());
		}
	}

	private static int findOrder() {
		int cnt = 0;
		while(!q.isEmpty()) {
			Docx now = q.poll();
			// 현재 문서보다 중요도가 높은 문서가 없다면
			if (isMostImportant(now)) {
				cnt++;
			} else {
				q.add(now);
				continue;
			}
			// 궁금한 문서일 경우
			if (now.idx == M) return cnt; 
		}
		
		return cnt;
	}

	private static boolean isMostImportant(Docx now) {
		for (Docx p : q) {
			if (now.w < p.w) return false;
		}
		
		return true;
	}

}