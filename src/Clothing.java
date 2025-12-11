//package src;
//enum size
//colour
public class Clothing extends Item {
    private String colour;
    private String size;

    public Clothing(String name, String description, String brand,
                    float price, int stock, String colour, String size) {                
        super(name, description, brand, price, stock);
        this.colour = colour;
        this.size = size;
        this.type = "Clothing";
    }

    @Override
    public String toString() {
        return super.toString() + " | Colour: " + colour + " | Size: " + size;
    }
}