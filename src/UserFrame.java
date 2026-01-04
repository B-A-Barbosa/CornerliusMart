import javax.swing.*;
import java.awt.*;

public class UserFrame extends JFrame {

    private Cart cart;
    private Store store;

    //UI components: category and item boxes, as well as the quantity selector
    private JComboBox<String> categoryDropdown;
    private JComboBox<Item> itemDropdown;
    private JSpinner quantitySpinner;

    //labels inside the details panel
    private JPanel detailsPanel;
    private JLabel nameLabel, brandLabel, descLabel, priceLabel;
    //optional labels
    private JLabel sizeLabel, colorLabel, materialLabel;

    //constructor makes the ui and event listeners
    public UserFrame(Cart cart) {
        //sets the store and cart variables
        this.cart = cart;
        this.store = cart.getStore();

        //set window title and size. make the window close when the X is clicked
        setTitle("Cornerlius Mart - Shopping");
        setSize(600, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //center window on screen
        setLocationRelativeTo(null);

        //use BorderLayout for top, center, bottom sections
        setLayout(new BorderLayout());

        //new panel for item selection (category + item)
        JPanel topPanel = new JPanel(new GridLayout(2, 2));

        //category label and dropdown menu
        topPanel.add(new JLabel("Category:"));
        categoryDropdown = new JComboBox<>(new String[]{"Clothing", "Furniture", "Grocery", "Prepackaged", "Other"});
        topPanel.add(categoryDropdown);

        //item label and dropdown menu
        topPanel.add(new JLabel("Item:"));
        itemDropdown = new JComboBox<>();
        topPanel.add(itemDropdown);
        add(topPanel, BorderLayout.NORTH);

        //makes it so only the item name shows in the dropdown, so its sofa rather than the full toString() (sofa black leather etc)
        itemDropdown.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                //if the value is item type, show only its name
                if (value instanceof Item item) {
                    setText(item.getName());
                }
                return this;
            }
        });
        
        detailsPanel = new JPanel(new GridLayout(8, 1));

        //create labels (start empty)
        nameLabel = new JLabel("Name: ");
        brandLabel = new JLabel("Brand: ");
        descLabel = new JLabel("Description: ");
        priceLabel = new JLabel("Price: ");

        //optional labels (start blank as we dont know if item has these yet)
        sizeLabel = new JLabel("");
        colorLabel = new JLabel("");
        materialLabel = new JLabel("");

        //add labels to panel
        detailsPanel.add(nameLabel);
        detailsPanel.add(brandLabel);
        detailsPanel.add(descLabel);
        detailsPanel.add(priceLabel);
        detailsPanel.add(sizeLabel);
        detailsPanel.add(colorLabel);
        detailsPanel.add(materialLabel);

        //add details panel to center of window
        add(detailsPanel, BorderLayout.CENTER);

       //creating the bottom panel wit the quantity selector and add to cart button
        JPanel bottomPanel = new JPanel(new FlowLayout());

        //quantity label and spinner
        bottomPanel.add(new JLabel("Quantity:"));
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        bottomPanel.add(quantitySpinner);

        //add to cart button
        JButton addButton = new JButton("Add to Cart");
        bottomPanel.add(addButton);

        //remove from cart button
        JButton removeButton = new JButton("Remove From Cart");
        bottomPanel.add(removeButton);

        //remove from cart event listener
        removeButton.addActionListener(e -> {
            Item baseItem = (Item) itemDropdown.getSelectedItem();
            if (baseItem == null) return;

            int quantity = (int) quantitySpinner.getValue();

            // how many of this item are in the cart?
            int inCart = cart.getItemCount(baseItem.generateCode());

            if (quantity > inCart) {
                JOptionPane.showMessageDialog(this,
                    "You only have " + inCart + " of this item in your cart.");
                return;
            }

            //remove from cart
            cart.removeFromCart(baseItem, quantity);

            //save changes
            SaveManager.SaveCart(cart);
            SaveManager.SaveCartsToFile();
            SaveManager.SaveStore(store);

            JOptionPane.showMessageDialog(this, "Removed from cart!");
        });

        //add bottom panel (spinner and button are inside the panel) to window
        add(bottomPanel, BorderLayout.SOUTH);

        JButton viewCartButton = new JButton("View Cart");
        bottomPanel.add(viewCartButton);

        viewCartButton.addActionListener(e -> new ViewCartFrame(cart));

        //event listeners
        //when category changes then reload the items in the item dropdown
        categoryDropdown.addActionListener(e -> updateItemDropdown());

        //when the item changes then update details panel (the new item may have different attributes)
        itemDropdown.addActionListener(e -> updateDetailsPanel());

        //make the add to cart button actually add the selected item to the cart
        addButton.addActionListener(e -> addSelectedItemToCart());

        //load initial items
        updateItemDropdown();

        //make the window visible
        setVisible(true);
    }

    //loads items in the item dropdown based on the selected category
    private void updateItemDropdown() {

        //clear the dropdown and then check which category is selected
        itemDropdown.removeAllItems();
        String category = (String) categoryDropdown.getSelectedItem();

        //go through every item in stock and add them if they match the selected category
        for (Item item : store.getItemCatalog()) {
            if (category.equals("Clothing") && item instanceof Clothing) {
                itemDropdown.addItem(item);
            }
            if (category.equals("Grocery") && item instanceof Grocery) {
                itemDropdown.addItem(item);
            }
            if (category.equals("Furniture") && item instanceof Furniture) {
                itemDropdown.addItem(item);
            }
            if (category.equals("Prepackaged") && item instanceof Prepackaged) {
                itemDropdown.addItem(item);
            }
            if (category.equals("Other") && item instanceof Other) {
                itemDropdown.addItem(item);
            }
        }
        //update the panel with the new item attributes (when you pick a new category the first item will be selected automatically)
        updateDetailsPanel();
    }

    //this updates the details panel to show the attributes of the selected item
    private void updateDetailsPanel() {

        //get the selected item
        Item item = (Item) itemDropdown.getSelectedItem();
        if (item == null) return;

        //basic info that is always there
        nameLabel.setText("Name: " + item.getName());
        brandLabel.setText("Brand: " + item.getBrand());
        descLabel.setText("Description: " + item.getDesc());
        priceLabel.setText("Price: $" + item.getPrice());

        //clear the optional labels
        sizeLabel.setText("");
        colorLabel.setText("");
        materialLabel.setText("");

        //if the item is clothing or furniture (the only item types with extra attributes)
        //then turn the item variable into the proper type so that we can use its methods to show those attributes
        if (item instanceof Clothing c) {
            sizeLabel.setText("Size: " + c.getSize());
            colorLabel.setText("Color: " + c.getColour());
        }
        else if (item instanceof Furniture f) {
            materialLabel.setText("Material: " + f.getMaterial());
            colorLabel.setText("Color: " + f.getColor());
        }
    }

    //this adds the selected item to the cart when the button is clicked
    private void addSelectedItemToCart() {
        //get our item and quantity and if the item is null, return
        Item baseItem = (Item) itemDropdown.getSelectedItem();
        if (baseItem == null) return;
        int quantity = (int) quantitySpinner.getValue();
        
        //check if enough stock is available by comparing requested quantity to store inventory
        int available = store.getInventory().getOrDefault(baseItem.generateCode(), 0);
        if (quantity > available) {
            JOptionPane.showMessageDialog(this,
                "Not enough stock! Only " + available + " left in store.");
            return;
        }

        //make a new variable so that we dont make a reference
        Item finalItem = null;

        //depending on the item type, make a new item of that type using the java feature explained above (turn item into proper type and use its methods)
        if (baseItem instanceof Clothing c) {
            finalItem = new Clothing(
                    c.getName(), c.getDesc(), c.getBrand(), c.getPrice(),
                    c.getColour(), c.getSize()
            );
        }
        else if (baseItem instanceof Grocery g) {
            finalItem = new Grocery(
                    g.getName(), g.getDesc(), g.getBrand(), g.getPrice()
            );
        }
        else if (baseItem instanceof Furniture f) {
            finalItem = new Furniture(
                    f.getName(), f.getDesc(), f.getBrand(), f.getPrice(),
                    f.getMaterial(), f.getColor()
            );
        }
        else if (baseItem instanceof Prepackaged p) {
            finalItem = new Prepackaged(
                    p.getName(), p.getDesc(), p.getBrand(), p.getPrice()
            );
        }
        else if (baseItem instanceof Other o) {
            finalItem = new Other(
                    o.getName(), o.getDesc(), o.getBrand(), o.getPrice()
            );
        }
        //finally, add the item to the cart
        cart.addToCart(finalItem, quantity);

        //save cart and store immediately after adding item so that no item duplication occurs
        SaveManager.SaveCart(cart);
        SaveManager.SaveCartsToFile();
        SaveManager.SaveStore(store);

        //confirmation message
        JOptionPane.showMessageDialog(this, "Added to cart!");
    }
}
