import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2605 {

	static int N;
	static ArrayList<Integer> members;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		members = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken());
			if(a == 0) members.add(i);
			else members.add(i-a, i);	
		}
		
		for (int i = 0; i < N; i++) {
			System.out.print(members.get(i) + 1 + " ");
		}
		
	}

}
