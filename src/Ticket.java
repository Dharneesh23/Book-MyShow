import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Ticket {//Ticket class to store the ticket fields of the user
    private String theatreName;//theatre name where the ticket is booked
    private LocalDate show_date;//date when the ticket is booked
    private String screen;//Screen name where the ticket is booked
    private LocalTime show_time;//timing of the show
    private long numberOfSeats;//number of seats booked
    private String movieName;//movie name
    private long paid_ticket;//total amount paid for the ticket
    private ArrayList<String> seats = new ArrayList<>();//seats booked by the user

    Ticket(String theatre, LocalDate date, String screen, LocalTime time, long numberOfSeats, long amount, ArrayList<String> seats, String movieName)//to pass the parameter to ticket constructor
     {
        this.screen = screen;//this will point itself for screen
        this.theatreName = theatre;//this will point itself for theatre
        this.show_date = date;//this will point itself for date
        this.show_time = time;//this will point itself for time
        this.numberOfSeats = numberOfSeats;//this will point itself for number of seats
        this.seats = seats;//this will point itself for seats
        this.movieName = movieName;//this will point itself for movie name
        this.paid_ticket = amount;//this will point itself for amount
    }

    public long getAmountPaid() {//getter for ticket amount paid
        return paid_ticket;
    }

    public String getMovieName() {//getter for movie name
        return movieName;
    }

    public String getTheatreName() {//getter for theatre name
        return theatreName;
    }

    public String getScreen() {//getter for screen
        return screen;
    }

    public LocalTime getTime() {//getter for time
        return show_time;
    }

    public long getNumberOfSeats() {//getter for number of seats
        return numberOfSeats;
    }

    public LocalDate getDate() {//getter for date
        return show_date;
    }

    public ArrayList<String> getSeats() {//getter for seats
        return seats;
    }
}
