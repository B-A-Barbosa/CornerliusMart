package src;

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
    private ArrayList<Item> ItemList;
    private HashMap<String, Integer> itemCount;

    //TODO reevaluate constructors (are both needed?)
    //In the case that a new cart is created
    public Cart(String userID) {
        this.userID = userID;
        this.ItemList = new ArrayList<>();
        itemCount = new HashMap<>();
    }
    //if a returning user has a cart saved
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
    public ArrayList<Item> getItemList() {
        return ItemList;
    }
    public void setItemList(ArrayList<Item> ItemList) {
        this.ItemList = ItemList;
    }

    //add the item to the arraylist, and then update the hashmap count
    public void addToCart(Item item, int quantity) {
        if (!ItemList.contains(item)){
            ItemList.add(item);
        }
        itemCount.put(item.getName(), itemCount.getOrDefault(item.getName(), 0) + quantity);
    }
    //if the item exists in the hashmap then get the number of items.
    //if there are less or equal items than to be removed, remove the item from the cart
    //else just decrease the number of items by quantity
    public void removeFromCart(Item item, int quantity) {
        if (itemCount.containsKey(item.getName())) {
            int currentCount = itemCount.get(item.getName());
            if (currentCount <= quantity) {
                item.returnToShelf(itemCount.get(item.getName()));
                itemCount.remove(item.getName());
                ItemList.remove(item);
                System.out.println("Removed all of " + item.getName() + " from cart.");
            } else {
                itemCount.put(item.getName(), currentCount - quantity);
                item.returnToShelf(quantity);
            }
        }
    }
}