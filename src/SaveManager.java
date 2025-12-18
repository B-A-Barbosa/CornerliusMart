import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import util.RuntimeTypeAdapterFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SaveManager {
    //the base gson library cannot differentiate between subclasses when converting to/from json
    //this factory (added in RuntimeTypeAdapterFactory.java) tells gson how to handle the different subclasses of Item by adding a "type" field to the json
    private static final RuntimeTypeAdapterFactory<Item> itemAdapterFactory = RuntimeTypeAdapterFactory
            .of(Item.class, "type")
            .registerSubtype(Clothing.class, "Clothing")
            .registerSubtype(Furniture.class, "Furniture")
            .registerSubtype(Grocery.class, "Grocery")
            .registerSubtype(Prepackaged.class, "Prepackaged")
            .registerSubtype(Other.class, "Other");

    //use the gson builder to create a gson object with item adapter factory (explained above)
    //and pretty printing (adds tabbing and spacing so that the file is human-readable)
    private static Gson gson = new GsonBuilder().registerTypeAdapterFactory(itemAdapterFactory).setPrettyPrinting().create();
    private static Map<String, Cart> userCarts = new HashMap<>();
    private static Map<String, String> passwordMap = new HashMap<>();
    private static final String CARTS_FILE = "SaveFiles/carts.json";
    private static final String STORE_FILE = "SaveFiles/store.json";

    //TODO throws ioexception annoying
    public static void SaveStore(Store store){
        try (FileWriter writer = new FileWriter(STORE_FILE)) {
            gson.toJson(store, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Store LoadStore() {
        System.out.println("Looking for file at: " + new File(STORE_FILE).getAbsolutePath());

        try (FileReader reader = new FileReader(STORE_FILE)) {
            Store store = gson.fromJson(reader, Store.class);
            return store;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void SaveCart(Cart cart) {
        userCarts.put(cart.getUserID(), cart);
    }
    public static Cart LoadCart(String userID, String password) {
        if (password.equals(passwordMap.get(userID))){
            System.out.println("Login successful!");
            return userCarts.get(userID);
        } else {
            System.out.println("Incorrect password.");
            return null;
        }
    }

    public static void SaveCartsToFile(){
        //create a new gson object with pretty printing (adds tabing and new lines for readability)
        //creates a new file writer to write to carts.json
        try (FileWriter writer = new FileWriter(CARTS_FILE)) {
            //go through each cart in the hasmap and write it to the file and to console
            ArrayList<Cart> cartsToSave = new ArrayList<>(userCarts.values());
            gson.toJson(cartsToSave, writer); 
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void LoadCartsFromFile() {
        //TODO error when file is empty (error when file doesnt exist???)
        try (FileReader reader = new FileReader(CARTS_FILE)) {
            Cart[] loadedCarts = gson.fromJson(reader, Cart[].class);
            for (Cart cart : loadedCarts) {
                passwordMap.put(cart.getUserID(), cart.getPassword());
                userCarts.put(cart.getUserID(), cart);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
