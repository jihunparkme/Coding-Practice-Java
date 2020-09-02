import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ19535_v3 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		long[] Edge = new long[N+1];
		long D = 0, G = 0; 
		
		Queue<Point> q = new LinkedList<>();
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			Edge[a]++; Edge[b]++;
			q.add(new Point(a, b));
		}

		// ㅈ Tree 찾기
		for (int i = 1; i <= N; i++) {
			if(Edge[i] >= 3) {
				// nC3 = n! / 3!(n-3)! = n(n-1)(n-2) / 3 * 2
				long n = Edge[i];
				G += (n * (n-1) * (n-2)) / (3 * 2);
			}
		}

		// ㄷ Tree 찾기
		while(!q.isEmpty()) {
			Point now = q.poll();
			D += (Edge[now.x] - 1) * (Edge[now.y] - 1);
		}

		if(G * 3 < D) System.out.println("D");
		else if(G * 3 > D) System.out.println("G");
		else System.out.println("DUDUDUNGA");
	}	
	
}
