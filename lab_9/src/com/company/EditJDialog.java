package com.company;

import java.awt.event.*;
import java.awt.*;
import java.util.zip.DataFormatException;
import javax.swing.*;


public class EditJDialog extends JDialog implements ActionListener {
    private JButton ok = new JButton("<html><i><font color=\"green\">ok</font></i></html>");


    private JLabel labelNumber = new JLabel("  Number:");
    private JLabel labelName = new JLabel("  Name:");
    private JLabel Name_of_subject = new JLabel("  Name of subject:");
    private JLabel Mark_field = new JLabel("  Mark:");


    private JTextField inputNumber = new JTextField("", 5);
    private JTextField inputName = new JTextField("", 5);
    private JTextField name_of_subject = new JTextField("", 5);
    private JTextField mark_field = new JTextField("", 5);

    private Student student;

    public EditJDialog(JFrame parent, String title, Student o) {
        super(parent, title, true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.student = o;

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(5, 2));

        container.add(labelName);
        inputName.setText(student.getName());
        container.add(inputName);

        container.add(labelNumber);
        inputNumber.setText(student.getNumber() + "");
        container.add(inputNumber);

        container.add(Name_of_subject);
        name_of_subject.setText(student.getName_of_subject() + "");
        container.add(name_of_subject);

        container.add(Mark_field);
        mark_field.setText(student.getMark_field() + "");
        container.add(mark_field);

        container.add(Main.empty);



        ok.addActionListener(this);
        container.add(ok);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            try {
                if (inputName.getText().equals(""))
                    throw new DataFormatException("Set name!");
                student.setName(inputName.getText());
                if (Integer.parseInt(inputNumber.getText()) < 1000000 || Integer.parseInt(inputNumber.getText()) > 9999999)
                    throw new DataFormatException("Set number!");
                student.setNumber(Integer.parseInt(inputNumber.getText()));
                student.setName_of_subject(name_of_subject.getText());
                if (Integer.parseInt(mark_field.getText()) < 0 || Integer.parseInt(mark_field.getText()) > 10)
                    throw new DataFormatException("Set mark!");
                student.setMark_field(Integer.parseInt(mark_field.getText()));

                setVisible(false);   // это норма?
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(this, err, "Error!", JOptionPane.PLAIN_MESSAGE);
            } catch (DataFormatException err) {
                JOptionPane.showMessageDialog(this, err.getMessage(), "Error!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}