public class Main {
    public static void addItemsLoop(Store store) {
        while (true) {
            // use InputManager.ChooseOption for the main menu
            String choice = InputManager.ChooseOption(new String[]{
                    "Add Clothing",
                    "Add Furniture",
                    "Add Grocery",
                    "Add Prepackaged",
                    "Add Other",
                    "Done"
            });

            if (choice.equalsIgnoreCase("Done")) {
                System.out.println("Starting Program");
                break;
            }

            //base fields for all items
            System.out.print("Name: ");
            String name = InputManager.scan.nextLine();

            System.out.print("Description: ");
            String desc = InputManager.scan.nextLine();

            System.out.print("Brand: ");
            String brand = InputManager.scan.nextLine();

            float price = InputManager.getFloat("Price: ");
            int stock = InputManager.getInt("Stock quantity: ");

            //creating the item based on the type chosen
            Item item = null;
            switch (choice) {
                //if type is clothing or furniture, ask extra questions
                case "Add Clothing":
                    System.out.print("Color: ");
                    String color = InputManager.scan.nextLine();

                    //sanitize size input
                    String size = InputManager.ChooseOption(new String[]{
                            "XS", "S", "M", "L", "XL", "XXL"
                    });

                    item = new Clothing(name, desc, brand, price, color, size);
                    break;
                case "Add Furniture":
                    System.out.print("Material: ");
                    String material = InputManager.scan.nextLine();

                    System.out.print("Color: ");
                    String fcolor = InputManager.scan.nextLine();

                    item = new Furniture(name, desc, brand, price, material, fcolor);
                    break;
                case "Add Grocery":
                    item = new Grocery(name, desc, brand, price);
                    break;
                case "Add Prepackaged":
                    item = new Prepackaged(name, desc, brand, price);
                    break;
                case "Add Other":
                    item = new Other(name, desc, brand, price);
                    break;

                default:
                    System.out.println("Unexpected choice, skipping item.");
                    continue;
            }
            store.addItemToCatalog(item, stock);
            System.out.println("Item added successfully!");
        }
    }
    public static void main(String[] args) {
        //load store and carts from file and make new store if none exists
        Store store = SaveManager.LoadStore();
        SaveManager.LoadCartsFromFile();
        if (store == null) {
            store = new Store();
        }
        //allow adding items before launching login frame
        addItemsLoop(store);

        //save store after adding items
        SaveManager.SaveStore(store);

        //launch login frame
        new LoginFrame(store);
    }
}
