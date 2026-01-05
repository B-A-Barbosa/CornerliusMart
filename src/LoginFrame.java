import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    //these panels will be swapped depending on the toggle
    private JPanel loginPanel;
    private JPanel createPanel;
    private Store store;

    //toggle button to switch between login and create account
    private JToggleButton toggleButton;

    public LoginFrame(Store store) {
        this.store = store;
        //creating the frame
        super("Grocery Store Login");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        //toggle button to switch between login and create account panels
        toggleButton = new JToggleButton("Switch to Create Account");

        //when pressed, it will switch between login and create panels
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //If toggle is ON then show create account panel otherwise show login panel
                if (toggleButton.isSelected()) {
                    toggleButton.setText("Switch to Login");
                    showCreatePanel();
                }
                else {
                    toggleButton.setText("Switch to Create Account");
                    showLoginPanel();
                }
            }
        });
        add(toggleButton, BorderLayout.NORTH);
        //create both panels
        createLoginPanel();
        createCreatePanel();

        // Start with login panel visible
        add(loginPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    //create login panel (existing username + password)
    private void createLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));

        //username
        loginPanel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        loginPanel.add(usernameField);

        //password
        loginPanel.add(new JLabel("Password:"));
        JTextField passwordField = new JTextField();
        loginPanel.add(passwordField);

        //login button
        JButton loginButton = new JButton("Login");
        loginPanel.add(loginButton);
        //delete account button
        JButton deleteButton = new JButton("Delete Account");
        loginPanel.add(deleteButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String user = usernameField.getText();
                String pass = passwordField.getText();

                //load cart using user and pass, if a cart is returned, open user frame
                Cart cart = SaveManager.LoadCart(user, pass);
                if (cart != null){
                    cart.SetStore(store);
                    new UserFrame(cart);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Login failed. Please check your username and password."
                    );
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String user = usernameField.getText();
                String pass = passwordField.getText();

                Cart cart = SaveManager.LoadCart(user, pass);
                if (cart != null){
                    //remove cart from saved carts
                    //then save and reload carts to update and make sure the cart is truly gone
                    cart.SetStore(store); //we still have to set the store for the cart so that it can return its stock
                    SaveManager.DeleteCart(user);

                    JOptionPane.showMessageDialog(
                        null,
                        "Account deleted for: " + user
                    );

                    //clear fields
                    usernameField.setText("");
                    passwordField.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Account deletion failed. Please check your username and password."
                    );
                }
            }
        });
    }

    private void createCreatePanel() {
        createPanel = new JPanel();
        createPanel.setLayout(new GridLayout(3, 2));

        //new username
        createPanel.add(new JLabel("New Username:"));
        JTextField newUserField = new JTextField();
        createPanel.add(newUserField);

        //new password
        createPanel.add(new JLabel("New Password:"));
        JTextField newPassField = new JTextField();
        createPanel.add(newPassField);

        //create button
        JButton createButton = new JButton("Create Account");
        createPanel.add(createButton);

        //empty placeholder for layout cleanliness
        createPanel.add(new JLabel(""));

        //logic for button press to create new account
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newUser = newUserField.getText();
                String newPass = newPassField.getText();
                
                //create new cart and save it
                Cart cart = new Cart(newUser, newPass);
                SaveManager.SaveCart(cart);
                SaveManager.SaveCartsToFile();
                SaveManager.LoadCartsFromFile();

                JOptionPane.showMessageDialog(
                    null,
                    "Account created for: " + newUser
                );

                //switch back to login panel automatically so that the user may login
                toggleButton.setSelected(false);
                toggleButton.setText("Switch to Create Account");
                showLoginPanel();
            }
        });
    }

    private void showLoginPanel() {
        //remove current panel and show login panel
        getContentPane().removeAll();
        add(toggleButton, BorderLayout.NORTH);
        add(loginPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showCreatePanel() {
        //remove current panel and show create account panel
        getContentPane().removeAll();
        add(toggleButton, BorderLayout.NORTH);
        add(createPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
