import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookMyShow {
    private static  ArrayList<Admin> admin_list = new ArrayList<>();// admin list to store admin reference
    private static  ArrayList<User> user_list = new ArrayList<>();// user list store user object reference
    private static  HashMap<String, Theatre> theatre_location= new HashMap<>();//theatre location to store in hash map

    // movie name and movie arraylist of objects to store the same name movie objects in the arraylist
    private static  HashMap<String, ArrayList<Movies>> movie_location = new HashMap<>();

    public static ArrayList<Admin> getAdminlist() { // getter for adminlist
        return admin_list;
    }


    public static ArrayList<User> getUseList() {//getter for userlist
        return user_list;
    }

    public static HashMap<String, ArrayList<Movies>> getmovielocation() { //getter for movie location
        return movie_location;
    }

    public static HashMap<String, Theatre> gettheatrelocation() {//getter for theatre location
        return theatre_location;
    }
    private static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");//date formatter
    private static LocalDate today = LocalDate.now();//to get current date
    private static DateTimeFormatter timeformatter=DateTimeFormatter.ofPattern("HH:mm");//time formatter

    public static DateTimeFormatter getTimeformatter() {//getter for time format
        return timeformatter;
    }
    public static LocalDate getToday() {//getter for get the local date
        return today;
    }

    public static DateTimeFormatter getFormatter() {//getter for date time formate
        return formatter;
    }
}