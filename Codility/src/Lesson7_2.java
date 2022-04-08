import java.util.*;

public class Lesson7_2 {

    public static void main(String[] args) {
        System.out.println("Case 1: " + solution(new int[]{4, 3, 2, 1, 5}, new int[]{0, 1, 0, 0, 0})); //2
        System.out.println("Case 2: " + solution(new int[]{4,1,3,5,2}, new int[]{0, 1, 0, 1, 0}));//3
        System.out.println("Case 3: " + solution(new int[]{5,1,2,3,4}, new int[]{1,0,0,0,0}));//1
        System.out.println("Case 4: " + solution(new int[]{5,1,2,3,4}, new int[]{1,1,1,1,1}));//5
        System.out.println("Case 5: " + solution(new int[]{5,1,2,3,4}, new int[]{0,0,0,0,0}));//5
        System.out.println("Case 6: " + solution(new int[]{1,4,3,5,2}, new int[]{0,1,1,1,0}));//4
    }

    /**
     * 배열 A는 물고기의 크기
     * 배열 B는 물고기의 방향 (0은 상류로,1은 하류로 흐르는)
     * 물고기가 만나면 큰 물고기가 작은 물고기를 잡아먹음.
     *
     * 살아남을 물고기의 수를 계산하기
     *
     * N is an integer within the range [1..100,000];
     * each element of array A is an integer within the range [0..1,000,000,000];
     * each element of array B is an integer that can have one of the following values: 0, 1;
     * the elements of A are all distinct.
     *
     * https://app.codility.com/demo/results/trainingWDMGE5-U6P/
     */
    public static int solution(int[] A, int[] B) {

        int result = 0;
        Stack<Integer> downFish = new Stack<>();

        for (int i = 0; i < A.length; i++) {
            if (B[i] == 1) { // 하류로 향하는 물고기
                downFish.push(A[i]);
            } else { // 상류로 향하는 물고기
                while(!downFish.isEmpty()) {
                    Integer pop = downFish.peek();
                    if (pop > A[i]) break;
                    else downFish.pop();
                }
                if (downFish.isEmpty()) result++;
            }
        }

        return result + downFish.size();
    }
}
