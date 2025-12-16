/*Cart class
Total price, # of items - saved or calculated when needed?
hashmap or an arraylist of everything inside ?
Hasmap for # of each item, and arraylist for the object?
Hashmap:
	Key = name of item, value = # of items in cart
Arraylist:
	Arraylist for each department
    CHANGE - single arraylist of items instead of one for each department
Methods
    Void Add to cart(item name, quantity) - adds x amount of items from cart
    Void remove from cart (item name, quantity) - removes x amount of items to cart
    Int return number of items(item name) - uses hashmap to get #
    Float return price
*/
import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    private String userID;
    private String password;
    private Store store;
    //TODO more efficient to do hashmap of items and counts only?
    private ArrayList<Item> ItemList;
    private HashMap<String, Integer> itemCount;

    //TODO reevaluate constructors (are both needed?)
    //In the case that a new cart is created
    public Cart(Store store, String userID, String password) {
        this.userID = userID;
        this.password = password;
        this.store = store;
        this.ItemList = new ArrayList<>();
        itemCount = new HashMap<>();
    }
    public void SetStore (Store store) {
        this.store = store;
    }
    //if a returning user has a cart saved
    //TODO needed?
    public Cart(String userID, ArrayList<Item> ItemList, HashMap<String, Integer> itemCount) {
        this.userID = userID;
        this.ItemList = ItemList;
        this.itemCount = itemCount;
    }

    public float getTotalPrice() {
        float total = 0;
        for (Item item : ItemList) {
            total += item.getPrice() * itemCount.get(item.getName());
        }
        return total;
    }
    public String getUserID() {
        return userID;
    }
    public String getPassword() {
        return password;
    }
    public ArrayList<Item> getItemList() {
        return ItemList;
    }
    public void setItemList(ArrayList<Item> ItemList) {
        this.ItemList = ItemList;
    }

    //add the item to the arraylist, and then update the hashmap count
    //TODO did we use the boolean?
    public boolean addToCart(Item item, int quantity) {
        if (store.removeItemFromCatalog(item, quantity)) {
            if (!ItemList.contains(item)){
                ItemList.add(item);
            }
            itemCount.put(item.getName(), itemCount.getOrDefault(item.getName(), 0) + quantity);
            return true;
        }
        return false;
    }
    //if the item exists in the hashmap then get the number of items.
    //if there are less or equal items than to be removed, remove the item from the cart
    //else just decrease the number of items by quantity
    //TODO did we use the boolean?
    //TODO change hashmap form name to code !!!!!!!!!!!
    //normalize quantity and stock name between cart and store
    public boolean removeFromCart(Item item, int quantity) {
        if (ItemList.contains(item)){
            int currentStock = itemCount.get(item.getName());
            if (currentStock >= quantity) {
                itemCount.put(item.getName(), currentStock - quantity);
                store.addItemToCatalog(item, quantity);
                return true;
            }
        }
        return false;
    }
}