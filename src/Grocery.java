//package src;

public class Grocery extends Item {
    private float weightKg;     
    private String expiryDate;

    public Grocery(String name, String description, String brand,
                   float pricePerKg, float weightKg, String expiryDate) {
        super(name, description, brand, pricePerKg, 1); 
        this.weightKg = weightKg;
        this.expiryDate = expiryDate;
        this.type = "Grocery";
    }

    // Customer buys a certain weight
    //TODO ?????
    public boolean takeWeight(float amountKg) {
        if (amountKg <= 0 || amountKg > weightKg) return false;
        weightKg -= amountKg;
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " | Weight: " + weightKg + "kg | Expires: " + expiryDate;
    }
}