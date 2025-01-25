import java.util.ArrayList;

public class User {//User pojo class
    private String userid;//user id
    private String password;//user pass
    private String location;//user location
    private ArrayList<Ticket>tickets=new ArrayList<>();//store the user tickets



    public ArrayList<Ticket> getTickets() {//getter for tickets
        return tickets;
    }
    public String getLocation() {//getter for location
        return location;
    }
    public void setLocation(String location) {//setter for location
        this.location = location;//this will point itself for location
    }


    public User(String id,String pass,String location){//constructor to initialize the id and pass and location
        this.userid=id;//this will point itself for id
        this.password=pass;//this will point itself for pass
        this.location=location;//this will point itself for location
    }
    public User(){//constructor for creating object without parameter

    }

    public String getPassword() {//getter for password
        return password;
    }

    public String getUserid() {//getter for  user id
        return userid;
    }
}