# 📚 Sistem Perpustakaan Digital Mini

Sistem Perpustakaan Digital Mini adalah aplikasi desktop berbasis Java yang dibuat untuk membantu proses pengelolaan perpustakaan sekolah secara sederhana dan efisien. Aplikasi ini mendukung fitur login user, CRUD buku, peminjaman dan pengembalian buku, serta history peminjaman menggunakan GUI Java Swing dan database MySQL.

Project ini dibuat sebagai Final Project Java Programming.

---

# 👥 Kelompok

## Tajir Melintir

## Anggota Kelompok
1. Dewani Dellaenza
2. Jildzian Christian
3. I Gusti Gede Agung Angga

---

# 🚀 Fitur Utama

## 👤 Authentication
- Register User
- Login User

## 📚 Book Management
- Tambah buku
- Lihat daftar buku
- Edit data buku
- Hapus buku

## 📖 Loan Management
- Pinjam buku
- Return buku
- Update stock otomatis

## 📜 Loan History
- Melihat seluruh history peminjaman
- Melihat history berdasarkan user login

## 🖥️ GUI
- Dashboard menu
- JTable untuk menampilkan data
- Popup dialog menggunakan JOptionPane

---

# 🛠️ Tools yang Digunakan

- Java
- Java Swing
- MySQL (Nama DB: library_db)
- JDBC
- Visual Studio Code

---

# 🗂️ Struktur Folder

```text
SISTEMPERPUSTAKAANDIGITALMINI/
│
├── src/
│   ├── config/
│   │   └── DatabaseConnection.java
│   │
│   ├── model/
│   │   ├── User.java
│   │   ├── Book.java
│   │   └── Loan.java
│   │
│   ├── dao/
│   │   ├── UserDAO.java
│   │   ├── BookDAO.java
│   │   └── LoanDAO.java
│   │
│   ├── controller/
│   │   ├── AuthController.java
│   │   ├── BookController.java
│   │   └── LoanController.java
│   │
│   ├── view/
│   │   ├── LoginForm.java
│   │   ├── Dashboard.java
│   │   ├── BookForm.java
│   │   ├── LoanForm.java
│   │   └── HistoryForm.java
│   │
│   └── Main.java
│
├── lib/
│   └── mysql-connector-j.jar
│
└── database.sql
