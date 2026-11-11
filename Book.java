public class Book {
    private String title;
    private String author;
    private Student borrower;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.borrower = null;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Student getBorrower() {
        return borrower;
    }

    public boolean isBorrowed() {
        return borrower != null;
    }

    // Allows one student to borrow this one copy of the book.
    public boolean borrowBy(Student student) {
        if (student == null || borrower != null || student.hasBorrowedBook()) {
            return false;
        }

        if (student.borrowBook(this)) {
            borrower = student;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return title + " (" + author + ")";
    }
}
