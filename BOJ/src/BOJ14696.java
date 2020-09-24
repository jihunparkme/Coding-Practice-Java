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
			st.nextToken();
			while(st.hasMoreTokens()) 
				A[Integer.parseInt(st.nextToken())]++;
			// B 딱지
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while(st.hasMoreTokens()) 
				B[Integer.parseInt(st.nextToken())]++;
			
			System.out.println(play(A, B));
		}
		
	}

	private static char play(int[] a, int[] b) {
		
		// 별 : 4, 동그라미 : 3, 네모 : 2, 세모 : 1
		// 만약 두 딱지의 별의 개수가 다르다면, 별이 많은 쪽의 딱지가 이긴다.
		if(a[4] != b[4]) {
			if(a[4] > b[4]) return 'A';
			else return 'B';
		}
		
		// 별의 개수가 같고 동그라미의 개수가 다르다면, 동그라미가 많은 쪽의 딱지가 이긴다.
		if(a[3] != b[3]) {
			if(a[3] > b[3]) return 'A';
			else return 'B';
		}
		
		// 별, 동그라미의 개수가 각각 같고 네모의 개수가 다르다면, 네모가 많은 쪽의 딱지가 이긴다.
		if(a[2] != b[2]) {
			if(a[2] > b[2]) return 'A';
			else return 'B';
		}
		
		// 별, 동그라미, 네모의 개수가 각각 같고 세모의 개수가 다르다면, 세모가 많은 쪽의 딱지가 이긴다.
		if(a[1] != b[1]) {
			if(a[1] > b[1]) return 'A';
			else return 'B';
		}
		
		// 별, 동그라미, 네모, 세모의 개수가 각각 모두 같다면 무승부이다.
		return 'D';
	}

}
