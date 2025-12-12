package src;
public class Prepackaged extends Item {
    private String expiryDate;

    public Prepackaged(String name, String desc, String brand,
                           float price, int stock, String expiryDate) {
        super(name, desc, brand, price, stock);
        this.expiryDate = expiryDate;
    }
    public String generateCode() {
        return "PRE-" + getName().substring(0,3).toUpperCase() + "-" + brand.toUpperCase();
    }

    @Override
    public String toString() {
        return super.toString() + " | Expires: " + expiryDate;
    }
}