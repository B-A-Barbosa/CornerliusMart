enum Size {
    XS, S, M, L, XL, XXL
}

public class Clothing extends Item {
    private String colour;
    private Size size;

    public Clothing(String name, String desc, String brand,
                    float price, int stock, String colour, Size size) {                
        super(name, desc, brand, price, stock);
        this.colour = colour;
        this.size = size;
    }
    @Override
    public String generateCode() {
        return "CLO-" + name.substring(0,3).toUpperCase() + "-" + colour.toUpperCase() + "-" + size.name();
    }

    @Override
    public String toString() {
        return super.toString() + " | Colour: " + colour + " | Size: " + size;
    }
}