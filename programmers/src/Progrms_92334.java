import java.util.*;
import java.util.stream.Collectors;

public class Progrms_92334 {
    public static void main(String[] args) {
//        int[] solution = solution(new String[]{"muzi", "frodo", "apeach", "neo"},
//                new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, 2);
        int[] solution = solution2(new String[]{"muzi", "frodo", "apeach", "neo"},
                new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, 2);
        for (int i = 0; i < solution.length; i++) {
            System.out.println(solution[i]);
        }
    }

    /**
     * 각 유저는 한 번에 한 명의 유저를 신고할 수 있습니다.
     *      신고 횟수에 제한은 없습니다. 서로 다른 유저를 계속해서 신고할 수 있습니다.
     *      한 유저를 여러 번 신고할 수도 있지만, 동일한 유저에 대한 신고 횟수는 1회로 처리됩니다.
     * k번 이상 신고된 유저는 게시판 이용이 정지되며, 해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송합니다.
     *      유저가 신고한 모든 내용을 취합하여 마지막에 한꺼번에 게시판 이용 정지를 시키면서 정지 메일을 발송합니다.
     */
    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Map<String, Integer> reportedTimes = new HashMap<>();
        Map<String, List<String>> reportReceipt = new HashMap<>();
        for (int i = 0; i < report.length; i++) {
            String[] result = report[i].split(" ");

            List<String> reportList = reportReceipt.getOrDefault(result[0], new ArrayList<>());
            if (reportList.contains(result[1])) continue;

            reportedTimes.put(result[1], reportedTimes.getOrDefault(result[1], 0) + 1);

            reportList.add(result[1]);
            reportReceipt.put(result[0], reportList);
        }

        Map<String, Integer> mailCount = new HashMap<>();
        for (String name : reportedTimes.keySet()){
            if (reportedTimes.get(name) >= k) {
                for (int i = 0; i < id_list.length; i++) {
                    if (reportReceipt.getOrDefault(id_list[i], new ArrayList<>()).contains(name)) {
                        mailCount.put(id_list[i], mailCount.getOrDefault(id_list[i], 0) + 1);
                    }
                }
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            answer[i] = mailCount.getOrDefault(id_list[i], 0);
        }

       return answer;
    }

    public static int[] solution2(String[] id_list, String[] report, int k) {
        List<String> list = Arrays.stream(report).distinct().collect(Collectors.toList());
        HashMap<String, Integer> count = new HashMap<>();
        for (String s : list) {
            String target = s.split(" ")[1];
            count.put(target, count.getOrDefault(target, 0) + 1);
        }

        return Arrays.stream(id_list).map(user -> {
            List<String> reportList = list.stream().filter(s -> s.startsWith(user)).collect(Collectors.toList());
            return reportList.stream().filter(s -> count.getOrDefault(s.split(" ")[1], 0) >= k).count();
        }).mapToInt(Long::intValue).toArray();
    }
}
