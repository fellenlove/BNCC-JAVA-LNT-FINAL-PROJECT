import config.DatabaseConnection;
import dao.LoanDAO;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {

        DatabaseConnection.getConnection();

        LoanDAO loanDAO = new LoanDAO();

        // =========================
        // TEST PINJAM BUKU
        // =========================
        boolean borrow = loanDAO.borrowBook(
                1, // user_id
                1, // book_id
                new Date(System.currentTimeMillis())
        );

        System.out.println("Borrow Success? " + borrow);

        // =========================
        // TEST RETURN BUKU
        // =========================
        boolean returned = loanDAO.returnBook(1, 1);

        System.out.println("Return Success? " + returned);
    }
}