import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Store {
    //TODO private
    public ArrayList<Item> ItemCatalog = new ArrayList<>();
    public Map<String, Integer> inventory = new HashMap<>();

    //TODO connect cart and store

    public void addItemToCatalog(Item item, int stock) {
        if (stock <0) return;
        if (!ItemCatalog.contains(item)){
            ItemCatalog.add(item);
        }
        inventory.put(item.generateCode(), inventory.getOrDefault(item, 0) + stock);
    }
    public void removeItemFromCatalog(Item item, int stock) {
        if (ItemCatalog.contains(item)) {
            int currentStock = inventory.get(item);
            if (currentStock <= stock) {
                inventory.remove(item.generateCode());
                ItemCatalog.remove(item);
                System.out.println("Removed all of " + item.getName() + " from catalog.");
            } else {
                inventory.put(item.generateCode(), currentStock - stock);
            }
        }
    }
}
