package EmployeeManager;

import Model.Employee.Employee;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EmployeeManager extends JFrame {
    private JTextField txtDob;
    private JTextField txtAddress;
    private JTextField txtEmployeeCode;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JTextField txtOverTime;
    private JTextField txtSalaryBasic;
    private JTextField txtName;
    private JRadioButton btnMale;
    private JRadioButton btnFemale;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDeletes;
    private JButton btnSort;
    private JButton btnReset;
    private JTextField txtSearch;
    private JPanel EmployeeManager;
    private JTable table1;
    private JRadioButton btnOther;
    private JButton btnDisplay;
    private JPanel JpDoB;
    List<Employee> employeeList = new ArrayList<>();
    DefaultTableModel tableModel;
    private int i = 0;
    Calendar cld = Calendar.getInstance();
    JDateChooser chooser = new JDateChooser(cld.getTime());

    public EmployeeManager() {
        chooser.setDateFormatString("dd/MM/yyyy");
        JpDoB.add(chooser);
        table1.setModel(new DefaultTableModel(null, new String[]{"ID", "Name", "Date of Birth", "Address", "EmployeeCode", "Phone",
                "Email", "OverTime", "BasicSalary", "Salary", "Gender"}));
        String[][] data = {{"1", "lebadat", "2-6-1996", "trungtrac-vanlam-hungyen", "B14DCDT208", "09-88888888",
                "lebadat@gmail.com", "3", "5000000", "5600000.0", "Male"}};
        tableModel =(DefaultTableModel)table1.getModel();
        for (String [] row : data){
            tableModel.addRow(row);
        }
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btnAdd();
                    WriterFile();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "login no Success");
                }
            }
        });
        btnMale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnMale.isSelected()) {
                    btnFemale.setSelected(false);
                    btnOther.setSelected(false);
                }
            }
        });
        btnFemale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnFemale.isSelected()) {
                    btnMale.setSelected(false);
                    btnOther.setSelected(false);
                }

            }
        });
        btnOther.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnOther.isSelected()) {
                    btnMale.setSelected(false);
                    btnFemale.setSelected(false);
                }
            }
        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        });
        btnSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setAutoCreateRowSorter(true);
                WriterFile();
            }
        });
        btnDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowInfo showInfo = new ShowInfo();
                ShowInfo.main(null);
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mouseClick();
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edit();
                WriterFile();
            }
        });
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                Search();
            }
        });
        btnDeletes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Deletes();
            }
        });
    }

    public void btnAdd() {
        Regex regex = new Regex().invoke();
        Employee employee = regex.getEmployee();
        String idName = regex.getIdName();
        String name = regex.getName();
        String idDob = regex.getIdDob();
        String Dob = regex.getDob();
        String idAddress = regex.getIdAddress();
        String add = regex.getAdd();
        String idEmployeeCode = regex.getIdEmployeeCode();
        String employeeCode = regex.getEmployeeCode();
        String idPhone = regex.getIdPhone();
        String phone = regex.getPhone();
        String idEmail = regex.getIdEmail();
        String email = regex.getEmail();
        String idOvertime = regex.getIdOvertime();
        String overtime = regex.getOvertime();
        String idBasicSalary = regex.getIdBasicSalary();
        String basicSalary = regex.getBasicSalary();
        if (!name.matches(idName) || !Dob.matches(idDob) || !add.matches(idAddress) || !employeeCode.matches(idEmployeeCode) ||
                !phone.matches(idPhone) || !email.matches(idEmail) || !overtime.matches(idOvertime) || !basicSalary.matches(idBasicSalary)) {
            JOptionPane.showMessageDialog(null, "Enter the information again!");
        } else {
            if (btnMale.isSelected()) {
                employee.setGender(btnMale.getText());
            }
            if (btnFemale.isSelected()) {
                employee.setGender(btnFemale.getText());
            }
            if (btnOther.isSelected()) {
                employee.setGender(btnOther.getText());
            }
            for (Employee employee1 : employeeList) {
                if (employee1.getName().equals(txtName.getText())) {
                    JOptionPane.showMessageDialog(null, "Enter another name");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Add success");
            employeeList.add(employee);
            i++;
            tableModel = (DefaultTableModel) table1.getModel();
            tableModel.addRow(new Object[]{table1.getRowCount() + 1, employee.getName(), employee.getDateOfBirth(), employee.getAddress()
                    , employee.getEmployeeCode(), employee.getNumberPhone(), employee.getEmail(), employee.getOvertime(), employee.getBasicSalary(), employee.getSalary(), employee.getGender()});

        }
    }

    private void Edit() {
        tableModel = (DefaultTableModel) table1.getModel();
        if (table1.getSelectedRowCount() == 1) {
            String name = txtName.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String dob = sdf.format(chooser.getDate());
            String add = txtAddress.getText();
            String phone = txtPhone.getText();
            String email = txtEmail.getText();
            String employeeCode = txtEmployeeCode.getText();
            String salaryBasic = txtSalaryBasic.getText();
            String overtime = txtOverTime.getText();
            String male = btnMale.getText();
            String Female = btnFemale.getText();
            String other = btnOther.getText();
            double salary = Double.parseDouble(salaryBasic) + (Double.parseDouble(overtime) * 200000);
            tableModel.setValueAt(name, table1.getSelectedRow(), 1);
            tableModel.setValueAt(dob, table1.getSelectedRow(), 2);
            tableModel.setValueAt(add, table1.getSelectedRow(), 3);
            tableModel.setValueAt(employeeCode, table1.getSelectedRow(), 4);
            tableModel.setValueAt(phone, table1.getSelectedRow(), 5);
            tableModel.setValueAt(email, table1.getSelectedRow(), 6);
            tableModel.setValueAt(overtime, table1.getSelectedRow(), 7);
            tableModel.setValueAt(salaryBasic, table1.getSelectedRow(), 8);
            tableModel.setValueAt(salary, table1.getSelectedRow(), 9);
            if (btnMale.isSelected()) {
                btnFemale.setSelected(false);
                btnOther.setSelected(false);
                tableModel.setValueAt(male, table1.getSelectedRow(), 10);
            }
            if (btnFemale.isSelected()) {
                btnMale.setSelected(false);
                btnOther.setSelected(false);
                tableModel.setValueAt(Female, table1.getSelectedRow(), 10);
            }
            if (btnOther.isSelected()) {
                btnFemale.setSelected(false);
                btnMale.setSelected(false);
                tableModel.setValueAt(other, table1.getSelectedRow(), 10);
            }

            JOptionPane.showMessageDialog(null, "Edit success");
        } else {
            if (table1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Table isEmpty");
            } else {
                JOptionPane.showMessageDialog(null, "Selection row for Edit");
            }
        }
    }

    private void Deletes() {
        tableModel = (DefaultTableModel) table1.getModel();
        try {
            int index = table1.getSelectedRow();
            int response = JOptionPane.showConfirmDialog(null, "Do you want to Deletes ?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                tableModel.removeRow(index);
                JOptionPane.showMessageDialog(null, "Remove success");
            } else if (response == JOptionPane.NO_OPTION) {
                return;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Selection index row !!");
        }
        WriterFile();
    }

    private void Search() {
        tableModel = (DefaultTableModel) table1.getModel();
        String search = txtSearch.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tableModel);
        table1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }

    private void mouseClick() {
        tableModel = (DefaultTableModel) table1.getModel();
        String tbName = tableModel.getValueAt(table1.getSelectedRow(), 1).toString();
        String tbAdd = tableModel.getValueAt(table1.getSelectedRow(), 3).toString();
        String tbEmployeeCode = tableModel.getValueAt(table1.getSelectedRow(), 4).toString();
        String tbPhone = tableModel.getValueAt(table1.getSelectedRow(), 5).toString();
        String tbEmail = tableModel.getValueAt(table1.getSelectedRow(), 6).toString();
        String tbOvertime = tableModel.getValueAt(table1.getSelectedRow(), 7).toString();
        String tbSalaryBasic = tableModel.getValueAt(table1.getSelectedRow(), 8).toString();
        txtName.setText(tbName);
        txtAddress.setText(tbAdd);
        txtEmployeeCode.setText(tbEmployeeCode);
        txtPhone.setText(tbPhone);
        txtEmail.setText(tbEmail);
        txtOverTime.setText(tbOvertime);
        txtSalaryBasic.setText(tbSalaryBasic);
    }


    private void WriterFile() {
        String filePath = "InfoEmployee.txt";
        File file = new File(filePath);
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int j = 0; j < table1.getRowCount(); j++) {
                for (int k = 0; k < table1.getColumnCount(); k++) {
                    bw.write(table1.getValueAt(j, k).toString() + " ");
                }
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void restart() {
        txtName.setText(" ");
        txtAddress.setText(" ");
        txtEmail.setText(" ");
        txtEmployeeCode.setText(" ");
        txtOverTime.setText(" ");
        txtSalaryBasic.setText(" ");
        txtPhone.setText(" ");
        btnMale.setSelected(false);
        btnFemale.setSelected(false);
        btnOther.setSelected(true);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("EmployeeManager");
        frame.setSize(900, 500);
        frame.setContentPane(new EmployeeManager().EmployeeManager);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private class Regex {
        private Employee employee;
        private String idName;
        private String name;
        private String idDob;
        private String dob;
        private String idAddress;
        private String add;
        private String idEmployeeCode;
        private String employeeCode;
        private String idPhone;
        private String phone;
        private String idEmail;
        private String email;
        private String idOvertime;
        private String overtime;
        private String idBasicSalary;
        private String basicSalary;

        public Employee getEmployee() {
            return employee;
        }

        public String getIdName() {
            return idName;
        }

        public String getName() {
            return name;
        }

        public String getIdDob() {
            return idDob;
        }

        public String getDob() {
            return dob;
        }

        public String getIdAddress() {
            return idAddress;
        }

        public String getAdd() {
            return add;
        }

        public String getIdEmployeeCode() {
            return idEmployeeCode;
        }

        public String getEmployeeCode() {
            return employeeCode;
        }

        public String getIdPhone() {
            return idPhone;
        }

        public String getPhone() {
            return phone;
        }

        public String getIdEmail() {
            return idEmail;
        }

        public String getEmail() {
            return email;
        }

        public String getIdOvertime() {
            return idOvertime;
        }

        public String getOvertime() {
            return overtime;
        }

        public String getIdBasicSalary() {
            return idBasicSalary;
        }

        public String getBasicSalary() {
            return basicSalary;
        }

        public Regex invoke() {
            employee = new Employee();
            idName = "[A-Za-z]{6,12}";
            name = employee.setName(txtName.getText());
            idDob = "\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{1,4}";
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            dob = employee.setDateOfBirth(sdf.format(chooser.getDate()));
            idAddress = "[a-zA-Z]{0,9}[-|/][a-zA-Z]{0,9}[-|/][a-zA-Z]{0,9}$";
            add = employee.setAddress(txtAddress.getText());
            idEmployeeCode = "^B[a-zA-Z0-9]{0,12}";
            employeeCode = employee.setEmployeeCode(txtEmployeeCode.getText());
            idPhone = "\\d{2}[-]\\d{0,9}";
            phone = employee.setNumberPhone(txtPhone.getText());
            idEmail = "[a-z]{0,9}[@]gmail[.]com$";
            email = employee.setEmail(txtEmail.getText());
            idOvertime = "\\d{1,2}$";
            overtime = employee.setOvertime(txtOverTime.getText());
            idBasicSalary = "\\d{7}";
            basicSalary = employee.setBasicSalary(txtSalaryBasic.getText());
            employee.setSalary(Double.parseDouble(basicSalary) + (Double.parseDouble(overtime) * 200000));
            return this;
        }
    }
}
