import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminAction {//Admin action class for admin operation
    private static  Scanner scanner = new Scanner(System.in);//Scanner method why static ?using static for to call the scanner method we do not create any object with the help of static

    public static Admin admin_login() {//Admin login method for admin login

        System.out.print("Enter The Username:");
        String adminId = scanner.next();//get username
        for (Admin admin: BookMyShow.getAdminlist()) {//enhanced for loop,to iterate at one by one and stored to reference of admin
            if (admin.getUserid().equals(adminId)) {//check the condition
                System.out.print("Enter The Password:");
                String adminPassword = scanner.next();// get password
                 // check the reference id and pass
                if (admin.getPassword().equals(adminPassword)) {//check the condition
                    return admin;// return the user
                } else {
                    return new Admin(null, null);//  wrong pass or wrong id return  null value to user
                }


            }

        }return null;//no account
        //pass null
    }

    public static void operations() { //operation class for admin operation
        while (true) {
            System.out.println("Enter the Admin Choice");
            System.out.println("1.Add Movie\n2.view all Movies\n3.Add Theatre\n4.view all theatre\n5.Exit\n");//admin options
            System.out.print("Enter your choice :");

            int choice = scanner.nextInt();//get the admin choice

            switch (choice) {
                case 1:
                    AdminAction.addMovie_to_theatre(scanner);// based on admin  choice call the function (1)
                    break;
                case 2:
                    AdminAction.viewallmovies();// based on admin  choice call the function (2)
                    break;
                case 3:
                    AdminAction.addTheatre();// based on admin  choice call the function (3)
                    break;
                case 4:
                    AdminAction.viewalltheatre();// based on admin  choice call the function (4)
                    break;
                case 5:
                    return;// exit the loop.... based on admin  choice call the function (5)
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
        LocalDate Starting_Date;//variable declaration
        while (true) {
            System.out.print("Enter the Date:");
             Starting_Date = LocalDate.parse(scanner.next(), BookMyShow.getFormatter());//variable initialization for starting data
            if (Starting_Date.isBefore(LocalDate.now())) {//check the condition for now date to before date
                continue;
            }break;
        }
        boolean Check_The_Theatre_in_This_location = false;// number of theatre in that locations

        ArrayList<Theatre> theatreInThatLocation=new ArrayList<>();//array list for theatre location
        for (String theatreName : BookMyShow.gettheatrelocation().keySet()) {//enhanced for loop for get the theatre location keyset to store in a theatrename variable
            Theatre theatreInLocation = BookMyShow.gettheatrelocation().get(theatreName);// get theatrename location to store in a theatreInlocation variable

            if (theatreInLocation.getLocation().equals(movieLocation)) {//check the theatres in that location is equal at moviename
                System.out.println("you entered location Theatre  are....."+ theatreInLocation.getTheatrename());//display purpose

                theatreInThatLocation.add(theatreInLocation);//add a theatrelocation in arraylist
                Check_The_Theatre_in_This_location = true;//number of theater in that locations
            }
        }

        if (!Check_The_Theatre_in_This_location) {// if no theatre in that location this statement is call
            System.out.println("No theatre in your location...........");
            return;//exit
        }

        Theatre currentTheatre = null;// to get the current looping theatre

        theatre: while (true) {//this condition will end until the condition will false
            System.out.print("Enter The Theatre Name:");
            String Theatre_Name = scanner.next();//get theatre name

            for (var theatreObjects :theatreInThatLocation) {// check the input theatre name is correct or already exists in that location
                if (theatreObjects.getTheatrename().equals(Theatre_Name)) {//check the condition at theatrelocation and theatre name
                    currentTheatre=theatreObjects;//get theatreobject variable to store a currenttheatre variable
                    break theatre;//break the theatre while loop labeled
                }
            }
            System.out.print("Incorrect theatre name******\ncontinue enter any number or press (1) to exit:");
            int choice = scanner.nextInt();//get the user choice for continue or not
            if (choice==1) {//based on admin choice call the function (1) to exit otherwise continue the loop
                return;
            }
            continue theatre;//continue the loop

        }
        Screen currentScreen = null;// store current screen to store in Screen class
        screen: while (true) {//this condition will end until the condition will false
            for (String screenName : currentTheatre.getScreen_name().keySet()) {//enhanced for loop to get the ketset value and store in screenName variable
                System.out.println("Screen in theatre are \n"+screenName);//display purpose
            }

            System.out.print("Enter your Screen Name:");
            String Screen_Name = scanner.next();//get the screen name

            for (String screen : currentTheatre.getScreen_name().keySet()) {//enhanced for loop to get the current theatre scrren name  ketset value and store in screen variable
                if (screen.equals(Screen_Name)) {// check the entered screen is in theatre
                    currentScreen = currentTheatre.getScreen_name().get(screen);//current theatre screen name to store in current screen variable
                    break screen ;//break the loop...screen labeled loop
                }
            }

            System.out.print("Incorrect Screen name****\n continue press any number or press (1) to exit:");//to reenter the choice
            int choice = scanner.nextInt();//get the admin choice
            if (choice==1) {// based on admin  choice call the function (1) to exit otherwise continue
                return;//exit the loop
            }
            continue screen;//continue the loop

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
                if (!BookMyShow.getmovielocation().containsKey(movieName)) {//check the condition if the movies is contain in requried location
                    BookMyShow.getmovielocation().put(movieName, new ArrayList<Movies>());//to put the value to movielocation
                }//if new movie name add the arraylist of movie object
                BookMyShow.getmovielocation().get(movieName).add(new Movies(movieName, movieLocation, Starting_Date,
                        Movie_Duration, currentTheatre, currentScreen, currentShow));//add to the movie arraylist
                System.out.println("<<<<Movie added successfully>>>>");
                return;//exit the loop
            }

            for (Show show : currentScreen.getShows()) {// if show is already there,getshow and stored in local variable show


                //if there is a show already in that particular date check the timing
                if (show.getShowDate().isEqual(Starting_Date)) {
                    if ((Show_Start_Time.isBefore(show.getStarttime()) || Show_Start_Time.isAfter(show.getEndtime()))
                            && (endTime.isBefore(show.getStarttime()) || endTime.isAfter(show.getEndtime()))) {//check the timing of the show already exists or not

                        currentShow = new Show(Show_Start_Time, Show_Start_Time.plusMinutes(Movie_Duration + 30),
                                Starting_Date, currentScreen, price, currentScreen.getSeatarrangement());// Store in the show object
                        if (currentScreen.getShows().contains(currentShow)) {//check for same show already exists or not
                            System.out.print("show already exists....\n Enter again number to continue or press (1) to exit:");//to reenter
                            int choice = scanner.nextInt();//get the user choice...
                            if (choice==1) {//check the condition
                                return;//exit the loop
                            }
                            continue show;//continue the while loop with the labeled show
                        }
                    } else {
                        System.out.print("Show already exists..\n Enter any number to continue  or press (1) to exit:");//to reenter the choice
                        int choice = scanner.nextInt();//get the user choice
                        if (choice==1) {//check the condition
                            return;//exit the loop
                        }
                        continue show;//continue the while loop
                    }
                }
                if (!BookMyShow.getmovielocation().containsKey(movieName)) {//if new movie name add the arraylist of movie object
                    BookMyShow.getmovielocation().put(movieName, new ArrayList<Movies>());//put the value to movielocation
                }
                currentScreen.getShows().add(currentShow);//add to the arraylist

                BookMyShow.getmovielocation().get(movieName).add(new Movies(movieName, movieLocation,Starting_Date,
                                Movie_Duration, currentTheatre, currentScreen, currentShow));//add to movielocation with get the moviename and add to it
                System.out.println("<<<<<Movie added successfully>>>>");
                return;//exit the loop


            }
            break;//break the statement
        }
    }

    public static void viewallmovies() {//to print all movies that are added
        if (BookMyShow.getmovielocation().isEmpty()) {//check whether the movie location is empty or not
            System.out.println("No movies sorry");
            return;//exit the loop
        }

        for (String movieName : BookMyShow.getmovielocation().keySet()) {//get the movie location key value to store in local variable moviename
            ArrayList<Movies> movies = BookMyShow.getmovielocation().get(movieName);//store the value in movie array list
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
            Theatre currentTheatre = BookMyShow.gettheatrelocation().get(existingTheatreName);//get theatre location and name,store to Theatre class local variable current theatre

            if (existingTheatreName.equals(theatreName) && currentTheatre.getLocation().equals(theatreLocation)) {//check both the condition theatre name and theatre location
                System.out.println("Theatre Already exists....");
                return;//exit the loop
            }
        }

        Theatre currentTheatreAdding = new Theatre(theatreLocation, theatreName);

        System.out.print("Enter total Number Of Screens in theatre :");
        long total_screen = Long.parseLong(scanner.next());//get the number of screens
        int screenCount = 1;//variable initialization

        screen: while (screenCount <= total_screen) {//check the condition for entering a theatre name
            System.out.print("Enter theatre Screen Name:");
            String screenName = scanner.next();//get the screen name from user

            for (String existingScreenName : currentTheatreAdding.getScreen_name().keySet()) {//get the current theatre screen name to store in local variable existingScreen name
                if (existingScreenName.equals(screenName)) {//check the screen name already exists
                    System.out.println("Screen name is already exists.....");
                    continue screen;//continue the loop
                }
            }

            System.out.print("Enter the total Number of Seats in theatre :");
            long total_seat = Long.parseLong(scanner.next());//get seats
            System.out.print("Enter the Screen Grid for space :");
            String grid = scanner.next();//get grid, gird purpose is to allocate space between at seats
            var seatsAndGrid = Utilities.generateGrid(total_seat, grid);//get pattern from utilities

            currentTheatreAdding.getScreen_name().put(screenName, new Screen(screenName, total_seat, seatsAndGrid, grid));//get the current theatre screen name and put the required value

            System.out.println("Screen was Added Successfully....");
            screenCount += 1;//screen count value will be increment
        }

        BookMyShow.gettheatrelocation().put(theatreName, currentTheatreAdding);//add in the hash map with the help of put
    }

    public static void viewalltheatre() {//print all the theatres that are added method
        for (String theatreName : BookMyShow.gettheatrelocation().keySet()) {//get the theatre location key value to store in local variable theatre name
            Theatre theatre = BookMyShow.gettheatrelocation().get(theatreName);//get theatre location and theatre name to store in theatre class local variable

            System.out.println("Theatre Name :" + theatre.getTheatrename());//for display purpose
            System.out.println("Theatre Location :" + theatre.getLocation());//for display purpose

            for (var screenEntry : theatre.getScreen_name().entrySet()) {//get theatre screen name to default entrySet()...entered screen name will get and store a local variable screen entry
                System.out.println("Screen Name :" + screenEntry.getKey());//for display purpose
                System.out.println("Number of seats :" + screenEntry.getValue().getAvailableseat());//for display purpose
                System.out.println("seat_arrangment :" + screenEntry.getValue().getSeatarrangement());//for display purpose
            }
        }
    }
}
