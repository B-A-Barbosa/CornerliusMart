import java.util.Objects;
public abstract class Item {
    //protected attributes so subclasses can access them
    protected String name;
    protected String desc;
    protected String brand;
    protected float price;

    //When adding items from memory (while code is running) to a cart with items grabbed from save file,
    //java will see them as different objects even if they have the same attributes. This is becuse they are different instances in memory.
    //to correct this we use an items "generateCode" and description to determine equality.
    //I made the generate code method use only immutable attributes (name, desc, brand) to avoid issues with changing attributes.
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
    //this method is abstract and must be implemented by subclasses so that no issues occur
    public abstract String generateCode();

    public Item(String name, String desc, String brand, float price) {
        this.name = name;
        this.desc = desc;
        this.brand = brand;     
        this.price = price;
    }

    public float getPrice(){
        return price;
    }
    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
    public String getBrand() {
        return brand;
    }
}