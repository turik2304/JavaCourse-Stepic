public class Duck extends Animal implements Swimmable, Swimmable2{


    public Duck(int age) {
        super(age);
    }

    @Override
    public void swim() {
        System.out.println("SWIM");

    }



    @Override
    public void eat() {

    }

    public static void main(String[] args) {
        Duck duck = new Duck(12);
        duck.swim();
    }

    @Override
    public void run() {

    }
}
