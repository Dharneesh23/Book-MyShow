import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Screen {
    private  String name;            // screen name
    private  long number_Seats;     // number of seats in that screen
    private  long availableSeats;   //available seats while booking
    private  HashSet<Show> shows = new HashSet<>();//hashmap for shows
    private  String grid;//grid variable name

    private HashMap<Character, ArrayList<String>> seat_Arrangement = new HashMap<>();    // seat pattern hash map, row name and seats

    public Screen(String name, long numberOfSeats, HashMap<Character, ArrayList<String>> seats, String grid) {//screen constructor
        this.name = name;//this will point itself for name
        this.number_Seats = numberOfSeats;//this will point itself for number of seats
        this.seat_Arrangement = seats;//this will point itself for seats
        this.availableSeats = numberOfSeats;//this will point itself for available seats
        this.grid = grid;//this will point itself for grid
    }

    public void setAvailableseats(long availableSeats) {//getter for available seats with any parameter to call this
        this.availableSeats = availableSeats;//this will point itself for available seats
    }

    public  long getAvailableseat() {//getter for available seat
        return availableSeats;
    }

    public HashSet<Show> getShows() {//getter for get shows
        return shows;
    }

    public HashMap<Character, ArrayList<String>> getSeatarrangement() {//getter for seat arrangement
        return seat_Arrangement;
    }

    public long getNumberofseats() {//getter for number of seats
        return number_Seats;
    }

    public String getGrid() {//getter for grid
        return grid;
    }

    public String getName() {//getter for name
        return name;
    }

    public void setSeatarrangement(HashMap<Character, ArrayList<String>> seatArrangement) {//setters
        this.seat_Arrangement = seatArrangement;
    }
}