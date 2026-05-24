package view;

import controller.BookController;
import model.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookForm extends JFrame {

    private BookController bookController;

    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField txtId;
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtStock;

    public BookForm() {

        bookController = new BookController();

        setTitle("Book Management");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        loadTable();

        setVisible(true);
    }

    private void initComponents() {

        setLayout(new BorderLayout(10, 10));


        // TABLE
        tableModel = new DefaultTableModel(
                new String[]{"ID", "Title", "Author", "Stock"},
                0
        );

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);


        // FORM PANEL
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        formPanel.setBorder(
                BorderFactory.createTitledBorder("Book Form")
        );

        txtId = new JTextField();
        txtId.setEnabled(false);

        txtTitle = new JTextField();
        txtAuthor = new JTextField();
        txtStock = new JTextField();

        formPanel.add(new JLabel("ID"));
        formPanel.add(txtId);

        formPanel.add(new JLabel("Title"));
        formPanel.add(txtTitle);

        formPanel.add(new JLabel("Author"));
        formPanel.add(txtAuthor);

        formPanel.add(new JLabel("Stock"));
        formPanel.add(txtStock);


        // BUTTON PANEL
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));

        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);


        // SOUTH PANEL
        JPanel southPanel = new JPanel(new BorderLayout(10, 10));

        southPanel.add(formPanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);


        // BUTTON ACTIONS

        // ADD
        btnAdd.addActionListener(e -> {

            try {

                String title = txtTitle.getText();
                String author = txtAuthor.getText();

                int stock = Integer.parseInt(
                        txtStock.getText()
                );

                boolean success = bookController.addBook(
                        title,
                        author,
                        stock
                );

                JOptionPane.showMessageDialog(
                        this,
                        success ? "Book Added!" : "Failed!"
                );

                loadTable();
                clearForm();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Stock harus angka!"
                );
            }
        });

        // UPDATE
        btnUpdate.addActionListener(e -> {

            try {

                int id = Integer.parseInt(
                        txtId.getText()
                );

                String title = txtTitle.getText();
                String author = txtAuthor.getText();

                int stock = Integer.parseInt(
                        txtStock.getText()
                );

                boolean success = bookController.updateBook(
                        id,
                        title,
                        author,
                        stock
                );

                JOptionPane.showMessageDialog(
                        this,
                        success ? "Book Updated!" : "Failed!"
                );

                loadTable();
                clearForm();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pilih data dulu!"
                );
            }
        });

        // DELETE
        btnDelete.addActionListener(e -> {

            try {

                int id = Integer.parseInt(
                        txtId.getText()
                );

                boolean success = bookController.deleteBook(id);

                JOptionPane.showMessageDialog(
                        this,
                        success ? "Book Deleted!" : "Failed!"
                );

                loadTable();
                clearForm();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pilih data dulu!"
                );
            }
        });

        // CLEAR
        btnClear.addActionListener(e -> clearForm());


        // TABLE CLICK

        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {

                txtId.setText(
                        table.getValueAt(row, 0).toString()
                );

                txtTitle.setText(
                        table.getValueAt(row, 1).toString()
                );

                txtAuthor.setText(
                        table.getValueAt(row, 2).toString()
                );

                txtStock.setText(
                        table.getValueAt(row, 3).toString()
                );
            }
        });
    }


    // LOAD TABLE
    private void loadTable() {

        tableModel.setRowCount(0);

        List<Book> books = bookController.getAllBooks();

        for (Book b : books) {

            tableModel.addRow(new Object[]{
                    b.getId(),
                    b.getTitle(),
                    b.getAuthor(),
                    b.getStock()
            });
        }
    }


    // CLEAR FORM
    private void clearForm() {

        txtId.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtStock.setText("");
    }
}
