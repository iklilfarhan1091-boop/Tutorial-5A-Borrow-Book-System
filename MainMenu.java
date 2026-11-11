import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {

    private JLabel title;
    private JButton btnStudent;
    private JButton btnBook;
    private JButton btnBorrow;

    public MainMenu() {

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout(5, 5));

        title = new JLabel("~~ BORROW BOOK SYSTEM ~~");
        title.setHorizontalAlignment(JLabel.CENTER);

        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));

        btnStudent = new JButton("Add Student Info");
        btnBook = new JButton("Add Book Info");
        btnBorrow = new JButton("Borrow Book");

        panel.add(btnStudent);
        panel.add(btnBook);
        panel.add(btnBorrow);

        pane.add(title, BorderLayout.NORTH);
        pane.add(panel, BorderLayout.CENTER);

        // Open Add Student Info
        btnStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                AddStudentInfo frame = new AddStudentInfo();

                frame.setTitle("Add Student Info");
                frame.setSize(380, 250);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(MainMenu.this);
                frame.setVisible(true);
            }
        });

        // Open Add Book Info
        btnBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                AddBookInfo frame = new AddBookInfo();

                frame.setTitle("Add Book Info");
                frame.setSize(390, 270);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(MainMenu.this);
                frame.setVisible(true);
            }
        });

        // Open Borrow Book
        btnBorrow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                BorrowBook frame = new BorrowBook();

                frame.setTitle("Borrow Book");
                frame.setSize(480, 260);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(MainMenu.this);
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {

        MainMenu frame = new MainMenu();

        frame.setTitle("Main Menu");
        frame.setSize(260, 230);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}