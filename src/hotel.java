
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class hotel {
    //array of objects
    private static Rooms[] roomArray = new Rooms[9];
    private static Person[] personArray = new Person[9];

    // previous code
    private static void initialise() {
        for (int x = 1; x < 9; x++) {
            roomArray[x] = new Rooms(x, "empty");
            personArray[x] = new Person("","",0,0 );

        }
    }
    //Hotel menu
    private static void HotelMenu() {
        System.out.println("......MENU......");
        System.out.println("V. View all rooms");
        System.out.println("A. Add customers to rooms");
        System.out.println("V. View empty rooms");
        System.out.println("D. Delete a customer");
        System.out.println("F. Find a customer");
        System.out.println("S. Store data into file");
        System.out.println("L. Load file");
        System.out.println("O. Alphabetical order of customer names");
        System.out.println("Q.EXIT");
    }

    //Add function//
    private static void add() {
        int roomNum = 1;
        while (roomNum < 9) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter room number (1-8) or 9 to stop:");
            if (input.hasNextInt()) {
            roomNum = input.nextInt();

                if (roomNum > 9) {
                    System.out.println("Room number is out of range");
                    break;
                }else if(roomNum == 9) {
                    break;
                }else if (roomArray[roomNum].customerName.equals("empty")) {
                    System.out.println("Enter first name: ");
                    String first_name = input.next();
                    System.out.println("Enter surname: ");
                    String sur_name = input.next();
                    System.out.println("Enter credit card number: ");
                    int creditcard_number = input.nextInt();
                    System.out.println("Enter number of guests: ");
                    int guest_count = input.nextInt();

                    roomArray[roomNum].customerName = first_name;
                    personArray[roomNum].f_name = first_name;
                    personArray[roomNum].sur_name = sur_name;
                    personArray[roomNum].credit_card_num = creditcard_number;
                    personArray[roomNum].number_of_guests = guest_count;

                    for (int x = 1; x < 9; x++) {
                        if (roomArray[x].customerName.equals("empty")) {
                            System.out.println("room " + x + " is empty");
                        } else {
                            System.out.println("room " + x + " occupied by " + roomArray[x].customerName);
                        }
                    }
                } else {
                    System.out.println("Room " + roomNum + " already have a customer. Please chose another room \n");
                }
            }
            else {
                System.out.println("Invalid input. Please enter a room number");
            }
        }

    }

    //View function//
    private static void view() {
        for (int roomCounter = 1; roomCounter < 9; roomCounter++) {
            int roomNumber = roomCounter;
            if (roomArray[roomCounter].customerName.equals("empty")) {
                System.out.println("room " + roomNumber + " is empty");
            } else {
                System.out.println("room " + roomNumber + " occupied by " + roomArray[roomCounter].customerName);
            }
        }

    }

    //empty room view function//
    private static void emptyRoomsView() {


        for (int x = 1; x < 9; x++) {
            if (roomArray[x].customerName.equals("empty")) {
                System.out.println("room " + x + " is empty");
            }
        }
    }

    //customer deletion function//
    private static void deleteCustomer() {
        String found = " ";
        Scanner input = new Scanner(System.in);
        System.out.println("Name of the customer need to be deleted: ");
        String D_Customer = input.next().toUpperCase(Locale.ROOT);

        for (int i = 1; i < 9; i++) {
            if (roomArray[i].customerName.toUpperCase(Locale.ROOT).equals(D_Customer)) {
                roomArray[i].customerName = "empty";
                personArray[i] = new Person(" "," ",0,0 );
                System.out.println("Customer deleted successfully");
                found = "yes";
            }

        }
        if(found.equals(" ")) {
            System.out.println("There is no customer by that name");
        }
    }

    //customer finding function//
    private static void find() {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter customer name for room:");
        String customer_name;
        String checked = " ";
        customer_name = input.next();  //stores name they enter as customers name
        for (int i = 1; i < 9; i++) {
            if (roomArray[i].customerName.toUpperCase(Locale.ROOT).equals(customer_name.toUpperCase(Locale.ROOT))) {
                System.out.println("room " + i + " is occupied by " + personArray[i].f_name + " " + personArray[i].sur_name);
                checked = "Found";
            }
        }
        if(checked.equals(" "))    System.out.println("There is no customer by that name...");

    }

    //data storing function//
    private static void storeData() {
        try {
            //create save file
            FileWriter saveArray = new FileWriter("D:\\RoomsClass.txt");
            for (int i = 1; i < roomArray.length; i++) {
                saveArray.write("Room" + i + ":" + roomArray[i].customerName +
                        "\n Full name: " + personArray[i].f_name + " " + personArray[i].sur_name + "\n Number of related guest :" + personArray[i].number_of_guests +
                        "\n Credit card number: " + personArray[i].credit_card_num + "\n");
            }
            //close file
            saveArray.close();
            System.out.println("Sucess......");
        } catch (IOException saveArray) {
            System.out.println("Incorrect Input....");
            saveArray.printStackTrace();
        }
        //return statement

    }

    //data loading function//
    private static Rooms[] load(Rooms[] rooms) throws FileNotFoundException {
        //get File object
        File myObj = new File("D:\\RoomsClass.txt");
        if (myObj.exists()) {
            System.out.println("File name: " + myObj.getName());
            System.out.println("Absolute path: " + myObj.getAbsolutePath());

            Scanner readFile = new Scanner(myObj);
            while (readFile.hasNext()) {
                System.out.println(readFile.nextLine());
            }

        } else {
            System.out.println("The file does not exist.");
        }
        return rooms;
    }

    //getting alphabetical order function//
    private static void sorting() {
        System.out.println("Start Sort");
        //check null index
        for (int i = 1; i < roomArray.length; i++) {
            for (int j = i + 1; j < roomArray.length; j++) {
                if (roomArray[i].customerName.equals("empty") | roomArray[j].customerName.equals("empty")) {
                    System.out.print("");
                    //check not null index
                } else if (!roomArray[i].customerName.equals("empty") | !roomArray[j].customerName.equals("empty")) {
                    if (roomArray[i].customerName.compareTo(roomArray[j].customerName) > 0) {
                        String temp;
                        temp = roomArray[i].customerName;
                        roomArray[i].customerName = roomArray[j].customerName;
                        roomArray[j].customerName = temp;
                    }
                }
            }
        }
        //display rooms array
        for (int i = 1; i < roomArray.length; i++) {
            System.out.println(roomArray[i].customerName);
        }
        System.out.println("End sort");
    }


    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        String roomName;
        initialise();
        //getting hotelRoomNumber menu
        System.out.println("Welcome  to the hotel");
        String userOption = " ";
        while (!userOption.toUpperCase(Locale.ROOT).equals("Q")) {
            HotelMenu();
            userOption = input.next().toUpperCase(Locale.ROOT);
            switch (userOption) {
                case "V": {
                    view();
                    break;
                }
                case "A": {
                    add();
                    break;
                }
                case "E": {
                    emptyRoomsView();
                    break;
                }
                case "D": {
                    deleteCustomer();
                    break;
                }
                case "F": {
                    find();
                    break;
                }
                case "S": {
                    storeData();
                    break;
                }
                case "L": {
                    load(roomArray);
                    break;
                }
                case "O": {
                    sorting();
                    break;
                }
                default:
                    System.out.println("Please enter a valid menu option letter");
            }

        }
        if (userOption.toUpperCase(Locale.ROOT).equals("Q")) {
            System.out.println("Program Over. Thank you !!!");
        }
    }
}
