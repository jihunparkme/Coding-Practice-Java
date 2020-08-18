import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15649 {
	
	static int N, M;
	static int arr[];
	static boolean visit[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		visit = new boolean[N];
		for (int i = 0; i < N; i++) 
			arr[i] = i+1;
		
		process(0);
	}
	
	public static void process(int idx) {
		if(idx == M) {
			System.out.println(sb);
			return;
		}
		for (int i = 0; i < N; i++) {
			if(!visit[i]) {
				// 방문할 경우
				visit[i] = true;
				sb.append(arr[i] + " ");
				
				process(idx+1);
				
				// 방문하지 않을 경우
				sb.setLength(sb.length()-2);
				visit[i] = false;
			}
		}
	}
}
