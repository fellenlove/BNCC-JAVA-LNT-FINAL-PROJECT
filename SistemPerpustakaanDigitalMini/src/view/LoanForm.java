package view;

import controller.BookController;
import controller.LoanController;
import model.Book;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LoanForm extends JFrame {

    private User user;

    private BookController bookController;
    private LoanController loanController;

    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField txtBookId;
    private JTextField txtLoanId;

    public LoanForm(User user) {
        this.user = user;

        bookController = new BookController();
        loanController = new LoanController();

        setTitle("Loan Management");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        loadBooks();

        setVisible(true);
    }

    private void initComponents() {

        setLayout(new BorderLayout());


        // TABLE BUKU
        tableModel = new DefaultTableModel(
                new String[]{"ID", "Title", "Author", "Stock"}, 0
        );

        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);


        // PANEL FORM
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("Book ID"));
        txtBookId = new JTextField();
        panel.add(txtBookId);

        panel.add(new JLabel("Loan ID (for return)"));
        txtLoanId = new JTextField();
        panel.add(txtLoanId);

        JButton btnBorrow = new JButton("Borrow");
        JButton btnReturn = new JButton("Return");
        JButton btnRefresh = new JButton("Refresh");

        panel.add(btnBorrow);
        panel.add(btnReturn);
        panel.add(btnRefresh);

        add(panel, BorderLayout.SOUTH);


        // CLICK TABLE (AUTO FILL BOOK ID)
        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {
                txtBookId.setText(table.getValueAt(row, 0).toString());
            }
        });


        // BORROW ACTION
        btnBorrow.addActionListener(e -> {

            try {
                int bookId = Integer.parseInt(txtBookId.getText());

                boolean success = loanController.borrowBook(user.getId(), bookId);

                JOptionPane.showMessageDialog(this,
                        success ? "Book Borrowed!" : "Borrow Failed!");

                loadBooks();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Book ID!");
            }
        });


        // RETURN ACTION
        btnReturn.addActionListener(e -> {

            try {
                int loanId = Integer.parseInt(txtLoanId.getText());
                int bookId = Integer.parseInt(txtBookId.getText());

                boolean success = loanController.returnBook(loanId, bookId);

                JOptionPane.showMessageDialog(this,
                        success ? "Book Returned!" : "Return Failed!");

                loadBooks();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input!");
            }
        });


        // REFRESH
        btnRefresh.addActionListener(e -> loadBooks());
    }


    // LOAD BOOKS
    private void loadBooks() {

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
}