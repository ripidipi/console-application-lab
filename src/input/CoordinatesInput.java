package input;

import modules.Coordinates;

import java.util.Scanner;

public class CoordinatesInput{

    public static Coordinates Input() {
        long x = 0;
        float y = 0;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the coordinates by x:");
            x = scanner.nextLong();
            System.out.println("Enter the coordinates by y:");
            y = scanner.nextFloat();
        } catch (Exception e) {
            System.out.println("Invalid coordinates");
            return Input();
        }
        return new Coordinates(x, y);
    };
}
