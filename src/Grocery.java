public class Grocery extends Item {
    public Grocery(String name, String desc, String brand,
                   float pricePerKg) {
        super(name, desc, brand, pricePerKg);
    }
    @Override
    public String generateCode() {
        return "GRO-" + name.toUpperCase() + "-" + desc.substring(0, 3).toUpperCase();
    }
}