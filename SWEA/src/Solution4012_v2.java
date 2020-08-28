import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution4012_v2  {
 
    static int T, N, res, map[][], sum[];
    static boolean visited[];
     
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
             
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            res = 987654321;
            // 식재료들을 각각 N / 2개씩 나누어 두 개의 요리를 진행
            visited = new boolean[N];
 
            selectIngredient(0, 0);
            System.out.println("#" + tc + " " + res);
        }
 
    }
 
    private static void selectIngredient(int start, int cnt) {
 
        if(start >= N) return;
        if(cnt > N/2) return;
        
        // A가 N/2개의 식재료를 모두 골랐다면 나머지는 B의 식재료
        if(cnt == N/2) {
            int sumA = 0, sumB = 0;
            for (int i = 0; i < N; i++) {
                // A의 재료일 경우
                if(visited[i]) {
                    for (int j = 0; j < N; j++) {
                        if(visited[j]) {
                            sumA += map[i][j];
                        }
                    }
                }
                // B의 재료일 경우
                else {
                    for (int j = 0; j < N; j++) {
                        if(!visited[j]) {
                            sumB += map[i][j];
                        }
                    }
                }
            }
 
            res = Math.min(res, Math.abs(sumA - sumB));
            return;
        }
         
        // 이 식재료를 A음식을 만드는데 사용해보자.
        visited[start] = true;
        selectIngredient(start+1, cnt+1);
        // 그냥 사용 안할래
        visited[start] = false;
        selectIngredient(start+1, cnt);
    }
 
}