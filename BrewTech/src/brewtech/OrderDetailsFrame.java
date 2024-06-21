package brewtech;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderDetailsFrame extends JFrame {

    public OrderDetailsFrame() {
        setTitle("Order Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 5, 5)); // 4 rows, 2 columns
        
        JLabel addressLabel = new JLabel("Address:");
        inputPanel.add(addressLabel);
        JTextField addressField = new JTextField();
        inputPanel.add(addressField);

        JLabel contactLabel = new JLabel("Contact Number:");
        inputPanel.add(contactLabel);
        JTextField contactField = new JTextField();
        inputPanel.add(contactField);

        JLabel paymentLabel = new JLabel("Payment Method:");
        inputPanel.add(paymentLabel);
        String[] paymentMethods = {"Cash", "Credit Card", "Gcash", "Maya"};
        JComboBox<String> paymentMethodComboBox = new JComboBox<>(paymentMethods);
        inputPanel.add(paymentMethodComboBox);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(90, 60, 30)); // Set button color to brown
        cancelButton.setForeground(Color.WHITE); // Set text color to white
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the OrderDetailsFrame on cancel
            }
        });

        JButton confirmButton = new JButton("Confirm Order");
        confirmButton.setBackground(new Color(90, 60, 30)); // Set button color to brown
        confirmButton.setForeground(Color.WHITE); // Set text color to white
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String address = addressField.getText().trim();
                String contactNumber = contactField.getText().trim();
                String paymentMethod = (String) paymentMethodComboBox.getSelectedItem();

                if (address.isEmpty() || contactNumber.isEmpty() || paymentMethod.isEmpty()) {
                    JOptionPane.showMessageDialog(OrderDetailsFrame.this, "Please fill out all fields.");
                } else {
                    JOptionPane.showMessageDialog(OrderDetailsFrame.this, "Order confirmed!");
                    dispose(); // Close the OrderDetailsFrame
                    showBrewTech(); // Go back to the start or main screen
                }
            }

            private void showBrewTech() {
                // Implement your redirection logic here.
                // For example, reopen the main application window or perform some action.
                System.out.println("Returning to the main screen...");
            }
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(cancelButton, BorderLayout.WEST);
        buttonPanel.add(confirmButton, BorderLayout.EAST);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new OrderDetailsFrame().setVisible(true);
        });
    }
}
