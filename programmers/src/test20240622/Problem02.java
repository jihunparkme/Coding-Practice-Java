package test20240622;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem02 {

    /**
     * 10
     * 0 255 123 12 2 4 12 4 55 2
     * 5 0 3
     *
     * [0 255 123] 12 2 (0 255 123) 55 2
     *
     *
     * 0 255 123 12 2 4 12 4 55 2
     * dest : 5
     * src: 0
     * size : 3
     *
     * src(0) 부터 src(0) + size(3) - 1 위치의 값들을
     * dest(5) 부터 dest(5) + size(3) - 1 위치까지 복사
     */
    public static void memcpy(byte[] v, final int dest, final int src, final int size) {
        if (isNotValid(v, dest, src, size)) return;

        try {
            for (int i = 0; i < size; i++) {
                v[dest + i] = v[src + i];
            }
        } catch (Exception e) {}
    }

    private static boolean isNotValid(final byte[] v, final int dest, final int src, final int size) {
        if (size == 0) {
            return true;
        }

        if (dest > v.length - 1 || src > v.length - 1) {
            return true;
        }

        if (src + size - 1 > v.length - 1 || dest + size - 1 > v.length - 1) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        byte[] memory = new byte[c];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            memory[i] = (byte) Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int dest = Integer.parseInt(st.nextToken());
        int src = Integer.parseInt(st.nextToken());
        int size = Integer.parseInt(st.nextToken());

        memcpy(memory, dest, src, size);

        for (int i = 0; i < memory.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(Byte.toUnsignedInt(memory[i]));
        }
    }
}