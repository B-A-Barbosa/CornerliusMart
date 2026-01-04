public class Clothing extends Item {
    private String colour;
    private String size;

    public Clothing(String name, String desc, String brand,
                    float price, String colour, String size) {                
        super(name, desc, brand, price);
        this.colour = colour;
        this.size = size;
    }
    @Override
    public String generateCode() {
        return "CLO-" + name.substring(0,3).toUpperCase() + "-" + colour.toUpperCase() + "-" + size.toUpperCase();
    }

    public String getColour() {
        return colour;
    }
    public String getSize() {
        return size;
    }
}