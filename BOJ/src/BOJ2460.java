import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2460 {
	
	static int train[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		train = new int[10][2];
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			train[i][0] = Integer.parseInt(st.nextToken());
			train[i][1] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(findLargestMember());
	}

	private static int findLargestMember() {
		int cnt = 0, max = 0;
		
		for (int i = 0; i < 10; i++) {
			// 하차
			cnt -= train[i][0];
			max = max < cnt ? cnt : max;
			// 승차
			cnt += train[i][1];
			max = max < cnt ? cnt : max;
		}
		
		return max;
	}
}
