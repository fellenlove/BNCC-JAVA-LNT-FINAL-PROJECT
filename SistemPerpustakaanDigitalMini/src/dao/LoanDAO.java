package dao;

import config.DatabaseConnection;
import model.Loan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {

    private Connection connection;

    public LoanDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // =========================
    // PINJAM BUKU
    // =========================
    public boolean borrowBook(int userId, int bookId, Date loanDate) {

        String insertLoan = "INSERT INTO loans (user_id, book_id, loan_date, status) VALUES (?, ?, ?, 'BORROWED')";
        String updateStock = "UPDATE books SET stock = stock - 1 WHERE id = ? AND stock > 0";

        try {

            // 1. kurangi stock dulu
            PreparedStatement ps1 = connection.prepareStatement(updateStock);
            ps1.setInt(1, bookId);

            int stockUpdated = ps1.executeUpdate();

            if (stockUpdated == 0) {
                System.out.println("Book out of stock!");
                return false;
            }

            // 2. insert ke loans
            PreparedStatement ps2 = connection.prepareStatement(insertLoan);
            ps2.setInt(1, userId);
            ps2.setInt(2, bookId);
            ps2.setDate(3, loanDate);

            return ps2.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Borrow Error: " + e.getMessage());
            return false;
        }
    }

    // =========================
    // RETURN BUKU
    // =========================
    public boolean returnBook(int loanId, int bookId) {

        String updateLoan = "UPDATE loans SET status = 'RETURNED', return_date = CURDATE() WHERE id = ?";
        String updateStock = "UPDATE books SET stock = stock + 1 WHERE id = ?";

        try {

            // 1. update status loan
            PreparedStatement ps1 = connection.prepareStatement(updateLoan);
            ps1.setInt(1, loanId);
            ps1.executeUpdate();

            // 2. tambah stock buku
            PreparedStatement ps2 = connection.prepareStatement(updateStock);
            ps2.setInt(1, bookId);

            return ps2.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Return Error: " + e.getMessage());
            return false;
        }
    }

    // =========================
    // HISTORY PEMINJAMAN (ALL)
    // =========================
    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();

        String query = "SELECT * FROM loans";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                loans.add(new Loan(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("book_id"),
                        rs.getDate("loan_date"),
                        rs.getDate("return_date"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Get Loans Error: " + e.getMessage());
        }

        return loans;
    }

    // =========================
    // HISTORY BY USER
    // =========================
    public List<Loan> getLoansByUser(int userId) {
        List<Loan> loans = new ArrayList<>();

        String query = "SELECT * FROM loans WHERE user_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                loans.add(new Loan(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("book_id"),
                        rs.getDate("loan_date"),
                        rs.getDate("return_date"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Get User Loans Error: " + e.getMessage());
        }

        return loans;
    }
}