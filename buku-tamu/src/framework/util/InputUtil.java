package framework.util;

import java.util.Scanner;

/**
 * Utilitas pembacaan input dari konsol.
 */
public class InputUtil {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputUtil() {
    }

    /** Membaca satu baris; mengembalikan null jika input sudah habis (EOF). */
    public static String readLine(String prompt) {
        if (prompt != null && !prompt.isEmpty()) {
            System.out.print(prompt);
        }
        if (!SCANNER.hasNextLine()) {
            return null;
        }
        return SCANNER.nextLine().trim();
    }

    /** True jika input adalah "x" (perintah batal/keluar). */
    public static boolean isExit(String input) {
        return input != null && input.equalsIgnoreCase("x");
    }
}
