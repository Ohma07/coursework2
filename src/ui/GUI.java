package ui;

import service.AttendanceService;
import service.FileService;
import model.AttendanceRecord;
import model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;

public class GUI {

    private AttendanceService service = new AttendanceService();

    private JFrame frame;
    private JTextField idField, nameField, emailField, indexField;
    private JComboBox<String> typeBox;

    private JTable table;
    private DefaultTableModel model;

    public GUI() {

        // 🔥 LOAD DATA ON START (IMPORTANT)
        service.setRecords(FileService.load());

        frame = new JFrame("Attendance Tracker");
        frame.setSize(750, 500);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        idField = new JTextField(6);
        nameField = new JTextField(10);
        emailField = new JTextField(15);
        indexField = new JTextField(5);

        typeBox = new JComboBox<>(new String[]{
                "CLOCK_IN", "CLOCK_OUT", "ABSENT", "LATE"
        });

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "Index", "ID", "Name", "Email", "Type", "Time"
        });

        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(700, 200));

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton viewBtn = new JButton("Refresh");

        frame.add(new JLabel("Index:"));
        frame.add(indexField);

        frame.add(new JLabel("ID:"));
        frame.add(idField);

        frame.add(new JLabel("Name:"));
        frame.add(nameField);

        frame.add(new JLabel("Email:"));
        frame.add(emailField);

        frame.add(new JLabel("Type:"));
        frame.add(typeBox);

        frame.add(addBtn);
        frame.add(updateBtn);
        frame.add(deleteBtn);
        frame.add(viewBtn);

        frame.add(scroll);

        // ADD
        addBtn.addActionListener(e -> {
            try {
                Employee emp = new Employee(
                        Integer.parseInt(idField.getText()),
                        nameField.getText(),
                        emailField.getText()
                );

                AttendanceRecord r = new AttendanceRecord(
                        emp,
                        (String) typeBox.getSelectedItem(),
                        LocalDateTime.now()
                );

                service.addRecord(r);
                FileService.save(service.getRecords());

                refreshTable();
                clearFields();

            } catch (Exception ex) {
                System.out.println("Add error");
            }
        });

        // UPDATE
        updateBtn.addActionListener(e -> {
            try {
                int index = Integer.parseInt(indexField.getText());

                Employee emp = new Employee(
                        Integer.parseInt(idField.getText()),
                        nameField.getText(),
                        emailField.getText()
                );

                AttendanceRecord r = new AttendanceRecord(
                        emp,
                        (String) typeBox.getSelectedItem(),
                        LocalDateTime.now()
                );

                service.updateRecord(index, r);
                FileService.save(service.getRecords());

                refreshTable();
                clearFields();

            } catch (Exception ex) {
                System.out.println("Update error");
            }
        });

        // DELETE
        deleteBtn.addActionListener(e -> {
            try {
                int index = Integer.parseInt(indexField.getText());

                service.deleteRecord(index);
                FileService.save(service.getRecords());

                refreshTable();
                clearFields();

            } catch (Exception ex) {
                System.out.println("Delete error");
            }
        });

        // VIEW
        viewBtn.addActionListener(e -> refreshTable());

        frame.setVisible(true);

        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);

        for (int i = 0; i < service.getRecords().size(); i++) {
            AttendanceRecord r = service.getRecords().get(i);

            model.addRow(new Object[]{
                    i,
                    r.getEmployee().getId(),
                    r.getEmployee().getName(),
                    r.getEmployee().getEmail(),
                    r.getAttendanceType(),
                    r.getTime()
            });
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        indexField.setText("");
        typeBox.setSelectedIndex(0);
    }
}
