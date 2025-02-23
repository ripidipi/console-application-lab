package inputOutput;

import java.util.Scanner;

public interface Inputable {

    static Object Input() {
        try {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            return new Object();
        } catch (Exception e) {
            System.err.println("Invalid data. Try again");
        }
        return Input();
    }

}
