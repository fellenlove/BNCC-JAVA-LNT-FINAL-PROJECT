package view;

import model.User;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    private User user;

    public Dashboard(User user) {
        this.user = user;

        setTitle("Dashboard - Sistem Perpustakaan");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {

        setLayout(new BorderLayout());


        // HEADER
        JLabel header = new JLabel("Welcome, " + user.getUsername(), SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);


        // MENU BUTTON PANEL
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnBooks = new JButton("📚 Manage Books");
        JButton btnLoan = new JButton("📖 Borrow Book");
        JButton btnReturn = new JButton("🔄 Return Book");
        JButton btnHistory = new JButton("📜 Loan History");
        JButton btnLogout = new JButton("🚪 Logout");

        panel.add(btnBooks);
        panel.add(btnLoan);
        panel.add(btnReturn);
        panel.add(btnHistory);
        panel.add(btnLogout);

        add(panel, BorderLayout.CENTER);


        // ACTIONS
        btnBooks.addActionListener(e -> {
            new BookForm();
        });

        btnLoan.addActionListener(e -> {
            new LoanForm(user);
        });

        btnReturn.addActionListener(e -> {
            new LoanForm(user);
        });

        btnHistory.addActionListener(e -> {
            new HistoryForm(user);
        });

        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Yakin mau logout?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                new LoginForm();
                this.dispose();
            }
        });
    }
}
