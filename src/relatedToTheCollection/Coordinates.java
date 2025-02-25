package relatedToTheCollection;

import exeptions.EmptyLine;
import inputOutput.BasicDataTypesInput;

public record Coordinates(Long x, Float y) {

    @Override
    public String toString() {
        return "Coordinates {" +
                "\nx coordinate: " + xToString() +
                "\nx coordinate: " + yToString() + '}';

    }
    public String yToString() {
        return (y==null ? " " : y.toString());
    }

    public String xToString() {
        return (x==null ? " " : x.toString());
    }

    public static boolean isRightFill(Coordinates coordinates) {
        return coordinates != null;
    }

    /**
     * Input manager to create object with class Coordinates
     * @return object with class Coordinates
     * @throws EmptyLine for empty gaps, if it incorrect format for it
     */
    public static Coordinates Input() throws EmptyLine {
        try {
            System.out.println("Enter information about coordinates");
            Long x = BasicDataTypesInput.Input("x coordinate", Long.class, false,
                    false, false, null);
            Float y = BasicDataTypesInput.Input("y coordinate", Float.class, false,
                    false, false, null);
            return new Coordinates(x, y);
        } catch (Exception e) {
            System.out.println("Invalid data. Try again");
        }
        System.out.flush();
        return Input();
    }

    public static Coordinates InputFromFile(String x, String y) {
        try {
            return new Coordinates(BasicDataTypesInput.InputFromFile("CoordinateX", x, Long.class,
                    false, false, false, null),
                    BasicDataTypesInput.InputFromFile("CoordinateY", y, Float.class, false,
                            false, false, null));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}