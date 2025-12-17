public class Furniture extends Item {
    private String material;
    private String colour;

    public Furniture(String name, String desc, String brand,
                     float price, String material, String colour) {
        super(name, desc, brand, price);
        this.material = material;
        this.colour = colour;
    }
    @Override
    public String generateCode() {
        return "FUR-" + name.substring(0,3).toUpperCase() + "-" + colour.toUpperCase() + "-" + material.toUpperCase();
    }
    
    @Override
    public String toString() {
        return super.toString() + " | Material: " + material + " | Colour: " + colour;
    }
}