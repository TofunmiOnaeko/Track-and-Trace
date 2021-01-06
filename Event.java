import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Event {
    private final User user;
    private LocalDate date;
    private String formattedDate;
    private final int partyNumber;
    private final Establishment establishment;

    Event(User user, LocalDate date, int partyNumber, Establishment establishment) {
        this.user = user;
        this.date = date;
        this.partyNumber = partyNumber;
        this.establishment = establishment;
    }

    Event(User user, int partyNumber, Establishment establishment) {
        this.user = user;
        this.partyNumber = partyNumber;
        this.establishment = establishment;
    }

    public static void main(String[] args) {  }

    /*
    This method returns the formatting for the first line of the instance details and ensures the random number generated is positive.
    This method enables us to appropriately start the instance details.
     */
    public String initialText() {
        Random rN = new Random();
        int random = rN.nextInt();

        if(random < 0){

            random = random * -1;

        } else {}

        return "Event ID: " + random + "| Recorded User";
    }

    /*
    This method returns the format for the user's details, including the date of birth into the requested format.
    This method enables us to return the correct format for the user details aspect of the instance details.
    */
    public String instanceDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = user.getDob();
        String bdayFormattedDate = date.format(formatter);

        return String.format("\t Name %s\n " +
                        "\t Date of Birth %s\n " +
                        "\t Email %s\n " +
                        "\t Contact Number %s\n " +
                        "\t Age %d",
                user.getName(),
                bdayFormattedDate,
                user.getEmailAddress(),
                user.getPhoneNumber(),
                user.calculateAge());
    }

    /*
    This method formats the event date and time and returns the event date, time, party size, it also returns the heading for the next block of text (Establishments).
    This method enables us to return the correct format for the event details section of the instance details.
    */
    public String laterText() {

        LocalTime time = LocalTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (date != null) {

            formattedDate = date.format(dateFormatter);

        } else {

                formattedDate = LocalDate.now().format(dateFormatter);
        }


        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = time.format(timeFormatter);

        return String.format("Date %s\n" +
                        "Time %s\n" +
                        "Party size %s\n" +
                        "Establishment",
                formattedDate,
                formattedTime,
                partyNumber);
    }

    /*
    This method returns the establishment name and address, the last section of the instance details.
    This method makes sure we have the format requested for the establishment details section of the instance details.
    */
    public String establishmentDetails() {
        return String.format("\t Name: %s,\n" +
                        "\t Address: %s\n",
                establishment.getEstName(),
                establishment.getFirstLineAddress() + " " + establishment.getPostCode());
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public LocalDate getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

}
