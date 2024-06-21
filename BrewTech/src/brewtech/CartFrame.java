package brewtech;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CartFrame extends JFrame {

    private ArrayList<OrderItemData> cartItems;

    public CartFrame(ArrayList<OrderItemData> cartItems) {
        this.cartItems = cartItems;

        setTitle("Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JTextArea cartTextArea = new JTextArea();
        cartTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        StringBuilder cartText = new StringBuilder();

        double totalAmount = 0.0;
        for (OrderItemData item : cartItems) {
            cartText.append("Drink: ").append(item.getItemName()).append("\n");
            cartText.append("Size: ").append(item.getSize()).append("\n");
            cartText.append("Add-Ons: ").append(item.getAddons()).append("\n");
            cartText.append("Total Price: Php").append(String.format("%.2f", item.getTotalPrice())).append("\n\n");

            totalAmount += item.getTotalPrice();
        }
        cartText.append("Total Amount: Php").append(String.format("%.2f", totalAmount)).append("\n\n");
        cartTextArea.setText(cartText.toString());

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        JButton continueButton = new JButton("Continue");

        backButton.setBackground(new Color(90, 60, 30)); // Set button color to brown
        backButton.setForeground(Color.WHITE); // Set text color to white
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logic to go back to the previous frame
                dispose(); // Close the current cart frame
            }
        });

        continueButton.setBackground(new Color(90, 60, 30)); // Set button color to brown
        continueButton.setForeground(Color.WHITE); // Set text color to white
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showOrderDetailsFrame(); // Show the order details frame
            }
        });

        buttonPanel.add(backButton);
        buttonPanel.add(continueButton);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void showOrderDetailsFrame() {
        JFrame orderDetailsFrame = new JFrame("Order Details");
        orderDetailsFrame.setSize(400, 300);
        orderDetailsFrame.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();
        JLabel contactLabel = new JLabel("Contact Number:");
        JTextField contactField = new JTextField();
        JLabel paymentLabel = new JLabel("Payment Method:");
        String[] paymentOptions = {"Cash", "Credit Card", "Gcash", "Maya"};
        JComboBox<String> paymentComboBox = new JComboBox<>(paymentOptions);

        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(contactLabel);
        panel.add(contactField);
        panel.add(paymentLabel);
        panel.add(paymentComboBox);

        JButton confirmButton = new JButton("Confirm Order");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.setBackground(new Color(90, 60, 30)); // Set button color to brown
        confirmButton.setForeground(Color.WHITE); // Set text color to white
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String address = addressField.getText().trim();
                String contactNumber = contactField.getText().trim();
                String paymentMethod = (String) paymentComboBox.getSelectedItem();

                if (address.isEmpty() || contactNumber.isEmpty() || paymentMethod.isEmpty()) {
                    JOptionPane.showMessageDialog(orderDetailsFrame, "Please fill out all fields.");
                } else {
                    JOptionPane.showMessageDialog(orderDetailsFrame, "Order confirmed!");
                    System.out.println(e);
                    cartItems.clear();
                    orderDetailsFrame.dispose(); // Close the OrderDetailsFrame
                    dispose(); // Close the CartFrame
                    
                }
            }

            private void openBrewTech() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

        });

        cancelButton.setBackground(new Color(90, 60, 30)); // Set button color to brown
        cancelButton.setForeground(Color.WHITE); // Set text color to white
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                orderDetailsFrame.dispose(); // Close the order details frame
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelButton, BorderLayout.WEST);
        buttonPanel.add(confirmButton, BorderLayout.EAST);

        orderDetailsFrame.getContentPane().add(panel, BorderLayout.CENTER);
        orderDetailsFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        orderDetailsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        // For testing purposes
        ArrayList<OrderItemData> cart = new ArrayList<>();
        cart.add(new OrderItemData("Coffee", "Medium", "Milk, Sugar", 140.00));
        cart.add(new OrderItemData("Tea", "Large", "Honey", 238.00));

        SwingUtilities.invokeLater(() -> {
            new CartFrame(cart).setVisible(true);
        });
    }
}

class OrderItemData {
    private String itemName;
    private String size;
    private String addons;
    private double totalPrice;

    public OrderItemData(String itemName, String size, String addons, double totalPrice) {
        this.itemName = itemName;
        this.size = size;
        this.addons = addons;
        this.totalPrice = totalPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public String getSize() {
        return size;
    }

    public String getAddons() {
        return addons;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

