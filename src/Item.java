
import java.util.ArrayList;
import java.util.Objects;
public abstract class Item {
    protected String name;
    protected String desc;
    protected String brand;
    protected float price;
    protected float percentOff;
    protected ArrayList<Object> list;

    //TODO explain the override methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Item)) return false;
        Item other = (Item) obj;
        return this.generateCode().equals(other.generateCode()) &&
               this.desc.equals(other.desc);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, brand, desc);
    }

    @Override 
    public String toString() {
        return name + " (" + brand + ") - $" + price;
    }

    public abstract String generateCode();

    public Item(String name, String desc, String brand, float price) {
        this.name = name;
        this.desc = desc;
        this.brand = brand;     
        this.price = price;
    }

    public void putOnSale(float percentOff) {
        this.percentOff = percentOff;
        if (percentOff < 0 || percentOff > 100) return;

        price = price - (price * (percentOff / 100f));
    }
    public void takeOffSale() {
        if (percentOff > 0) {
            price = price / (1 - (percentOff / 100f));
        }
    }

    public void setPrice(float newPrice) {
        if (newPrice > 0) {
            price = newPrice;
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