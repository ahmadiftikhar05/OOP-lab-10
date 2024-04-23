import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentInfo {
    private JTextField tfName;
    private JTextField tfID;
    private JTextField tfEmail;
    private JRadioButton MALERadioButton;
    private JRadioButton FEMALERadioButton;
    private JCheckBox agreeToTermsAndCheckBox;
    private JButton REGISTERButton;
    private JPanel StudentPanel;
    private JTable StudentTable;
    private DefaultTableModel tableModel;

    public StudentInfo() {
        agreeToTermsAndCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Terms and Conditions Accepted!", "Move Forward", JOptionPane.PLAIN_MESSAGE);
            }
        });
        REGISTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerStudent();
            }
        });

        initComponents();
    }

    private void initComponents() {
        // Initialize the table model with column names
        String[] columnNames = {"Name", "ID", "Email", "Gender"};
        tableModel = new DefaultTableModel(0, columnNames.length);
        tableModel.setColumnIdentifiers(columnNames);
        StudentTable.setModel(tableModel); // Set the table model to the JTable component

        // Add a row at index 0 to simulate the table header
        tableModel.addRow(columnNames);
    }



    private void registerStudent() {
        String name = tfName.getText();
        String id = tfID.getText();
        String email = tfEmail.getText();
        String gender = MALERadioButton.isSelected() ? "Male" : "Female";

        if (name.isEmpty() || id.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add the student details to the table model
        Object[] rowData = {name, id, email, gender};
        tableModel.addRow(rowData);

        // Display a dialog confirming registration
        JOptionPane.showMessageDialog(null, "Registered student:\nName: " + name + "\nID: " + id + "\nEmail: " + email + "\nGender: " + gender, "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Student Registration");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new StudentInfo().StudentPanel);
                frame.setMinimumSize(new Dimension(750,474));
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
