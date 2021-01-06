/*
The programme CSC8011 track and trace has been created to take event details that have been input using the keyboard,
save those details to an Array List and return filtered results.

Resources:
1. https://howtodoinjava.com/java/date-time/localdate-parse-string/ - examples used to understand how to turn a String into a local date to be able to filter by it.
Original Author: Lokesh Gupta
Modifying Author: Tofunmi Onaeko

2. https://beginnersbook.com/2014/01/how-to-convert-12-hour-time-to-24-hour-date-in-java/ - examples used to understand how to change a 12 hour clock (hh:mm) into a 24 hour clock (HH:mm).
Original Author: Chaitanya Singh
Modifying Author: Tofunmi Onaeko

3. https://ncl.instructure.com/courses/24644/pages/assessment-demo-1?module_item_id=1297844 - used canvas demos and lecture notes as a starting point for the Event, User and Establishment classes and to check various concepts.
Original Author: Jordan Barnes
Modifying Author: Tofunmi Onaeko

4. https://nucode.ncl.ac.uk/scomp/msc-computer-science/csc8011/code-examples-from-videos - used to check various concepts, i.e. switch statements, for loops and calling methods
Original Author: Jordan Barnes
Modifying Author: Tofunmi Onaeko

5. https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html - used to understand how to calculate the Years between two dates
Original Author: Unknown
Modifying Author: Tofunmi Onaeko
*/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class IO {

    private final Controller controller;

    IO() {
        this.controller = new Controller();
    }

    /*
    The main method starts the programme and enables the user to select the program menu, select the debug method or quit the program.
     */
    public static void main(String[] args) {
        System.out.println("Please input a number to select an option from the following:\n" +
                "1. Run the main menu\n" +
                "2. Run the debug method\n" +
                "3. Quit the program\n");

        boolean exit = false;

        while (!exit) {

            Scanner scan = new Scanner(System.in);

            int choice = scan.nextInt();

            switch (choice) {

                case 1:
                    IO menuIO = new IO();

                    menuIO.menu();

                    //The code below ensures the user sees the main menu again when choosing the next option.
                    System.out.println("\nPlease input a number to select an option from the following:\n" +
                            "1. Run the main menu\n" +
                            "2. Run the debug method\n" +
                            "3. Quit the program\n");

                    break;

                case 2:
                    IO debugIO = new IO();

                    debugIO.debug();

                    //The code below ensures the user sees the main menu again when choosing the next option.
                    System.out.println("\nPlease input a number to select an option from the following:\n" +
                            "1. Run the main menu\n" +
                            "2. Run the debug method\n" +
                            "3. Quit the program\n");

                    break;

                case 3:
                    exit = true;
                    break;
            }
        }
    }

    /*
    This method ensures the user sees the main menu options again after choosing an intial option.
     */
    public String mainMenu() {
        return "\nPlease input a number to select an option from the following:\n" +
                "1. Record an Event\n" +
                "2. Add Establishment\n" +
                "3. Filters\n" +
                "4. Print Events\n" +
                "5. Print Establishments\n" +
                "6. Return to the previous menu\n";
    }

    public void menu() {
        ArrayList<Event> eArrayList = controller.eventArrayList;

        ArrayList<Establishment> estArrayList = controller.establishmentArrayList;

        Scanner scanner = new Scanner(System.in);

        IO IO = new IO();

        System.out.println(IO.mainMenu());

        boolean quit = false;

        while (!quit) {

            int select = scanner.nextInt();

            switch (select) {
                case 1:
                    //The first case allows the user to input an event and save it to an Array List.
                    Scanner case1 = new Scanner(System.in);

                    System.out.println("\nYou have chosen to record an event.\n" +
                            "Please input the information as requested:\n" +
                            "Name: (please include your first and last name)\n");
                    String name = case1.nextLine();

                    System.out.println("\nestablishment name\n");
                    String establishmentName = case1.nextLine();

                    System.out.println("\nestablishment first line of address\n");
                    String firstLine = case1.nextLine();

                    System.out.println("\nestablishment postcode\n");
                    String postCode = case1.nextLine();

                    System.out.println("\nestablishment Maximum Occupancy\n");
                    int mxOccupancy = case1.nextInt();

                    System.out.println("\nevent month (please include this as a single number where appropriate. i.e. June = 6)\n");
                    int month = case1.nextInt();

                    System.out.println("\nevent date (please enter a number)\n");
                    int date = case1.nextInt();

                    System.out.println("\nparty number (as a number)\n");
                    int partyNumber = case1.nextInt();

                    System.out.println("\nyear of birth\n");
                    int yearOfBirth = case1.nextInt();

                    System.out.println("\nmonth of birth (please include this as a single number where appropriate. i.e. June = 6)\n");
                    int monthOfBirth = case1.nextInt();

                    System.out.println("\ndate of birth (please enter a number)\n");
                    int dateOfBirth = case1.nextInt();

                    System.out.println("\nemail address\n");
                    String email = case1.next();

                    System.out.println("\nphone number\n");
                    String phoneNumber = case1.next();

                    //calculates if the user's date of birth is in the future.
                    if (LocalDate.of(yearOfBirth, monthOfBirth, dateOfBirth).isAfter(LocalDate.now())) {

                        System.out.println("\nYour Date of Birth cannot be in the future, we will now return you to the main menu\n");

                    //calculates if the email address is valid and contains an @ sign.
                    } else if (!email.contains("@")) {

                        System.out.println("\nYour email must include an @ sign, we will now return you to the main menu.\n");

                    //calculates that a phone number is the correct length and doesn't include international calling codes.
                    } else if (phoneNumber.length() != 11 || phoneNumber.contains("+")) {

                        System.out.println("\nPhone number must be 11 digits long and must not have an international calling code (i.e. +44),\n" +
                                "we will now return you to the main menu.\n");

                    } else {

                        //the following block creates new objects (Event, User and Establishment) if all the data entered is valid and adds the Event and Establishment to the right Array Lists.
                        User newUser = new User(name, LocalDate.of(yearOfBirth, monthOfBirth, dateOfBirth), email, phoneNumber);

                        Establishment newEstablishment = new Establishment(establishmentName, firstLine, postCode, mxOccupancy);

                        Event newEvent = new Event(newUser, LocalDate.of(LocalDate.now().getYear(), month, date), partyNumber, newEstablishment);

                        //The following code checks if an array list already contains the event created, if it doesn't the event is recorded
                        if(eArrayList.contains(newEvent.getUser())) {

                            System.out.println("\nYour event already exists\n");

                        } else {

                            eArrayList.add(newEvent);
                            System.out.println("\nYour event has been recorded\n");

                        }

                        //The following code checks if an array list already contains the establishment created, if it doesn't the establishment is recorded
                        if(estArrayList.contains(newEstablishment.getEstName())) {

                            System.out.println("\nYour establishment already exists\n");

                        } else {

                            estArrayList.add(newEstablishment);
                            System.out.println("\nYour establishment has been recorded\n");
                        }

                    }

                    System.out.println(IO.mainMenu());

                    break;

                case 2:
                    //The second case allows the user to input an establishment and save it to the establishment Array List.
                    Scanner case2 = new Scanner(System.in);

                    System.out.println("\nPlease enter the establishment details as asked\n" +
                            "Establishment name:\n");
                    String name2 = case2.nextLine();

                    System.out.println("\nEstablishment first line of address:\n");
                    String firstLine2 = case2.nextLine();

                    System.out.println("\nEstablishment postcode:\n");
                    String postCode2 = case2.nextLine();

                    System.out.println("\nEstablishment Maximum Occupancy:\n");
                    int mxOccupancy2 = case2.nextInt();

                    Establishment newEstablishment2 = new Establishment(name2, firstLine2, postCode2, mxOccupancy2);

                        if (estArrayList.contains(newEstablishment2)) {

                            /*for(Establishment est : estArrayList) {
                                        est.getEstName();

                            if(est.getEstName().equals(name2)) {

                                System.out.println("\nYour establishment already exists\n");

                            } else {

                                estArrayList.add(newEstablishment2);
                                System.out.println("\nYour establishment has been recorded\n");
                            }

                            }*/


                            System.out.println("\nYour establishment already exists\n");

                        } else {

                            estArrayList.add(newEstablishment2);
                            System.out.println("\nYour establishment has been recorded\n");
                        }

                    System.out.println(IO.mainMenu());
                    break;

                case 3:
                    //The third case allows the user to select an option from the filter menu and filter any events and establishments that have already been stored.
                    Scanner filterScanner = new Scanner(System.in);

                    System.out.println("\nYou have selected the filters option, please select a filter option from the following:\n" +
                            "1. Select records by establishment\n" +
                            "2. Select records by date\n" +
                            "3. Select records by name and email address\n" +
                            "4. Return to the previous menu\n");

                    int option = filterScanner.nextInt();

                    boolean back = false;

                    while (!back) {

                        switch (option) {

                            case 1:
                                //The first case in the filter menu allows the user to filter saved Events by establishment name.
                                System.out.println("\nPlease type in the name of the establishment:\n");

                                Scanner filterCase1 = new Scanner(System.in);

                                String estName = filterCase1.nextLine();

                                controller.filterEventByEstablishment(estName);

                                for(Event event: controller.nameFilteredEvents) {
                                    System.out.println(event.initialText());
                                    System.out.println(event.instanceDetails());
                                    System.out.println(event.laterText());
                                    System.out.println(event.establishmentDetails());
                                }

                                //The code below clears the Array List with the filtered details, so if the user wants to filter by a different establishment name, they get a fresh result.
                                controller.filterEventByEstablishment(estName).clear();
                                back = true;
                                break;

                            case 2:
                                //The second case in the filter menu allows the user to filter saved Events by the event date.
                                System.out.println("\nPlease type in the date of the event, in the format dd/mm/yyyy:\n");

                                Scanner filterCase2 = new Scanner(System.in);

                                String Date = filterCase2.next();

                                //The block of code takes a date in the format dd/MM/yyyy and turns it into a local date so it can be used to filter the Event in the Controller class.
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                LocalDate eventDate = LocalDate.parse(Date, formatter);

                                controller.filterEventByDate(eventDate);

                                for(Event event: controller.dateFilteredEvents) {
                                    System.out.println(event.initialText());
                                    System.out.println(event.instanceDetails());
                                    System.out.println(event.laterText());
                                    System.out.println(event.establishmentDetails());
                                }

                                //The code below clears the Array List with the filtered details, so if the user wants to filter by a different date they get a fresh result.
                                controller.dateFilteredEvents.clear();
                                back = true;
                                break;

                            case 3:
                                //The third case in the filter menu allows the user to filter saved Events by the user's name and email address.
                                System.out.println("\nPlease type in the name (first name and last name):\n");
                                Scanner filterCase3 = new Scanner(System.in);

                                String nameC3 = filterCase3.nextLine();

                                System.out.println("\nPlease type in the email address:\n");

                                String emailC3 = filterCase3.nextLine();

                                controller.filterEventByUser(nameC3, emailC3);

                                for(Event event: controller.userFilteredEvents) {
                                    System.out.println(event.initialText());
                                    System.out.println(event.instanceDetails());
                                    System.out.println(event.laterText());
                                    System.out.println(event.establishmentDetails());
                                }

                                //The code below clears the Array List with the filtered details, so if the user wants to filter by a different name and email address, they get a fresh result.
                                controller.filterEventByUser(nameC3, emailC3).clear();
                                back = true;
                                break;

                            case 4:
                                back = true;
                                break;
                        }
                    }

                    System.out.println(IO.mainMenu());
                    break;

                case 4:
                    //The fourth case allows the user to print all saved events in the requested format.
                    for (int i = 0; i < eArrayList.size(); i++) {
                        Event placeholder = eArrayList.get(i);
                        System.out.println(placeholder.initialText());
                        System.out.println(placeholder.instanceDetails());
                        System.out.println(placeholder.laterText());
                        System.out.println(placeholder.establishmentDetails());
                    }

                    System.out.println(IO.mainMenu());
                    break;

                case 5:
                    //The fourth case allows the user to print all saved establishments in the requested format.
                    for (int i = 0; i < estArrayList.size(); i++) {

                        Establishment placeholder = estArrayList.get(i);
                        System.out.print("Establishment\n");
                        System.out.println(placeholder.format());
                    }

                    System.out.println(IO.mainMenu());
                    break;

                case 6:
                    quit = true;
                    break;
            }
        }
    }

    /*
    The debug method takes a variety of Event, Users and Establishments and test the code in all the classes with valid, boundary, erroneous and extreme values.
    This method tests that the code across all classes works as expected, all erroneous instances have been commented out with reasons as to why they are erroneous.
     */
    public void debug() {
        User userValid = new User("Tofunmi Onaeko", LocalDate.of(1994, 7, 21), "tonaeko@hotmail.co.uk", "07805381111");
        System.out.println(userValid.calculateAge());
        System.out.println(userValid.instanceDetails());
        System.out.println(userValid.singleLinePrint());

        User userValid1 = new User("Anna Gregory", LocalDate.of(1993, 11, 12), "a.greg@hotmail.com", "07805381111");
        System.out.println(userValid1.calculateAge());
        System.out.println(userValid1.instanceDetails());
        System.out.println(userValid1.singleLinePrint());

        User userBoundary = new User("Grand Father", LocalDate.of(1920, 11, 5), "grand.father@hotmail.co.uk", "071111111111");
        System.out.println(userBoundary.calculateAge());
        System.out.println(userBoundary.instanceDetails());
        System.out.println(userBoundary.singleLinePrint());

        //extreme user (extreme values as his age will be stupid large):
        User userExtreme = new User("Adam Genesis", LocalDate.of(1, 1, 1), "1.1@hotmail.com", "11111111111");
        System.out.println(userExtreme.calculateAge());
        System.out.println(userExtreme.instanceDetails());
        System.out.println(userExtreme.singleLinePrint());

        /*erroneous user, good as it failed when I expected it to, user name is an int instead of a String and phone number is not a string
        User userError = new User(54321, LocalDate.of(2000, July, 10)), "numbershotmail.com", 00000000000);
        System.out.println(userError.calculateAge());
        System.out.println(userError.instanceDetails());
        System.out.println(userError.singleLinePrint());*/

        Establishment estValid = new Establishment("Lola's", "95 Glenthorn Road", "NE1 1RR", 20);
        estValid.format();

        String[] address = {"45 Francisco Close", "RM16 6YE"};
        Establishment estValid1 = new Establishment("Fat Hippo", address, 22);
        System.out.println(estValid1.format());

        String[] address1 = {"Apartment A 95 Glenthorn Road", "NE1 1RR"};
        Establishment estBoundary = new Establishment("McDonalds", address1, 150);
        System.out.println(estBoundary.format());

        Establishment estExtreme = new Establishment("Starbucks", "1111111111111111111111", "111111111", 2000);
        System.out.println(estExtreme.format());

        /*erroneous user, failed as wrong data types used, no string for establishment name, first line of address and postcode, string used for maximum occupancy
        Establishment estError = new Establishment(Frankie and Benny, 95 Glenthorn Road, NE1 1RR, "150");
        System.out.println(estError.format());*/

        Event validEvent = new Event(userValid, LocalDate.of(LocalDate.now().getYear(), 5, 10), 4, estValid);
        System.out.println(validEvent.initialText());
        System.out.println(validEvent.instanceDetails());
        System.out.println(validEvent.laterText());
        System.out.println(validEvent.establishmentDetails());

        Event validEvent1 = new Event(userValid1, 10, estValid1);
        System.out.println(validEvent1.initialText());
        System.out.println(validEvent1.instanceDetails());
        System.out.println(validEvent1.laterText());
        System.out.println(validEvent1.establishmentDetails());

        Event boundaryEvent = new Event(userBoundary, LocalDate.of(LocalDate.now().getYear(), 5, 10), 4, estBoundary);
        System.out.println(boundaryEvent.initialText());
        System.out.println(boundaryEvent.instanceDetails());
        System.out.println(boundaryEvent.laterText());
        System.out.println(boundaryEvent.establishmentDetails());

        Event extremeEvent = new Event(userExtreme, LocalDate.of(LocalDate.now().getYear(), 1, 1), 100, estExtreme);
        System.out.println(extremeEvent.initialText());
        System.out.println(extremeEvent.instanceDetails());
        System.out.println(extremeEvent.laterText());
        System.out.println(extremeEvent.establishmentDetails());

        /*Erroneous, a local data that doesn't exist, string as a partyNumber
        Event erroneousEvent = new Event(userExtreme, LocalDate.of(LocalDate.now().getYear(), 13, 32), "100", estError);
        System.out.println(erroneousEvent.initialText());
        System.out.println(erroneousEvent.instanceDetails());
        System.out.println(erroneousEvent.laterText());
        System.out.println(erroneousEvent.establishmentDetails());
         */
    }
}
