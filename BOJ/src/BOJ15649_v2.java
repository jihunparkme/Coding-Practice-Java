import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15649_v2 {
	
	static int N, M;
	static int arr[];
	static boolean visited[];
	static int sel[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// init
		arr = new int[N];
		visited = new boolean[N];
		sel = new int[M];
		for (int i = 0; i < N; i++) 
			arr[i] = i+1;
		
		process(0);
		System.out.println(sb);
	}
	
	public static void process(int cnt) {
		// M개의 자연수를 골랐다면 
		if(cnt == M) {
			int x = 0;
			// 선택된 자연수를 buffer에 저장
			for (int j = 0; j < N; j++) {
				if(visited[j]) {
					sb.append(sel[x++] + " ");
				}
			}
			sb.append('\n');
			return;
		}
		for (int i = 0; i < N; i++) {
			// 중복을 허용하지 않으므로
			// 사용되지 않은 자연수일 경우
			if(!visited[i]) {
				// 이 자연수를 사용해보자.
				visited[i] = true;
				// 사용된 자연수를 배열에 저장
				sel[cnt] = arr[i];
				
				// 위 자연수를 사용했으므로 cnt + 1 해주고, 재귀함수로 넘겨주자.
				process(cnt+1);
				
				// 이 자연수를 사용하지 않을 경우.
				visited[i] = false;
			}
		}
	}
}
