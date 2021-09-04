import java.io.*;
import java.util.*;

public class Main {

	FastScanner in;
	PrintWriter out;

	static class Point implements Comparable<Point>{
        int x, y, num;
        char dir;

		public Point(int x, int y, int num, char dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}

		@Override
        public int compareTo(Point o) {
			if (o.dir == 'E') return Integer.compare(this.y, o.y);
			else return Integer.compare(this.x, o.x);
        }
    }

	static int N, a, b, moveCnt[];
	static boolean isStopped[];
	static ArrayList<Point> ECows, NCows;
	void solve() {
		N = in.nextInt();
		ECows = new ArrayList<>();
		NCows = new ArrayList<>();
		moveCnt = new int[N];
		isStopped = new boolean[N];
		for (int i = 0; i < N; i++) {
			char dir = in.nextToken().charAt(0);
			int x = in.nextInt();
			int y = in.nextInt();
			if(dir == 'E') ECows.add(new Point(x, y, i, dir));
			else NCows.add(new Point(x, y, i, dir));
		}

		Collections.sort(ECows);
		Collections.sort(NCows);
		
		// 동쪽 방향으로 가는 소들
		for (Point ep : ECows) {
			// 북쪽 방향으로 가는 소들
			for (Point np : NCows) { 
				if (isStopped[np.num]) continue; // 해당 북쪽 방향 소가 멈췄다면 pass
				if (ep.x > np.x || ep.y < np.y) continue; // 두 방향의 소가 마주칠 일이 없다면 pass
				
				if (np.x - ep.x > ep.y - np.y) {// x의 차이가 y의 차이보다 크다면 동쪽 방향 소가 충돌
					isStopped[ep.num] = true;
					moveCnt[np.num] =  ep.y - np.y;
					moveCnt[ep.num] =  np.x - ep.x - 1;
					break;
				} else if (np.x - ep.x < ep.y - np.y) {// y의 차이가 x의 차이보다 크다면 북쪽 방향 소가 충돌
					isStopped[np.num] = true;
					moveCnt[np.num] =  ep.y - np.y - 1;
					moveCnt[ep.num] =  np.x - ep.x;
				} else {
					//소가 동시에 같은 셀로 오는 경우 셀 공유 
					moveCnt[np.num] =  ep.y - np.y;
					moveCnt[ep.num] =  np.x - ep.x;
				}
			}
		}
		
		// 멈추지 않은 소는 돌진
		for (int i = 0; i < N; i++) {
			if(!isStopped[i]) out.println("Infinity");
			else out.println(moveCnt[i] + 1);
		}
	}

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