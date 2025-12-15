import java.io.IOException;
import java.util.Scanner;

public class Main {
    //TODO is throws ioexception needed here? (what other way to do it?)
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        Store store = SaveManager.LoadStore();
        SaveManager.SaveStore(store);

/* 
        Size enu = Size.M;
        store.addItemToCatalog(new Grocery("Apple", "Fresh red apple", "FarmFresh", 3.99f, 1.5f, "10/10/10"), 50);
        store.addItemToCatalog(new Clothing("T-Shirt", "Cotton t-shirt", "ClothCo", 19.99f, 100, "Blue", enu), 100);
        store.addItemToCatalog(new Prepackaged("Chips", "Potato chips", "SnackCorp", 2.49f, 200, "12/12/12"), 200);
        store.addItemToCatalog(new Furniture("Sofa", "Comfortable sofa", "FurniCo", 499.99f, 5, "Leather", "Black"), 5);
        SaveManager.SaveStore(store);
*/



/* 
        SaveManager.loadCartsFromFile();
        System.out.println("Welcome to Cornerlius Mart!");
        System.out.println("user: ");
        String user = scan.nextLine();
        System.out.println("password: ");
        String password = scan.nextLine();
        //Cart userCart = new Cart(user, password);
        Cart userCart = SaveManager.LoadCart(user, password);
        Furniture chair = new Furniture("Chair", "A comfy chair", "FurniCo", 49.99f, 10, "Wood", "Brown");
        userCart.addToCart(chair, 2);
        System.out.println("Total price in cart: $" + userCart.getTotalPrice());

        SaveManager.SaveCart(user, userCart);
        SaveManager.saveCartsToFile();
*/
        /* 
        SaveManager.loadCartsFromFile();
        Cart cart = SaveManager.LoadCart("bernardo123", "123");
        cart.addToCart(chair, 2);
        System.out.println("Total price in cart: $" + cart.getTotalPrice());
        chair.putOnSale(0.10f); // 10% off
        System.out.println("Total price in cart: $" + cart.getTotalPrice());
        SaveManager.SaveCart("bernardo123", cart);
        SaveManager.saveCartsToFile();
        */
    }
}