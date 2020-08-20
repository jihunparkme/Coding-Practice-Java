import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18808 {
	
	static int N, M, K, R, C, res = 0, scale = 0, notebk[][], sticker[][];
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
			
			scale = 0;
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < C; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
					// 스티커 칸
					if(sticker[i][j] == 1) scale++;
				}
			}
			
			bfr = nr = R;
			bfc = nc = C;
			// 스티커 붙여볼까?
			findLocate();
		}
		
		System.out.println(res);
	}
	// 붙일 수 있는 위치 찾아서 붙이기(가장 위쪽, 가장 왼쪽)
	public static void findLocate() {
		// 0, 90, 180, 270도 회전해볼꺼야
		for (int r = 0; r < 4; r++) {
			
			// 붙일 수 있는 위치를 찾다가
			for (int ntr = 0; ntr < N; ntr++) {
				for (int ntc = 0; ntc < M; ntc++) {
					// 노트북 크기을 벗어나면 pass
					if(ntr + nr > N || ntc + nc > M) continue;
					
					// 다른 스티커와 겹치면 pass
					boolean isPut = true;
					out:for (int stkr = 0; stkr < nr; stkr++) {
						for (int stkc = 0; stkc < nc; stkc++) {
							// 붙일 수 없는 상황이면
							if(notebk[ntr+stkr][ntc+stkc] == 1 &&
									sticker[stkr][stkc] == 1) {
								isPut = false;
								break out;
							}
						}
					}
					
					// 이 위치에 붙일 수 있다면 붙이기
					if(isPut) {
						for (int stkr = 0; stkr < nr; stkr++) {
							for (int stkc = 0; stkc < nc; stkc++) {
								if(sticker[stkr][stkc] == 1) 
									notebk[ntr+stkr][ntc+stkc] = 1;
							}
						}
						// 붙였으면 return
						res += scale;
						return;
					}
				}
			}
			// 270도까지 회전시켜도 붙일 곳이 없다면 버리기
			if(r == 3) break;
			// 붙일 자리가 없다면 회전
			rotate90(r);
		}
	}
	
	// 스티커를 붙이지 못했다면 시계방향으로 90도 회전
	public static void rotate90(int th) {
		// 모눈종이도 같이 돌아감
		int tmpSticker[][];
		// 기존 모눈종이, 바뀐 모눈종이
		// 180도 회전은 그대로
		if(th == 1) {
			bfr = nr;
			bfc = nc;
			nr = R;
			nc = C;
			tmpSticker = new int[R][C];
		}
		// 90도, 270도는 행열 바뀜 
		else {
			bfr = nr;
			bfc = nc;
			nr = C;
			nc = R;
			tmpSticker = new int[C][R];
		}
		
		// 기존 열 -> 행
		// 기존 행 ->  열 (N(열)-1-행)
		for (int i = 0; i < bfr; i++) {
			for (int j = 0; j < bfc; j++) {
				tmpSticker[j][nc-1-i] = sticker[i][j];
			}
		}
		
		// 스티커 갱신
		sticker = new int[nr][nc];
		for (int i = 0; i < nr; i++) {
			System.arraycopy(tmpSticker[i], 0, sticker[i], 0, nc);
		}
		
	}
	
}