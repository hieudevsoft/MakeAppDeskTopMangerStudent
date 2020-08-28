package com.appmanagerstudent.hieu;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class App {
    private JPanel rootPanel;
    private JTextField tf_Id;
    private JTextField tf_Name;
    private JTextField tf_Birth;
    private JTextField tf_Gender;
    private JTextField tf_Point;
    private JButton bt_Add;
    private JButton bt_Update;
    private JButton bt_Delete;
    private JButton bt_Clear;
    private JButton bt_Exit;
    private JTable tb_Student;
    private String [] rows = new String[5];
    private DefaultTableModel model;
    private ListSelectionModel listSelectionModel;
    private  String [] columns = {"ID","Name","Birth","Gender","Point"};
    private void initComponents() {

        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tb_Student.setModel(model);
        model.setColumnIdentifiers(columns);
        tb_Student.setColumnSelectionAllowed(false);
        tb_Student.setCellSelectionEnabled(true);
        listSelectionModel = tb_Student.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    public App()
    {
        initComponents();
        addStudent();
        updateStudent();
        clearButtonClick();
        exitClick();
        deleteClick();
    }

    private void deleteClick() {
        bt_Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tb_Student.getSelectedRow();
                System.out.println(row);e
                if(row>=0)
                {
                    model.removeRow(row);
                }
                else JOptionPane.showMessageDialog(rootPanel, "no rows select or table empty","Warning",JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void addStudent()
    {
        bt_Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = tf_Id.getText().trim();
                if(id.length()==0)
                    JOptionPane.showMessageDialog(rootPanel, "ID not empty","Warning",JOptionPane.ERROR_MESSAGE);
                else{
                    String birth = tf_Birth.getText().trim();
                    if(!validBirth(birth))
                        JOptionPane.showMessageDialog(rootPanel, "Birth Day is not correct(01-01-1000 is correct)","Warning",JOptionPane.ERROR_MESSAGE);
                    else
                    {
                        String Gender = tf_Gender.getText().trim().toLowerCase();
                        if(Gender.equals("male")||Gender.equals("female")||Gender.equals("other"))
                        {
                                StringBuilder stringBuffer = new StringBuilder(Gender);
                                stringBuffer.deleteCharAt(0);
                                stringBuffer.insert(0, Character.toUpperCase(Gender.charAt(0)));
                                Gender = stringBuffer.toString();
                                String point = tf_Point.getText().trim();
                                try {
                                    Double pointDouble = Double.parseDouble(point);
                                    String name = tf_Name.getText().trim();
                                    System.out.println(id+" " + birth + " " + Gender + " " + pointDouble + " " + name);
                                    rows[0] = id;
                                    rows[1] = name;
                                    rows[2] = birth;
                                    rows[3] = Gender;
                                    rows[4] = String.valueOf(pointDouble);
                                    model.addRow(rows);
                                    clearText();
                                }catch (Exception ee)
                                {
                                    JOptionPane.showMessageDialog(rootPanel, "Point is number or real number","Warning",JOptionPane.ERROR_MESSAGE);
                                }


                        }
                        else JOptionPane.showMessageDialog(rootPanel, "Gender incorrect (Male,Female,Other)","Warning",JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
    private void updateStudent()
    {
        listSelectionModel.addListSelectionListener(e -> {
            try{
                int[] rows = tb_Student.getSelectedRows();
                tf_Id.setText(String.valueOf(tb_Student.getValueAt(rows[0], 0)));
                tf_Name.setText(String.valueOf(tb_Student.getValueAt(rows[0], 1)));
                tf_Birth.setText(String.valueOf(tb_Student.getValueAt(rows[0], 2)));
                tf_Gender.setText(String.valueOf(tb_Student.getValueAt(rows[0], 3)));
                tf_Point.setText(String.valueOf(tb_Student.getValueAt(rows[0], 4)));
            }catch (Exception ee)
            {

            }

        });
        bt_Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row_select = tb_Student.getSelectedRow();

                rows[0] = tf_Id.getText().trim();
                rows[1] = tf_Name.getText().trim();
                rows[2] = tf_Birth.getText().trim();
                rows[3] = tf_Gender.getText().trim();
                rows[4] = tf_Point.getText().trim();

                model.setValueAt(rows[0], row_select, 0);
                model.setValueAt(rows[1], row_select, 1);
                model.setValueAt(rows[2], row_select, 2);
                model.setValueAt(rows[3], row_select, 3);
                model.setValueAt(rows[4], row_select, 4);

            }
        });

    }
    private void clearButtonClick()
    {
        bt_Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearText();
            }
        });

    }
    private boolean validBirth(String s)
    {
        Pattern datePattern = Pattern.compile("^[0-3]{1}[0-9]{1}-[0-1]{1}[1-9]{1}-[1-9]{1}[0-9]{3}$");
        return datePattern.matcher(s).matches();
    }
    private void clearText()
    {
        tf_Id.setText("");
        tf_Birth.setText("");
        tf_Name.setText("");
        tf_Gender.setText("");
        tf_Point.setText("");
    }
    private void exitClick()
    {
        bt_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
