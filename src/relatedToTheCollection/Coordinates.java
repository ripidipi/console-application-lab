package relatedToTheCollection;

public record Coordinates(long x, float y) {

    @Override
    public String toString() {
        return "Coordinates {" +
                "\nx coordinate: " + x +
                "\nx coordinate: " + y + '}';

    }

}