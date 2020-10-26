import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ19591 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		System.out.println(process(input));
	}

	private static int process(String input) {
		
		int res = 0;
		int len = input.length();
		ArrayList<Character> opers = new ArrayList<>();
		ArrayList<Integer> nums = new ArrayList<>();
		
		
		
		
		return res;
	}

}

/*
1. 수식에서 맨 앞의 연산자, 또는 맨 뒤의 연산자 먼저 계산한다. 단, 음수의 부호는 연산자로 취급하지 않는다.
2. 곱셈, 나눗셈을 덧셈, 뺄셈보다 더 먼저 계산한다.
3. 연산자의 우선순위가 같다면 해당 연산자를 계산했을 때 결과가 큰 것부터 계산한다.
4. 계산했을 때 결과 값 또한 같다면 앞에 것을 먼저 계산한다.


맨 앞을 제외하고 음수가 들어오는 경우도 존재하지 않는다.
불필요한 0이 앞에 있을 수 있다
*/