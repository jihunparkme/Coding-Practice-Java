import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17780_v2{

   public static class miniun {
      int x;
      int y;
      int d;

      public miniun(int x, int y, int d) {
         this.x = x;
         this.y = y;
         this.d = d;
      }
   }

   static int map[][], dir[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }, result = -1, n, k;
   static ArrayList<Integer> s[][];
   static miniun num[];

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      k = Integer.parseInt(st.nextToken());
      map = new int[n + 1][n + 1];
      num = new miniun[k];
      s = new ArrayList[n + 1][n + 1];
      for (int i = 1; i <= n; i++) {
         st = new StringTokenizer(br.readLine());
         for (int j = 1; j <= n; j++) {
            map[i][j] = Integer.parseInt(st.nextToken());
            s[i][j] = new ArrayList<Integer>();
         }
      }
      for (int i = 0; i < k; i++) {
         st = new StringTokenizer(br.readLine());
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         int d = Integer.parseInt(st.nextToken());
         if (d == 1) {
            d = 1;
         } else if (d == 2) {
            d = 3;
         } else if (d == 3) {
            d = 0;
         } else if (d == 4) {
            d = 2;
         }
         num[i] = new miniun(x, y, d);
         s[x][y].add(i);
      }
      start();
      System.out.println(result);
   }
   private static void start() {
      // TODO Auto-generated method stub
      int time = 1;
      while (time <= 1000) {
         for (int i = 0; i < k; i++) {
            miniun next = num[i];
            int x=next.x;
            int y=next.y;
            if(s[next.x][next.y].get(0)!=i) {
               continue;
            }
            int x1 = next.x + dir[next.d][0];
            int y1 = next.y + dir[next.d][1];
            if (x1 < 1 || x1 > n || y1 < 1 || y1 > n || map[x1][y1] == 2) {
               next.d = (next.d + 2) % 4; // 방향 바꾸기
               x1 = next.x + dir[next.d][0];
               y1 = next.y + dir[next.d][1];
               if (x1 < 1 || x1 > n || y1 < 1 || y1 > n ||map[x1][y1] == 2) {
                  next.d = (next.d + 2) % 4;
               }else if (map[x1][y1] == 0) {
                  Stack<Integer> ne = new Stack<Integer>();
                  while(s[next.x][next.y].size()!=0) {
                     ne.add(s[next.x][next.y].remove(s[next.x][next.y].size()-1));
                  }
                  while(!ne.isEmpty()) {
                     int nextnum=ne.pop();
                     num[nextnum].x=x1;
                     num[nextnum].y=y1;
                     s[x1][y1].add(nextnum);
                  }
                  if (s[x1][y1].size() >= 4) {
                     result = time;
                     return;
                  }
               } // 흰색판
               else {
                  while (s[x][y].size()!=0) {
                     int nextnum=s[next.x][next.y].remove(s[next.x][next.y].size() - 1);
                     num[nextnum].x=x1;
                     num[nextnum].y=y1;
                     s[x1][y1].add(nextnum);
                  }
                  if (s[x1][y1].size() >= 4) {
                     result = time;
                     return;
                  }
               }
            } // 파란색이나 반대 판
            else if (map[x1][y1] == 0) {
               Stack<Integer> ne = new Stack<Integer>();
               while(s[next.x][next.y].size()!=0) {
                  ne.add(s[next.x][next.y].remove(s[next.x][next.y].size()-1));
               }
               while(!ne.isEmpty()) {
                  int nextnum=ne.pop();
                  num[nextnum].x=x1;
                  num[nextnum].y=y1;
                  s[x1][y1].add(nextnum);
               }
               if (s[x1][y1].size() >= 4) {
                  result = time;
                  return;
               }
            } // 흰색판
            else {
               while (s[x][y].size()!=0) {
                  int nextnum=s[next.x][next.y].remove(s[next.x][next.y].size() - 1);
                  num[nextnum].x=x1;
                  num[nextnum].y=y1;
                  s[x1][y1].add(nextnum);
               }
               if (s[x1][y1].size() >= 4) {
                  result = time;
                  return;
               }
            }
         }
         time++;
      }

   }

} 