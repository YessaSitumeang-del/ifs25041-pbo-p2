package framework.util;

import java.util.Scanner;

public class InputUtil {
    private final Scanner scanner = new Scanner(System.in);

    // Cetak prompt lalu baca satu baris. Jika input habis, dianggap "x".
    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.hasNextLine() ? scanner.nextLine().trim() : "x";
    }
}
