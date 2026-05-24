package view;

import controller.LoanController;
import model.Loan;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoryForm extends JFrame {

    private User user;
    private LoanController loanController;

    private JTable table;
    private DefaultTableModel tableModel;

    public HistoryForm(User user) {
        this.user = user;

        loanController = new LoanController();

        setTitle("Loan History");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        loadHistory();

        setVisible(true);
    }

    private void initComponents() {

        setLayout(new BorderLayout());


        // TABLE
        tableModel = new DefaultTableModel(
                new String[]{
                        "ID",
                        "User ID",
                        "Book ID",
                        "Loan Date",
                        "Return Date",
                        "Status"
                }, 0
        );

        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);


        // BUTTON PANEL
        JPanel panel = new JPanel();

        JButton btnRefresh = new JButton("Refresh");
        JButton btnMyHistory = new JButton("My History");

        panel.add(btnRefresh);
        panel.add(btnMyHistory);

        add(panel, BorderLayout.SOUTH);


        // ACTIONS
        btnRefresh.addActionListener(e -> loadHistory());

        btnMyHistory.addActionListener(e -> loadMyHistory());
    }


    // ALL HISTORY
    private void loadHistory() {

        tableModel.setRowCount(0);

        List<Loan> loans = loanController.getAllLoans();

        for (Loan l : loans) {
            tableModel.addRow(new Object[]{
                    l.getId(),
                    l.getUserId(),
                    l.getBookId(),
                    l.getLoanDate(),
                    l.getReturnDate(),
                    l.getStatus()
            });
        }
    }


    // FILTER BY USER
    private void loadMyHistory() {

        tableModel.setRowCount(0);

        List<Loan> loans = loanController.getLoansByUser(user.getId());

        for (Loan l : loans) {
            tableModel.addRow(new Object[]{
                    l.getId(),
                    l.getUserId(),
                    l.getBookId(),
                    l.getLoanDate(),
                    l.getReturnDate(),
                    l.getStatus()
            });
        }
    }
}