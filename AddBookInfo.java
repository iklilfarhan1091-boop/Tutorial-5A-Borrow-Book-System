import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddBookInfo extends JFrame {
    private JLabel lblTitle;
    private JLabel lblAuthor;
    private JLabel lblBooks;
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JButton btnAdd;
    private JButton btnList;
    private JTextArea txtBooks;

    // Shared list used by the Borrow Book window.
    private static ArrayList<Book> bookList = new ArrayList<Book>();

    public AddBookInfo() {
        setTitle("Add Book Info");
        setSize(390, 270);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout(8, 8));
        ((JComponent) pane).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Book title and author input fields.
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 8, 6));
        lblTitle = new JLabel("Book Title:");
        txtTitle = new JTextField(18);
        lblAuthor = new JLabel("Author:");
        txtAuthor = new JTextField(18);
        inputPanel.add(lblTitle);
        inputPanel.add(txtTitle);
        inputPanel.add(lblAuthor);
        inputPanel.add(txtAuthor);

        // Add and List buttons.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));
        btnAdd = new JButton("Add");
        btnList = new JButton("List");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnList);

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Registered book list.
        JPanel listPanel = new JPanel(new BorderLayout(5, 5));
        lblBooks = new JLabel("Books:");
        txtBooks = new JTextArea(5, 30);
        txtBooks.setEditable(false);
        txtBooks.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(txtBooks);
        listPanel.add(lblBooks, BorderLayout.NORTH);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        pane.add(topPanel, BorderLayout.NORTH);
        pane.add(listPanel, BorderLayout.CENTER);

        // Add button creates and stores a Book object.
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = txtTitle.getText().trim();
                String author = txtAuthor.getText().trim();

                if (title.length() == 0 || author.length() == 0) {
                    JOptionPane.showMessageDialog(AddBookInfo.this,
                            "Please enter the book title and author.");
                    return;
                }

                if (findBookByTitle(title) != null) {
                    JOptionPane.showMessageDialog(AddBookInfo.this,
                            "This book title is already registered.");
                    return;
                }

                Book book = new Book(title, author);
                bookList.add(book);
                txtTitle.setText("");
                txtAuthor.setText("");
                txtTitle.requestFocus();
                JOptionPane.showMessageDialog(AddBookInfo.this,
                        "Book added successfully.");
            }
        });

        // List button displays all registered books.
        btnList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtBooks.setText("");
                for (int i = 0; i < bookList.size(); i++) {
                    Book book = bookList.get(i);
                    txtBooks.append("Book title : " + book.getTitle()
                            + " (Author : " + book.getAuthor() + ")\n");
                }
            }
        });
    }

    private Book findBookByTitle(String title) {
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public static ArrayList<Book> getBookList() {
        return bookList;
    }

    public static void main(String[] args) {
        AddBookInfo frame = new AddBookInfo();
        frame.setVisible(true);
    }
}
