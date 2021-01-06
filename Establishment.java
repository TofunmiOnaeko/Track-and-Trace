public class Establishment {
    private String estName;
    private String firstLineAddress;
    private String postCode;
    private int maxOccupancy;

    Establishment(String estName, String firstLineAddress, String postCode, int maxOccupancy) {
        this.estName = estName;
        this.firstLineAddress = firstLineAddress;
        this.postCode = postCode;
        this.maxOccupancy = maxOccupancy;
    }

    Establishment(String estName, String[] address, int maxOccupancy) {
        this.estName = estName;
        this.firstLineAddress = address[0];
        this.postCode = address[1];
        this.maxOccupancy = maxOccupancy;
    }

    public static void main(String[] args) {
    }

    /*
    This method returns the name and address of the establishment.
    This method enables us to correctly format any establishment details we provide.
    */
    public String format() {
        return String.format("\t Name: %s,\n" +
                        "\t Address: %s",
                estName,
                firstLineAddress + " " + postCode);
    }

    public String getFirstLineAddress() {
        return firstLineAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getEstName() {
        return estName;
    }
}



