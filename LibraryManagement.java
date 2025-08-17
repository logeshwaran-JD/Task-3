import java.util.*;
import java.text.SimpleDateFormat;

public class LibraryManagementSystem {
    private Map<String, Book> books;
    private Map<String, User> users;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    public LibraryManagementSystem() {
        books = new HashMap<>();
        users = new HashMap<>();
        scanner = new Scanner(System.in);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        initializeData();
    }

    private void initializeData() {
        // Add some sample books
        addBook("978-0-7432-7356-5", "To Kill a Mockingbird", "Harper Lee", "Fiction");
        addBook("978-0-452-28423-4", "1984", "George Orwell", "Dystopian Fiction");
        addBook("978-0-7432-4722-4", "The Great Gatsby", "F. Scott Fitzgerald", "Classic");
        addBook("978-0-316-76948-0", "The Catcher in the Rye", "J.D. Salinger", "Coming-of-age");
        addBook("978-0-06-112008-4", "Where the Crawdads Sing", "Delia Owens", "Mystery");

        // Add sample users
        addUser(new Member("M001", "Alice Johnson", "alice@email.com", "555-0101", "01/01/2024"));
        addUser(new Member("M002", "Bob Smith", "bob@email.com", "555-0102", "15/02/2024"));
        addUser(new Librarian("L001", "Carol Wilson", "carol@library.com", "555-0201", "EMP001", "Main Library"));
    }

    public void addBook(String isbn, String title, String author, String genre) {
        Book book = new Book(isbn, title, author, genre);
        books.put(isbn, book);
    }

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public void displayAllBooks() {
        System.out.println("\n=== ALL BOOKS ===");
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }

    public void displayAvailableBooks() {
        System.out.println("\n=== AVAILABLE BOOKS ===");
        boolean hasAvailable = false;
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                System.out.println(book);
                hasAvailable = true;
            }
        }
        if (!hasAvailable) {
            System.out.println("No books available for borrowing.");
        }
    }

    public void displayAllUsers() {
        System.out.println("\n=== ALL USERS ===");
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        for (User user : users.values()) {
            System.out.println(user);
        }
    }

    public void borrowBook() {
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        
        User user = users.get(memberId);
        if (user == null || !(user instanceof Member)) {
            System.out.println("Member not found!");
            return;
        }

        Member member = (Member) user;
        if (!member.canBorrowMore()) {
            System.out.println("Member has reached the maximum borrowing limit!");
            return;
        }

        System.out.print("Enter Book ISBN: ");
        String isbn = scanner.nextLine();
        
        Book book = books.get(isbn);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is already borrowed!");
            return;
        }

        book.setAvailable(false);
        book.setBorrowedBy(memberId);
        
        String currentDate = dateFormat.format(new Date());
        book.setBorrowDate(currentDate);
 
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 14);
        String dueDate = dateFormat.format(cal.getTime());
        book.setDueDate(dueDate);
        
        member.addBorrowedBook(isbn);
        
        System.out.println("Book borrowed successfully!");
        System.out.println("Due date: " + dueDate);
    }

    public void returnBook() {
        System.out.print("Enter Book ISBN: ");
        String isbn = scanner.nextLine();
        
        Book book = books.get(isbn);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (book.isAvailable()) {
            System.out.println("Book is not currently borrowed!");
            return;
        }

        String memberId = book.getBorrowedBy();
        Member member = (Member) users.get(memberId);
    
        book.setAvailable(true);
        book.setBorrowedBy(null);
        book.setBorrowDate(null);
        book.setDueDate(null);
        
        if (member != null) {
            member.removeBorrowedBook(isbn);
        }
        
        System.out.println("Book returned successfully!");
    }

    public void searchBooks() {
        System.out.println("\nSearch by:");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Genre");
        System.out.print("Choose option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine().toLowerCase();
        
        System.out.println("\n=== SEARCH RESULTS ===");
        boolean found = false;
        
        for (Book book : books.values()) {
            boolean matches = false;
            switch (choice) {
                case 1:
                    matches = book.getTitle().toLowerCase().contains(searchTerm);
                    break;
                case 2:
                    matches = book.getAuthor().toLowerCase().contains(searchTerm);
                    break;
                case 3:
                    matches = book.getGenre().toLowerCase().contains(searchTerm);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }
            
            if (matches) {
                System.out.println(book);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No books found matching your search.");
        }
    }

    public void addNewBook() {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        
        if (books.containsKey(isbn)) {
            System.out.println("Book with this ISBN already exists!");
            return;
        }
        
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        
        addBook(isbn, title, author, genre);
        System.out.println("Book added successfully!");
    }

    public void addNewMember() {
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        
        if (users.containsKey(memberId)) {
            System.out.println("User with this ID already exists!");
            return;
        }
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        
        String membershipDate = dateFormat.format(new Date());
        Member member = new Member(memberId, name, email, phone, membershipDate);
        addUser(member);
        
        System.out.println("Member added successfully!");
    }

    public void showMenu() {
        System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
        System.out.println("1. Display All Books");
        System.out.println("2. Display Available Books");
        System.out.println("3. Display All Users");
        System.out.println("4. Add New Book");
        System.out.println("5. Add New Member");
        System.out.println("6. Borrow Book");
        System.out.println("7. Return Book");
        System.out.println("8. Search Books");
        System.out.println("9. Exit");
        System.out.print("Choose an option: ");
    }

    public void run() {
        System.out.println("Welcome to the Library Management System!");
        
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    displayAllBooks();
                    break;
                case 2:
                    displayAvailableBooks();
                    break;
                case 3:
                    displayAllUsers();
                    break;
                case 4:
                    addNewBook();
                    break;
                case 5:
                    addNewMember();
                    break;
                case 6:
                    borrowBook();
                    break;
                case 7:
                    returnBook();
                    break;
                case 8:
                    searchBooks();
                    break;
                case 9:
                    System.out.println("Thank you for using Library Management System!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem lms = new LibraryManagementSystem();
        lms.run();
    }
}
