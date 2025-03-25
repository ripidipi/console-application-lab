package related_to_the_collection;

public interface PersonFabric {

    static Person getPerson(String input, String inputMode) {
        String[] inputSplit = input.split(",");
        if (inputMode.equalsIgnoreCase("M")) {
            return Person.inputMixed(inputSplit);
        } else if (inputMode.equalsIgnoreCase("F")) {
            return Person.inputFromFile(inputSplit[0], inputSplit[1], inputSplit[2], inputSplit[3]);
        }
        return Person.input();
    }

}
