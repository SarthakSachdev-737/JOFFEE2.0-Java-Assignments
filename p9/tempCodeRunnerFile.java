import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PhoneCalculator extends JFrame implements ActionListener {
    JTextField num1Field, num2Field, resultField;
    JButton addButton, subtractButton, multiplyButton, divideButton, resetButton, resultButton;
    JLabel num1Label, num2Label;
    private double num1 = 0, num2 = 0;
    private String operation = "";

    public PhoneCalculator() {
        setTitle("Phone Calculator");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        num1Label = new JLabel("Add Number 1:");
        num2Label = new JLabel("Add Number 2:");
        num1Field = new JTextField();
        num2Field = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        topPanel.add(num1Label);
        topPanel.add(num1Field);
        topPanel.add(num2Label);
        topPanel.add(num2Field);
        topPanel.add(new JLabel("Result:"));
        topPanel.add(resultField);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        addButton = new JButton("Add");
        subtractButton = new JButton("Subtract");
        multiplyButton = new JButton("Multiply");
        divideButton = new JButton("Divide");
        resetButton = new JButton("Reset");
        resultButton = new JButton("Result");

        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(divideButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(resultButton);

        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);
        resetButton.addActionListener(this);
        resultButton.addActionListener(this);

        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == resetButton) {
                num1Field.setText("");
                num2Field.setText("");
                resultField.setText("");
                resultField.setForeground(Color.BLACK);
                operation = "";
                return;
            }

            num1 = Double.parseDouble(num1Field.getText());
            num2 = Double.parseDouble(num2Field.getText());
            resultField.setForeground(Color.BLACK);

            if (e.getSource() == addButton) {
                operation = "Add";
                resultField.setText("Addition operation selected");
            } else if (e.getSource() == subtractButton) {
                operation = "Subtract";
                resultField.setText("Subtraction operation selected");
            } else if (e.getSource() == multiplyButton) {
                operation = "Multiply";
                resultField.setText("Multiplication operation selected");
            } else if (e.getSource() == divideButton) {
                operation = "Divide";
                resultField.setText("Division operation selected");
            } else if (e.getSource() == resultButton) {
                if (operation.isEmpty()) {
                    resultField.setForeground(Color.RED);
                    resultField.setText("Error: No operation selected");
                } else {
                    switch (operation) {
                        case "Add":
                            resultField.setText(String.valueOf(num1 + num2));
                            break;
                        case "Subtract":
                            resultField.setText(String.valueOf(num1 - num2));
                            break;
                        case "Multiply":
                            resultField.setText(String.valueOf(num1 * num2));
                            break;
                        case "Divide":
                            if (num2 == 0) {
                                resultField.setForeground(Color.RED);
                                resultField.setText("Error: Cannot divide by zero");
                            } else {
                                resultField.setText(String.valueOf(num1 / num2));
                            }
                            break;
                    }
                    operation = "";
                }
            }
        } catch (NumberFormatException ex) {
            resultField.setForeground(Color.RED);
            resultField.setText("Error: Please enter valid numbers");
        }
    }

    public static void main(String[] args) {
        PhoneCalculator calculator = new PhoneCalculator();
        calculator.setVisible(true);
    }
}
