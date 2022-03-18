import java.util.*;

public class Progrms_92341 {
    public static void main(String[] args) {
        int[] solution = solution(new int[]{180, 5000, 10, 600}, new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"});
//        int[] solution = solution(new int[]{120, 0, 60, 591}, new String[]{"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"});
        for (int i = 0; i < solution.length; i++) {
            System.out.println(solution[i]);
        }
    }

    /**
     *  기본시간(분)	기본요금(원)	단위시간(분)	단위요금(원)
     *  180	        5000	    10      	600
     *
     *  어떤 차량이 입차된 후에 출차된 내역이 없다면, 23:59에 출차된 것으로 간주합니다.
     *  00:00부터 23:59까지의 입/출차 내역을 바탕으로 차량별 누적 주차 시간을 계산하여 요금을 일괄로 정산합니다.
     *  누적 주차 시간이 기본 시간이하라면, 기본 요금을 청구합니다.
     *  누적 주차 시간이 기본 시간을 초과하면, 기본 요금에 더해서, 초과한 시간에 대해서 단위 시간 마다 단위 요금을 청구합니다.
     *      초과한 시간이 단위 시간으로 나누어 떨어지지 않으면, 올림합니다.
     *      ⌈a⌉ : a보다 작지 않은 최소의 정수를 의미합니다. 즉, 올림을 의미합니다
     *
     *
     *  차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 정수 배열에 담아서 return
     */
    public static int[] solution(int[] fees, String[] records) {

        int baseTime = fees[0];
        int basePrice = fees[1];
        int unitTime = fees[2];
        int unitPrice = fees[3];
        Map<Integer, Integer> parkingTime = new HashMap<>();
        Map<Integer, String> innerTime = new HashMap<>();

        for (int i = 0; i < records.length; i++) {
            String[] record = records[i].split(" ");
            int carNumber = Integer.parseInt(record[1]);
            if ("IN".equals(record[2])) {
                innerTime.put(carNumber, record[0]);
                parkingTime.putIfAbsent(carNumber, 0);
            } else{
                int stay = getStayTime(innerTime.get(carNumber), record[0]);
                parkingTime.put(carNumber, parkingTime.get(carNumber) + stay);
                innerTime.remove(carNumber);
            }
        }

        for (Integer carNumber : innerTime.keySet()) {
            int stay = getStayTime(innerTime.get(carNumber), "23:59");
            parkingTime.put(carNumber, parkingTime.get(carNumber) + stay);
        }

        for(Integer carNumber : parkingTime.keySet()) {
            Integer time = parkingTime.get(carNumber);
            if (time <= baseTime) parkingTime.put(carNumber, basePrice);
            else parkingTime.put(carNumber, getParkingPrice(time, baseTime, basePrice, unitTime, unitPrice));
        }

        int[] answer = new int[parkingTime.keySet().size()];
        int cnt = 0;
        List<Integer> keys = new ArrayList<>(parkingTime.keySet());
        Collections.sort(keys);
        for (Integer carNumber : keys) {
            answer[cnt++] = parkingTime.get(carNumber);
        }

        return answer;
    }

    private static int getParkingPrice(Integer time, int baseTime, int basePrice, int unitTime, int unitPrice) {
        double diff = ((double)time - (double)baseTime) / (double)unitTime;
        return basePrice + (int) Math.ceil(diff) * unitPrice;
    }

    private static int getStayTime(String in, String out) {
        String[] inTime = in.split(":");
        String[] outTime = out.split(":");
        int inMin = Integer.parseInt(inTime[0]) * 60 + Integer.parseInt(inTime[1]);
        int outMin = Integer.parseInt(outTime[0]) * 60 + Integer.parseInt(outTime[1]);

        return outMin - inMin;
    }
}
