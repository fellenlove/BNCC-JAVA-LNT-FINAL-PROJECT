package model;

import java.sql.Date;

public class Loan {
    private int id;
    private int userId;
    private int bookId;
    private Date loanDate;
    private Date returnDate;
    private String status;

    public Loan() {}

    public Loan(int id, int userId, int bookId, Date loanDate, Date returnDate, String status) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getBookId() { return bookId; }
    public Date getLoanDate() { return loanDate; }
    public Date getReturnDate() { return returnDate; }
    public String getStatus() { return status; }
}