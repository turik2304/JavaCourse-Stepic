import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class Animal implements Serializable {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Animal) {
            return Objects.equals(name, ((Animal) obj).name);
        }
        return false;
    }

    public static Animal[] deserializeAnimalArray(byte[] data) {
        final int numberOfAnimals;
        final Animal[] animals;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(data));
            numberOfAnimals = inputStream.readInt();
            animals = new Animal[numberOfAnimals];
            for (int i = 0; i < numberOfAnimals; i++) {
                animals[i] = (Animal) inputStream.readObject();
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException();
        }
        return animals;

    }

    public static void main(String[] args) {
        byte[] b = {50, 51, 43, 43, 23};
        Animal[] anotherAnimals = deserializeAnimalArray(b);
        System.out.println(Arrays.toString(anotherAnimals));
    }
}

