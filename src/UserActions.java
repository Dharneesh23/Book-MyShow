import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class UserActions {//useraction class for user options
    public static Scanner scanner = new Scanner(System.in);

    public static User login() {//login method for user

        System.out.print("Enter The Username:");
        String userId = scanner.next();// get user name
        System.out.print("Enter The Password:");
        String userPass = scanner.next();// get password


        for (User temp : BookMyShow.getUseList()) {// get user arraylist
            if (temp.getUserid().equals(userId)) {// check all the  id and pass
                if (temp.getPassword().equals(userPass)) {//check the condition
                    return temp;// if matches return the current user object
                } else {
                    return new User(null, null, null);//  wrong pass return reference with null value
                }
            }

        }


        return null;// no account pass null
    }

    public static void register() {// if user is not found this method will call


        System.out.print("No user found \nso register hear!! 1.continue for register\n2.Exit:");
        int choice = scanner.nextInt();//get the input


        if (choice==1) {// check input
            System.out.print("Enter the username:");
            String username = scanner.next();// get user name
            System.out.print("Enter the password:");
            String userpassword = scanner.next();// get password
            System.out.print("Enter your Location:");
            String userlocation = scanner.next();// get locatiom

            BookMyShow.getUseList().add(new User(username, userpassword, userlocation));//added to userlist
        }

        else if (choice==2) {
            return;// else return
        }
    }




    public static void operations(User currentUser) {//user option method


        UserActions.displayAllMovies(currentUser, BookMyShow.getToday());//added to display

        while (true) {
            System.out.println("1.Display Movies\n2.view Tickets\n3.change location\n4.change date\n5.exit");
            System.out.print("Enter your choice:");
            int choice = scanner.nextInt();//get user choice

            switch (choice) {
                case 1:// the admin call the method 1 this operation will class
                    UserActions.displayAllMovies(currentUser, BookMyShow.getToday());
                    break;
                case 2:// the admin call the method  this operation will class
                    UserActions.viewtickets(currentUser);
                    break;
                case 4:// the admin call the method 4 this operation will class
                    LocalDate dateUpdate = UserActions.changeDate(currentUser);
                    UserActions.displayAllMovies(currentUser, dateUpdate);// after the change in the date show the  movies

                    break;
                case 3:// the admin call the method 3 this operation will class
                    UserActions.changeLocation(currentUser);
                    UserActions.displayAllMovies(currentUser, BookMyShow.getToday());// after the change in the location
                    break;
                case 5:// the admin call the method 5 this operation will class
                    return;
            }
        }
    }

    public static void displayAllMovies(User currentuser, LocalDate today) {// display movies based on users location and date

        HashSet<String> moviesinthatlocation = new HashSet<>();// to store the movies in that location
        ArrayList<Movies> moviesavailable = new ArrayList<>();//arraylist to add an available movies


        if (BookMyShow.getmovielocation().keySet().isEmpty()) {//check the condition
            System.out.println( "No movies in theatre sorry.." );
            return;
        }

        System.out.println("Movies Available");


        for (var movies : BookMyShow.getmovielocation().keySet()) {//var is data type it will automatically change with required data type
            var movielist = BookMyShow.getmovielocation().get(movies);
            for (var movieobject : movielist) {
                if(movieobject.getLocation().equals(currentuser.getLocation())) {
                    moviesinthatlocation.add(movies);
                    if (movieobject.getStartingdate().isEqual(today)) {
                        System.out.println(movies);


                    }else{
                        System.out.println("Please select another date ");
                        System.out.println("1.Change date \n 2.Exit");
                        int choice = scanner.nextInt();
                        switch (choice)
                        {
                            case 1:
                                changeDate(currentuser);//to call the change date method
                                break;
                            case 2:
                                break;
                        }
                    }
                }
break;
            }
        }
        if (moviesinthatlocation.isEmpty()) {
            System.out.println("Movie is not available at your location ");
            System.out.println("1.Do you change your location \n 2.Exit");
            int choice = scanner.nextInt();
            switch (choice)
            {
                case 1:
                    changeLocation(currentuser);//to call change location method
                    break;
                case 2:
                    break;
            }
            return;
        }

