public class Book {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;
    private String borrowedBy;
    private String borrowDate;
    private String dueDate;

    public Book(String isbn, String title, String author, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = true;
        this.borrowedBy = null;
        this.borrowDate = null;
        this.dueDate = null;
    }

    
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public boolean isAvailable() { return isAvailable; }
    public String getBorrowedBy() { return borrowedBy; }
    public String getBorrowDate() { return borrowDate; }
    public String getDueDate() { return dueDate; }

    public void setAvailable(boolean available) { this.isAvailable = available; }
    public void setBorrowedBy(String borrowedBy) { this.borrowedBy = borrowedBy; }
    public void setBorrowDate(String borrowDate) { this.borrowDate = borrowDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    @Override
    public String toString() {
        return String.format("ISBN: %s | Title: %s | Author: %s | Genre: %s | Available: %s",
                isbn, title, author, genre, isAvailable ? "Yes" : "No");
    }
}

