import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ13022 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		System.out.println(process(input));
	}

	private static int process(String str) {
		
		int[] cnt = new int[4];
		for (int i = 0; i < str.length(); i++) {
			switch(str.charAt(i)) {
			case 'w':
				cnt[0]++;
				break;
			case 'o':
				cnt[1]++;
				if(cnt[0] != cnt[1]) return 0;
				break;
			case 'l':
				cnt[2]++;
				if(cnt[1] != cnt[2]) return 0;
				break;
			case 'f':
				cnt[3]++;
				if(cnt[2] != cnt[3]) return 0;
				break;
			}
		}
		
		return 1;
	}
	
}

/*
임의의 양의 정수 n에 대해서, 'w'가 n번 나오고, 
그 다음에 'o'가 n번, 그 다음에 'l'이 n번, 
그 다음에 'f'가 n번 나온 단어는 올바른 단어이다.

올바른 단어 두 개를 이은 단어도 올바른 단어이다.

1번과 2번 조건으로 만들 수 있는 단어만 올바른 단어이다.
*/