import java.util.ArrayList;

public class Item {
    protected String name;
    protected String desc;
    protected String brand;     
    protected int stock;
    protected float price;
    protected float percentOff;
    protected boolean onsale; 
    protected ArrayList<Object> list;

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

    public void setPrice(float newPrice) {
        if (newPrice > 0) {
            price = newPrice;
            this.onsale = false;
            percentOff = 0;
        }
    }
    public String getname(){
        return name;
    }

    public float getprice(){
        return price;
    }
    @Override 
    public String toString() {
        return name + " (" + brand + ") - $" + price + ", stock: " + stock;
    }

    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
}