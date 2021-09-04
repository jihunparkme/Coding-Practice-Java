import java.io.*;
import java.util.*;

public class Main {

	FastScanner in;
	PrintWriter out;

	static int N;
	static Map<String, Integer> dirMap =  new HashMap<String, Integer>() {
		{
	        put("NE", 1);
	        put("ES", 1);
	        put("SW", 1);
	        put("WN", 1);
	        put("WS", -1);
	        put("SE", -1);
	        put("EN", -1);
	        put("NW", -1);
	        put("EE", 0);
	        put("WW", 0);
	        put("SS", 0);
	        put("NN", 0);
		}
	};

	void solve() {
		try {
			N = in.nextInt();
			for (int i = 0; i < N; i++) {
				int rst = 0;
				String fence = in.br.readLine();
				
				for (int j = 0; j < fence.length() ; j++) {
					if (j == fence.length() - 1) {
						rst += dirMap.get(Character.toString(fence.charAt(j)) + Character.toString(fence.charAt(0))).intValue();
					} else {
						rst += dirMap.get(fence.substring(j, j+2)).intValue();
					}
				}
				
				if (rst == 4) out.println("CW");
				else out.println("CCW");
			}
		} catch (Exception e) {
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