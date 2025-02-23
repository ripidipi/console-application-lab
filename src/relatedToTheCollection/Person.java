package relatedToTheCollection;

import java.time.format.DateTimeFormatter;

public record Person(String name, java.time.LocalDateTime birthday, double height, String passportID) {

    public String getBirthdayString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return birthday.format(formatter);
    }

    @Override
    public String toString() {
        return "Person {" +
                "\nname: " + name +
                "\nbirthday: " + getBirthdayString() +
                "\nheight: " + height +
                "\npassportID: " + passportID + '}';

    }
}
