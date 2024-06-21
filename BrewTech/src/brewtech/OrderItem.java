package brewtech;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderItem extends JFrame {

    private String itemName;
    private double basePrice;
    private JComboBox<String> sizeComboBox;
    private JPanel addonsPanel;
    private ArrayList<OrderItemData> cartItems;

    public OrderItem(String itemName, double basePrice, ArrayList<OrderItemData> cart) {
        this.itemName = itemName;
        this.basePrice = basePrice;
        this.cartItems = cart;

        setTitle("Order Details - " + itemName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close current window only

        initComponents(); // Initialize components

        setSize(400, 300);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true); // Make the frame visible
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Center panel for drink details
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding

        // Selected Drink label centered
        JLabel selectedItemLabel = new JLabel("Selected Drink: " + itemName);
        selectedItemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(selectedItemLabel);

        // Size selection panel
        sizeComboBox = new JComboBox<>(new String[]{"Regular", "Grande", "Venti"});
        JPanel sizePanel = new JPanel();
        sizePanel.add(new JLabel("Size: "));
        sizePanel.add(sizeComboBox);
        centerPanel.add(sizePanel);

        // Add-ons label centered
        JLabel addonsLabel = new JLabel("Add-ons:");
        addonsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(addonsLabel);

        // Add-ons panel for checkboxes
        addonsPanel = new JPanel();
        addonsPanel.setLayout(new BoxLayout(addonsPanel, BoxLayout.Y_AXIS)); // Align checkboxes vertically
        HashMap<String, Double> addonsPrices = getAddonsPrices();
        for (String addon : addonsPrices.keySet()) {
            JCheckBox checkbox = new JCheckBox(addon);
            addonsPanel.add(checkbox);
        }
        centerPanel.add(addonsPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Bottom panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(90, 60, 30)); // Set button color to brown
        backButton.setForeground(Color.WHITE); // Set text color to white
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
            }
        });
        buttonPanel.add(backButton);

        JButton continueButton = new JButton("Continue");
        continueButton.setBackground(new Color(90, 60, 30)); // Set button color to brown
        continueButton.setForeground(Color.WHITE); // Set text color to white
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCart();
            }
        });
        buttonPanel.add(continueButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    private HashMap<String, Double> getAddonsPrices() {
        HashMap<String, Double> addonsPrices = new HashMap<>();
        addonsPrices.put("Sugar", 15.00);
        addonsPrices.put("Milk", 15.00);
        addonsPrices.put("Whipped Cream", 15.00);
        addonsPrices.put("Syrup", 15.00);
        addonsPrices.put("Ice", 15.00);
        return addonsPrices;
    }

    private void addToCart() {
        String selectedSize = (String) sizeComboBox.getSelectedItem();

        StringBuilder selectedAddons = new StringBuilder();
        for (Component component : addonsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox checkbox = (JCheckBox) component;
                if (checkbox.isSelected()) {
                    selectedAddons.append(checkbox.getText()).append(", ");
                }
            }
        }
        if (selectedAddons.length() > 0) {
            selectedAddons.delete(selectedAddons.length() - 2, selectedAddons.length());
        } else {
            selectedAddons.append("None");
        }

        double totalPrice = basePrice;
        switch (selectedSize) {
            case "Regular":
                totalPrice += 0.0;
                break;
            case "Grande":
                totalPrice += 12.0;
                break;
            case "Venti":
                totalPrice += 20.0;
                break;
            default:
                break;
        }
        for (Component component : addonsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox checkbox = (JCheckBox) component;
                if (checkbox.isSelected()) {
                    totalPrice += getAddonsPrices().get(checkbox.getText());
                }
            }
        }

        OrderItemData item = new OrderItemData(itemName, selectedSize, selectedAddons.toString(), totalPrice);
        cartItems.add(item);

        JOptionPane.showMessageDialog(this, itemName + " added to cart.");

        dispose();
    }

    public static void main(String[] args) {
        ArrayList<OrderItemData> cart = new ArrayList<>();
        SwingUtilities.invokeLater(() -> {
            new OrderItem("Coffee", 2.50, cart).setVisible(true);
        });
    }
}

