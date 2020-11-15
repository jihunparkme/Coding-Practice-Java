import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ15886 {

	static int N, num[];
	static char map[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N];
		num = new int[N];
		map = br.readLine().toCharArray();
		
		System.out.println(process());
	}

	private static int process() {
		
		int id = 0;
		for (int i = 0; i < N; i++) {
			// 이미 길번호가 정해졌다면 pass
			if(num[i] != 0) continue;
			go(i, ++id);
		}
		
		int res = 0;
		int prev = num[0];
		for (int i = 1; i < N; i++) {
			if(prev != num[i]) res++;
			prev = num[i];
		}
		
		return res + 1;
	}

	private static int go(int idx, int id) {
		// 이미 길번호가 정해진 길이면
		if(num[idx] != 0) return num[idx];
		
		num[idx] = id;
		
		int tmp = id;
		if(map[idx] == 'E') {
			tmp = go(idx + 1, id);
		} else {
			tmp = go(idx - 1, id);
		}
		
		// 이미 있는 길에 포함된다면
		return num[idx] = tmp;
	}

}
