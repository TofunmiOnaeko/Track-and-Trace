import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    public ArrayList<Establishment> establishmentArrayList = new ArrayList<Establishment>();
    public ArrayList<Event> eventArrayList = new ArrayList<Event>();
    public ArrayList<Event> nameFilteredEvents = new ArrayList<Event>();
    public ArrayList<Event> dateFilteredEvents = new ArrayList<Event>();
    public ArrayList<Event> userFilteredEvents = new ArrayList<Event>();

    public static void main(String[] args) throws FileNotFoundException {
    }

    /*
    This method reads the CSV file establishments.csv and stores the events into an ArrayList
    Returns: the establishment Array List so the details can be used by other methods.
    */
    public ArrayList<Establishment> readStoreEstablishment() throws FileNotFoundException {

        File establishments = new File("src/establishments.csv");

        Scanner read = new Scanner(establishments);

        //This line of code ensures that the first line of the csv file, the headings, are skipped.
        read.nextLine();

        while (read.hasNext()) {

            String[] temp = read.nextLine().split(",");
            String name = temp[0];
            String firstLineAddress = temp[1];
            String postCode = temp[2];
            int mxOccupancy = Integer.parseInt(temp[3]);

            Establishment newEstablishment = new Establishment(name, firstLineAddress, postCode, mxOccupancy);

            if (!establishmentArrayList.contains(newEstablishment)) {

                establishmentArrayList.add(newEstablishment);

            } else {
            }
        }

        return establishmentArrayList;
    }

    /*
    This method utilises the Array List returned by readStoreEstablishment method, but it then prints the establishments to the console.
     */
    public void printStoreEstablishment() throws FileNotFoundException {
        Controller c = new Controller();

        c.readStoreEstablishment();

        for (int i = 0; i < c.establishmentArrayList.size(); i++) {

            Establishment placeholder = c.establishmentArrayList.get(i);
            System.out.print("Establishment\n");
            System.out.println(placeholder.format());
        }

    }

    /*
    This method checks if establishment details are already listed within the establishment Array List, if they are listed the method returns false.
    This method allows us to ensure that there are no duplicates in our establishment Array List.
     */
    public boolean storeEstablishment(Establishment newEstablishment) {

        boolean fileFound = true;

        if (!establishmentArrayList.contains(newEstablishment)) {

            establishmentArrayList.add(newEstablishment);

        } else {

            fileFound = false;

        }

        return fileFound;
    }

    /*
    This method checks if event details are already listed within the event Array List, if they are listed the method returns false.
    This method allows us to ensure that there are no duplicates in our event Array List.
    */
    public boolean storeEvent(Event event) {

        boolean fileFound = true;

        if (!eventArrayList.contains(event)) {

            eventArrayList.add(event);

        } else {

            fileFound = false;
        }

        return fileFound;

    }

    /*
    This method allows us take an existing establishment Array List and filter it by an establishment's name.
    This method returns an Array List with the filtered results to be used by other methods.
    The establishment name is passed through to this method and is used to filter the existing establishment Array List.
     */
    public ArrayList<Event> filterEventByEstablishment(String estName) {

        for (Event event : eventArrayList) {

            ArrayList<Establishment> getEst = new ArrayList<Establishment>();
            getEst.add(event.getEstablishment());

            for (Establishment est : getEst) {
                if (est.getEstName().contains(estName)) {
                    nameFilteredEvents.add(event);
                } else {
                }
            }
        }

        return nameFilteredEvents;
    }

    /*
    This method allows us take an existing event Array List and filter it by the date of an event.
    This method returns an Array List with the filtered results to be used by other methods.
    The date of the event the user wants to filter by is passed through to this method and is used to filter the existing event Array List.
     */
    public ArrayList<Event> filterEventByDate(LocalDate eventDate) {

        for (Event event : eventArrayList) {

            if (event.getDate().equals(eventDate)) {
                dateFilteredEvents.add(event);

            } else {
            }
        }

        return dateFilteredEvents;
    }

    /*
    This method allows us take an existing event Array List and filter it by a user's name and email address.
    This method returns an Array List with the filtered results to be used by other methods.
    The user's name and email address we want to filter by is passed through to this method and is used to filter the existing event Array List.
     */
    public ArrayList<Event> filterEventByUser(String name, String email) {

        for (Event event : eventArrayList) {

            ArrayList<User> getUser = new ArrayList<User>();
            getUser.add(event.getUser());

            for (User user : getUser) {
                if (user.getName().contains(name) && user.getEmailAddress().contains(email)) {
                    userFilteredEvents.add(event);
                } else {
                }
            }
        }

        return userFilteredEvents;
    }

}






