public class Student {
    private String name;
    private String matric;
    private Book borrowedBook;

    public Student(String name, String matric) {
        this.name = name;
        this.matric = matric;
        this.borrowedBook = null;
    }

    public String getName() {
        return name;
    }

    public String getMatric() {
        return matric;
    }

    public Book getBorrowedBook() {
        return borrowedBook;
    }

    public boolean hasBorrowedBook() {
        return borrowedBook != null;
    }

    // Records the book borrowed by this student.
    public boolean borrowBook(Book book) {
        if (book == null || borrowedBook != null) {
            return false;
        }
        borrowedBook = book;
        return true;
    }

    @Override
    public String toString() {
        return name + " (" + matric + ")";
    }
}
