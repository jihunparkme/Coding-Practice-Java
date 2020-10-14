import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ19236_v2 {

   public static class fish {
      int x;
      int y;
      int d;

      public fish(int x, int y, int d) {
         super();
         this.x = x;
         this.y = y;
         this.d = d;
      }

   }

   static int sx, sy, dk, max = 1;
   static int dir[][] = { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 },
         { -1, 1 } };

   public static void main(String[] args) throws IOException {
      // TODO Auto-generated method stub
      BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
      boolean death[];
      int map[][];
      fish num[];
      death = new boolean[16 + 1];
      map = new int[4][4];
      num = new fish[16 + 1];
      for (int i = 0; i < 4; i++) {
         StringTokenizer st = new StringTokenizer(kb.readLine());
         for (int j = 0; j < 4; j++) {
            int n = Integer.parseInt(st.nextToken());
            int di = Integer.parseInt(st.nextToken());
            num[n] = new fish(i, j, di-1);
            map[i][j] = n;
         }
      }
      int next = map[0][0];
      dk=num[next].d;
      death[map[0][0]] = true;
      map[0][0] = 0;
      go(death, num, map, next);
      System.out.println(max);

   }

   private static void go(boolean[] death, fish[] num, int[][] map, int count) {
      // TODO Auto-generated method stub
      if (max < count) {
         max = count;
      }
      fish num2[] = new fish[16 + 1];
      boolean death2[] = new boolean[16 + 1];
      int map2[][] = new int[4][4];
      for (int k = 1; k <= 16; k++) {
         num2[k] = new fish(num[k].x, num[k].y, num[k].d);
      }
      for (int k = 1; k <= 16; k++) {
         death2[k] = death[k];
      }
      for (int k = 0; k < 4; k++) {
         for (int b = 0; b < 4; b++) {
            map2[k][b] = map[k][b];
         }
      } // 모든 정보 복사
      move(death2, num2, map2);
      dfs(death2, num2, map2, count);

   }

   private static void dfs(boolean[] death, fish[] num2, int[][] map2, int count) {
      // TODO Auto-generated method stub
      int x = sx;
      int y = sy;
      int d2= dk;
      sx += dir[d2][0];
      sy += dir[d2][1];
      while (true) {
         if (sx < 0 || sx >= 4 || sy < 0 || sy >= 4) {
            break;
         }
         if (map2[sx][sy] != 0) {
            int next = map2[sx][sy];
            map2[sx][sy] = 0;
            death[next] = true;
            dk=num2[next].d;
            go(death, num2, map2, count + next);
            map2[sx][sy] = next;
            death[next] = false;
            dk=d2;
         }
         sx += dir[dk][0];
         sy += dir[dk][1];
      }
      sx = x;
      sy = y;

   }

   private static void move(boolean[] death, fish[] num, int[][] map) {
      for (int k = 1; k <= 16; k++) {
         if (death[k]) {
            continue;
         }
         for (int b = num[k].d; b < num[k].d + 8; b++) {
            int nextx = num[k].x + dir[b % 8][0];
            int nexty = num[k].y + dir[b % 8][1];
            if (nextx < 0 || nextx >= 4 || nexty < 0 || nexty >= 4 || nextx == sx && nexty == sy) {
               continue;
            } else {
               if (map[nextx][nexty] == 0) {
                  map[num[k].x][num[k].y]=0;
                  num[k].x = nextx;
                  num[k].y = nexty;
                  num[k].d = b % 8;
                  map[nextx][nexty]=k;

               } else {
                  int next = map[nextx][nexty];
                  num[next].x = num[k].x;
                  num[next].y = num[k].y;
                  map[num[k].x][num[k].y]=next;
                  num[k].x = nextx;
                  num[k].y = nexty;
                  num[k].d = b % 8;
                  map[nextx][nexty]=k;
               }
               break;
            }
         }
      }
   }

}