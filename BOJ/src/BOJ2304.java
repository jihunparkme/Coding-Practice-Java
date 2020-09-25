import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2304 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] pillar = new int[1001];
		
		int longest = 0, lIdx = 0, lastIdx = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			pillar[a] = b; 
			// find last index
			lastIdx = Math.max(lastIdx, a);
			// find longest pillar and it's index
			if(b > longest) {
				longest = b;
				lIdx = a;
			}
		}
		
		// From the left to the center pillar
		int h = pillar[0];
		for (int i = 1; i < lIdx; i++) {
			if(h < pillar[i]) {
				h = pillar[i];
			} else {
				pillar[i] = h;
			}
		}
		
		// From the right to the center pillar
		h = pillar[lastIdx];
		for (int i = lastIdx - 1; i > lIdx; i--) {
			if(h < pillar[i]) {
				h = pillar[i];
			} else {
				pillar[i] = h;
			}
		}
		
		int sum = 0;
		for (int i = 1; i <= lastIdx; i++) 
			sum += pillar[i];
		
		System.out.println(sum);
	}

}
