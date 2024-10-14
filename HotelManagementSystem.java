import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelManagementSystem {
    private JFrame frame;
    private JTextField nameField, daysField, roomField, totalField;
    private JButton calculateButton, bookButton;
    private JTextArea outputArea;

    public HotelManagementSystem() {
        frame = new JFrame("Hotel Management System");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        frame.add(new JLabel("Guest Name:"));
        nameField = new JTextField(20);
        frame.add(nameField);

        frame.add(new JLabel("Number of Days:"));
        daysField = new JTextField(5);
        frame.add(daysField);

        frame.add(new JLabel("Room Type (1 for Standard, 2 for Deluxe):"));
        roomField = new JTextField(5);
        frame.add(roomField);

        calculateButton = new JButton("Calculate Total");
        frame.add(calculateButton);

        totalField = new JTextField(10);
        totalField.setEditable(false);
        frame.add(totalField);

        bookButton = new JButton("Book Room");
        frame.add(bookButton);

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea));

        calculateButton.addActionListener(new CalculateAction());
        bookButton.addActionListener(new BookAction());

        frame.setVisible(true);
    }

    private class CalculateAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int days = Integer.parseInt(daysField.getText());
                int roomType = Integer.parseInt(roomField.getText());
                int rate = (roomType == 1) ? 100 : (roomType == 2) ? 150 : 0;

                if (rate == 0) {
                    outputArea.setText("Invalid room type selected.");
                    totalField.setText("");
                    return;
                }

                int totalCost = days * rate;
                totalField.setText(String.valueOf(totalCost));
            } catch (NumberFormatException ex) {
                outputArea.setText("Please enter valid numbers.");
                totalField.setText("");
            }
        }
    }

    private class BookAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String totalCost = totalField.getText();
            if (totalCost.isEmpty()) {
                outputArea.setText("Calculate total cost before booking.");
            } else {
                outputArea.setText("Room booked for " + name + ". Total cost: " + totalCost);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HotelManagementSystem::new);
    }
}
