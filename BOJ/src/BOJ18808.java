import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18808 {
	
	static int N, M, K, R, C, notebk[][], sticker[][];
	static int bfr, bfc, nr, nc;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());	// 놋북 세로
		M = Integer.parseInt(st.nextToken());	// 놋북 가로
		K = Integer.parseInt(st.nextToken());	// 스티커 개수
		notebk = new int[N][M];
		
		// 스티커를 붙여보자.
		for (int stk = 0; stk < K; stk++) {
			st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());	// 행
			C = Integer.parseInt(st.nextToken());	// 열
			sticker = new int[R][C];
			
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < C; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfr = nr = R;
			bfc = nc = C;
			// 스티커 붙여볼까?
			findLocate();
			
			System.out.println();
		}
		
	}
	
	// 붙일 수 있는 위치 찾아서 붙이기(가장 위쪽, 가장 왼쪽)
	public static void findLocate() {
		// 0, 90, 180, 270도 회전해볼꺼야
		for (int r = 0; r < 4; r++) {
			// 붙일 수 있는 위치를 찾다가
			for (int i = 0; i < nr; i++) {
				for (int j = 0; j < nc; j++) {
					// 다른 스티커와 겹치면 pass
					// 노트북을 벗어나면 pass
					
					
					// 붙일 자리가 있다면 붙이기
					return;
				}
			}
			// 붙일 자리가 없다면 
			rotate90(r);
		}
		
		// 270도까지 회전시켜도 붙일 곳이 없다면 버리기
	}
	
	// 스티커를 붙이지 못했다면 시계방향으로 90도 회전
	public static void rotate90(int th) {
		// 모눈종이도 같이 돌아감
		int tmpSticker[][];
		// 기존 모눈종이, 바뀐 모눈종이
		// 180도 회전은 그대로
		if(th == 1) {
			bfr = C;
			bfc = R;
			nr = R;
			nc = C;
			tmpSticker = new int[R][C];
		}
		// 90도, 270도는 행열 바뀜 
		else {
			bfr = R;
			bfc = C;
			nr = C;
			nc = R;
			tmpSticker = new int[C][R];
		}
		
		// 기존 열 -> 행, 기존 행 ->  N(열)-1-행 (열)
		for (int i = 0; i < bfr; i++) {
			for (int j = 0; j < bfc; j++) {
				tmpSticker[j][bfc-1-i] = sticker[i][j];
			}
		}
		
		// 스티커 갱신
		sticker = new int[nr][nc];
		for (int i = 0; i < nr; i++) {
			System.arraycopy(tmpSticker, 0, sticker, 0, nc);
		}
	}
	
}
