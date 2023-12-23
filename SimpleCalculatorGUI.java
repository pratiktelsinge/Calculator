import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculatorGUI extends JFrame {
    // Components
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton;
    private JButton clearButton;

    // Variables
    private double num1, num2, result;
    private char operator;

    public SimpleCalculatorGUI() {
        // Initialize frame
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());

        // Display
        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setPreferredSize(new Dimension(300, 60));
        display.setFont(new Font("Arial", Font.PLAIN, 30));

        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Number buttons
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createButton(String.valueOf(i));
            numberButtons[i].addActionListener(new NumberButtonListener());
        }

        // Operation buttons
        operationButtons = new JButton[4];
        operationButtons[0] = createButton("+");
        operationButtons[1] = createButton("-");
        operationButtons[2] = createButton("x");
        operationButtons[3] = createButton("/");
        for (JButton button : operationButtons) {
            button.addActionListener(new OperationButtonListener());
        }

        // Equals button
        equalsButton = createButton("=");
        equalsButton.addActionListener(new EqualsButtonListener());

        // Clear button
        clearButton = createButton("C");
        clearButton.addActionListener(new ClearButtonListener());

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5)); // Adjusted the layout with some spacing

        // Add buttons to panel
        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(operationButtons[0]);
        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(operationButtons[1]);
        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(operationButtons[2]);
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(clearButton);
        buttonPanel.add(equalsButton);
        buttonPanel.add(operationButtons[3]);

        // Add button panel to frame
        add(buttonPanel, BorderLayout.CENTER);

        // Set frame visibility
        setVisible(true);
    }

    // Method to create styled buttons
    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setPreferredSize(new Dimension(60, 60)); // Adjust the preferred size
        button.setBackground(new Color(229, 204, 255)); // Light orange colo
        button.setFont(new Font("Arial", Font.BOLD, 16));
        return button;
    }
       
    // ActionListener for number buttons
    private class NumberButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            display.setText(display.getText() + source.getText());
        }
    }

    // ActionListener for operation buttons
    private class OperationButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            num1 = Double.parseDouble(display.getText());
            operator = source.getText().charAt(0);
            display.setText("");
        }
    }

    // ActionListener for equals button
    private class EqualsButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case 'x':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
        }
    }

    // ActionListener for clear button
    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num1 = 0;
            num2 = 0;
            operator = '\0';
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleCalculatorGUI());
    }
}
