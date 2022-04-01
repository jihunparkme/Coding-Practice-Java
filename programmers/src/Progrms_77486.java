import java.util.HashMap;
import java.util.Map;

public class Progrms_77486 {
    public static void main(String[] args) {
        int[] solution = solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"young", "john", "tod", "emily", "mary"},
                new int[]{12, 4, 2, 5, 10});

        for (int i = 0; i < solution.length; i++) {
            System.out.println(solution[i]);
        }
    }

    /**
     * 모든 판매원은 칫솔의 판매에 의하여 발생하는 이익에서 10% 를 계산하여 자신을 조직에 참여시킨 추천인에게 배분하고 나머지는 자신이 가집니다.
     * 단, 10% 를 계산할 때에는 원 단위에서 절사하며, 10%를 계산한 금액이 1 원 미만인 경우에는 이득을 분배하지 않고 자신이 모두 가집니다.
     *
     * 각 판매원의 이름을 담은 배열 enroll,
     * 각 판매원을 다단계 조직에 참여시킨 다른 판매원의 이름을 담은 배열 referral,
     * 판매량 집계 데이터의 판매원 이름을 나열한 배열 seller,
     * 판매량 집계 데이터의 판매 수량을 나열한 배열 amount가 매개변수로 주어질 때, 각 판매원이 득한 이익금을 나열한 배열을 return 하도록 solution 함수를 완성해주세요.
     *
     * 판매원에게 배분된 이익금의 총합을 계산하여(정수형으로), 입력으로 주어진 enroll에 이름이 포함된 순서에 따라 나열하
     */
    private static final int PRICE = 100;
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        //1. 부모 노드를 저장.
        Map<String, String> parents = new HashMap<>();
        Map<String, Integer> revenue = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            parents.put(enroll[i], referral[i]);
        }

        //2. 부모 노드에 계속 이득의 10% 씩 올리기
        for (int i = 0; i < seller.length; i++) {

            int cost = amount[i] * PRICE;
            String now = seller[i];

            while(!"-".equals(now)) {
                int tax = (int) (cost * 0.1);
                revenue.put(now, revenue.getOrDefault(now, 0) + cost - tax);

                cost = tax;
                now = parents.get(now);

                if (tax < 1) break;
            }
        }

        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = revenue.getOrDefault(enroll[i], 0);
        }

        return answer;
    }
}
