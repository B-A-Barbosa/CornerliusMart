package src;

import java.io.IOException;

public class Main {
    //TODO is throws ioexception needed here? (what other way to do it?)
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Cornerlius Mart!");
        CartManager.loadCartsFromFile();
        Furniture chair = new Furniture("Chair", "A comfy chair", "FurniCo", 49.99f, 10, "Wood", "Brown");
        Cart cart = CartManager.LoadCart("bernardo123");
        cart.addToCart(chair, 2);
        System.out.println("Total price in cart: $" + cart.getTotalPrice());
        chair.putOnSale(0.10f); // 10% off
        System.out.println("Total price in cart: $" + cart.getTotalPrice());
        CartManager.SaveCart("bernardo123", cart);
        CartManager.saveCartsToFile();
    }
}