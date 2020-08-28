import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3234 {

   static int T, res;
   
   public static void main(String[] args) throws IOException {
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      
      T = Integer.parseInt(br.readLine());
      for (int tc = 1; tc <= T; tc++) {
         res = 0;
         int N = Integer.parseInt(br.readLine());
         int[] arr = new int[N];
         
         st = new StringTokenizer(br.readLine());
         for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
         }
         
         balances(0, 0, 0, new boolean[N], arr, N);
         
         System.out.println("#" + tc + " " + res);
      }

   }

   private static void balances(int idx, int sumL, int sumR, boolean[] used, final int[] arr, int N) {
      // 모든 추를 올렸다면
      if(idx == N) {
         res++;
         return;
      }
       
      // 순열과 부분집합을 동시에 수행
      for (int i = 0; i < N; i++) {   
         // 이미 사용된 추일 경우 pass
         if(used[i]) continue;
         // 고른 추를 체크
         used[i] = true;
         // 왼쪽에 해당 추를 사용
         balances(idx + 1, sumL + arr[i], sumR, used, arr, N);
         
         // 오른쪽에 올라가 있는 무게의 총합이 왼쪽에 올라가 있는 무게의 총합보다 더 커지면 안됨
         if(sumR + arr[i] <= sumL) {
            // 오른쪽에 해당 추를 사용
            balances(idx + 1, sumL, sumR + arr[i], used, arr, N);
         }
         // 고른 추 해제
         used[i] = false;
      }
   }
}