import java.time.LocalDate;

public class Movies {
    private  String name; // variable for name
     private  Long duration; // variable for duration
    private  String location; // variable for location
    private  LocalDate startDate;// variable for date of the movie
    private  Theatre theatre; // variable for theatre
    private  Screen screen; // variable for screen
    private  Show show; // variable for show

    public Movies(String name,String location, LocalDate startDate,long duration, Theatre theatre,Screen screen, Show show) {
        this.name = name;//this will point itself for name
        this.duration = duration;//this will point itself for duration
        this.location = location;//this will point itself for location
        this.startDate = startDate;//this will point itself for starting date
        this.theatre = theatre;//this will point itself for theatre
        this.screen = screen;//this will point itself for screen
        this.show = show;//this will point itself for show
    }

    // Getters
    public String getName() {//getter for name
        return name;
    }

     public Long getDuration() {//getter for duration
     return duration;
     }

    public LocalDate getStartingdate() {//getter for stating date
        return startDate;
    }

    public String getLocation() {//getter for location
        return location;
    }

    public Screen getscreen() {//getter for screen
        return screen;
    }

    public Show getShow() {//getter for show
        return show;
    }

    public Theatre getTheatre() {//getter for theatre
        return theatre;
    }
}
