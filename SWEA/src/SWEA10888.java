import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA10888 {

	static int N, sel[], res;
	static ArrayList<Point> restaurant, house;
	static ArrayList<Integer> feeList;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			res = Integer.MAX_VALUE;
			restaurant = new ArrayList<>();
			feeList = new ArrayList<>();
			house = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					int a = Integer.parseInt(st.nextToken());
					// 집 위치 저장
					if(a == 1) house.add(new Point(i, j));
					// 음식점 위치 및 정보 저장
					else if(a >= 2) {
						restaurant.add(new Point(i, j));
						feeList.add(a);						
					}
				}
			}
			
			// 조합으로 치킨집을 선택해보자.
			for (int i = 1; i <= restaurant.size(); i++) {
				sel = new int[i];
				comb(0, i, 0, 0);
			}
			
			System.out.println("#" + tc + " " + res);
		}
		
	}

	private static void comb(int idx, int n, int cnt, int fee) {
		
		// 모든 음식점을 선택했다면
		if(cnt == n) {
			int tmpRes = 0;
			// 각 집에서 가장 가까운 음식점과의 배달 거리
			for (Point now : house) {
				int minDist = Integer.MAX_VALUE;
				// restaurant List
				for (int i = 0; i < n; i++) {
					int dist = Math.abs(restaurant.get(sel[i]).x - now.x) + 
							Math.abs(restaurant.get(sel[i]).y - now.y);
					
					minDist = Math.min(minDist, dist);
				}
				tmpRes += minDist;
			}
			// 운영비 합의 최소
			res = Math.min(res, tmpRes + fee);
			
			return;
		}
		// 더이상 선택할 수 있는 치킨집이 없을 경우 
		if(idx == restaurant.size()) return;
		
		for (int i = idx; i < restaurant.size(); i++) {
			sel[cnt] = i;
			comb(i + 1, n, cnt + 1, fee + feeList.get(i));
			
		}
		
	}

}
