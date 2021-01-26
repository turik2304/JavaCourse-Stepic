public class Test {

    public static String getCallerClassAndMethodName() {
        Thread currentThread = Thread.currentThread();
        StackTraceElement[] methods = currentThread.getStackTrace();
        int lengthStack = methods.length;
        if (lengthStack == 3) {
            return null;
        } else {
            return methods[3].getClassName() +
                    "#" +
                    methods[3].getMethodName(); // your implementation here
        }
    }


    public static void main(String[] args) {

        System.out.println(getCallerClassAndMethodName());
        m1();
    }

    static void m1() {
        System.out.println(getCallerClassAndMethodName());
        m2();
    }

    static void m2() {
        System.out.println(getCallerClassAndMethodName());
        m3();
    }

    static void m3() {
        System.out.println(getCallerClassAndMethodName());

    }

}