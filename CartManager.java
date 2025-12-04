import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartManager {
    private static Map<String, Cart> userCarts = new HashMap<>();

    public static void SaveCart(String userID, Cart cart) {
        userCarts.put(userID, cart);
    }

    public static Cart LoadCart(String userID) {
        return userCarts.get(userID);
    }

    // Save all carts manually as JSON-like text
    public static void saveAllToJson(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("{\n  \"carts\": [\n");
            int i = 0;
            for (Map.Entry<String, Cart> entry : userCarts.entrySet()) {
                Cart cart = entry.getValue();
                writer.write("    {\n");
                writer.write("      \"userId\": \"" + entry.getKey() + "\",\n");
                writer.write("      \"totalPrice\": " + cart.getTotalPrice() + "\n");
                writer.write("    }");
                if (i < userCarts.size() - 1) writer.write(",");
                writer.write("\n");
                i++;
            }
            writer.write("  ]\n}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load carts back (very simple example)
    public static void loadAllFromJson(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // You would parse the JSON-like text here manually
                // For a beginner project, you can just read userId and totalPrice lines
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
