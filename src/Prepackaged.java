//package src;
//nutrition

public class Prepackaged extends Item {
    private String expiryDate;

    public Prepackaged(String name, String description, String brand,
                           float price, int stock, String expiryDate) {
        super(name, description, brand, price, stock);
        this.expiryDate = expiryDate;
        this.type = "Prepackaged";
    }

    @Override
    public String toString() {
        return super.toString() + " | Expires: " + expiryDate;
    }
}