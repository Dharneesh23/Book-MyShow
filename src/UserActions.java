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


        moviename:while (true) {

            System.out.print("1.continue for booking movie \n 2.Exit:");
            int choice = scanner.nextInt();// get the movie name to book

           if(choice==1)
           {
               System.out.println("Enter the movie name : ");
               String movie_name=scanner.next();//get the movie name
            if (moviesinthatlocation.contains(movie_name)) {// if movie entered bye the user exists in that location
                for (var movieobject : BookMyShow.getmovielocation().get(movie_name)) {
                    if (movieobject.getLocation().equals(currentuser.getLocation()) || (movieobject.getStartingdate().isEqual(today))) {
                        moviesavailable.add(movieobject);
                        show_movie_details(moviesavailable, movie_name, currentuser);// add all the movie  in arraylist
                    }

                }
            }
            } else if (choice==2) {
                return;
            } else {
                System.out.print("Incorrect theatre name Enter again press(any number)to continue or press (1) to exit:");
                int choic = scanner.nextInt();
                if (choic==1) {
                    return;
                }
                continue moviename;

            }

        }
    }

    public static void show_movie_details(ArrayList<Movies> movieavailable, String moviechoice, User currenUser) {//show details method
        System.out.println("Movie :" + moviechoice);
        HashMap<String, HashSet<Show>> theatreShow = TheatreActions.theatreAgainstShow(movieavailable);//hash set to add theatre show

        for (String keytheatrename : theatreShow.keySet()) {
            System.out.println("Theatre :" + keytheatrename);
            System.out.println("shows " + theatreShow.get(keytheatrename).toString());
        }


        String theatrename;
        theatre:
        while (true) {
            System.out.print("Enter the Theatre name:");
            theatrename = scanner.next();// get the theatre name
            for (String keytheatrename : theatreShow.keySet()) {
                if (theatrename.equals(keytheatrename)) {
                    break theatre;//check the user entered theatre name is valid or not

                }
            }
            System.out.print("Incorrect theatre name Enter again (y) or press (N) to exit:");
            String choice = scanner.next();
            if (choice.toLowerCase().equals("n")) {
                return;
            }
            continue theatre;

        }


        Show currentshow = null;
        LocalTime showtime;


        show:while (true) {
            System.out.print("Enter the Show Start Time :");
            showtime = LocalTime.parse(scanner.next(),
                    BookMyShow.getTimeformatter());// get the show start time
            var showcheck = theatreShow.get(theatrename);// get the show object hashset

            for (var shows : showcheck) {//to get the required show
                if (shows.getStarttime().equals(showtime)) {// check the user entered show time is valid or not
                    currentshow = shows;// assassin the current show
                    break show;

                }
            }

            System.out.print("Incorrect show time Enter any number to continue  or press (1) to exit:");
            int choice = scanner.nextInt();//get input
            if (choice==1) {
                return;
            }
            continue show;


        }

        if (currentshow == null) {
            System.out.println("Enter the correct details:");
            return;
        }

        bookTickets(currentshow, currenUser, theatrename, moviechoice, showtime);//pass the arugument to booktickets
    }

    public static void bookTickets(Show currentshow, User currenUser, String theatrename, String moviechoice, LocalTime showtime) {// to book the ticket

        System.out.println("Screen Name" + currentshow.getScreen().getName());// print the screen of the show

        System.out.println("Screen Available in theatre seats count" + currentshow.getScreen().getAvailableseat());// show  available in the show
        System.out.println("Price" + currentshow.getPrice());// price for the ticket
        for (var seats : currentshow.getSeat_arrangement().entrySet()) {
            System.out.println(seats.getKey()+" "+seats.getValue());
        }

        System.out.print("Enter the  seat number for booking:");
        Long seatcount = Long.parseLong(scanner.next());// get the number of seats to book
        long finalseatcount = seatcount;// printing price at last

        ArrayList<String> userseats = new ArrayList<>();// for storing which seats does the user books

        HashMap<Character, ArrayList<String>> duplicate = new HashMap<>();

        for (var getcopy : currentshow.getSeat_arrangement().entrySet()) {
            duplicate.put(getcopy.getKey(), new ArrayList<String>());

            duplicate.get(getcopy.getKey()).addAll(getcopy.getValue());
        }
        if (currentshow.getScreen().getAvailableseat() < seatcount) {
            System.out.println("Seats are not available");
            System.out.println("The available seats are " + currentshow.getScreen().getAvailableseat());
            return;
        }

        int setprint= 1;
        while (seatcount > 0) {// continue untill the seatcount is 0


            String grid = currentshow.getScreen().getGrid();// get the grid
            var removedstar = grid.split("\\*");// seperate the grid using regex and stored in array list
            long sum = 0;// sum initialized to 0

            for (String a : removedstar) {// to calculate sum
                long temp = Long.parseLong(a);
                sum += temp;
            }

            System.out.print("Enter the row for " + setprint + " st seat to book :");// get the ticket row and seat number

            String choiceseat = scanner.next();
            char row = choiceseat.charAt(0);// get the row from the string

            String bookseat = null;// to check the seat  is already booked or not

            int seatchoice = Integer.parseInt(choiceseat.substring(1));// seat number
            if (seatchoice > sum) {
                System.out.println("enter correct seat number");
                continue;
            }

            if (seatchoice <= Integer.parseInt(removedstar[0])) {
                bookseat = duplicate.get(row).get(seatchoice - 1);
                if (bookseat.equals("X")) {// check the bookseat is X or not
                    System.out.println(duplicate.get(row));
                    System.out.println("Seat already booked Try any other seats");// say the user to book another seat
                    continue;
                }
                duplicate.get(row).set(seatchoice - 1, "X");
                userseats.add(choiceseat);// add the user seats
                System.out.println(duplicate.get(row));// to print the selected seat


            } else if (seatchoice >= ((sum + 1) - Integer.parseInt(removedstar[2]))) {// If seatNumber is greater than or equal to sum + 1 and minus the last grid size to calculate the seat after the second seat
                bookseat = duplicate.get(row).get(seatchoice + 1);
                if (bookseat.equals("X")) {// check the bookseat is X or not
                    System.out.println(duplicate.get(row));
                    System.out.println("Seat already booked Try any other seats");
                    continue;
                }
                duplicate.get(row).set(seatchoice + 1, "X");
                userseats.add(choiceseat);// add the user seats
                System.out.println(duplicate.get(row));// display selected seat
            } else if (seatchoice > Integer.parseInt(removedstar[0])) {// for second grid make the seatnumber as it is
                bookseat = duplicate.get(row).get(seatchoice);
                if (bookseat.equals("X")) {// check the bookseat is X or not
                    System.out.println(duplicate.get(row));
                    System.out.println("Seat already booked Try any other seats");
                    continue;
                }
                duplicate.get(row).set(seatchoice, "X");
                userseats.add(choiceseat);// add the user seats
                System.out.println(duplicate.get(row));// display the selected seat

            }

            setprint++;// increment for printing
            seatcount--;// to exit while or condition
        }
        double totalamount = finalseatcount * currentshow.getPrice();

        System.out.println("The total amount for the ticket  :" + totalamount);// show the total ticket price

        System.out.print("Enter 1 to Confirm The Ticket Booking or 2 to exit");

        int userchoice = scanner.nextInt();
        if (userchoice==1) {// if yes
            currentshow.getScreen().setAvailableseats(currentshow.getScreen().getAvailableseat() - seatcount);// Reduce the seat count

            currenUser.getTickets().add(new Ticket(theatrename, currentshow.getShowDate(),
                    currentshow.getScreen().getName(), showtime, finalseatcount, (long) totalamount, userseats, moviechoice));// create the ticket object and pass essential fields

            currentshow.setSeat_arrangement(duplicate);
            System.out.println("Successfully ticket was booked !!");
            // mark the original seats to be the clone

        } else if (userchoice==2) {
            return;
        }
    }
    public static void changeLocation(User currentuserob) {// to change location method

        System.out.print("Enter  Location To Change:");
        String locchange = scanner.next();// get the input to change location
        currentuserob.setLocation(locchange);// set to user
        return;
    }

    public static LocalDate changeDate(User currentuserob) {// to change date method

        System.out.print("Enter The date To Change:");
        LocalDate datechange = LocalDate.parse(scanner.next(),BookMyShow.getFormatter());

        return datechange;// return the date which is choosen by the user

    }

    public static void viewtickets(User currentuser) {// to dispaly the ticket method
        for (var ticket : currentuser.getTickets()) {
            // list
            System.out.println("Movie_Name :" + ticket.getMovieName());//for display purpose
            System.out.println("Theatre_Name :" + ticket.getTheatreName());//for display purpose
            System.out.println("Movie_Date :" + ticket.getDate());//for display purpose
            System.out.println("Screen name :" + ticket.getScreen());//for display purpose
            System.out.println("Movie_Timing :" + ticket.getTime());//for display purpose
            System.out.println("Number of seats :" + ticket.getNumberOfSeats());//for display purpose
            System.out.println("Amount_paid_for_ticket :" + ticket.getAmountPaid());//for display purpose
            System.out.println("seats :" + ticket.getSeats());//for display purpose
        }
    }
}

