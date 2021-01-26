public interface Swimmable {
    public void swim();

    public default void run() {
        System.out.println("BEGI");
    };
    public void eat();

}
