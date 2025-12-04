//nutrition
//
import java.time.LocalDate;

public class Prepackaged extends Item {
    private LocalDate expiryDate;

    public Prepackaged(String name, String description, String brand,
                           float price, int stock, LocalDate expiryDate) {
        super(name, description, brand, price, stock);
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return super.toString() + " | Expires: " + expiryDate;
    }
}