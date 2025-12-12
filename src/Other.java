
public class Other extends Item {
    public Other(String name, String desc, String brand,
                 float price, int stock) {
        super(name, desc, brand, price, stock);
    }
    public String generateCode() {
        return "OTH-" + getName().substring(0,3).toUpperCase();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
