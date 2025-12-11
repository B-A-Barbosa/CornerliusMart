import java.io.IOException;

public class Main {
    //TODO is throws ioexception needed here? (what other way to do it?)
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Cornerlius Mart!");
        SaveManager.loadCartsFromFile();
        Furniture chair = new Furniture("Chair", "A comfy chair", "FurniCo", 49.99f, 10, "Wood", "Brown");
        Cart cart = SaveManager.LoadCart("bernardo123");
        cart.addToCart(chair, 2);
        System.out.println("Total price in cart: $" + cart.getTotalPrice());
        chair.putOnSale(0.10f); // 10% off
        System.out.println("Total price in cart: $" + cart.getTotalPrice());
        SaveManager.SaveCart("bernardo123", cart);
        SaveManager.saveCartsToFile();
    }
}