public class Main {
    public static void main(String[] args){
        Store store = SaveManager.LoadStore();
        if (store == null) {
            store = new Store();
        }
        System.out.println("Welcome to Cornerlius Mart!");
        Cart cart = InputManager.Login();
        cart.SetStore(store);
        SaveManager.SaveCart(cart);
        
    }
    

}



/* Testing adding items to cart and them taking away from store.
        Cart cart = new Cart(store, "ber", "123");
        cart.addToCart(new Clothing("T-Shirt", "Cotton t-shirt", "ClothCo", 19.99f, "Blue", Size.M), 2);
        cart.addToCart(new Grocery("Apple", "Fresh red apple", "FarmFresh", 3.99f, "10/10/10"), 5);
        cart.addToCart(store.getItemCatalog().get(0), 5);
        SaveManager.SaveCart(cart);
        SaveManager.SaveCartsToFile();
        SaveManager.SaveStore(store);
*/
/* Creates new test store objects
        Size enu = Size.M;
        store.addItemToCatalog(new Grocery("Apple", "Fresh red apple", "FarmFresh", 3.99f, "10/10/10"), 50);
        store.addItemToCatalog(new Clothing("T-Shirt", "Cotton t-shirt", "ClothCo", 19.99f, "Blue", enu), 100);
        store.addItemToCatalog(new Prepackaged("Chips", "Potato chips", "SnackCorp", 2.49f, "12/12/12"), 200);
        store.addItemToCatalog(new Furniture("Sofa", "Comfortable sofa", "FurniCo", 499.99f, "Leather", "Black"), 5);
        SaveManager.SaveStore(store);
*/
/* Creating a new cart and adding items to it
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
/* Testing cart save and load
        SaveManager.loadCartsFromFile();
        Cart cart = SaveManager.LoadCart("bernardo123", "123");
        cart.addToCart(chair, 2);
        System.out.println("TSotal price in cart: $" + cart.getTotalPrice());
        chair.putOnSale(0.10f); // 10% off
        System.out.println("Total price in cart: $" + cart.getTotalPrice());
        SaveManager.SaveCart("bernardo123", cart);
        SaveManager.saveCartsToFile();
*/