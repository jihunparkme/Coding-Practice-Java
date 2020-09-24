import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12015_v2 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		// 각 LIS의 길이를 만족하는 맨 끝에오는 최솟값을 저장
		int[] LIS = new int[N];	
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());  
		}
		
		int size = 0;
		for (int i = 0; i < N; i++) {
			// 탐색키를 찾으면 해당 index return
			// 못 찾으면 음수값으로 자신이 삽입될 위치 return
				// -(삽입위치) - 1 : (0일 경우를 구별하기 위해 -1)
			int tmp = Arrays.binarySearch(LIS, 0, size, arr[i]);
			
			// 중복값이 없다면 tmp : 음수값
			// 삽입위치 환산
			if(tmp < 0) 
				tmp = Math.abs(tmp) - 1;
			LIS[tmp] = arr[i];
			
			// 맨 뒤에 추가할 경우
			if(tmp == size) ++size;
		}
		
		System.out.println(size);
	}
	
}
