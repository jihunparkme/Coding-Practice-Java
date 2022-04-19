package test_20220419;

public class Problem03 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,1,2,3,3}, 3) == true); // t
        System.out.println(solution(new int[]{1,1,3}, 2) == false); // f
        System.out.println(solution(new int[]{1}, 3) == false); // f
        System.out.println(solution(new int[]{1,2}, 3) == false); // f
        System.out.println(solution(new int[]{1,1,1,1,1,1,2,2,2,2}, 3) == false); // f
        System.out.println(solution(new int[]{1,1,2,3,4,5,7,7,7,7,7,7,7,8}, 5) == true); // t
        System.out.println(solution(new int[]{2,3,4,5,7,7,7,7,7,7,7,8}, 5) == false); // f
        System.out.println(solution(new int[]{2,4,6,8}, 8) == false);
        System.out.println(solution(new int[]{2,3,4,5}, 5) == false);
        System.out.println(solution(new int[]{1,2,3,4,5}, 4) == true);
        System.out.println(solution(new int[]{1,2,3,4,5}, 3) == true);
        System.out.println(solution(new int[]{1,1,2,2,3,3,4,4,5}, 5) == true);
        System.out.println(solution(new int[]{1,1,2,2,2,2,2,2,2,2,2,2,3,3,4,4,5,5,5,5,5,5,5,6}, 5) == true);
        System.out.println(solution(new int[]{2,3,4,5}, 5) == false);
        System.out.println(solution(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2}, 2) == true);
        System.out.println(solution(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2}, 1) == true);
        System.out.println(solution(new int[]{1,2,3,4,5,6,7,8}, 8) == true);
        System.out.println(solution(new int[]{1,2,4,6,8}, 8) == false);
        System.out.println(solution(new int[]{1,2,4,6,8}, 2) == true);
        System.out.println(solution(new int[]{1}, 1) == true);
        System.out.println(solution(new int[]{3}, 3) == false);
        System.out.println(solution(new int[]{2,3}, 3) == false);
        System.out.println(solution(new int[]{3,4,5}, 5) == false);
        System.out.println(solution(new int[]{1,2,3,5}, 3) == true);
        System.out.println(solution(new int[]{1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,3,3,3,3,3,3,4,5}, 5) == true);
        System.out.println(solution(new int[]{1}, 5) == false);
        System.out.println(solution(new int[]{5}, 5) == false);
        System.out.println(solution(new int[]{5}, 300_000) == false);
        System.out.println(solution(new int[]{1,2,3,1_000_000_000}, 300_000) == false);
        System.out.println(solution(new int[]{1,2,3,1_000_000_000}, 1_000_000_000) == false);
        System.out.println(solution(new int[]{1,2,3,6}, 6) == false);
        System.out.println(solution(new int[]{1,3,4,5,6}, 3) == false);
        System.out.println(solution(new int[]{1,3,4,5}, 5) == false);
        System.out.println(solution(new int[]{1,2,3,4,5}, 4) == true);
    }

    /**
     * 배열에 1,2,3 .. K 가 포함되는지 확인
     */
    public static boolean solution(int[] A, int K) {
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] + 1 < A[i + 1])
                return false;
            if (A[i + 1] == K && A[0] == 1) return true;
        }
        if (A[0] != 1 || A[n - 1] != K)
            return false;
        else
            return true;
    }
}
