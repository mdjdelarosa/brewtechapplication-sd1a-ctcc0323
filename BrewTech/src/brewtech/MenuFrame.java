package brewtech;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MenuFrame extends JFrame {

    private ArrayList<OrderItemData> cart = new ArrayList<>();
    private JPanel menuPanel;

    public MenuFrame(String drinkType) {
        setTitle("BrewTech - " + drinkType);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 400);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(102, 51, 0)); // Dark brown background
        setLayout(new BorderLayout()); // Ensure border layout for components

        initComponents(drinkType);
    }

    private void initComponents(String drinkType) {
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        displayMenu(drinkType);

        JScrollPane scrollPane = new JScrollPane(menuPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(new Color(90, 60, 30)); // Dark brown background
        scrollPane.getViewport().setBackground(new Color(102, 51, 0)); // Set viewport background

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(90, 60, 30)); // Dark brown background

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openDrinkTypeSelectionFrame();
            }
        });
        bottomPanel.add(backButton);

        JButton cartButton = new JButton("Open Cart");
        cartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCartFrame();
            }
        });
        bottomPanel.add(cartButton);

        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }

    private void displayMenu(String drinkType) {
        menuPanel.removeAll();
        menuPanel.setBackground(new Color(90, 60, 30)); // Dark brown background

        HashMap<String, Double> menuItems = getMenuItems(drinkType);
        for (String item : menuItems.keySet()) {
            JPanel itemPanel = createItemPanel(item, menuItems.get(item));
            menuPanel.add(itemPanel);
        }

        revalidate();
        repaint();
    }

    private HashMap<String, Double> getMenuItems(String drinkType) {
        HashMap<String, Double> items = new HashMap<>();
        switch (drinkType) {
            case "Hot Coffee":
                items.put("Espresso", 140.00);
                items.put("Latte", 160.00);
                items.put("Cappuccino", 170.00);
                items.put("Americano", 120.00);
                items.put("Flat White", 160.00);
                items.put("Mocha", 180.00);
                items.put("Macchiato", 120.00);
                break;
            case "Iced Coffee":
                items.put("Iced Latte", 170.00);
                items.put("Iced Americano", 130.00);
                items.put("Iced Mocha", 190.00);
                items.put("Iced Macchiato", 150.00);
                items.put("Iced Cappuccino", 180.00);
                items.put("Frappuccino", 190.00);
                break;
            case "Milk Tea":
                items.put("Classic Pearl Milk Tea", 120.00);
                items.put("Wintermelon Milk Tea", 140.00);
                items.put("Okinawa Milk Tea", 140.00);
                items.put("Cheesecake Milk Tea", 150.00);
                items.put("Chocolate Milk Tea", 140.00);
                items.put("Strawberry Milk Tea", 140.00);
                items.put("Red Bean Milk Tea", 130.00);
                break;
            case "Fruity Drink":
                items.put("Blueberry", 110.00);
                items.put("Pomegranate", 100.00);
                items.put("Strawberry", 110.00);
                items.put("Mango", 100.00);
                items.put("Tangerine", 100.00);
                items.put("Peach", 110.00);
                items.put("Grape", 100.00);
                items.put("Watermelon", 100.00);
                items.put("Dragonfruit", 110.00);
                items.put("Lychee", 100.00);
                break;
            case "Soda":
                items.put("Cucumber Mint", 120.00);
                items.put("Lavender Soda", 120.00);
                items.put("Cherry Vanilla Soda", 120.00);
                items.put("Maple Soda", 120.00);
                items.put("Cinnamon Cola", 120.00);
                items.put("Basil Lime Soda", 120.00);
                items.put("Caramel Cream Soda", 120.00);
                items.put("Tutti Frutti Soda", 120.00);
                items.put("Toffee Soda", 120.00);
                items.put("Prickly Pear Soda", 120.00);
                break;
            default:
                break;
        }
        return items;
    }

    private JPanel createItemPanel(String itemName, double basePrice) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemPanel.setBackground(new Color(90, 60, 30)); // Dark brown background

        JLabel itemLabel = new JLabel(itemName);
        itemLabel.setFont(itemLabel.getFont().deriveFont(24.0f));
        itemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemLabel.setForeground(Color.WHITE);

        JLabel priceLabel = new JLabel(String.format("Php%.2f", basePrice));
        priceLabel.setFont(priceLabel.getFont().deriveFont(18.0f));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceLabel.setForeground(Color.WHITE);

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCart(itemName, basePrice);
            }
        });

        itemPanel.add(itemLabel);
        itemPanel.add(priceLabel);
        itemPanel.add(addToCartButton);
        itemPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        return itemPanel;
    }

    private void addToCart(String itemName, double basePrice) {
        OrderItem orderItemFrame = new OrderItem(itemName, basePrice, cart);
        orderItemFrame.setVisible(true);
    }

    private void openCartFrame() {
        CartFrame cartFrame = new CartFrame(cart);
        cartFrame.setVisible(true);
    }

    private void openDrinkTypeSelectionFrame() {
        DrinkTypeFrame selectionFrame = new DrinkTypeFrame();
        selectionFrame.setVisible(true);
        dispose(); // Close the current frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuFrame menuFrame = new MenuFrame("Hot Coffee");
            menuFrame.setVisible(true);
        });
    }
}
