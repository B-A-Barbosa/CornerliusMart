import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SaveManager {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static Map<String, Cart> userCarts = new HashMap<>();
    //TODO private static final String CARTS_FILE = "carts.json";
    private static ArrayList<Item> itemCatalog = new ArrayList<>();
    private static Map<Item, Integer> inventory = new HashMap<>();

    //TODO save / load inventory and item catalog methods also rethink the naming    

    public static void SaveCart(String userID, Cart cart) {
        userCarts.put(userID, cart);
    }

    public static Cart LoadCart(String userID) {
        return userCarts.get(userID);
    }

    public static void saveCartsToFile() throws IOException{
        //create a new gson object with pretty printing (adds tabing and new lines for readability)
        //creates a new file writer to write to carts.json
        try (FileWriter writer = new FileWriter("carts2.json")) {
            //go through each cart in the hasmap and write it to the file and to console
            ArrayList<Cart> cartsToSave = new ArrayList<>(userCarts.values());
            gson.toJson(cartsToSave, writer);
            //gson.toJson(cart, System.out);
            
        }
    }
    public static void loadCartsFromFile() throws IOException{
        //TODO error when file is empty (error when file doesnt exist???)
        try (FileReader reader = new FileReader("carts.json")) {
            Cart[] loadedCarts = gson.fromJson(reader, Cart[].class);
            //go through each cart and fix the list, then add it to the userCarts hashmap
            for (Cart cart : loadedCarts) {
                //set the item list equal to the fixed item list which is based on the original item list of the cart
                cart.setItemList(fixCartItems(cart.getItemList()));
                userCarts.put(cart.getUserID(), cart);
                System.out.println("Loaded cart for user: " + cart);
            }
        }
    }
    //Problem : the json reader doesnt know which subclass to use when loading the items
    //Solution : add a type field to each item subclass, and then use it to add the item to a new arraylist with its proper item type.
    public static ArrayList<Item> fixCartItems(ArrayList<Item> itemList) {
        ArrayList<Item> fixedList = new ArrayList<>();
        //go through each item in the list given and then add it to the new list with its proper subclass
        for (Item item : itemList) {
            switch (item.type) {
                case "Clothing":
                    fixedList.add(gson.fromJson(gson.toJson(item), Clothing.class));
                    break;
                case "Furniture":
                    fixedList.add(gson.fromJson(gson.toJson(item), Furniture.class));
                    break;
                case "Grocery":
                    fixedList.add(gson.fromJson(gson.toJson(item), Grocery.class));
                    break;
                case "Prepackaged":
                    fixedList.add(gson.fromJson(gson.toJson(item), Prepackaged.class));
                    break;
                default:
                    fixedList.add(item);
                    break;
            }
        }
        return fixedList;
    }        
}
