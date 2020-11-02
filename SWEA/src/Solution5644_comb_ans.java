import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class Solution5644_comb_ans {
     
    static int M,aCnt;//시간,충전소개수
    static int[] pathA,pathB,playerA,playerB;
    static int[][] ap;//충전소들의 좌표
    /*
     * 0(이동하지않음), 1(상), 2(우), 3(하), 4(좌)
     */
    static int[] dx = {0,0,1,0,-1};
    static int[] dy = {0,-1,0,1,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        playerA = new int[2]; //A의 좌표
        playerB = new int[2]; //B의 좌표
         
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine().trim());
            M = Integer.parseInt(st.nextToken());
            aCnt = Integer.parseInt(st.nextToken());
             
            // 두 플레이어의 초기위치
            playerA[0] = playerA[1] = 1;
            playerB[0] = playerB[1] = 10;
             
            pathA = new int[M+1]; // 0초 즉,처음출발위치에서도 처리 하도록 M+1
            pathB = new int[M+1];
            ap = new int[aCnt][4];//x,y,충전가능거리,충전량
             
            String charsA = br.readLine();
            String charsB = br.readLine();
             
            for(int c=0 ; c<M ; c++) {
                pathA[c+1] = charsA.charAt(c*2)-'0';
                pathB[c+1] = charsB.charAt(c*2)-'0';
            }
             
            for(int a=0 ; a<aCnt ; a++) {
                st = new StringTokenizer(br.readLine()," ");
                ap[a][0] = Integer.parseInt(st.nextToken()); // x
                ap[a][1] = Integer.parseInt(st.nextToken()); // y
                ap[a][2] = Integer.parseInt(st.nextToken()); // c
                ap[a][3] = Integer.parseInt(st.nextToken()); // p
            }
            System.out.println("#"+t+" "+move());
        }
    }
    private static int move() {//매 시간마다 두 플레이어의 충전량의 합의 최대값을 구하고 그 값을 모든 시간동안 누적
        ArrayList<Integer> apListA,apListB;
        int totalSum = 0;
        int time = 0;
         
        while(time<=M) {
            //두 플레이어를 해당 시간의 이동정보에 맞게 이동
            playerA[0] += dx[pathA[time]];
            playerA[1] += dy[pathA[time]];
            playerB[0] += dx[pathB[time]];
            playerB[1] += dy[pathB[time]];
             
            //두 플레이어의 자신의 위치 기준으로 충전가능한 충전소리스트 가져오기
            apListA = getAp(playerA[0],playerA[1]);
            apListB = getAp(playerB[0],playerB[1]);
             
            totalSum += getCharge(apListA,apListB);
             
            time++;
        }
         
        return totalSum;
    }
     
    private static int getCharge(ArrayList<Integer> apListA, ArrayList<Integer> apListB) {
        int max = 0,temp =0;
         
        int aSize = apListA.size(),bSize = apListB.size();
         
        if(aSize==0 && bSize==0) {
            return 0;
        }else if(aSize==0) {
            return getMaxPower(apListB); //플레이어b만 충전가능한 상황
        }else if(bSize==0) {
            return getMaxPower(apListA); //플레이어 a만 충전가능
        }else {
            //플레이어 a,b모두 충전 가능한 상황은 다 조합을 고려해본다.
            for (Integer a : apListA) {
                for (Integer b : apListB) {
                    if(a != b) temp = ap[a][3]+ap[b][3];
                    else temp = Math.max(ap[a][3], ap[b][3]);
                     
                    if(max<temp) max = temp;
                }
            }
        }       
         
        return max;
    }
     
    private static int getMaxPower(ArrayList<Integer> apList) {
        int max = 0;
        for(Integer a : apList) {
            if(max<ap[a][3]) max = ap[a][3];
        }
        return max;
    }
    private static ArrayList<Integer> getAp(int x, int y) {
        ArrayList<Integer> apList = new ArrayList<Integer>();
        int d = 0;
        for(int a=0;a<aCnt;a++) {
            d = Math.abs(ap[a][0]- x)+Math.abs(ap[a][1] - y);
            if(d<=ap[a][2]) apList.add(a);
        }       
         
        return apList;
    }
     
}