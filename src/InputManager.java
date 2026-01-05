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
                        return option; // return their choice in lowercase
                    }
                }
            }
            //if the user input was invalid, prompt again
            System.out.println("Invalid choice. Please choose again: ");
            choice = scan.nextLine();
        }
    }
    //input sanitization for integer inputs
    public static int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = InputManager.scan.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }
    //input sanitization for float inputs
    public static float getFloat(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = InputManager.scan.nextLine().trim();
            try {
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Try again.");
            }
        }
    }
}
