import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Lesson7_1 {

    public static void main(String[] args) {
        System.out.println(solution("{[()()]}"));
        System.out.println(solution( "([)()]")); // 실패 케이스
        System.out.println(solution( ")(")); // 닫는 괄호가 먼저 나올 경우
        System.out.println(solution( "{{{{")); // 여는 괄호만 나올 경우
        System.out.println(solution( "}}}}"));
        System.out.println(solution( "")); // N이 0일 경우
    }

    /**
     * S가 적절하게 중첩되면 1을 반환하고 그렇지 않으면 0을 반환
     *
     * N is an integer within the range [0..200,000];
     * string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
     *
     * https://app.codility.com/demo/results/training2ZF9ZD-FXE/
     */
    public static int solution(String S) {

        int size = S.length();
        if (size == 0) return 1;

        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < size; i++) {
            char c = S.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return 0;
                Character pop = stack.pop();
                if (c != map.get(pop)) {
                    return 0;
                }
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }
}
