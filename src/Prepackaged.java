
public class Prepackaged extends Item {
    private String expiryDate;

    public Prepackaged(String name, String desc, String brand,
                           float price, String expiryDate) {
        super(name, desc, brand, price);
        this.expiryDate = expiryDate;
    }
    @Override
    public String generateCode() {
        return "PRE-" + name.substring(0,3).toUpperCase() + "-" + brand.toUpperCase();
    }

    @Override
    public String toString() {
        return super.toString() + " | Expiry Date: " + expiryDate;
    }
}