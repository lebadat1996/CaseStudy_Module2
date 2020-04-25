package EmployeeManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import Model.User.User;

public class Login {
    private JTextField txtUser;
    private JTextField txtPassword;
    private JPanel Login;
    private JButton resetButton;
    private JButton loginButton;
    private JButton exitButton;
    private JButton btnRegistration;
    private JTextField txtRePassword;
    DefaultTableModel model;
    List<User> userList = new LinkedList<User>();

    public Login() {
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUser.setText("");
                txtPassword.setText("");
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFile();
                loginUser();
            }
        });
        btnRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register register = new Register();
                Register.main(null);
            }
        });
    }

    private void loginUser() {
        User user = new User();
        String name = user.setUser(txtUser.getText());
        String pass = user.setPassword(txtPassword.getText());
        String rePass = user.setRePassword(txtRePassword.getText());
        if (name.equals("") && pass.equals("") && rePass.equals("")) {
            JOptionPane.showMessageDialog(null, "Login No success");
        } else {
            for (int j = 0; j < userList.size(); j++) {
                if (name.equals(userList.get(j).getUser()) || pass.equals(userList.get(j).getPassword())
                        || rePass.equals(userList.get(j).getRePassword())) {
                    JOptionPane.showMessageDialog(null, "Login success");
                    txtUser.setText(null);
                    txtPassword.setText(null);
                    txtRePassword.setText(null);
                    EmployeeManager login = new EmployeeManager();
                    EmployeeManager.main(null);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Enter other User");
        }
    }

    private void readFile() {
        String path = "InfoUser.txt";
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            objectInputStream = new ObjectInputStream(fileInputStream);
            while (true) {
                User user = (User) objectInputStream.readObject();
                userList.add(user);
            }
        } catch (Exception e) {
        } finally {
            try {
                objectInputStream.close();
                fileInputStream.close();
            } catch (Exception e) {
            }

        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(400, 200);
        frame.setContentPane(new Login().Login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
