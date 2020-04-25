package EmployeeManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ShowInfo extends JFrame{

    private JPanel ShowInfo;
    private JTable table1;
    private JButton button1;
    DefaultTableModel tableModel;
    public ShowInfo() {
        table1.setModel(new DefaultTableModel(null, new String[]{"ID","Name", "Date of Birth", "Address", "EmployeeCode", "Phone",
                "Email", "OverTime", "BasicSalary", "Salary", "Gender"}));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReaderFile();
            }
        });
    }
    private void ReaderFile() {
        String filePath = "InfoEmployee.txt";
        File file = new File(filePath);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            tableModel = (DefaultTableModel) table1.getModel();
            Object[] line = bfr.lines().toArray();
            for (int j = 0; j < line.length; j++) {
                String[] row = line[j].toString().split(" ");
                tableModel.addRow(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("ShowInfo");
        frame.setSize(700,400);
        frame.setContentPane(new ShowInfo().ShowInfo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
