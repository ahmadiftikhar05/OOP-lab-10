import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContactInfo {
    private JTextField tfName;
    private JTextField tfPhoneNumber;
    private JTextField tfEmail;
    private JButton editContactButton;
    private JButton deleteContactButton;
    private JButton registerContactButton;
    private JPanel ContactBook;
    private JTable ContactsTable;
    private DefaultTableModel tableModel;

    public ContactInfo() {
        registerContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        editContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editContact();
            }
        });

        deleteContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteContact();
            }
        });

        initializeTable();
        setupTableSelectionListener();
    }

    private void initializeTable() {
        String[] columnNames = {"Name", "Phone Number", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        ContactsTable.setModel(tableModel);

        // Initialize the first row with column identifiers
        tableModel.addRow(columnNames);
    }

    private void setupTableSelectionListener() {
        ContactsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = ContactsTable.getSelectedRow();
                if (selectedRow != -1 && selectedRow != 0) { // Exclude the first row (column identifiers)
                    tfName.setText((String) tableModel.getValueAt(selectedRow, 0));
                    tfPhoneNumber.setText((String) tableModel.getValueAt(selectedRow, 1));
                    tfEmail.setText((String) tableModel.getValueAt(selectedRow, 2));
                }
            }
        });
    }

    private void addContact() {
        String name = tfName.getText();
        String phoneNumber = tfPhoneNumber.getText();
        String email = tfEmail.getText();

        if (name.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] rowData = {name, phoneNumber, email};
        tableModel.addRow(rowData);

        clearFields();
    }

    private void editContact() {
        int selectedRow = ContactsTable.getSelectedRow();
        if (selectedRow == -1 || selectedRow == 0) {
            JOptionPane.showMessageDialog(null, "Please select a contact to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = tfName.getText();
        String phoneNumber = tfPhoneNumber.getText();
        String email = tfEmail.getText();

        if (name.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.setValueAt(name, selectedRow, 0);
        tableModel.setValueAt(phoneNumber, selectedRow, 1);
        tableModel.setValueAt(email, selectedRow, 2);

        clearFields();
    }

    private void deleteContact() {
        int selectedRow = ContactsTable.getSelectedRow();
        if (selectedRow == -1 || selectedRow == 0) {
            JOptionPane.showMessageDialog(null, "Please select a contact to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.removeRow(selectedRow);
    }

    private void clearFields() {
        tfName.setText("");
        tfPhoneNumber.setText("");
        tfEmail.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Contact Book");
        frame.setContentPane(new ContactInfo().ContactBook);
        frame.setMinimumSize(new Dimension(750,474));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
