import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        byte[] test = {};
        InputStream in = new ByteArrayInputStream(test);
        int a;
        int b;
        a = in.read();
        while (true) {
            if (a == -1) {
                break;
            }
            if (a == 13) {
                b = in.read();
                if (b == 10) {
                    System.out.println(b);
                    a = in.read();
                } else {
                    System.out.println(a);
                    a = b;
                }
            } else {
                System.out.println(a);
                a = in.read();
            }
        }
    }

}