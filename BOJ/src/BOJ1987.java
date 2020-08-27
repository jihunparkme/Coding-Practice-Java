import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987 {

   static int R, C, res;
   static boolean[] alpa;
   static char[][] map;
   static int[] dr = { -1, 0, 1, 0 }, dc = { 0, -1, 0, 1 };

   public static void main(String[] args) throws IOException {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      R = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      map = new char[R][];
      for (int i = 0; i < R; i++)
         map[i] = br.readLine().toCharArray();

      alpa = new boolean[26];
      // 시작 지점
      alpa[map[0][0] - 'A'] = true;
      move(0, 0, 1);
      
      System.out.println(res);
   }

   private static void move(int r, int c, int cnt) {
	   // 모든 알파벳을 다 모았을 경우 최대 칸이므로 더이상 볼 필요가 없음
	   if(res == 26) return;
   
      // 4방 탐색
      int rr, cc;
      for (int d = 0; d < 4; d++) {
         rr = r + dr[d];
         cc = c + dc[d];
         
         // 범위를 벗어날 경우 pass
         if(rr < 0 || cc < 0 || rr >= R || cc >= C) continue;
         // 알파벳이 이미 체크되어있다면 pass
         if(alpa[map[rr][cc] - 'A']) continue;

         // 현재 알파벳 체크
         alpa[map[rr][cc] - 'A'] = true;
         // 갈 수 있는 곳이라면 가보자!
         move(rr, cc, cnt + 1);
         // 현재 알파벳 체크 해제
         alpa[map[rr][cc] - 'A'] = false;
      }
      // 사방으로 갈 길이 없다면 최대 칸 수 갱신
      res = Math.max(res, cnt);
   }

}