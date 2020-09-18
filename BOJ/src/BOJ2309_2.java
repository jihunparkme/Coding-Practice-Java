import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2309_2 {

	static int[] baby, selected;
	static boolean isFin;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		baby = new int[9];
		selected = new int[7];
		for (int i = 0; i < 9; i++) {
			baby[i] = Integer.parseInt(br.readLine());
		}
		
		comb(0, 0, 0);
	}

	private static void comb(int idx, int cnt, int sum) {
		
		if(isFin) return;
		if(sum > 100) return;
		// 일곱 난쟁이의 키 합은 100
		if(cnt == 7) {
			if(sum == 100) {
				isFin = true;
				// 일곱 난쟁이의 키를 오름차순으로 출력	
				Arrays.sort(selected);
				for (int i = 0; i < 7; i++) {
					System.out.println(selected[i]);
				}
			}
			
			return;
		}
		
		for (int i = idx; i < 9; i++) {
			selected[cnt] = baby[i];
			comb(i + 1, cnt + 1, sum + baby[i]);
		}
	}

}
