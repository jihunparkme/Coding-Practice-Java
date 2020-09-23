import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2096 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] min = new int[3];
		int[] max = new int[3];

		// 첫 줄 세팅
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			
			min[i] = tmp;
			max[i] = tmp;
		}
		
		int a, b, c, n0, n1, n2;
		for (int i = 0; i < N - 1; i++) {
			
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			// 최소 구하기
			n0 = n1 = n2 = 0;
			n0 = min[0] + a;
			n1 = min[0] + b;
			
			n0 = Math.min(n0, min[1] + a);
			n1 = Math.min(n1, min[1] + b);
			n2 = min[1] + c;
			
			n1 = Math.min(n1, min[2] + b);
			n2 = Math.min(n2, min[2] + c);
			
			min[0] = n0;
			min[1] = n1;
			min[2] = n2;
			
			// 최대 구하기
			n0 = n1 = n2 = 0;
			n0 = max[0] + a;
			n1 = max[0] + b;
			
			n0 = Math.max(n0, max[1] + a);
			n1 = Math.max(n1, max[1] + b);
			n2 = max[1] + c;
			
			n1 = Math.max(n1, max[2] + b);
			n2 = Math.max(n2, max[2] + c);
			
			max[0] = n0;
			max[1] = n1;
			max[2] = n2;
		}
		
		Arrays.sort(min);
		Arrays.sort(max);
		
		System.out.println(max[2] + " " + min[0]);
	}

}
