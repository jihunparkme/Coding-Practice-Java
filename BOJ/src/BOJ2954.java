import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2954 {

	static char mm[] = { 'a', 'e', 'i', 'o', 'u' };

	public static void main(String[] args) throws IOException {

		// 모음(a,e,i,o,u)다음에 자신의 이니셜 'p' + 그 모음을 하나 더
		// s, a, f, y 철자가 들어가면 'p' + 원래 철자
		// 모음이나 safy에 포함되는 문자가 나오면 뒤에 두 문자는 skip 처리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		char input[] = br.readLine().toCharArray(); // 입력된 문자열

		for (int i = 0; i < input.length; i++) {
			sb.append(input[i]);
			// 해당 문자가 모음인지 확인
			for (int j = 0; j < mm.length; j++) {
				// 공백이면 pass
				if (input[i] == ' ')
					continue;
				// 모음이라면
				if (input[i] == mm[j]) {
					// 두 글자 뛰어넘기
					i += 2;
					break;
				}
			}
		}
		sb.append('\n');
		System.out.println(sb);
	}

}
