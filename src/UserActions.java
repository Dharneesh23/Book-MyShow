import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class UserActions {
    public static Scanner scanner = new Scanner(System.in);//scanner for get the input from the user

    public static User login() {//login method for login

        System.out.print("Enter The Username:");
        String userId = scanner.next();//get username
        for (User user: BookMyShow.getUseList()) {//enhanced for loop,to iterate at one by one and stored to reference of admin
            if (user.getUserid().equals(userId)) {//check the condition
                System.out.print("Enter The Password:");
                String userPassword = scanner.next();// get password



                    if (user.getUserid().equals(userId)) {// check the reference id and pass
                        if (user.getPassword().equals(userPassword)) {//check the condition
                            return user;// return the user
                        } else {
                            return new User(null, null, null);//  wrong pass or wrong id return  null value to user
                        }
                    }

                }

            }return null;// no account pass null
        }





    public static void register() {//register method for register a new user


        System.out.print("No user found \n do you Like to Register press 1 or press 2 to exit:");// get into register
        int choice = scanner.nextInt();


        if (choice==1) {// check input
            System.out.print("Enter username:");
            String userid = scanner.next();// get username
            System.out.print("Enter password:");
            String userpassword = scanner.next();// get password
            System.out.print("Enter Your Location:");
            String userlocation = scanner.next();// get locatiom
            BookMyShow.getUseList().add(new User(userid, userpassword, userlocation));//get the details and stored to user
        }

        else if (choice==2) {
            return;// if n return to previous features
        }


    }

    public static void operations(User currentUser) {//operations method


        UserActions.displayAllMovies(currentUser, BookMyShow.getToday());// very bigning of the login show the movies

        while (true) {
            System.out.println("Enter The User Choice...");
            System.out.println(" 1.Change Location\n2.Change Date\n3.view thickets\n4.Display Movies\n5.exit");
            System.out.print("Enter your choice:");
            int choice = scanner.nextInt();


            switch (choice) {

                case 1:// based on the choice call the function (1)
                    UserActions.changeLocation(currentUser);
                    UserActions.displayAllMovies(currentUser, BookMyShow.getToday());// after the change in the location
                    // show the movies
                    break;
                case 2:// based on the choice call the function (2)
                    LocalDate dateUpdate = UserActions.changeDate(currentUser);
                    UserActions.displayAllMovies(currentUser, dateUpdate);// after the change in the date show the  movies

                    break;
                case 3:// based on the choice call the function (3)
                    UserActions.viewtickets(currentUser);
                    break;
                case 4:// based on the choice call the function (4)
                    UserActions.displayAllMovies(currentUser, BookMyShow.getToday());
                    break;
                case 5:// exit the function and go to previous function
                    return;
            }
        }
    }

    public static void displayAllMovies(User currentuser, LocalDate today) {// display movies based on users location and date

        HashSet<String> moviesinthatlocation = new HashSet<>();// to store the movies in that location
        ArrayList<Movies> moviesavailable = new ArrayList<>();// to store the every object of the particulat movie of   the user entered


        if (BookMyShow.getmovielocation().keySet().isEmpty()) {
            System.out.println("\n" + "Movies not available" + "\n");
            return;
        } // to check for no movies in the entire app

        System.out.println("\n"+"Movies are Available....");


        for (var movies : BookMyShow.getmovielocation().keySet()) {
            var moviesarraylist = BookMyShow.getmovielocation().get(movies);
            for (var movieobject : moviesarraylist) {
                // Check and display the movies based on the location and date
                if (movieobject.getLocation().equals(currentuser.getLocation()) && (movieobject.getStartingdate().isEqual(today))) {
                    System.out.println( movies);
                    moviesinthatlocation.add(movies);

                }break;

            }
        }
        if(moviesinthatlocation.isEmpty()){
            System.out.println("No movies in your location or date");
            return;
        }


        moviename:while(true){

            System.out.print("Enter the movie name to book or press (n) to exit the option:");
            String movieChoice = scanner.next();// get the movie name to book


            if (moviesinthatlocation.contains(movieChoice)) {// if movie entered bye the user exists in that location
                for (var movieobject : BookMyShow.getmovielocation().get(movieChoice)) {
                    if (movieobject.getLocation().equals(currentuser.getLocation()) && (movieobject.getStartingdate().isEqual(today))) {
                        moviesavailable.add(movieobject);// add all the movie object in arraylist
                    }

                }

            } else if (movieChoice.equals("n") || movieChoice.equals("N")) {
                System.out.println("Thank you");
                return;// if n return
            }

            else {
                System.out.print("Incorrect theatre name\n Enter again press(1) or press (2) to exit:");
                int choice = scanner.nextInt();
                if (choice==1) {
                    return;
                }
                continue moviename;

            }

            show_movie_details(moviesavailable, movieChoice, currentuser);// pass the movie object,current user, finally movie name

            break;}}

    public static void show_movie_details(ArrayList<Movies> movieavailable, String moviechoice, User currenUser) {

        System.out.println("Movie:" + moviechoice);

        HashMap<String, HashSet<Show>> theatreAgainstShow = TheatreActions.theatreAgainstShow(movieavailable);
        // to reduce the time consuming process to prirnt the theatre name and shows in the theatre alone


        for (String keytheatrename : theatreAgainstShow.keySet()) {
            System.out.println("Theatre:" + keytheatrename);// print all theatre name were the movies are running

            System.out.println("shows are:" + theatreAgainstShow.get(keytheatrename).toString());// print show in the theatre

        }


        String theatrename;
        theatre:while(true){
            System.out.print("Enter the Theatre name:");
            theatrename = scanner.next();// get the theatre name
            for (String keytheatrename : theatreAgainstShow.keySet()) {
                if (theatrename.equals(keytheatrename)) {
                    break theatre;//check the user entered theatre name is valid or not

                }}
            System.out.print("Incorrect theatre name \n continue or press (1) to exit\n:");
            int choice = scanner.nextInt();
            if (choice==1) {
                return;
            }
            continue theatre;

        }


        Show currentshow = null;// to get the show object of the user entered show
        LocalTime showtime;


        show: while(true){
            System.out.print("Enter the Starting Time of the show (HH:MM):");
            showtime = LocalTime.parse(scanner.next(),
                    BookMyShow.getTimeformatter());// get the show start time
            var showtocheck = theatreAgainstShow.get(theatrename);// get the show object hashset

            for (var shows : showtocheck) {//to get the particular show
                if (shows.getStarttime().equals(showtime)) {// check the user entered show time is valid or not
                    currentshow = shows;// assagin the current show
                    break show;

                }}

            System.out.print("Incorrect show time Enter again (y) or press (N) to exit:");//if not ask another try
            String choice = scanner.nextLine();
            if (choice.toLowerCase().equals("n")) {
                return;
            }
            continue show;





        }

        if (currentshow == null) {
            System.out.println("Enter the correct details:");
            return;
        } // if the show time is wrong

        bookTickets(currentshow,currenUser,theatrename,moviechoice,showtime);
    }



    public static void bookTickets(Show currentshow,User currenUser,String theatrename,String moviechoice,LocalTime showtime){// to book the ticket

        System.out.println("Screen Name" + currentshow.getScreen().getName());// print the screen name of   the show

        System.out.println(" Available Seats Count in Screen" + currentshow.getScreen().getAvailableseat());// show  available in the show
        System.out.println("Price of the Ticket" + currentshow.getPrice() );// price for the ticket
        for (var seats : currentshow.getSeat_arrangement().entrySet()) {
            System.out.println(seats.getKey() + " " + seats.getValue());
        } // print the seat to book

        System.out.print("Enter how many numbers of seats to book:");
        int seatcount = scanner.nextInt();// get the number of seats to book

        int finalseatcount = seatcount;// for printing price at last

        ArrayList<String> userseats = new ArrayList<>();// for storing which seats does the user books

        HashMap<Character, ArrayList<String>> duplicate = new HashMap<>();// for avoiding the change if the user gives no  while paying

        for (var duplicateclone : currentshow.getSeat_arrangement().entrySet()) {
            duplicate.put(duplicateclone.getKey(), new ArrayList<String>());// store the all the elements from the old  object

            duplicate.get(duplicateclone.getKey()).addAll(duplicateclone.getValue());
        }


        if (currentshow.getScreen().getAvailableseat() < seatcount) {
            System.out.println("Seats in the screen are not available");
            System.out.println("The available seats are" + currentshow.getScreen().getAvailableseat());
            return;
        } // check entered seats are availabe in the show

        int printingPurpose = 1;
        while (seatcount > 0) {// continue untill the seatcount is 0


            String grid = currentshow.getScreen().getGrid();// get the grid
            var starremoved = grid.split("\\*");// seperate the grid using regex and stored in array list
            long sum = 0;// sum initialized to 0

            for (String a : starremoved) {// to calculate sum
                long temp = Long.parseLong(a);
                sum += temp;
            }
             if( currentshow.getScreen().getAvailableseat()>seatcount)
             {
            System.out.print("Enter the row for " + printingPurpose + "seat to book :");// get the ticket row and seat number

            String choiceseat = scanner.next();
            char row = choiceseat.charAt(0);// get the row from the string

            String bookseat = null;// to check the seat  is already booked or not

            int seatchoice = Integer.parseInt(choiceseat.substring(1));// seat number
            if(seatchoice>sum){
                System.out.println("enter the  correct seat number");
                continue ;
            }

            if (seatchoice <= Integer.parseInt(starremoved[0])) {// if seatnumber is less or equal to first grid
                bookseat = duplicate.get(row).get(seatchoice - 1);// get the -1 (because no seat)
                if (bookseat.equals("X")) {// check the bookseat is X or not
                    System.out.println(duplicate.get(row));// if x
                    System.out.println("Seat already booked ........");// say the user to book another seat
                    continue;
                }
                duplicate.get(row).set(seatchoice - 1, "X");// set the -1 (because no seat)
                userseats.add(choiceseat);// add the user seats
                System.out.println(duplicate.get(row));// to print the selected seat


            }

            else if (seatchoice >= ((sum + 1) - Integer.parseInt(starremoved[2]))) {// If seatNumber is greater than or equal to sum + 1 and minus the last grid size to calculate the seat after the second seat
                bookseat = duplicate.get(row).get(seatchoice + 1);
                if (bookseat.equals("X")) {// check the bookseat is X or not
                    System.out.println(duplicate.get(row));// if x
                    System.out.println("Seat already booked please try to book any other seats.......");// say the user to book another seat
                    continue;
                }
                duplicate.get(row).set(seatchoice + 1, "X");
                userseats.add(choiceseat);// add the user seats
                System.out.println(duplicate.get(row));// to print the selected seat
            } else if (seatchoice > Integer.parseInt(starremoved[0])) {// for second grid make the seatnumber as it is
                bookseat = duplicate.get(row).get(seatchoice);
                if (bookseat.equals("X")) {// check the bookseat is X or not
                    System.out.println(duplicate.get(row));// if x
                    System.out.println("Seat already booked please try to book any other seats");// say the user to book another seat
                    continue;
                }
                duplicate.get(row).set(seatchoice, "X");
                userseats.add(choiceseat);// add the user seats
                System.out.println(duplicate.get(row));// to print the selected seat

            }

            printingPurpose++;// increment for printing
            seatcount--;// to exit while or condition
        }
             }

        long totalamount = (long) (finalseatcount * currentshow.getPrice());

        System.out.println("The total amount of the  ticket is:" + totalamount);// show the total ticket price

        System.out.print(" Confirming Ticket Booking press (1)or press (2)to exit:");

        int userchoice = scanner.nextInt();// ask for conformation


        if (userchoice==1) {// if yes
            currentshow.getScreen().setAvailableseats(currentshow.getScreen().getAvailableseat() - seatcount);// Reduce the seat count

            currenUser.getTickets().add(new Ticket(theatrename, currentshow.getShowDate(),
                    currentshow.getScreen().getName(), showtime, finalseatcount, totalamount, userseats, moviechoice));// create the ticket object and pass essential fields

            currentshow.setSeat_arrangement(duplicate);// mark the original seats to be the clone

        } else if (userchoice==2) {
            System.out.println("Thank you");
            return;// else return to the function
        }

    }

    public static void changeLocation(User currentuserob) {// to change location

        System.out.print("Enter The Location To Change:");
        String changelocation = scanner.next();// get the input to change location
        currentuserob.setLocation(changelocation);// set to user
        System.out.println("Location change successfully!!");
        return;

    }

    public static LocalDate changeDate(User currentuserob) {// tp change date

        LocalDate changedate;
        while (true) {
            System.out.println("Enter the date for change :");
             changedate = LocalDate.parse(scanner.next(), BookMyShow.getFormatter());
            if (changedate.isBefore(LocalDate.now())) {
                System.out.println("reenter the current date!!!");
                continue;
            }break;
        }
        System.out.println("Date change successfully!!!");
        return changedate;// return the date

    }

    public static void viewtickets(User currentuser) {// to dispaly the ticket
        for (var ticket : currentuser.getTickets()) {//enhanced for loop to iterate and store the current user tickets to store in ticket


            System.out.println("Movie name:" + ticket.getMovieName());//display purpose
            System.out.println("Theare name:" + ticket.getTheatreName());//display purpose
            System.out.println(" Date:" + ticket.getDate());//display purpose
            System.out.println(" Screen name:" + ticket.getScreen());//display purpose
            System.out.println("Timing:" + ticket.getTime());//display purpose
            System.out.println("Number of seats:" + ticket.getNumberOfSeats());//display purpose
            System.out.println("Amount Paid for Ticket:" + ticket.getAmountPaid());//display purpose
            System.out.println("seats are:" + ticket.getSeats());//display purpose


        }
    }

}

