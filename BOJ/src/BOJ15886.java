import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15886 {

	static int N, cnt[];
	static char map[];
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N];
		cnt = new int[N];
		map = br.readLine().toCharArray();
		
		System.out.println(process());
	}

	private static int process() {
		
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			go(i);
		}
		
		int res = 0;
		int prev = cnt[0];
		for (int i = 1; i < N; i++) {
			if(prev == cnt[i]) res++;
			prev = cnt[i];
		}
		
		return res;
	}

	private static void go(int i) {
	
		visited[i] = true;
		cnt[i]++;
		
		if(map[i] == 'E') {
			if(visited[i + 1]) return;
			go(i + 1);
		} else {
			if(visited[i - 1]) return;
			go(i - 1);
		}
	}

}
