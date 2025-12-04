public class Furniture extends Item {
    private String material;
    private String colour;

    public Furniture(String name, String description, String brand,
                     float price, int stock, String material, String colour) {
        super(name, description, brand, price, stock);
        this.material = material;
        this.colour = colour;
    }

    
    @Override
    public String toString() {
        return super.toString() + " | Material: " + material + " | Colour: " + colour;
    }
}