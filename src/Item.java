//package src;
import java.util.ArrayList;
import java.util.Objects;
public class Item {
    protected String name;
    protected String desc;
    protected String brand;   
    protected int stock;
    protected String type;
    protected float price;
    protected float percentOff;
    protected boolean onsale; 
    protected ArrayList<Object> list;

    //TODO explain the override methods
    //derive a number from these fields to create item id?
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Item)) return false;
        Item other = (Item) obj;
        return this.name.equals(other.name) &&
                this.brand.equals(other.brand) &&
                this.desc.equals(other.desc);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, brand, desc);
    }

    @Override 
    public String toString() {
        return name + " (" + brand + ") - $" + price + ", stock: " + stock;
    }
    public Item(String name, String desc, String brand, float price, int stock) {
        this.name = name;
        this.desc = desc;
        this.brand = brand;     
        this.stock = stock;
        this.price = price;
    }

    public void putOnSale(float percentOff) {
        this.percentOff = percentOff;
        if (percentOff < 0 || percentOff > 100) return;

        price = price - (price * (percentOff / 100f));
        this.onsale = true; 
    }
    public void takeOffSale() {
        if (this.onsale) {
            price = price / (1 - (percentOff / 100f));
            this.onsale = false; 
        }
    }
    public void returnToShelf(int amount){
        if (amount > 0){
            stock += amount;
        }
    }
    public void takeOffShelf(int amount){
        if (stock >= amount){
            stock -= amount;
        }
    }
    //getters and setters
    public void setPrice(float newPrice) {
        if (newPrice > 0) {
            price = newPrice;
            this.onsale = false;
            percentOff = 0;
        }
    }
    public float getPrice(){
        return price;
    }
    public String getName() {
        return name;
    }
}