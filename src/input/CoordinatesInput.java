package input;

import exeptions.EmptyLine;
import relatedToTheCollection.Coordinates;

/**
 * class with static method for coordinates readings
 */
public class CoordinatesInput implements Inputable{

    /**
     * Input manager to create object with class Coordinates
     * @return object with class Coordinates
     * @throws EmptyLine for empty gaps, if it incorrect format for it
     */
    public static Coordinates Input() throws EmptyLine {
        try {
            Long x = BasicDataTypesInput.Input("x coordinate", Long.class, true, false);
            Float y = BasicDataTypesInput.Input("y coordinate", Float.class, true, false);
            return new Coordinates(x, y);
        } catch (Exception e) {
            System.out.println("Invalid data. Try again");
        }
        return Input();
    }
}
