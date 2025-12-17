public class Grocery extends Item {
    private String expiryDate;

    public Grocery(String name, String desc, String brand,
                   float pricePerKg, String expiryDate) {
        super(name, desc, brand, pricePerKg);
        this.expiryDate = expiryDate;
    }
    @Override
    public String generateCode() {
        return "GRO-" + name.toUpperCase() + "-" + desc.substring(0, 3).toUpperCase();
    }
    @Override
    public String toString() {
        return super.toString() + " | Expiry Date: " + expiryDate;
    }
}