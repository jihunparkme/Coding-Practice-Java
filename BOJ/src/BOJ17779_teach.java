import java.util.Arrays;
import java.util.Scanner;

public class BOJ17779_teach {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++)
				map[i][j] = sc.nextInt();
		}
		int min = 987654321;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				//모든 기준점에 대해서
				for( int d1 = 1; d1 < N; d1++ ) {
					for( int d2 = 1; d2 < N; d2++ ) {
						//doSomething
						//기준 열에서 d1만큼 왼쪽으로 가도 안나가떨어지고
						//기준 열에서 d2만큼 오른쪽으로 가도 안나가떨어지고
						//기준 행에서 d1+d2만큼 밑으로 내려가도 안나가떨어진다.
						if(j - d1 >= 0 && j + d2 < N && i + d1 + d2 < N) {
							 min = Math.min(min, cal(map, i, j ,d1, d2));
						}
					}
				}
			}
		}
		System.out.println(min);
	}
	//기준점과 d1,d2를 받아서 영역을 검사한다.
	static int cal(int[][] map, int r, int c, int d1, int d2) {
		boolean[][]	 isArea5 = new boolean[map.length][map.length];
		//5번영역을 isArea5에 true로 칠한다.
		int size = 1;
		int nc = c;//현재 행에서의 시작열을 유지
		for(int i = 0; i <= d1+d2; i++) {
			//현재줄 현재행부터 size만큼 오른쪽으로 가며 true로 칠한다.
			for(int j = 0; j < size; j++)
				isArea5[r + i][nc + j] = true;
			//아직 행이 d1만큼 내려오지 않았다면 출발열은 한칸 왼쪽으로 가고
			if( i < d1 )
				nc--;
			else
				nc++;
			//d1보다 더 많이 내려왔다면 출발열을 한칸 오른쪽으로 보낸다.
			if( i < d1 && i < d2)
				size += 2;
			else if( i >= d1 && i >= d2)
				size -= 2;
			//마름모가 커지는 중이면 size += 2
			//마름모가 작아지는 중이면 size -= 2
		}
		int[] sum = new int[5];//1~5번 총합을 저장할 배열
		//모든 맵을 검사하면서
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				//5번영역이 아니면서 1번선거구 조건을 만족하면 1번
				if(!isArea5[i][j] && i >= 0 && i < r + d1 && j >= 0 && j <= c )
					sum[0] += map[i][j];
				else if (i >= 0 && i <= r + d2 && j > c && j < map.length&& !isArea5[i][j]) {
					sum[1] += map[i][j];
				} else if (i >= r + d1 && i < map.length && j >= 0 && j < c - d1 + d2&& !isArea5[i][j]) {
					sum[2] += map[i][j];
				} else if (i > r + d2 && i < map.length && j >= c - d1 + d2 && j < map.length&& !isArea5[i][j]) {
					sum[3] += map[i][j];
				}
				else
					sum[4] += map[i][j];
			}
		}
		//5번영역이 아니면서 각 1,2,3,4번 영역에 맞는 조건이라면 자신의 위치에 더한다.
//		1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
//		2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
//		3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
//		4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
		//정렬해서 젤큰거에서 젤 작은거 빼서 리턴
		Arrays.sort(sum);
		return sum[4] - sum[0];
	}
}