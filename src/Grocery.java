
public class Grocery extends Item {
    private float weightKg;     

    public Grocery(String name, String desc, String brand,
                   float pricePerKg, float weightKg, String expiryDate) {
        super(name, desc, brand, pricePerKg, 1); 
        this.weightKg = weightKg;
    }
    public String generateCode() {
        return "GRO-" + getName().substring(0,3).toUpperCase();
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
        return super.toString() + " | Weight: " + weightKg + "kg";
    }
}