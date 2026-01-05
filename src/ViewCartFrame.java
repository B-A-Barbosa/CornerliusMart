import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewCartFrame extends JFrame {

    public ViewCartFrame(Cart cart) {
        //create window
        setTitle("Your Cart");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //panel to hold all cart items
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));

        // Loop through items in the cart
        for (Item item : cart.getItemList()) {

            int quantity = cart.getItemCount(item.generateCode());

            JLabel label = new JLabel(
                item.getName() + "  |  Qty: " + quantity + "  |  $" + item.getPrice()
            );

            itemPanel.add(label);
        }

        //scroll pane for long carts
        JScrollPane scrollPane = new JScrollPane(itemPanel);
        add(scrollPane, BorderLayout.CENTER);

        //bottom panel for total price and close button
        JPanel bottomPanel = new JPanel(new BorderLayout());

        //total price label
        JLabel totalLabel = new JLabel("Total: $" + cart.getTotalPrice());
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(totalLabel, BorderLayout.WEST);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (cart.getItemList().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your cart is empty!");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Thank you for your purchase of $" + cart.getTotalPrice() + "!");
                cart.ClearCart();
                SaveManager.SaveCart(cart);
                SaveManager.SaveCartsToFile();
                dispose();
            }
        });
        bottomPanel.add(checkoutButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
