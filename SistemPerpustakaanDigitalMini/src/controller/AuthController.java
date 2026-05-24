package controller;

import dao.UserDAO;
import model.User;

public class AuthController {

    private UserDAO userDAO;

    public AuthController() {
        userDAO = new UserDAO();
    }


    // REGISTER USER
    public boolean register(String username, String password) {

        // validasi sederhana
        if (username == null || username.isEmpty()) {
            System.out.println("Username tidak boleh kosong!");
            return false;
        }

        if (password == null || password.isEmpty()) {
            System.out.println("Password tidak boleh kosong!");
            return false;
        }

        // cek duplikat
        if (userDAO.isUsernameExists(username)) {
            System.out.println("Username sudah digunakan!");
            return false;
        }

        // kirim ke DAO
        User user = new User(0, username, password);
        return userDAO.register(user);
    }


    // LOGIN USER
    public User login(String username, String password) {

        if (username == null || username.isEmpty()) {
            System.out.println("Username kosong!");
            return null;
        }

        if (password == null || password.isEmpty()) {
            System.out.println("Password kosong!");
            return null;
        }

        return userDAO.login(username, password);
    }
}