import java.io.*;
import java.util.*;

public class Main {

	FastScanner in;
	PrintWriter out;

	static class Point implements Comparable<Point> {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void switching() {
			int tmp = this.x;
			this.x = this.y;
			this.y = tmp;
		}

		@Override
		public int compareTo(Point p) {
			if (this.x == p.x) {
				return Integer.compare(this.y, p.y);
			} else {
				return Integer.compare(this.x, p.x);
			}
		}
	}
	static int N, cntCamera = 0;
	static Map<Integer, Integer> cntCowByLine;
	static List<Point> cowsList;
	void solve() {
		
		N = in.nextInt();
		cowsList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			cowsList.add(new Point(x, y));
		}
		
		cowsList.sort(null);
		setCameraOnY();
		setCameraOnX();
		
		if (isPossible()) {
			out.println(1);
			return;
		}
		
		// x, y 좌표 바꿔서 다시 수행.
		cntCamera = 0;
		for (Point p : cowsList) p.switching();
		
		cowsList.sort(null);
		setCameraOnY();
		setCameraOnX();
		
		if (isPossible()) {
			out.println(1);
		} else {
			out.println(0);
		}
			
	}
	
	private Boolean isPossible() {
		
		if (cntCamera > 3) {
			return false;
		} else {
			return true;
		}
		
	}

	private void setCameraOnX() {
		
		int xLine = -1;
		
		// x 좌표를 확인
		for (Point p : cowsList) {
			// 해당 점의 y가 다른 점 y와 중복되어 있다면 패스. 
			if (cntCowByLine.get(p.y) > 1) continue;
			
			// 감시망을 피한 소일 경우.
			// 이전에 설치한 X 선과 같은 라인에 있다면
			if (xLine == p.x) {
				cntCamera--;
			} else {
				// 그렇지 않다면 X선에 카메라를 설치하고 
				// xLine 갱신
				xLine = p.x;
			}
		}
		
	}

	private void setCameraOnY() {
		cntCowByLine = new HashMap<>();
		// y 좌표 기준으로 모두 카메라를 설치
		for (Point p : cowsList) {
			if (cntCowByLine.get(p.y) == null) {
				cntCowByLine.put(p.y, 1);
				cntCamera++;
			} else {
				cntCowByLine.put(p.y, cntCowByLine.get(p.y) + 1);
			}
		}
	}

	/********************************** Input function **********************************/
	
	void run() {
		in = new FastScanner();
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	public static void main(String[] args) {
		new Main().run();
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		public FastScanner() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastScanner(String s) {
			try {
				br = new BufferedReader(new FileReader(s));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		String nextToken() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(nextToken());
		}

		long nextLong() {
			return Long.parseLong(nextToken());
		}

		double nextDouble() {
			return Double.parseDouble(nextToken());
		}
	}
}