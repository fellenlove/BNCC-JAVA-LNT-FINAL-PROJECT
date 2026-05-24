package controller;

import dao.LoanDAO;
import model.Loan;

import java.sql.Date;
import java.util.List;

public class LoanController {

    private LoanDAO loanDAO;

    public LoanController() {
        loanDAO = new LoanDAO();
    }


    // PINJAM BUKU
    public boolean borrowBook(int userId, int bookId) {

        // validasi sederhana
        if (userId <= 0 || bookId <= 0) {
            System.out.println("User ID atau Book ID tidak valid!");
            return false;
        }

        Date today = new Date(System.currentTimeMillis());

        return loanDAO.borrowBook(userId, bookId, today);
    }


    // KEMBALIKAN BUKU
    public boolean returnBook(int loanId, int bookId) {

        if (loanId <= 0 || bookId <= 0) {
            System.out.println("Loan ID atau Book ID tidak valid!");
            return false;
        }

        return loanDAO.returnBook(loanId, bookId);
    }


    // AMBIL SEMUA HISTORY
    public List<Loan> getAllLoans() {
        return loanDAO.getAllLoans();
    }


    // HISTORY PER USER
    public List<Loan> getLoansByUser(int userId) {

        if (userId <= 0) {
            System.out.println("User ID tidak valid!");
            return null;
        }

        return loanDAO.getLoansByUser(userId);
    }
}