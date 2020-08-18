import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class Solution1211 {
 
    static int T, N, map[][], res, resIdx, cnt;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        T = 10;
        N = 100;
 
        for (int tc = 1; tc <= T; tc++) {
             
            map = new int[N][N];
            ArrayList<Integer> startPointY = new ArrayList<Integer>();
            Integer.parseInt(br.readLine());
            res = 987654321;
            
            // ��ٸ� �����
            for (int x = 0; x < N; x++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int y = 0; y < N; y++) {    
                    map[x][y] = Integer.parseInt(st.nextToken());
                    // �������� üũ
                    if(x == 0 && map[x][y] == 1) startPointY.add(y);
                }
            }
  
            // �ϳ��� ����������
            for (int i = 0; i < startPointY.size(); i++) {
				
            	int x = 0;
            	int y = startPointY.get(i);
            	int idx = i;
            	cnt = 0;
            	
            	// ���� �� ĭ ��������
            	x++; cnt++;
            	
            	// ���������� ������ ������ �ݺ�
            	while(x != N-1) {
            		// �¿쿡 ���� �ִ��� Ȯ��
            		// 0:��, 1:��, 2:�� ����
            		int direction = 2;
            		// ������ ���� ���� ���
            		if(y-1 >= 0 && map[x][y-1] == 1) {
            			direction = 0;
            			idx--;
            		}
            		// ������ ���� ���� ���
            		if(y+1 < N && map[x][y+1] == 1) {
            			direction = 1;
            			idx++;
            		}
            		// ���� ���ٸ�
            		if(direction == 2) {
            			x += 1;
            			continue;
            		}
            		// ���� �ִٸ� �ش� ��� �̵�
            		cnt += (Math.abs(y - startPointY.get(idx)));
            		y = startPointY.get(idx);
            		// �ű� �࿡�� �ٽ� �� ĭ ��������
            		x++; cnt++;
            	}	
            	
            	if(res > cnt) {
            		res = cnt;
            		resIdx = startPointY.get(i);
            	}
			}
            
            // ��� ������� �˻��ϰ� ���� �F�� �̵� �Ÿ��� ���� ������ x�� ��ȯ
    		// �������� ��� ���� ū x��ǥ
        	System.out.println("#" + tc + " " + resIdx);
        }
    }
}