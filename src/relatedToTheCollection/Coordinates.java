package relatedToTheCollection;

public record Coordinates(Long x, Float y) {

    @Override
    public String toString() {
        return "Coordinates {" +
                "\nx coordinate: " + (x==null ? " " : x) +
                "\nx coordinate: " + (y==null ? " " : y) + '}';

    }

}