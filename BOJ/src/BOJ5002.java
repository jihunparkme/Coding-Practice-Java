import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ5002 {

	static int X, M, W;
	static ArrayList<Character> line;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		X = Integer.parseInt(br.readLine());
		line = new ArrayList<>();
		String str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			line.add(str.charAt(i));
		}

		System.out.println(process());

	}

	private static int process() {

		// 남/여 차이가 기억할 수 있는 값보다 클 때까지
		while (!line.isEmpty()) {

			// 첫 번째 서있는 사람을 입장시킬 수 있다면
			if(check(0)) continue;
			
			// 첫 번째 서있는 사람을 입장시킬 수 없는 경우,
			
			// 두 번째에 서있는 사람이 없다면 종료
			if (line.size() == 1) return M + W;
			
			// 두 번째 서있는 사람을 입장시킬 수 있다면
			if(check(1)) continue;

			// 첫 번째, 두 번째 서있는 사람 모두 입장이 안된다면 종료
			return M + W;
		}
		
		return M + W;
	}

	private static boolean check(int th) {
		
		char gender = line.get(th);
		
		// 성별이 M일 경우
		if (gender == 'M' && Math.abs((M + 1) - W) <= X) {
			line.remove(th);
			M++;
			return true;
		}
		// 성별이 W일 경우
		else if (gender == 'W' && Math.abs(M - (W + 1)) <= X) {
			line.remove(th);
			W++;
			return true;
		}
		
		return false;
	}

}