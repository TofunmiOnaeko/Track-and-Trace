import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class User {

    private final String name;
    private final LocalDate dob;
    private final String emailAddress;
    private final String phoneNumber;
    private long age;

    User(String name, LocalDate dob, String emailAddress, String phoneNumber) {
        this.name = name;
        this.dob = dob;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    /*
    This method calculates the age based on the dob given when instantiating a user.
    This method enables us to complete the User data needed in the instance details.
     */
    public long calculateAge() {
        long age = ChronoUnit.YEARS.between(dob, LocalDate.now());
        this.age = age;
        return age;
    }

    public static void main(String[] args) {
    }

    /*
    This method returns the single line printing for the instance details.
     */
    public String singleLinePrint() {
        return name + " | Contact number: " + phoneNumber;
    }

    /*
    This method formats the user's dob and returns what will become the main body of the instance details: the name, dob, email address, phone number and age of the user.
    This method enables us to correctly format the user details we provide.
     */
    public String instanceDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dob.format(formatter);

        return String.format("\t Name %s\n " +
                        "\t Date of Birth %s\n " +
                        "\t Email %s\n " +
                        "\t Contact Number %s\n " +
                        "\t Age %d",
                name,
                formattedDate,
                emailAddress,
                phoneNumber,
                age);
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}