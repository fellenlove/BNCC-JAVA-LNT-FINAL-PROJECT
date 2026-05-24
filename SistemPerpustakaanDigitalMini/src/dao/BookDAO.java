package dao;

import config.DatabaseConnection;
import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private Connection connection;

    public BookDAO() {
        connection = DatabaseConnection.getConnection();
    }


    // CREATE - TAMBAH BUKU
    public boolean addBook(Book book) {
        String query = "INSERT INTO books (title, author, stock) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getStock());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Add Book Error: " + e.getMessage());
            return false;
        }
    }


    // READ - AMBIL SEMUA BUKU
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        String query = "SELECT * FROM books";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("stock")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Get Books Error: " + e.getMessage());
        }

        return books;
    }

 
    // UPDATE BUKU
    public boolean updateBook(Book book) {
        String query = "UPDATE books SET title = ?, author = ?, stock = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getStock());
            ps.setInt(4, book.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update Book Error: " + e.getMessage());
            return false;
        }
    }


    // DELETE BUKU
    public boolean deleteBook(int id) {
        String query = "DELETE FROM books WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Delete Book Error: " + e.getMessage());
            return false;
        }
    }


    // SEARCH BUKU BY ID
    public Book getBookById(int id) {
        String query = "SELECT * FROM books WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            System.out.println("Get Book By ID Error: " + e.getMessage());
        }

        return null;
    }
}