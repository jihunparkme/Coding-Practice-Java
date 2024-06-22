package test20240622;

public class Problem01 {

    public static String solution(final String a, final String b) {
        StringBuilder result = new StringBuilder();

        int carry = 0;

        int aSize = a.length() - 1;
        int bSize = b.length() - 1;

        while (aSize >= 0 || bSize >= 0) {
            int digit1 = getNum(a, aSize);
            int digit2 = getNum(b, bSize);
            int sumDigits = digit1 + digit2 + carry;

            result.insert(0, sumDigits % 10);
            carry = sumDigits / 10;

            aSize--;
            bSize--;
        }

        if (carry > 0) {
            result.insert(0, carry);
        }

        return result.toString();
    }

    private static int getNum(final String num, final int size) {
        if (size >= 0) {
            return Character.getNumericValue(num.charAt(size));
        }

        return 0;
    }

    public static void main(String[] args) {
        final String result = solution("131", "1452");
        System.out.println("1583".equals(result));
    }
}
