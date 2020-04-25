package EmployeeManager;

import Model.User.User;
import EmployeeManager.EmployeeManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public class Register {
    private JTextField textName;
    private JTextField txtPass;
    private JTextField txtRePass;
    private JButton btnRegister;
    private JPanel Register;
    List<User> List = new LinkedList<User>();

    public Register() {
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
                WriterFile();
            }
        });
    }

    public void registerUser() {
        String idName = "[A-Za-z]{5,10}\\d{2,4}$";
        String name = textName.getText();
        String idPass = "[a-z]{5,7}\\d{4}$";
        String pass = txtPass.getText();
        String idRepass = "[a-z]{5,7}\\d{4}$";
        String rePass = txtRePass.getText();
        User user = new User(name, pass, rePass);
        if (name.matches(idName) && pass.matches(idPass) && rePass.matches(idRepass) && pass.equals(rePass)) {
            List.add(user);
            JOptionPane.showMessageDialog(null, "Registration success");
        }else {
            JOptionPane.showMessageDialog(null, "Registration  no success");
        }
    }
    private void WriterFile() {
        String filePath = "InfoUser.txt";
        User user = new User();
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (int i = 0; i < List.size(); i++) {
                objectOutputStream.writeObject(List.get(i));
            }

        } catch (Exception e) {
        } finally {
            try {
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (Exception e) {

            }
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Register");
        frame.setSize(300, 200);
        frame.setContentPane(new Register().Register);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
