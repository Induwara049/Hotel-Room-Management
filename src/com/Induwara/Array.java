package com.Induwara;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Array {

    private static void HotelMenu() {
        System.out.println("......MENU......");
        System.out.println("V. View all rooms");
        System.out.println("A. Add customers to rooms");
        System.out.println("E. View empty rooms");
        System.out.println("D. Delete a customer");
        System.out.println("F. Find a customer");
        System.out.println("S. Store data into file");
        System.out.println("L. Load file");
        System.out.println("O. Alphabetical order of customer names");
        System.out.println("Q. EXIT");
    }

    public static void main(String[] args) throws FileNotFoundException {


        //creating the arrays
        String hotelAll[] = new String[9];
        String customer_details[] = new String[9];

        Scanner input = new Scanner(System.in);
        String roomName;
        initialise(hotelAll, customer_details);
        //getting hotelRoomNumber menu

        String userOption = " ";
        System.out.println("Welcome  to the hotel");
        while (!userOption.equals("Q") ) {
            HotelMenu();
            userOption = input.next().toUpperCase(Locale.ROOT);
            switch (userOption) {
                case "V": {
                    view(hotelAll);
                    break;
                }
                case "A":{
                    add(hotelAll, customer_details);
                    break;
                }
                case "E":{
                    emptyRoomsView(hotelAll);
                    break;
                }
                case "D":{
                    deleteCustomer(hotelAll, customer_details);
                    break;
                }
                case "F": {
                    find(hotelAll);
                    break;
                }
                case "S": {
                    storeData(hotelAll, customer_details);
                    break;
                }
                case "L": {
                    load(hotelAll);
                    break;
                }
                case "O": {
                    sorting(hotelAll);
                    break;
                }
                default:
                    System.out.println("Please enter a valid menu option letter");
            }
        }
        if (userOption.toUpperCase(Locale.ROOT).equals("Q")){
            System.out.println("Program Over. Thank you !!!");
        }

    }

    //hotelUpdate function//
    public static void hotelUpdate(String hotel[]) {
        add(hotel, null );
    }

    //initialise function//
    private static void initialise(String hotelRef[], String customer_details[]) {
        for (int x = 1; x < 9; x++) {
            hotelRef[x] = "empty";
            customer_details[x] = "";

        }
    }

    //add function//
    private static String[] add(String hotel[], String customer_details[]) {
        String roomName;
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
                }else if (hotel[roomNum].equals("empty")) {
                    System.out.println("Enter first name: ");
                    String first_name = input.next();
                    System.out.println("Enter surname: ");
                    String sur_name = input.next();
                    System.out.println("Enter credit card number: ");
                    int creditcard_number = input.nextInt();
                    System.out.println("Enter number of guests: ");
                    int guest_count = input.nextInt();

                    hotel[roomNum] = first_name;
                    customer_details[roomNum] = " \n Full name: " + first_name + " " + sur_name + "\n Number of related guests: " + guest_count +
                            "\n Credit card number: " + creditcard_number;

                    for (int x = 1; x < 9; x++) {
                        if (hotel[x].equals("empty")) {
                            System.out.println("room " + x + " is empty");
                        } else {
                            System.out.println("room " + x + " occupied by " + hotel[x]);
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
        return hotel;
    }

    //view function//
    private static void view(String hotel[]) {
        for (int x = 1; x < 9; x++) {
            if(hotel[x].equals("empty") ){
                System.out.println("room " + x + " is empty");
            }
            else {
                System.out.println("room " + x + " occupied by " + hotel[x]);
            }
         }
    }

    //empty room view function//
    private static void emptyRoomsView(String hotel[]) {
        for (int x = 1; x < 9; x++) {
            if (hotel[x].equals("empty")) {
                System.out.println("room " + x + " is empty");
            }
        }
    }

    //customer deletion function//
    private static void deleteCustomer(String hotel[], String customer_details[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Name of the customer need to be deleted: ");
        String D_Customer = input.next().toUpperCase(Locale.ROOT);

        for (int i = 1; i < 9; i++) {
            if (hotel[i].toUpperCase(Locale.ROOT).equals(D_Customer)) {
                hotel[i] = "empty";
                customer_details[i] = null;

                System.out.println("Customer deleted successfully");
            }
        }

    }

    //customer finding function//
    private static void find(String hotel[]) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter customer name for room:");
        String customer_name;
        String checked = " ";
        customer_name = input.next().toUpperCase(Locale.ROOT);  //stores name they enter as customers name
        for (int i = 1; i < 9; i++) {
            //int j = i+1;
            if (hotel[i].toUpperCase(Locale.ROOT).equals(customer_name)) {
                System.out.println("room " + i + " is occupied by " + hotel[i]);
                checked = "Found";
            }
        }
        if(checked.equals(" "))    System.out.println("There is no customer by that name...");

    }

    private static void storeData(String hotelRef[], String customer_details[]) {
        try {
            //create save file
            FileWriter saveArray = new FileWriter("D:\\RoomsArray.txt");
            for (int i = 1; i < hotelRef.length; i++) {
                saveArray.write("Room " + i + ":" + hotelRef[i] + "\n" + customer_details[i] + "\n");
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


    //getting alphabetical order function//
    private static void sorting(String[] hotelRef) {
        System.out.println("Start Sort");
        //check null index
        for (int i = 1; i < hotelRef.length; i++) {
            for (int j = i + 1; j < hotelRef.length; j++) {
                if (hotelRef[i] == null | hotelRef[j] == null) {
                    System.out.print("");
                    //check not null index
                } else if (hotelRef[i] != null | hotelRef[j] != null) {
                    if (hotelRef[i].compareTo(hotelRef[j]) > 0) {
                        String temp;
                        temp = hotelRef[i];
                        hotelRef[i] = hotelRef[j];
                        hotelRef[j] = temp;
                    }
                }
            }
        }
        //display rooms array
        for (int i = 1; i < hotelRef.length; i++) {
            System.out.println(hotelRef[i]);
        }
        System.out.println("End sort");
    }

    //data loading function//
    private static String[] load(String[] room) throws FileNotFoundException {
        //get File object
        File myObj = new File("D:\\RoomsArray.txt");
        if (myObj.exists()) {
            System.out.println("File name: " + myObj.getName());
            System.out.println("Absolute path: " + myObj.getAbsolutePath());

            Scanner readFile=new Scanner(myObj);
            while (readFile.hasNext()){
                System.out.println(readFile.nextLine());
            }

        } else {
            System.out.println("The file does not exist.");
        }
        return room;
    }




}
