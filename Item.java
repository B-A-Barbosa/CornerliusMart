import java.util.ArrayList;
//types of item, prepackaged, grocery, clothing, furniture, ...
public class Item {
    protected String name;
    protected String desc;
    protected int stock;
    protected float price; //for 1 or /kg
    protected ArrayList<Object> list;

    public Item(String name, String desc, float price, int stock) {
        this.name = name;
        this.desc = desc;
        this.stock = stock;
        this.price = price;
    }
}