import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Main {
    //TODO is throws ioexception needed here? (what other way to do it?)
    static Scanner scan = new Scanner(System.in);
    private static final String LOGIN_BG = "MartPass.png";
    private static final String SCROLL_BG = "MartB.png";

    //image loaderrrrrrrrrrrrrrrrrrrrrrrr
        private static BufferedImage loadImage(String fileName) {
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                System.err.println("Missing file: " + f.getAbsolutePath());
                return null;
            }
            return ImageIO.read(f);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
        //FONT AND COLOUR EDITOR
        static JLabel makeLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.BOLD, 16));
        l.setForeground(Color.WHITE); // looks better on dark images; change if needed
        return l;
    }


    static void styleField(JTextField f) {
        f.setFont(new Font("SansSerif", Font.PLAIN, 18));
    }


        // PANEL FOR IMAGE
        static class BackgroundPanel extends JPanel {
        private final BufferedImage image;


        BackgroundPanel(BufferedImage image) {
            this.image = image;
        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            } else {
                g.setColor(Color.DARK_GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.WHITE);
                g.drawString("Missing background image.", 20, 30);
            }
        }
    }
        //PANEL FOR IMAGE PART 2
    static class StartFrame extends JFrame {


        private final CardLayout cards = new CardLayout();
        private final JPanel cardHost = new JPanel(cards);


        private final BufferedImage martPassImg;


        StartFrame() {
            super("CornerMart");


            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            // Up to 1920x1080, but never exceed actual screen
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int w = Math.min(1920, screen.width);
            int h = Math.min(1080, screen.height);
            setSize(w, h);
            setLocationRelativeTo(null);


            martPassImg = loadImage(LOGIN_BG);
            setContentPane(new BackgroundPanel(martPassImg));
        }
    }


        static class ImagePanel extends JPanel {
        private final BufferedImage image;


        ImagePanel(BufferedImage image) {
            this.image = image;
            setBackground(Color.BLACK);
        }
       
        @Override
        public Dimension getPreferredSize() {
            if (image == null) return new Dimension(800, 600);
                return new Dimension(image.getWidth(), image.getHeight());
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, null);
        }
    }




    static class ScrollImageFrame extends JFrame {
        ScrollImageFrame() {
            super("CornerMart");


            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            // NOTE: 1920x3240 is bigger than most monitors.
            // We’ll size the window up to the monitor, but the IMAGE can be any size and will scroll.
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int w = Math.min(1920, screen.width);
            int h = Math.min(1080, screen.height); // viewport height
            setSize(w, h);
            setLocationRelativeTo(null);


            BufferedImage img = loadImage(SCROLL_BG);
            if (img == null) {
                JPanel fallback = new JPanel();
                fallback.add(new JLabel("Could not load " + SCROLL_BG));
                setContentPane(fallback);
                return;
            }
           
            ImagePanel imagePanel = new ImagePanel(img);


            // --------------------------
            // 2. ScrollPane for the image
            // --------------------------
            JScrollPane scrollPane = new JScrollPane(imagePanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


            // --------------------------
            // 3. Make scroll speed FAST & SMOOTH
            // --------------------------
            scrollPane.getVerticalScrollBar().setUnitIncrement(40);  // faster scroll
            scrollPane.getVerticalScrollBar().setBlockIncrement(200);


            // Smooth wheel scrolling (feels modern)
            scrollPane.setWheelScrollingEnabled(true);


            setContentPane(scrollPane);
        }
    }


    public static void main(String[] args) throws IOException {
        Store store = SaveManager.LoadStore();
        if (store == null) {
            store = new Store();
        }
        System.out.println(store.getItemCatalog().size() + " items loaded in store catalog.");
/* Testing adding items to cart and them taking away from store.
        Cart cart = new Cart(store, "ber", "123");
        cart.addToCart(new Clothing("T-Shirt", "Cotton t-shirt", "ClothCo", 19.99f, "Blue", Size.M), 2);
        cart.addToCart(new Grocery("Apple", "Fresh red apple", "FarmFresh", 3.99f, "10/10/10"), 5);
        cart.addToCart(store.getItemCatalog().get(0), 5);
        SaveManager.SaveCart(cart);
        SaveManager.SaveCartsToFile();
        SaveManager.SaveStore(store);
*/
/* Creates new test store objects
        Size enu = Size.M;
        store.addItemToCatalog(new Grocery("Apple", "Fresh red apple", "FarmFresh", 3.99f, "10/10/10"), 50);
        store.addItemToCatalog(new Clothing("T-Shirt", "Cotton t-shirt", "ClothCo", 19.99f, "Blue", enu), 100);
        store.addItemToCatalog(new Prepackaged("Chips", "Potato chips", "SnackCorp", 2.49f, "12/12/12"), 200);
        store.addItemToCatalog(new Furniture("Sofa", "Comfortable sofa", "FurniCo", 499.99f, "Leather", "Black"), 5);
        SaveManager.SaveStore(store);
*/
/* Creating a new cart and adding items to it
        SaveManager.loadCartsFromFile();
        System.out.println("Welcome to Cornerlius Mart!");
        System.out.println("user: ");
        String user = scan.nextLine();
        System.out.println("password: ");
        String password = scan.nextLine();
        //Cart userCart = new Cart(user, password);
        Cart userCart = SaveManager.LoadCart(user, password);
        Furniture chair = new Furniture("Chair", "A comfy chair", "FurniCo", 49.99f, 10, "Wood", "Brown");
        userCart.addToCart(chair, 2);
        System.out.println("Total price in cart: $" + userCart.getTotalPrice());


        SaveManager.SaveCart(user, userCart);
        SaveManager.saveCartsToFile();
*/
/* Testing cart save and load
        SaveManager.loadCartsFromFile();
        Cart cart = SaveManager.LoadCart("bernardo123", "123");
        cart.addToCart(chair, 2);
        System.out.println("TSotal price in cart: $" + cart.getTotalPrice());
        chair.putOnSale(0.10f); // 10% off
        System.out.println("Total price in cart: $" + cart.getTotalPrice());
        SaveManager.SaveCart("bernardo123", cart);
        SaveManager.saveCartsToFile();
*/
       
        SwingUtilities.invokeLater(() -> {
            StartFrame frame = new StartFrame();
            frame.setVisible(true);
        });


    }
}

