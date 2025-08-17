public class Member extends User {
    private java.util.List<String> borrowedBooks;
    private String membershipDate;
    private int maxBooksAllowed;

    public Member(String userId, String name, String email, String phone, String membershipDate) {
        super(userId, name, email, phone);
        this.borrowedBooks = new java.util.ArrayList<>();
        this.membershipDate = membershipDate;
        this.maxBooksAllowed = 3; // Default limit
    }

    @Override
    public String getUserType() {
        return "Member";
    }

    public java.util.List<String> getBorrowedBooks() { return borrowedBooks; }
    public String getMembershipDate() { return membershipDate; }
    public int getMaxBooksAllowed() { return maxBooksAllowed; }

    public boolean canBorrowMore() {
        return borrowedBooks.size() < maxBooksAllowed;
    }

    public void addBorrowedBook(String isbn) {
        borrowedBooks.add(isbn);
    }

    public void removeBorrowedBook(String isbn) {
        borrowedBooks.remove(isbn);
    }
}

