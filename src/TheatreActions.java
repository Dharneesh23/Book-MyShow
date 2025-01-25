import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TheatreActions {//theatre actions class
    public static HashMap<String, HashSet<Show>> theatreAgainstShow(ArrayList<Movies> movieAvailable) {
        HashMap<String, HashSet<Show>> theatreAgainstShow = new HashMap<>();
        for (var currentMovie : movieAvailable) {// get all the movie object fields which is entered by the user (only the user location and date)

            if (theatreAgainstShow.containsKey(currentMovie.getTheatre().getTheatrename())) {// if theatre name already exists
                theatreAgainstShow.get(currentMovie.getTheatre().getTheatrename()).add(currentMovie.getShow());// add the show object
            } else {
                HashSet<Show> show = new HashSet<>();// create new hash set
                show.add(currentMovie.getShow());// add to hash set
                theatreAgainstShow.put(currentMovie.getTheatre().getTheatrename(), show);// add to hashmap
            }
        }
        return theatreAgainstShow;
    }
}