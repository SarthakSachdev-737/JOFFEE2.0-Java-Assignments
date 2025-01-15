import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrationForm extends JFrame implements ActionListener {
    JLabel nameLabel, rollNoLabel, mobileLabel, emailLabel, dobLabel, courseLabel;
    JTextField nameField, rollNoField, mobileField, emailField;
    JComboBox<String> dayComboBox, monthComboBox, yearComboBox, courseComboBox;
    JButton submitButton;
    JTextArea displayArea;
    JScrollPane scrollPane;

    RegistrationForm() {
        setTitle("Registration Form");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 20);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(120, 20, 150, 20);
        add(nameField);

        rollNoLabel = new JLabel("Roll No:");
        rollNoLabel.setBounds(20, 50, 100, 20);
        add(rollNoLabel);
        rollNoField = new JTextField();
        rollNoField.setBounds(120, 50, 150, 20);
        add(rollNoField);

        mobileLabel = new JLabel("Mobile No:");
        mobileLabel.setBounds(20, 80, 100, 20);
        add(mobileLabel);
        mobileField = new JTextField();
        mobileField.setBounds(120, 80, 150, 20);
        add(mobileField);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 110, 100, 20);
        add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(120, 110, 150, 20);
        add(emailField);

        dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(20, 140, 100, 20);
        add(dobLabel);
        String[] days = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
        String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        String[] years = { "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010" };
        dayComboBox = new JComboBox<>(days);
        monthComboBox = new JComboBox<>(months);
        yearComboBox = new JComboBox<>(years);
        dayComboBox.setBounds(120, 140, 50, 20);
        monthComboBox.setBounds(180, 140, 70, 20);
        yearComboBox.setBounds(260, 140, 70, 20);
        add(dayComboBox);
        add(monthComboBox);
        add(yearComboBox);

        courseLabel = new JLabel("Course:");
        courseLabel.setBounds(20, 170, 100, 20);
        add(courseLabel);
        String[] courses = { "Select Course", "Computer Science", "Mechanical Engineering", "Electrical Engineering", "Civil Engineering", "Chemical Engineering"};
        courseComboBox = new JComboBox<>(courses);
        courseComboBox.setBounds(120, 170, 150, 20);
        add(courseComboBox);

        submitButton = new JButton("Submit");
        submitButton.setBounds(20, 210, 100, 30);
        add(submitButton);
        submitButton.addActionListener(this);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setForeground(Color.BLACK);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 14));
        scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(350, 20, 300, 400);
        add(scrollPane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String rollNo = rollNoField.getText();
        String mobile = mobileField.getText();
        String email = emailField.getText();
        String dob = dayComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() + "-" + yearComboBox.getSelectedItem();
        String selectedCourse = (String) courseComboBox.getSelectedItem();

        StringBuilder errorBuilder = new StringBuilder();
        StringBuilder detailsBuilder = new StringBuilder();

        if (name.isEmpty()) errorBuilder.append("Error: Name cannot be empty.\n");
        if (rollNo.isEmpty()) errorBuilder.append("Error: Roll No cannot be empty.\n");
        if (mobile.isEmpty() || !mobile.matches("\\d{10}")) errorBuilder.append("Error: Enter a valid 10-digit Mobile No.\n");
        if (email.isEmpty() || !email.contains("@")) errorBuilder.append("Error: Enter a valid Email ID.\n");
        if (selectedCourse.equals("Select Course")) errorBuilder.append("Error: Please select a course.\n");

        if (errorBuilder.length() > 0) {
            displayArea.setText(errorBuilder.toString());
            displayArea.setForeground(Color.RED);
        } else {
            detailsBuilder.append("Registration Successful!\n\n");
            detailsBuilder.append("Name: ").append(name).append("\n");
            detailsBuilder.append("Roll No: ").append(rollNo).append("\n");
            detailsBuilder.append("Mobile No: ").append(mobile).append("\n");
            detailsBuilder.append("Email: ").append(email).append("\n");
            detailsBuilder.append("Date of Birth: ").append(dob).append("\n");
            detailsBuilder.append("Course: ").append(selectedCourse).append("\n");
            displayArea.setText(detailsBuilder.toString());
            displayArea.setForeground(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        new RegistrationForm();
    }
}
