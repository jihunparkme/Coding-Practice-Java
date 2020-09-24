import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14696 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] A, B;
		
		int N = Integer.parseInt(br.readLine());
		for (int r = 0; r < N; r++) {
			
			A = new int[5];
			B = new int[5];

			// A 딱지
			st = new StringTokenizer(br.readLine());
			if(st.hasMoreTokens()) A[Integer.parseInt(st.nextToken())]++;
			// B 딱지
			st = new StringTokenizer(br.readLine());
			if(st.hasMoreTokens()) B[Integer.parseInt(st.nextToken())]++;
			
			// 별 : 4, 동그라미 : 3, 네모 : 2, 세모 : 1
			// 만약 두 딱지의 별의 개수가 다르다면, 별이 많은 쪽의 딱지가 이긴다.
			// 별의 개수가 같고 동그라미의 개수가 다르다면, 동그라미가 많은 쪽의 딱지가 이긴다.
			// 별, 동그라미의 개수가 각각 같고 네모의 개수가 다르다면, 네모가 많은 쪽의 딱지가 이긴다.
			// 별, 동그라미, 네모의 개수가 각각 같고 세모의 개수가 다르다면, 세모가 많은 쪽의 딱지가 이긴다.
			// 별, 동그라미, 네모, 세모의 개수가 각각 모두 같다면 무승부이다.
			
			
		}
		
	}

}
