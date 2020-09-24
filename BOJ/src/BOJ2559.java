import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2559 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 온도를 측정한 전체 날짜
		int K = Integer.parseInt(st.nextToken());	// 합을 구하기 위한 연속적인 날짜의 수
		int[] tempers = new int[N];
		int[] sum = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < sum.length; i++) {
			tempers[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < K; i++) {
			sum[K -1] += tempers[i];
		}
		
		int max = sum[K -1];
		for (int start = 1, end = K; end < N; start++, end++) {
			sum[end] = tempers[end] + sum[end - 1] - tempers[start - 1];
			max = Math.max(max, sum[end]);
		}
		
		System.out.println(max);
	}

}
