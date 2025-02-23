package relatedToTheCollection;

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

}