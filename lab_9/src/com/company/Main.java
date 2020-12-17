package com.company;

import com.company.EditJDialog;
import com.company.Student;
import com.company.myComparator;

import java.awt.List;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Main extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new Main("Students");
    }

    protected static JLabel empty = new JLabel("");
    private JButton show = new JButton("Show subjects");
    private JButton show_exelents = new JButton("Show exselents");
    private JButton show_many_stud = new JButton("Show many stud");
    private JButton edit = new JButton("Edit");
    private JButton add = new JButton("Add");
    private JLabel label = new JLabel("Students:");
    private List list = new List();
    private List list2 = new List();
    private ArrayList<Student> a;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;

    public Main(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);

        menuBar = new JMenuBar();
        menu = new JMenu("File");

        menuItem = new JMenuItem("Open");
        menuItem.setMnemonic(KeyEvent.VK_O);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        Box b = new Box(2);

        Container container = this.getContentPane();
        container.setLayout(new BoxLayout(container, 1));
        JPanel jPanel = new JPanel();
        jPanel.add(label);

        b.add(jPanel);
        b.add(list);
        b.add(list2);
        container.add(b);

        Box b2 = new Box(2);

        show.addActionListener(this);
        JPanel jPanel1 = new JPanel();
        jPanel1.add(show);
        b2.add(jPanel1);

        edit.addActionListener(this);
        JPanel jPanel2 = new JPanel();
        jPanel2.add(edit);
        b2.add(jPanel2);

        add.addActionListener(this);
        JPanel jPanel3 = new JPanel();
        jPanel3.add(add);
        b2.add(jPanel3);

        show_exelents.addActionListener(this);
        JPanel jPanel4 = new JPanel();
        jPanel4.add(show_exelents);
        b2.add(jPanel4);


        show_many_stud.addActionListener(this);
        JPanel jPanel5 = new JPanel();
        jPanel5.add(show_many_stud);
        b2.add(jPanel5);

        container.add(b2);

        read("data.txt");
        show(list, a);
        setSize(450, 180);
        //pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == show) {
            show(list, a);
            ArrayList<String> set1 = new ArrayList();
            //ArrayList<Student> set2 = new ArrayList();

            for (Student el : a)
                set1.add(el.getName_of_subject());

            Collections.sort(set1);
            show2(list2, set1);
        } else if (e.getSource() == show_exelents) {
            show(list, a);
            list2.removeAll();
            ArrayList<Student> set1 = new ArrayList();
            //ArrayList<Student> set2 = new ArrayList();

            for (Student el : a)
                if(el.getMark_field() >= 9) {
                    set1.add(el);
                }
            for(int i = 0; i < set1.size()-1; i++){
                for(int j = 0; j < set1.size()-1; j++){
                    if(set1.get(j + 1).getMark_field() > set1.get(j).getMark_field()) {
                        Student temp = set1.get(j);
                        set1.set(j, set1.get(j + 1));
                        set1.set(j + 1, temp);
                    }
                }
            }
            show(list2, set1);
        } else if (e.getSource() == show_many_stud) {
            show(list, a);
            list2.removeAll();
            ArrayList<Student> set1 = a;
            ArrayList<Student> set2 = new ArrayList();
            for(int i = 0; i < set1.size()-1; i++){
                for(int j = 0; j < set1.size()-1; j++){
                    if(set1.get(j + 1).getName().compareTo(set1.get(j).getName()) < 0) {
                        Student temp = set1.get(j);
                        set1.set(j, set1.get(j + 1));
                        set1.set(j + 1, temp);
                    }
                }
            }
            //show3(list2, set1);
            boolean was_it = false;
            for(int i = 0; i < set1.size() - 1; i++){

                Student next = set1.get(i + 1); // 0 0 0 1 1 1 1 2 2 2 3
                if(set1.get(i).getName().compareTo(next.getName()) == 0 && was_it == false) {
                    was_it = true;
                    set2.add(next);

                } else if(set1.get(i).getName().compareTo(next.getName()) != 0) {
                    was_it = false;

                }

            }


            show3(list2, set2);
        } else if (e.getSource() == menuItem) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            chooser.setFileFilter(filter);
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                read(chooser.getSelectedFile().getName());
                show(list, a);
                list2.removeAll();
            }

        } else if (e.getSource() == add) {
            Student tempStudent = new Student();
            new EditJDialog(this, "add Student", tempStudent);

            if (!tempStudent.equals(new Student())) {
                a.add(tempStudent);
                show(list, a);
            }
        } else if (e.getSource() == edit) {
            int ind = list.getSelectedIndex();
            if (ind != -1) {
                new EditJDialog(this, "edit Student", a.get(ind));
                show(list, a);
            } else {
                JOptionPane.showMessageDialog(this, "Select element!", "Error!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private void read(String filename) {
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(filename));
            a = new ArrayList<>();
            while (sc.hasNext()) {
                Student temp_student = new Student(sc.nextInt(), sc.next(), sc.next(), sc.nextInt());
                if(temp_student.getNumber() > 1000000 && temp_student.getNumber() < 9999999 && temp_student.getMark_field() > 0 && temp_student.getMark_field() < 11)  {
                    a.add(temp_student);
                }
            }
        } catch (FileNotFoundException err) {
            JOptionPane.showMessageDialog(this, err, "Error!", JOptionPane.PLAIN_MESSAGE);
        } finally {
            if (sc != null)
                sc.close();
        }
    }

    private void show(List list, Collection<Student> a) {
        if (a != null) {
            list.removeAll();
            for (Student el : a)
                list.add(el.toString());
        } else {
            JOptionPane.showMessageDialog(this, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
        }

    }
    private void show2(List list, Collection<String> a) {
        if (a != null) {
            list.removeAll();
            for (String el : a)
                list.add(el);
        } else {
            JOptionPane.showMessageDialog(this, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
        }

    }
    private void show3(List list, Collection<Student> a) {
        if (a != null) {
            list.removeAll();
            for (Student el : a)
                list.add(el.toString2());
        } else {
            JOptionPane.showMessageDialog(this, "There are no elements!", "Error!", JOptionPane.PLAIN_MESSAGE);
        }

    }
}