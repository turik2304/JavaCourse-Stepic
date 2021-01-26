public abstract class Animal {
    private static int age;
    /*
    public void swim() {
        System.out.println("Swim");
    }
         */
    public Animal(int age) {
        this.age = age;
    }
    public abstract void run();

    public static void main(String[] args) {
        System.out.println(age);
    }
}

