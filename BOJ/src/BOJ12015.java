import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ12015 {

	static int[] nums;
	static ArrayList<Integer> lis;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		// 최장 증가 수열 저장
		lis = new ArrayList<>();			
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());  
			
			if(lis.isEmpty() || num > lis.get(lis.size() - 1))
				lis.add(num);
			else {
				int idx = binarySearch(lis.size(), num);
				lis.set(idx, num);
			}
		}
		
		System.out.println(lis.size());
	}

	private static int binarySearch(int end, int find) {
		
		int start = 0, mid = 0;
		// 이분탐색
		while(start <= end) {
			mid = (start + end) / 2;
			
			// find 보다 크다면
			if(find < lis.get(mid)) end = mid - 1;
			// find 보다 크다면
			else if(find > lis.get(mid)) start = mid + 1;
			// find 와 같다면
			else return mid;
		}
		
		return start;
	}

}
