package test20240622;

import java.util.Arrays;
import java.util.Stack;

public class Problem04 {

    private static int COUNT = 0;

    private static String[] solution(final int size, final String[] commands) {
        Stack<String> history = new Stack<>();
        Stack<String> temp = new Stack<>();
        COUNT = size;

        for (int i = 0; i < commands.length; i++) {
            final String command = commands[i];
            if (command.contains("undo")) {
                push(command, temp, history);
                continue;
            }

            if (command.contains("redo")) {
                push(command, history, temp);
                continue;
            }

            history.push(command.split("action ")[1]);
        }

        int historySize = history.size();
        String[] answer = new String[historySize];
        for (int i = 0; i < historySize; i++) {
            answer[historySize - 1 - i] = history.pop();
        }

        return answer;
    }

    private static void push(final String command, final Stack<String> stack1, final Stack<String> stack2) {
        final String[] split = command.split(" ");
        int loopSize = getLoopSize(split);
        for (int j = 0; j < loopSize; j++) {
            stack1.push(stack2.pop());
        }
    }

    private static int getLoopSize(final String[] split) {
        if (COUNT == 0) {
            return 0;
        }

        int size = 0;
        if (split.length == 1) {
            size = 1;
        } else {
            size = Integer.parseInt(split[1]);
        }

        if (COUNT >= size) {
            COUNT -= size;
            return size;
        }

        size = size - COUNT;
        COUNT = 0;
        return size;
    }

    public static void main(String[] args) {
        String[] result = solution(10, new String[]{"action addPage - 1", "action movePage 1", "undo"});
        Arrays.stream(result).forEach(num -> System.out.print(num + ", "));

        System.out.println();
        result = solution(10, new String[]{"action addPage - 1", "action movePage 1", "action move 10, 8, 4, 2", "undo 3", "redo 2"});
        Arrays.stream(result).forEach(num -> System.out.print(num + ", "));
    }
}
