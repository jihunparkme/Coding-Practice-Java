import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987 {

   static int R, C, res;
   static boolean[] alpa;
   static char[][] map;
   static boolean[][] visited;
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
      visited = new boolean[R][C];
      
      visited[0][0] = true;
      alpa[map[0][0] - 'A'] = true;
      move(0, 0, 1);
      
      System.out.println(res);
   }

   private static void move(int r, int c, int cnt) {
   
      // 4방 탐색
      int rr, cc;
      for (int d = 0; d < 4; d++) {
         rr = r + dr[d];
         cc = c + dc[d];
         
         // 범위를 벗어날 경우 pass
         if(rr < 0 || cc < 0 || rr >= R || cc >= C) continue;
         // 이미 많은 칸을 거쳐 방문한 곳이거나 알파벳이 체크되어있다면 pass
         if(visited[rr][cc] || alpa[map[rr][cc] - 'A']) continue;
         
         // 현재 위치 방문 처리
         visited[rr][cc] = true;
         // 현재 알파벳 체크
         alpa[map[rr][cc] - 'A'] = true;
         // 갈 수 있는 곳이라면 가보자!
         move(rr, cc, cnt + 1);
         // 현재 위치 방문 처리
         visited[rr][cc] = false;
         // 현재 알파벳 체크
         alpa[map[rr][cc] - 'A'] = false;
      }
      // 사방으로 갈 길이 없다면
      res = Math.max(res, cnt);
   }

}