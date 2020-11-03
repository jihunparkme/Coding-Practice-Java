

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author taeheekim
*/
public class Solution5656__DFS_teacher {

	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,-1,1};
	private static int N,W,H,min,bCnt;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine().trim());
		for (int t = 1; t <= T; ++t) {
				//// #1, N = 3, W = 10, H = 10
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken()); // 시간
			W = Integer.parseInt(st.nextToken()); // 열크기
			H = Integer.parseInt(st.nextToken()); // 행크기
			int[][] map = new int[H][W];
			int total = 0;
			for (int r = 0; r < H; ++r) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < W; ++c) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if(map[r][c]>0) total++;
				}
			}			
			min = Integer.MAX_VALUE;
			go(0,total,map);
			System.out.println("#" + t + " "+min);
		}
	}

	private static boolean go(int count,int remainCount,int[][] map) {
		if(remainCount == 0) {
			min = 0;
			return true;
		}
		if(count == N) {
			min = Math.min(min, remainCount);
			return false;
		}
		int[][] newMap = new int[H][W];
		for (int c = 0; c < W; ++c) { // 매열마다 구슬 떨어드리는 시도.			
			// 구슬을 떨어뜨리면 맞는 벽돌이 있는 행 찾기
			int r=0;
			while(r<H && map[r][c]==0)r++;
			if(r==H) continue; // 모두 빈칸이면 다음 열로 시도
			
			// 터트리는 시도하기 전에 직전 count횟수까지의 맵 상태를 이용하여 배열 복사하여 초기화 
			copy(map,newMap);
			
			bCnt = 0;
			boom(newMap,r,c,newMap[r][c]);
			down(newMap);
			if(go(count+1,remainCount-bCnt, newMap)) return true;
		}	
		return false;
	}
	private static void boom(int[][] map,int r,int c,int cnt) {
		bCnt++;
		map[r][c] = 0;
		if(cnt==1) return;
		
		for (int d = 0; d < 4; ++d) {
			int nr = r,nc = c;
			for (int k = 1; k < cnt; ++k) {// 자신의 숫자-1만큼 제거처리할 벽돌 큐에 넣기
				nr += dr[d];
				nc += dc[d];
				if(nr>=0 && nr<H && nc>=0 && nc<W && map[nr][nc]!=0) {
					boom(map,nr,nc,map[nr][nc]);
				}
			}
		}
	}
	private static void down(int[][] map) {
		for (int c = 0; c < W; ++c) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			int r;
			for (r = H-1; r >= 0; --r) {
				if(map[r][c] > 0) {
					list.add(map[r][c]);
					map[r][c] = 0;
				}
			}
			r=H;
			for (int b : list) map[--r][c] = b;
		}
	}
	private static void copy(int[][] map,int[][] newMap) {
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				newMap[r][c] = map[r][c];
			}
		}
	}
}