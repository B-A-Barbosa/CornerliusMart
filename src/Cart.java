import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    private String userID;
    private String password;
    //Transient so it is not saved when serializing, there is already a store save file so saving it in the cart is redundant
    private transient Store store;
    //Item list is only for saving purposes, as gson cannot serialize hashmaps properly, otherwise i would have used a Hashmap<Item, Integer>
    private ArrayList<Item> ItemList;
    private HashMap<String, Integer> ItemCount; //Key: item code, Value: number of items in cart

    public Cart(String userID, String password) {
        this.userID = userID;
        this.password = password;
        this.store = null;
        this.ItemList = new ArrayList<>();
        ItemCount = new HashMap<>();
    }
    public void SetStore (Store store) {
        this.store = store;
    }
    //getters and setters
    public float getTotalPrice() {
        float total = 0;
        for (Item item : ItemList) {
            total += item.getPrice() * ItemCount.get(item.generateCode());
        }
        return total;
    }
    public String getUserID() {
        return userID;
    }
    public String getPassword() {
        return password;
    }
    public Store getStore() {
        return store;
    }
    public int getItemCount(String itemCode) {
        return ItemCount.getOrDefault(itemCode, 0);
    }
    public ArrayList<Item> getItemList() {
        return ItemList;
    }
    public void setItemList(ArrayList<Item> ItemList) {
        this.ItemList = ItemList;
    }

    public void ClearCart() {
        ItemList.clear();
        ItemCount.clear();
    }

    //add the item to the arraylist, and then update the hashmap count
    public void addToCart(Item item, int quantity) {
        if (store.removeItemFromCatalog(item, quantity)) {
            if (!ItemList.contains(item)){
                ItemList.add(item);
            }
            ItemCount.put(item.generateCode(), ItemCount.getOrDefault(item.generateCode(), 0) + quantity);
        }
    }
    //if the item is in the cart, remove the specified quantity and return it to the store inventory
    public void removeFromCart(Item item, int quantity) {
        if (ItemList.contains(item)){
            int currentStock = ItemCount.get(item.generateCode());
            if (currentStock >= quantity) {
                ItemCount.put(item.generateCode(), currentStock - quantity);
                store.addItemToCatalog(item, quantity);
            }
            //delete item from cart if quantity is 0
            if (ItemCount.get(item.generateCode()) == 0) {
                ItemList.remove(item);
                ItemCount.remove(item.generateCode());
            }
        }
    }
}