
import java.util.Scanner;

public class BookMyShowaction {
    public static Scanner scanner = new Scanner(System.in);//scanner method for get the input
    public static void start() {//start method

        BookMyShow.getAdminlist().add(new Admin("1", "1"));//set the value to constructor

        System.out.println("*****WELCOMME*****");


        while (true) {
            System.out.println("\n1.Admin\n2.User\n3.Exit");
            System.out.print("Enter your choice : ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1://for admin login
                {
                    Admin login = AdminAction.admin_login();
                    if (login != null) {//return login will null call the operation
                        AdminAction.operations(login);
                        break;
                    } else {
                        System.out.println("invalid admin");//admin not found
                        break;
                    }
                }
                case 2://user login
                {
                    User user = UserActions.login();
                    if (user == null) {//no account
                        UserActions.register();

                    } else if (user.getUserid() == null) {//password attempts
                        System.out.println("Wrong password:");
                    } else {
                        UserActions.operations(user);//success of login
                    }       break;
                }
                case 3:
                    return;//exit
                default:
                    System.out.println("INVALID CHOICE....");//invalid choice
                    break;
            }
        }
    }
}