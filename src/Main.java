
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

    static Scanner scan = new Scanner(System.in);

    // Background images
    private static final String LOGIN_BG = "MartPass.png"; // login screen
    private static final String SCROLL_BG = "MartB.png";   // next screen (scrollable)

    // Optional: global session references if you want them accessible elsewhere
    public static String sessionUser = null;
    public static String sessionPassword = null;
    public static Cart sessionCart = null;

    // ----------------------------
    // Image loader
    // ----------------------------
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

    // ----------------------------
    // Styling helpers
    // ----------------------------
    static void styleField(JTextField f) {
        f.setFont(new Font("SansSerif", Font.PLAIN, 18));
    }

    // ----------------------------
    // Background panel (scales image to fit panel)
    // ----------------------------
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

    // ----------------------------
    // Login Screen (MartPass.png)
    // ----------------------------
    static class StartFrame extends JFrame {

        // --- EASY REPOSITION ---
        // Adjust these values to match the slots on MartPass.png
        private static int USER_X = 640;
        private static int USER_Y = 450;
        private static int USER_W = 320;
        private static int USER_H = 44;

        private static int PASS_X = 640;
        private static int PASS_Y = 520;
        private static int PASS_W = 320;
        private static int PASS_H = 44;

        // Text fields
        private final JTextField userField = new JTextField();
        private final JPasswordField passField = new JPasswordField();

        // ✅ These variables EXIST as fields (so they’re never "undefined")
        private String user;
        private String password;

        private final BufferedImage martPassImg;
        private final Store store;

        StartFrame(Store store) {
            super("CornerMart");
            this.store = store;

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Size window to screen (up to 1920x1080)
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int w = Math.min(1920, screen.width);
            int h = Math.min(1080, screen.height);
            setSize(w, h);
            setLocationRelativeTo(null);

            martPassImg = loadImage(LOGIN_BG);

            BackgroundPanel bg = new BackgroundPanel(martPassImg);
            bg.setLayout(null);  // absolute positioning
            setContentPane(bg);

            // Username field (VISIBLE)
            styleField(userField);
            userField.setBounds(USER_X, USER_Y, USER_W, USER_H);
            userField.setOpaque(true);
            userField.setBackground(new Color(255, 255, 255, 220));
            userField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            // Password field (HIDDEN)
            styleField(passField);
            passField.setBounds(PASS_X, PASS_Y, PASS_W, PASS_H);
            passField.setOpaque(true);
            passField.setBackground(new Color(255, 255, 255, 220));
            passField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            // Press Enter in either field to login
            userField.addActionListener(e -> {
                try {
                    attemptLogin();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
            passField.addActionListener(e -> {
                try {
                    attemptLogin();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });

            bg.add(userField);
            bg.add(passField);

            // Coordinate helper
            enableCoordinatePicker(bg);

            // Focus username on open
            SwingUtilities.invokeLater(userField::requestFocusInWindow);

            System.out.println("Coordinate helper enabled:");
            System.out.println(" - Click background: prints x,y");
            System.out.println(" - SHIFT + click: move USER field to x,y");
            System.out.println(" - CTRL + SHIFT + click: move PASS field to x,y");
        }

        private void attemptLogin() throws IOException {
            // ✅ Assign textbox input into the CLASS variables (fields)
            user = userField.getText().trim();

            char[] pwChars = passField.getPassword();
            password = new String(pwChars);
            java.util.Arrays.fill(pwChars, '\0'); // wipe char[] after use

            if (user.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a username and password.");
                return;
            }

            // ✅ Now user/password are defined and can be used anywhere in this frame
            // Create cart exactly how you asked:
            


        
            

            // Optional: store globally too (if you need them later in other classes)
            Main.sessionUser = user;
            Main.sessionPassword = password;
            //Main.sessionCart = cart;

            System.out.println("Created new cart for user: " + user);
            SaveManager.loadCartsFromFile();
            Cart cart = SaveManager.LoadCart(user, password);
            if (cart == null) {
                cart = new Cart(user, password);
            }
            // Next screen
            ScrollImageFrame next = new ScrollImageFrame();
            next.setVisible(true);
            dispose();
            SaveManager.SaveCart(user, cart);
            SaveManager.saveCartsToFile();
        }

        private void enableCoordinatePicker(JPanel bg) {
            bg.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();

                    System.out.println("Clicked at: x=" + x + ", y=" + y);

                    boolean shift = (e.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) != 0;
                    boolean ctrl  = (e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0;

                    // SHIFT + click => move username field
                    if (shift && !ctrl) {
                        USER_X = x;
                        USER_Y = y;
                        userField.setLocation(USER_X, USER_Y);
                        System.out.println("Moved USER field to: x=" + USER_X + ", y=" + USER_Y);
                    }

                    // CTRL + SHIFT + click => move password field
                    if (shift && ctrl) {
                        PASS_X = x;
                        PASS_Y = y;
                        passField.setLocation(PASS_X, PASS_Y);
                        System.out.println("Moved PASS field to: x=" + PASS_X + ", y=" + PASS_Y);
                    }
                }
            });
        }

        // Optional getters if you ever want them
        public String getUser() { return user; }
        public String getPassword() { return password; }
    }

    // ----------------------------
    // Image panel for scrolling image
    // ----------------------------
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
            if (image != null) g.drawImage(image, 0, 0, null);
        }
    }

    // ----------------------------
    // Scrollable image screen (MartB.png)
    // ----------------------------
    static class ScrollImageFrame extends JFrame {
        ScrollImageFrame() {
            super("CornerMart");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int w = Math.min(1920, screen.width);
            int h = Math.min(1080, screen.height);
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

            JScrollPane scrollPane = new JScrollPane(imagePanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            scrollPane.getVerticalScrollBar().setUnitIncrement(40);
            scrollPane.getVerticalScrollBar().setBlockIncrement(200);
            scrollPane.setWheelScrollingEnabled(true);

            setContentPane(scrollPane);
        }
    }

    // ----------------------------
    // Main
    // ----------------------------

    public static void main(String[] args) throws IOException{
        Store store = SaveManager.LoadStore();
        
        SwingUtilities.invokeLater(() -> {
            StartFrame frame = new StartFrame(store);
            frame.setVisible(true);
        });
    }
}

