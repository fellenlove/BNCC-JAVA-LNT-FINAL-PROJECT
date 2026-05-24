package controller;

import dao.BookDAO;
import model.Book;

import java.util.List;

public class BookController {

    private BookDAO bookDAO;

    public BookController() {
        bookDAO = new BookDAO();
    }


    // TAMBAH BUKU
    public boolean addBook(String title, String author, int stock) {

        // validasi input
        if (title == null || title.isEmpty()) {
            System.out.println("Judul tidak boleh kosong!");
            return false;
        }

        if (author == null || author.isEmpty()) {
            System.out.println("Author tidak boleh kosong!");
            return false;
        }

        if (stock < 0) {
            System.out.println("Stock tidak boleh negatif!");
            return false;
        }

        Book book = new Book(0, title, author, stock);
        return bookDAO.addBook(book);
    }


    // AMBIL SEMUA BUKU
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }


    // UPDATE BUKU
    public boolean updateBook(int id, String title, String author, int stock) {

        if (id <= 0) {
            System.out.println("ID tidak valid!");
            return false;
        }

        Book book = new Book(id, title, author, stock);
        return bookDAO.updateBook(book);
    }


    // DELETE BUKU
    public boolean deleteBook(int id) {

        if (id <= 0) {
            System.out.println("ID tidak valid!");
            return false;
        }

        return bookDAO.deleteBook(id);
    }


    // GET BOOK BY ID
    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }
}
