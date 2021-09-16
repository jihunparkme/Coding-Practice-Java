import java.io.*;
import java.util.*;

public class Main {

	FastScanner in;
	PrintWriter out;

	static class Cow {
		int year;
		String zodiac;
		
		public Cow(int year, String zodiac) {
			this.year = year;
			this.zodiac = zodiac;
		}
	}
	static Map<String, Integer> zodiacMap =  new HashMap<String, Integer>() {
		{
	        put("Ox", 1);
	        put("Tiger", 2);
	        put("Rabbit", 3);
	        put("Dragon", 4);
	        put("Snake", 5);
	        put("Horse", 6);
	        put("Goat", 7);
	        put("Monkey", 8);
	        put("Rooster", 9);
	        put("Dog", 10);
	        put("Pig", 11);
	        put("Rat", 12);
		}
	};
	void solve() {
		
		int N = in.nextInt();
		Map<String, Cow> birthYears = new HashMap<>();
		birthYears.put("Bessie", new Cow(2021, "Ox"));
		for (int i = 0; i < N; i++) {
			String name = in.nextToken();
			in.nextToken(); // born
			in.nextToken(); // in
			String time = in.nextToken(); // previous-next
			String zodiac = in.nextToken();
			in.nextToken(); // year
			in.nextToken(); // from
			String fromName = in.nextToken();
			
			int to = zodiacMap.get(zodiac);
			int from = zodiacMap.get(birthYears.get(fromName).zodiac);
			int y = 0;
			int birth = 0;
			if ("previous".equals(time)) {
				if (to < from) 	y = from - to;
				else if (to == from) y = 12;
				else y = from + (12 - to);
				birth = birthYears.get(fromName).year - y;
			} else { // next
				if (to < from) y = (12 - from) + to;
				else if (to == from) y = 12;
				else y = to - from;
				birth = birthYears.get(fromName).year + y;
			}
			
			birthYears.put(name, new Cow(birth, zodiac));
		}	
		
		out.println(Math.abs(birthYears.get("Bessie").year - birthYears.get("Elsie").year));
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