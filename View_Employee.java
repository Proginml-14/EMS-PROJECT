package employee.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class View_Employee extends JFrame implements ActionListener {

    JTable table;        //ek table bnane ke liye jisme humara phone number etc rhega
    Choice choiceEMP;   //drop down box ke liye
    JButton searchbtn, print, update, back;

    View_Employee() {

        getContentPane().setBackground(new Color(173, 216, 230));
        JLabel search = new JLabel("Search by employee id");
        search.setBounds(20, 20, 150, 20);
        add(search);

        //dropdown box ke liye
        choiceEMP = new Choice();
        choiceEMP.setBounds(180, 20, 150, 20);
        add(choiceEMP);

        //exception ko handle karne ke liye hum try and catch bnaege.
        try {

            conn c = new conn();
            //jo result nikal ke aaega wo resultset ke andr rhega.
            ResultSet resultSet = c.statement.executeQuery("select * from employee");  //jab humari data ko database me se nikal kar lana hota hai
            //ye loop jabtak chalta rhega jabtak humara data line by line mere choice ke andr visible nhi hojate
            while (resultSet.next()) {
                choiceEMP.add(resultSet.getString("empID")); //yaha get.string isliye kiya hai q ki mere
                //database ke andr ek empID name se column h usmese jo bhi details h nikalkr choice ke andr store krwa dena hai
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        //jo bhi mere table me data h usko nikal kar store krane ke liye hum lege try and catch.
        try {

            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee");
            //table bnane le llye hume setModel and Dbutils lena hota hai
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e) {
            e.printStackTrace();
        }
        //table scroll karne ke lie
        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0, 100, 900, 600);
        add(jp);

        searchbtn = new JButton("Search");
        searchbtn.setBounds(20, 70, 80, 20);
        searchbtn.addActionListener(this);
        add(searchbtn);

        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(220, 70, 80, 20);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(320, 70, 80, 20);
        back.addActionListener(this);
        add(back);


        setSize(900, 700);
        setLayout(null);
        setLocation(300, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchbtn) {
            String query = "select * from employee where empID = '" + choiceEMP.getSelectedItem() + "'";
            try {
                conn c = new conn();
                ResultSet resultSet = c.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == update) {
            setVisible(false);
            new updateEmployee(choiceEMP.getSelectedItem());
        }else {
            setVisible(false);
            new Main_class();
        }
    }
        public static void main (String[]args){
            new View_Employee();

        }
    }

