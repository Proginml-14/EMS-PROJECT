package employee.management.system;


import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {          //frame banane ke liye hum class ko extends karwaege

    Splash(){                                //this is our constructor

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/front.gif"));
                                 //image ko scale karnwe ke liye
        Image i2 = i1.getImage().getScaledInstance(1170, 650, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);    // scaled image ko leke aane ke liye
        JLabel image = new JLabel(i3);       // image ko set karane ke liye
        image.setBounds(0, 0, 1170, 650); //kis position pe set hoga uske liye
        add(image);                          // show karane ke liye


        setSize(1170, 650);      // frame ke size set karne ke liye setSize lete hai
        setLocation(200, 50);           // frame ko kis location pe dikhana hai uske liye setLocation
        setLayout(null);                     // layout set karne ke liye hum setLayout ka use krege
        setVisible(true);                    // Frame ko visible karana ke liye setVisible ka use ka use karte hai

        try{
            Thread.sleep(4000);        //agar 4sec ke liye hold karna h to
            setVisible(false);              // close karne ke liye
            new Login();


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Splash();                         // constructor object
    }
}
