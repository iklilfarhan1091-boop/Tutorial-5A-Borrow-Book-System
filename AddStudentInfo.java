import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddStudentInfo extends JFrame {
    private JLabel lblName;
    private JLabel lblMatric;
    private JLabel lblStudents;
    private JTextField txtName;
    private JTextField txtMatric;
    private JButton btnAdd;
    private JButton btnList;
    private JTextArea txtStudents;

    // Shared list used by the Borrow Book window.
    private static ArrayList<Student> studentList = new ArrayList<Student>();

    public AddStudentInfo() {
        setTitle("Add Student Info");
        setSize(390, 270);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout(8, 8));
        ((JComponent) pane).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Student name and matric input fields.
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 8, 6));
        lblName = new JLabel("Name:");
        txtName = new JTextField(18);
        lblMatric = new JLabel("Matric:");
        txtMatric = new JTextField(18);
        inputPanel.add(lblName);
        inputPanel.add(txtName);
        inputPanel.add(lblMatric);
        inputPanel.add(txtMatric);

        // Add and List buttons.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));
        btnAdd = new JButton("Add");
        btnList = new JButton("List");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnList);

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Registered student list.
        JPanel listPanel = new JPanel(new BorderLayout(5, 5));
        lblStudents = new JLabel("Students:");
        txtStudents = new JTextArea(5, 30);
        txtStudents.setEditable(false);
        txtStudents.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(txtStudents);
        listPanel.add(lblStudents, BorderLayout.NORTH);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        pane.add(topPanel, BorderLayout.NORTH);
        pane.add(listPanel, BorderLayout.CENTER);

        // Add button creates and stores a Student object.
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText().trim();
                String matric = txtMatric.getText().trim();

                if (name.length() == 0 || matric.length() == 0) {
                    JOptionPane.showMessageDialog(AddStudentInfo.this,
                            "Please enter the student name and matric number.");
                    return;
                }

                if (findStudentByMatric(matric) != null) {
                    JOptionPane.showMessageDialog(AddStudentInfo.this,
                            "This matric number is already registered.");
                    return;
                }

                Student student = new Student(name, matric);
                studentList.add(student);
                txtName.setText("");
                txtMatric.setText("");
                txtName.requestFocus();
                JOptionPane.showMessageDialog(AddStudentInfo.this,
                        "Student added successfully.");
            }
        });

        // List button displays all registered students.
        btnList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtStudents.setText("");
                for (int i = 0; i < studentList.size(); i++) {
                    Student student = studentList.get(i);
                    txtStudents.append("Name : " + student.toString() + "\n");
                }
            }
        });
    }

    private Student findStudentByMatric(String matric) {
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getMatric().equalsIgnoreCase(matric)) {
                return student;
            }
        }
        return null;
    }

    public static ArrayList<Student> getStudentList() {
        return studentList;
    }

    public static void main(String[] args) {
        AddStudentInfo frame = new AddStudentInfo();
        frame.setVisible(true);
    }
}
