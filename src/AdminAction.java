import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminAction {//Admin action class for admin operation
    private static  Scanner scanner = new Scanner(System.in);//Scanner method why static ?using static for to call the scanner method we do not create any object with the help of static

    public static Admin admin_login() {//Admin login method for admin login
        System.out.print("Enter The admin_Id:");
        String adminId = scanner.next();//get the admin id
        System.out.print("Enter The Password:");
        String adminPass = scanner.next();//get the admin password
        for (Admin admin : BookMyShow.getAdminlist()) {//enhanced for loop,to iterate at one by one and stored to referance of admin
            if (admin.getUserid().equals(adminId)) {//check the condition
                if (admin.getPassword().equals(adminPass)) {//check the condition
                    return admin;//return the reference of admin
                }
            }
        }
        return null;
    }

    public static void operations(Admin user) {//operation class for admin operation
        while (true) {
            System.out.println("Enter the Admin Choice");
            System.out.println(
                    "1.Add Movie\n2.view all Movies\n3.Add Theatre\n4.view all theatre\n5.Exit\n");
            System.out.print("Enter your choice :");

            int choice = scanner.nextInt();//get the admin choice

            switch (choice) {
                case 1:
                    AdminAction.addMovie_to_theatre(scanner);// the admin call the method 1 this operation will class
                    break;
                case 2:
                    AdminAction.viewallmovies();// the admin call the method 2 this operation will class
                    break;
                case 3:
                    AdminAction.addTheatre();// the admin call the method 3 this operation will class
                    break;
                case 4:
                    AdminAction.viewalltheatre();// the admin call the method 4 this operation will class
                    break;
                case 5:
                    return;// exit the loop
                default:
                    System.out.println("Enter The Valid Choice");//  invalid choice
            }
        }
    }

    public static void addMovie_to_theatre(Scanner scanner) {//add the movie to theatre method

        System.out.print("Enter the Movie Name:");
        String movieName = scanner.next();//get the movie name
        System.out.print("Enter the Location:");
        String movieLocation = scanner.next();//get the location of the movie based on theatre
        System.out.print("Enter the Date:");
        LocalDate Starting_Date = LocalDate.parse(scanner.next(), BookMyShow.getFormatter());// LocalDate is implicit class in java to get the local date
        boolean Check_The_Theatre_in_This_location = false;// number of theatre in that locations

        ArrayList<Theatre> theatreInThatLocation=new ArrayList<>();//array list for theatre location
        for (String theatreName : BookMyShow.gettheatrelocation().keySet()) {
            Theatre theatreInLocation = BookMyShow.gettheatrelocation().get(theatreName);// get theatre

            if (theatreInLocation.getLocation().equals(movieLocation)) {//check the theatres in that location
                System.out.println("you entered location Theatre  are....."+ theatreInLocation.getTheatrename());

                theatreInThatLocation.add(theatreInLocation);
                Check_The_Theatre_in_This_location = true;//number of theater in that loactions
            }
        }

        if (!Check_The_Theatre_in_This_location) {// if no theatre in that location this statement is call
            System.out.println("No theatre in your location...........");
            return;
        }

        Theatre currentTheatre = null;// to get the current looping theatre

        theatre: while (true) {//this condition will end until the condition will false
            System.out.print("Enter The Theatre Name:");
            String Theatre_Name = scanner.next();//get theatre name

            for (var theatreObjects :theatreInThatLocation) {// check the input theatre name is correct or already exists in that location
                if (theatreObjects.getTheatrename().equals(Theatre_Name)) {
                    currentTheatre=theatreObjects;
                    break theatre;
                }
            }
            System.out.print("Incorrect theatre name******\ncontinue enter any number or press (1) to exit:");
            int choice = scanner.nextInt();
            if (choice==1) {
                return;
            }
            continue theatre;

        }

        Screen currentScreen = null;// store current screen
        screen: while (true) {//this condition will end until the condition will false
            for (String screenName : currentTheatre.getScreen_name().keySet()) {
                System.out.println("Screen in theatre are \n"+screenName);
            }

            System.out.print("Enter your Screen Name:");
            String Screen_Name = scanner.next();//get the screen name

            for (String screen : currentTheatre.getScreen_name().keySet()) {// check the entered screen is in theatre
                if (screen.equals(Screen_Name)) {
                    currentScreen = currentTheatre.getScreen_name().get(screen);
                    break screen ;
                }
            }

            System.out.print("Incorrect Screen name****\n continue press any number or press (1) to exit:");//to reenter the choice
            int choice = scanner.nextInt();
            if (choice==1) {
                return;
            }
            continue screen;

        }

        show: while(true) {//this condition will end until the condition will false
            System.out.print("Enter The Show start time(HH:mm): ");// get the show starting time
            LocalTime Show_Start_Time = LocalTime.parse(scanner.next(), BookMyShow.getTimeformatter());// Using localTime store the time and format is (HH:mm)
            System.out.print("Enter The Movie Duration :");
            long Movie_Duration = Long.parseLong(scanner.next());// get the duration of the movie
            LocalTime endTime = Show_Start_Time.plusMinutes(Movie_Duration + 30);// end time is starttime +duration and 30 minutes for the interval

            System.out.print("Enter the price of the show :");
            long price = Long.parseLong(scanner.next());//to get the ticket price

            Show currentShow = null;// store current show

            if (currentScreen.getShows().isEmpty()) {// if show class is empty add directly
                currentShow = new Show(Show_Start_Time, Show_Start_Time.plusMinutes(Movie_Duration + 30),
                        Starting_Date, currentScreen, price, currentScreen.getSeatarrangement());//create new show object
                currentScreen.getShows().add(currentShow);//add to the arraylist
                if (!BookMyShow.getmovielocation().containsKey(movieName)) {
                    BookMyShow.getmovielocation().put(movieName, new ArrayList<Movies>());
                }//if new movie name add the arraylist of movie object
                BookMyShow.getmovielocation().get(movieName).add(new Movies(movieName, movieLocation, Starting_Date,
                        Movie_Duration, currentTheatre, currentScreen, currentShow));//add to the movie arraylist
                System.out.println("<<<<Movie added successfully>>>>");
                return;
            }

            for (Show show : currentScreen.getShows()) {// if show is already there


                //if there is a show already in that particular date check the timing
                if (show.getShowDate().isEqual(Starting_Date)) {
                    if ((Show_Start_Time.isBefore(show.getStarttime()) || Show_Start_Time.isAfter(show.getEndtime()))
                            && (endTime.isBefore(show.getStarttime()) || endTime.isAfter(show.getEndtime()))) {//check the timing of the show already exists or not

                        currentShow = new Show(Show_Start_Time, Show_Start_Time.plusMinutes(Movie_Duration + 30),
                                Starting_Date, currentScreen, price, currentScreen.getSeatarrangement());// Store in the show object
                        if (currentScreen.getShows().contains(currentShow)) {//check for same show already exists or not
                            System.out.print("show already exists....\n Enter again number to continue or press (1) to exit:");//to reenter
                            int choice = scanner.nextInt();
                            if (choice==1) {
                                return;
                            }
                            continue show;
                        }
                    } else {
                        System.out.print("Show already exists..\n Enter any number to continue  or press (1) to exit:");//to reenter the choice
                        int choice = scanner.nextInt();
                        if (choice==1) {
                            return;
                        }
                        continue show;}
                }
                if (!BookMyShow.getmovielocation().containsKey(movieName)) {//if new movie name add the arraylist of movie object
                    BookMyShow.getmovielocation().put(movieName, new ArrayList<Movies>());
                }
                currentScreen.getShows().add(currentShow);//add to the arraylist

                BookMyShow.getmovielocation().get(movieName)
                        .add(new Movies(movieName, movieLocation,Starting_Date,
                                Movie_Duration, currentTheatre, currentScreen, currentShow));//add to the movie arraylist
                System.out.println("<<<<<Movie added successfully>>>>");
                return;


            }
            break;
        }
    }

    public static void viewallmovies() {//to print all movies that are added
        if (BookMyShow.getmovielocation().isEmpty()) {
            System.out.println("No movies sorry");
            return;
        }

        for (String movieName : BookMyShow.getmovielocation().keySet()) {
            ArrayList<Movies> movies = BookMyShow.getmovielocation().get(movieName);
            for (Movies currentMovie : movies) {//enhanced for_loop it will get the element one by one
                System.out.println("Theatre Name :" + currentMovie.getTheatre().getTheatrename());//for display purpose
                System.out.println("Theatre Location :" + currentMovie.getTheatre().getLocation());//for display purpose
                System.out.println("Theatre Screen :" + currentMovie.getscreen().getName());//for display purpose
                System.out.println("Movie Name :" + currentMovie.getName());//for display purpose
                System.out.println("Movie Date :" + currentMovie.getShow().getShowDate());//for display purpose
                System.out.println("Show start Time:" + currentMovie.getShow().getStarttime());//for display purpose
                System.out.println("Show end Time:" + currentMovie.getShow().getEndtime());//for display purpose
                System.out.println("Seat arrangement :" + currentMovie.getscreen().getSeatarrangement());//for display purpose

            }
        }
    }

    public static void addTheatre() {//add theatre method for adding theatres

        System.out.print("Enter the Theatre Name :");
        String theatreName = scanner.next();// get theatre name

        System.out.print("Enter the Theatre location :");
        String theatreLocation = scanner.next();// get theatre location

        for (String existingTheatreName : BookMyShow.gettheatrelocation().keySet()) {//check the theatre name in that location whether it will already exists or not
            Theatre currentTheatre = BookMyShow.gettheatrelocation().get(existingTheatreName);

            if (existingTheatreName.equals(theatreName) && currentTheatre.getLocation().equals(theatreLocation)) {
                System.out.println("Theatre Already exists....");
                return;
            }
        }

        Theatre currentTheatreAdding = new Theatre(theatreLocation, theatreName);

        System.out.print("Enter total Number Of Screens in theatre :");
        long total_screen = Long.parseLong(scanner.next());//get the number of screens
        int screenCount = 1;

        screen: while (screenCount <= total_screen) {//check the condition for entering a theatre name
            System.out.print("Enter theatre Screen Name:");
            String screenName = scanner.next();

            for (String existingScreenName : currentTheatreAdding.getScreen_name().keySet()) {
                if (existingScreenName.equals(screenName)) {//check the screen name already exists
                    System.out.println("Screen name is already exists.....");
                    continue screen;
                }
            }

            System.out.print("Enter the total Number of Seats in theatre :");
            long total_seat = Long.parseLong(scanner.next());//get seats
            System.out.print("Enter the Screen Grid for space :");
            String grid = scanner.next();//get grid, gird purpose is to allocate space between at seats
            var seatsAndGrid = Utilities.generateGrid(total_seat, grid);//get pattern from utilities

            currentTheatreAdding.getScreen_name().put(screenName,
                    new Screen(screenName, total_seat, seatsAndGrid, grid));

            System.out.println("Screen was Added Successfully....");
            screenCount += 1;
        }

        BookMyShow.gettheatrelocation().put(theatreName, currentTheatreAdding);//add in the hash map
    }

    public static void viewalltheatre() {//print all the theatres that are added method
        for (String theatreName : BookMyShow.gettheatrelocation().keySet()) {
            Theatre theatre = BookMyShow.gettheatrelocation().get(theatreName);

            System.out.println("Theatre Name :" + theatre.getTheatrename());//for display purpose
            System.out.println("Theatre Location :" + theatre.getLocation());//for display purpose

            for (var screenEntry : theatre.getScreen_name().entrySet()) {
                System.out.println("Screen Name :" + screenEntry.getKey());//for display purpose
                System.out.println("Number of seats :" + screenEntry.getValue().getAvailableseat());//for display purpose
                System.out.println("seat_arrangment :" + screenEntry.getValue().getSeatarrangement());//for display purpose
            }
        }
    }
}