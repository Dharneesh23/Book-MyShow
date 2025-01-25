import java.util.HashMap;

public class Theatre {//theatre pojo class
    private String theatreName;//theatre name
    private String location;//theatre location
    private  HashMap<String, Screen> screen_name = new HashMap<>();//screen name and screen object of theatre

    public Theatre(String location,String name) {//to pass the parameter to theatre constructor
        this.theatreName=name;//this will point itself for name
        this.location=location;//this will point itself for location
    }

    public String getTheatrename() {//getter for theatre name
        return theatreName;
    }

    public  HashMap<String, Screen> getScreen_name() {//getter for screen name at hashmap
        return screen_name;
    }

    public String getLocation() {//getter for location
        return location;
    }
}