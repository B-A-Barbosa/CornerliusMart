public class Prepackaged extends Item {
    public Prepackaged(String name, String desc, String brand,
                           float price) {
        super(name, desc, brand, price);
    }
    @Override
    public String generateCode() {
        return "PRE-" + name.substring(0,3).toUpperCase() + "-" + brand.toUpperCase();
    }
}