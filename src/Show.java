import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Show {//Show pojo class
    private LocalTime starting_Time;// start time of show
    private LocalTime ending_Time;// end time of show
    private double ticket_price;//ticket price
    private LocalDate showDate;// show date

    private Screen screen;//screen
    private HashMap<Character, ArrayList<String>> seat_Arrangement = new HashMap<>();// seat pattern hash map, row name and seats

    public Show(LocalTime startTime, LocalTime endTime, LocalDate showDate,  Screen screen,double price, HashMap<Character, ArrayList<String>> seatArrangement) {
        this.starting_Time = startTime;//this will point itself for start time
        this.ending_Time = endTime;//this will point itself for end time
        this.showDate = showDate;//this will point itself for show date

        this.screen = screen;//this will point itself for screen
        this.ticket_price = price;//this will point itself for price
        this.seat_Arrangement = seatArrangement;//this will point itself for seat arrangement
    }

    public HashMap<Character, ArrayList<String>> getSeat_arrangement() {//getter for seat arrangement
        return seat_Arrangement;
    }

    public double getPrice() {//getter for ticket price
        return  ticket_price;
    }



    public LocalTime getEndtime() {//getter for end time
        return ending_Time;
    }

    public LocalTime getStarttime() {//getter for starting time
        return starting_Time;
    }

    public Screen getScreen() {//getter for screen
        return screen;
    }

    public LocalDate getShowDate() {//getter for shoe date
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {//setter for show date
        this.showDate = showDate;//this will point itself for showdate
    }

    public void setStarttime(LocalTime startTime) {//setter for start time
        this.starting_Time = startTime;//this will point itself for starting time
    }

    public void setEndtime(LocalTime endTime) {//setter for ending time
        this.ending_Time = endTime;//this will point itself for ending time
    }

    public void setSeat_arrangement(HashMap<Character, ArrayList<String>> seatArrangement) {//setter for seat arrangement
        this.seat_Arrangement = seatArrangement;//this will point itself for seat arrangement
    }

    @Override
    public boolean equals(Object obj) {//equals method for overriding
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Show shows = (Show) obj;
        return Objects.equals(this.starting_Time, shows.starting_Time)
                && Objects.equals(this.ending_Time, shows.ending_Time)
                && Objects.equals(this.showDate, shows.showDate);// to for same timing of the show
    }

    @Override
    public int hashCode() {
        return Objects.hash(starting_Time, ending_Time, showDate);
    }

    @Override
    public String toString() {
        return starting_Time.toString()  + ending_Time.toString();
    }
}