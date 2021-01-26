import java.io.*;
import java.util.Scanner;

public class CharStreams {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line = "a";
        Double result = 0.0;
        while (scanner.hasNext()) {
            try {
                result += Double.parseDouble(scanner.next());
            } catch (NumberFormatException e) {
                e.getSuppressed();
            }
        }
        PrintWriter printWriter = new PrintWriter(System.out);
        printWriter.printf("%.6f", result);
        printWriter.flush();
    }

}
