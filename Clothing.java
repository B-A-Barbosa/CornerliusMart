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
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    
    @Override
    public String toString() {
        return super.toString() + " | Colour: " + colour + " | Size: " + size;
    }
}