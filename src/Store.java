package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Store {
    private static ArrayList<Item> ItemCatalog = new ArrayList<>();
    private static Map<Item, Integer> inventory = new HashMap<>();
    //TODO connect cart and store

    public static void addItemToCatalog(Item item, int stock) {
        if (stock <0) return;
        if (!ItemCatalog.contains(item)){
            ItemCatalog.add(item);
        }
        inventory.put(item, inventory.getOrDefault(item, 0) + stock);
    }
    public static void removeItemFromCatalog(Item item, int stock) {
        if (ItemCatalog.contains(item)) {
            int currentStock = inventory.get(item);
            if (currentStock <= stock) {
                inventory.remove(item);
                ItemCatalog.remove(item);
                System.out.println("Removed all of " + item.getName() + " from catalog.");
            } else {
                inventory.put(item, currentStock - stock);
            }
        }
    }
}
