
import java.util.ArrayList;

//types of item, prepackaged, grocery, clothing, furniture, ...
public class Item {
    protected String name;
    protected String desc;
    protected String brand;     
    protected int stock;
    protected float price; 
    protected boolean onsale; 
    protected ArrayList<Object> list;

    public Item(String name, String desc, String brand, float price, int stock) {
        this.name = name;
        this.desc = desc;
        this.brand = brand;     
        this.stock = stock;
        this.price = price;
    }
}