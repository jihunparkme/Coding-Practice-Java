import java.util.Stack;

public class Lesson7_3 {

    public static void main(String[] args) {
        System.out.println(solution("(()(())())"));
        System.out.println(solution( "())"));
        System.out.println(solution( "(((("));
        System.out.println(solution( "))))"));
        System.out.println(solution( "())))"));
        System.out.println(solution( "((((()"));
    }

    /**
     * 문자열 S가 적절하게 중첩되면 1을 반환하고 그렇지 않으면 0을 반환
     *
     * N is an integer within the range [0..1,000,000];
     * string S consists only of the characters "(" and/or ")".
     *
     * https://app.codility.com/demo/results/trainingEZUC6T-ZBY/
     */
    public static int solution(String S) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return 0;
                stack.pop();
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }
}
