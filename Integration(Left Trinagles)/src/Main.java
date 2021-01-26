import java.util.function.DoubleUnaryOperator;

public class Main {

    public static double integrate(DoubleUnaryOperator f, double a, double b) {
        double h = 1;
        double s1 = 0;
        double s2 = 1;
        int step = 0;
        while (Math.abs(s2 - s1) > 0.000001) {
            s1 = 0;
            s2 = 0;
            for (int l = 0; (a + l * h) < b; l++) {
                s1 += f.applyAsDouble(a + l * h) * h;
            }
            System.out.println("s1 = " + s1);
            h /= 2;
            for (int i = 0; (a + i * h) < b; i++) {
                s2 += f.applyAsDouble(a + i * h) * h;
            }
            System.out.println("s2 = " + s2);
            System.out.println("h = " + h);
            System.out.println("s2 - s1 = " + (s2 - s1));
            System.out.println("Step " + step++);
            System.out.println("------------------------");

        }

        return s2;

    }

    public static void main(String[] args) {
        System.out.println("Result: " + integrate(x -> x + 2, 0, 10));//70.0
    }
}
