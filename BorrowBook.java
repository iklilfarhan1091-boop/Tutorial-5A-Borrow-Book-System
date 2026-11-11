import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BorrowBook extends JFrame {

    private JLabel lblStudent;
    private JLabel lblBook;
    private JLabel lblBorrowInfo;

    // Use JComboBox without generic type for older JDK
    private JComboBox cmbStudent;
    private JComboBox cmbBook;

    private JButton btnBorrow;
    private JTextArea txtBorrowInfo;

    public BorrowBook() {

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout(8, 8));

        // Create student and book selection panel
        JPanel formPanel = new JPanel(
            new GridLayout(2, 2, 8, 8)
        );

        lblStudent = new JLabel("Student Name & Matric:");
        lblBook = new JLabel("Book Title & Author:");

        cmbStudent = new JComboBox();
        cmbBook = new JComboBox();

        formPanel.add(lblStudent);
        formPanel.add(cmbStudent);
        formPanel.add(lblBook);
        formPanel.add(cmbBook);

        // Load registered students
        loadStudents();

        // Load registered books
        loadBooks();

        // Create Borrow button
        btnBorrow = new JButton("Borrow");

        JPanel buttonPanel = new JPanel(
            new FlowLayout(FlowLayout.CENTER)
        );

        buttonPanel.add(btnBorrow);

        JPanel topPanel = new JPanel(
            new BorderLayout(5, 5)
        );

        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Create borrow information area
        JPanel infoPanel = new JPanel(
            new BorderLayout(5, 5)
        );

        lblBorrowInfo = new JLabel("Borrow Information:");

        txtBorrowInfo = new JTextArea(3, 35);
        txtBorrowInfo.setEditable(false);
        txtBorrowInfo.setLineWrap(true);
        txtBorrowInfo.setWrapStyleWord(true);

        JScrollPane scrollPane =
            new JScrollPane(txtBorrowInfo);

        infoPanel.add(lblBorrowInfo, BorderLayout.NORTH);
        infoPanel.add(scrollPane, BorderLayout.CENTER);

        // Add panels to container
        pane.add(topPanel, BorderLayout.NORTH);
        pane.add(infoPanel, BorderLayout.CENTER);

        // Handle Borrow button event
        btnBorrow.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Student student =
                    (Student) cmbStudent.getSelectedItem();

                Book book =
                    (Book) cmbBook.getSelectedItem();

                if (student == null || book == null) {

                    JOptionPane.showMessageDialog(
                        BorrowBook.this,
                        "Please register and select a student and a book first."
                    );

                    return;
                }

                if (student.hasBorrowedBook()) {

                    JOptionPane.showMessageDialog(
                        BorrowBook.this,
                        student.getName() +
                        " has already borrowed a book."
                    );

                    return;
                }

                if (book.isBorrowed()) {

                    JOptionPane.showMessageDialog(
                        BorrowBook.this,
                        "This book has already been borrowed."
                    );

                    return;
                }

                if (book.borrowBy(student)) {

                    txtBorrowInfo.setText(
                        student.getName() +
                        " with matric number " +
                        student.getMatric() +
                        " has borrowed a book with title " +
                        book.getTitle() + "."
                    );

                } else {

                    JOptionPane.showMessageDialog(
                        BorrowBook.this,
                        "The borrowing request could not be completed."
                    );
                }
            }
        });
    }

    // Load student objects into ComboBox
    private void loadStudents() {

        ArrayList<Student> students =
            AddStudentInfo.getStudentList();

        for (int i = 0; i < students.size(); i++) {

            cmbStudent.addItem(students.get(i));
        }
    }

    // Load book objects into ComboBox
    private void loadBooks() {

        ArrayList<Book> books =
            AddBookInfo.getBookList();

        for (int i = 0; i < books.size(); i++) {

            cmbBook.addItem(books.get(i));
        }
    }

    public static void main(String[] args) {

        BorrowBook frame = new BorrowBook();

        frame.setTitle("Borrow Book");
        frame.setSize(480, 260);
        frame.setDefaultCloseOperation(
            JFrame.EXIT_ON_CLOSE
        );
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}