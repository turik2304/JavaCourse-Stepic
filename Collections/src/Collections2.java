import java.io.*;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Scanner;

public class Collections2 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        boolean isEvenNumber = true;
        while (s.hasNext()) {
            if (isEvenNumber) {
                s.next();
                isEvenNumber = false;
            } else {
                arrayDeque.add(s.nextInt());
                isEvenNumber = true;
            }
        }
        PrintStream p = new PrintStream(System.out);
        while (!arrayDeque.isEmpty()) {
            p.print(arrayDeque.pollLast());
            p.append(" ");
        }
        p.flush();
    }

}
