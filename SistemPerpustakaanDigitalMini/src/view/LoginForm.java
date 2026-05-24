package view;

import controller.AuthController;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;

    private AuthController authController;

    public LoginForm() {
        authController = new AuthController();

        setTitle("Login - Sistem Perpustakaan");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        panel.add(txtUsername);

        panel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        btnLogin = new JButton("Login");
        btnRegister = new JButton("Register");

        panel.add(btnLogin);
        panel.add(btnRegister);

        add(panel);


        // ACTION LOGIN
        btnLogin.addActionListener(e -> {

            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            User user = authController.login(username, password);

            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login Success!");

                // buka dashboard
                new Dashboard(user);

                this.dispose(); // tutup login
            } else {
                JOptionPane.showMessageDialog(this, "Login Failed!");
            }
        });


        // ACTION REGISTER
        btnRegister.addActionListener(e -> {

            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            boolean success = authController.register(username, password);

            if (success) {
                JOptionPane.showMessageDialog(this, "Register Success!");
            } else {
                JOptionPane.showMessageDialog(this, "Register Failed!");
            }
        });
    }
}
