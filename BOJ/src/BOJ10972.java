import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10972 {

	static int N, nums[];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(st.nextToken());

		if (nextPermutation()) {
			for (int i = 0; i < N; i++) {
				System.out.print(nums[i] + " ");
			}
		}
		else
			System.out.println("-1");
	}

	public static boolean nextPermutation() {
		int i = N - 1;
		while (i > 0 && nums[i - 1] >= nums[i]) i--;
		if (i == 0) return false;

		int j = N - 1;
		while(nums[i-1] >= nums[j]) j--;
		
		swap(i-1, j);
		
		int k = N-1;
		while(i<k) swap(i++, k--);
		
		return true;
	}
	
	public static void swap(int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
