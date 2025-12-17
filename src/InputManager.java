import java.util.Scanner;

public class InputManager {
        static Scanner scan = new Scanner(System.in);

    public static String ChooseOption(String[] options) {
        System.out.println("Please choose an option: ");
        //Print numbered options
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        String choice = scan.nextLine();
        //loop until valid choice, 
        while (true) {
            //check if the user entered a valid number first.
            try {
                int numChoice = Integer.parseInt(choice);
                //check if number is in range
                if (numChoice > 0 && numChoice <= options.length) {
                    return options[numChoice - 1]; // return the option string
                }
            } catch (NumberFormatException e) {
                //if its not a number (converting threw an error), check if its a valid string option
                //go though options to see if it matches any
                for (String option : options) {
                    //comparing the lowercase option to the trimmed lowercase user choice
                    if (option.toLowerCase().equals(choice.trim().toLowerCase())) {
                        return option; // return their choice
                    }
                }
            }
            //if the user input was invalid, prompt again
            System.out.println("Invalid choice. Please choose again: ");
            choice = scan.nextLine();
        }
    }

    public static Cart Login() {
        String choice = ChooseOption(new String[]{"Login", "Register"}).toLowerCase();
        if (choice.equals("login")) {
            System.out.println("You chose Login");
            while (true) {
                System.out.println("Please enter your username: ");
                String username = scan.nextLine();
                System.out.println("Please enter your password: ");
                String password = scan.nextLine();
                Cart loadedCart = SaveManager.LoadCart(username, password);
                if (loadedCart != null) {
                    return loadedCart;
                } else {
                    System.out.println("Login failed. Would you like to try again? (y/n): ");
                    String retry = scan.nextLine().toLowerCase();
                    if (!(retry.equals("y") || retry.equals("yes"))) {
                        choice = "register";
                        break;
                    }
                }
            }
        }
        if (choice.equals("register")) {
            System.out.println("You chose Register");
            while (true) {
                System.out.println("Please enter your desired username: ");
                String username = scan.nextLine();
                System.out.println("Please enter your desired password: ");
                String password = scan.nextLine();
                System.out.println("Is this information correct? (y/n): Username: " + username + ", Password: " + password);
                String confirm = scan.nextLine().toLowerCase();
                if (confirm.equals("y") || confirm.equals("yes")) {
                    return new Cart(username, password);
                }
            }
        }
        return null;
    }
}
