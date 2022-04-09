import java.util.Stack;

public class Lesson7_4 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{8, 8, 5, 7, 9, 8, 7, 4, 8}));
        System.out.println(solution(new int[]{1,2,3,4,5,6,7,8}));
        System.out.println(solution(new int[]{1,2,3,4,3,8,7,8}));
    }

    /**
     * 벽을 만드는 데 필요한 최소 블록 수
     *
     * N is an integer within the range [1..100,000];
     * each element of array H is an integer within the range [1..1,000,000,000].
     *
     * https://app.codility.com/demo/results/trainingFDPADE-QYQ/
     */
    public static int solution(int[] H) {

        Stack<Integer> blocks = new Stack<>();
        int result = 0;
        for (int i = 0; i < H.length; i++) {
            while(!blocks.isEmpty() && blocks.peek() > H[i]) {
                blocks.pop();
            }
            if (blocks.isEmpty() || blocks.peek() < H[i]) {
                blocks.push(H[i]);
                result++;
            }
        }

        return result;
    }
}
