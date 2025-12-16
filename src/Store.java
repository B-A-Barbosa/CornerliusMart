import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Store {
    //TODO more efficient to do hashmap of items and counts only?
    private ArrayList<Item> ItemCatalog = new ArrayList<>();
    private Map<String, Integer> Inventory = new HashMap<>();

    //TODO connect cart and store

    public void addItemToCatalog(Item item, int stock) {
        if (stock <0) return;
        if (!ItemCatalog.contains(item)){
            ItemCatalog.add(item);
        }
        Inventory.put(item.generateCode(), Inventory.getOrDefault(item.generateCode(), 0) + stock);
    }
    public boolean removeItemFromCatalog(Item item, int stock) {
        if (ItemCatalog.contains(item)){
            int currentStock = Inventory.get(item.generateCode());
            if (currentStock >= stock) {
                Inventory.put(item.generateCode(), currentStock - stock);
                return true;
            }
        }
        return false;
    }
}
