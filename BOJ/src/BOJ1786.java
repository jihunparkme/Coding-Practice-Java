import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ1786 {
	
	static ArrayList<Integer> res;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String T = br.readLine();
		String P = br.readLine();
		res = new ArrayList<>();
		
		KMP(T, P);
		System.out.println(res.size());
		for (int r : res) {
			System.out.print(r + " ");
		}
	}
	
	static int[] getPi(String pattern) {
		int pLen = pattern.length();
		// 접두사와 접미사가 일치하는 최대길이를 담을 배열 선언
		int[] pi = new int[pLen];
		// idx
		int j = 0;
		// i,j가 가리키는 값이 일치하면 둘다 증가
		// 불일치하면 i만 증가시켜야 하므로 for문
		for (int i = 1; i < pLen; i++) {
			// pattern 내에서 i와 j가 가리키는 값이 다를때
			//while문안에 넣는 이유는 중간단계를 건너뛰고 최대한으로 점프하려고
			//j가 더 후퇴할 곳이 있다면 마지막 같았던 곳 까지 후퇴한다.
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				//j의 값을 한칸 뺀곳의 값으로 j를 바꿈
				j = pi[j - 1];
			}
			// pattern 내에서 i와 j가 가리키는 값이 같으면
			if (pattern.charAt(i) == pattern.charAt(j)) {
				// i번째의 최대길이는 ++j한 값
				pi[i] = ++j;
			}
		}

		return pi;
	}
	
	static void KMP(String sentence, String pattern) {
		int[] table = getPi(pattern);
//		System.out.println("점프테이블");
//		System.out.println(Arrays.toString(table));
		int sLen = sentence.length();
		int pLen = pattern.length();
		int j = 0; 
		for(int i = 0 ; i< sLen; i++) {
			//다르다면 같을때까지 혹은 더이상 후퇴할 곳 이 없을때까지 후퇴
			while(j >0 && sentence.charAt(i) != pattern.charAt(j)) {
				j = table[j - 1];
			}
			//부모와 패턴이 일치한다면
			if(sentence.charAt(i) == pattern.charAt(j)) {
				//j의 값이 패턴의 길이-1이라면 한번 다찾은거니까
				//패틴걸이만큼 다 일치한거면 패턴 발견
				if(j == pLen-1) {
					res.add((i-pattern.length()+1) + 1);
//					System.out.println((i-pattern.length()+1) + 1 + "째 인덱스에서 찾음" );
					//패턴을 또 찾기 위해서
					j = table[j];
					//아니면 j랑 i랑 둘다 다음으로 가
				}else {
					//다찾은건아니라면 계속 진행해야하므로 j값 증가
					j++;
				}
			}
		}
		
	}
}