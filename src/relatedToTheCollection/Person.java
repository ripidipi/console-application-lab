package relatedToTheCollection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Person(String name, LocalDateTime birthday, Double height, String passportID) {

    public String getBirthdayString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return birthday.format(formatter);
    }

    @Override
    public String toString() {
        return "Group admin {" +
                "\nname: " + name +
                "\nbirthday: " + getBirthdayString() +
                "\nheight: " + (height==null ? "" : height) +
                "\npassportID: " + passportID + '}';
    }

}
