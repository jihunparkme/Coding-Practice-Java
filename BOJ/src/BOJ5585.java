import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5585 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = 1000 - Integer.parseInt(br.readLine());
		int[] coins = { 500, 100, 50, 10, 5, 1 };
		
		int cnt = 0;
		for (int i = 0; i < 6; i++) {
			if(K >= coins[i]) {
				cnt += K/coins[i];
				K %= coins[i];
			}
		}
		
		System.out.println(cnt);
	}

}
