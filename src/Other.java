
public class Other extends Item {
    public Other(String name, String desc, String brand,
                 float price, int stock) {
        super(name, desc, brand, price, stock);
    }
    @Override
    public String generateCode() {
        return "OTH-" + name.substring(0,3).toUpperCase() + "-" + brand.toUpperCase();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
