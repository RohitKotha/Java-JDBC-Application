import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;

public class mainFrame {
    public static void main(String[] args) throws SQLException {
        JFrame frame=new JFrame("feedback");
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        //coonecting database

         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feedback" ,"root","Bhaskar");
         if(con!=null)
         {
             System.out.println("successfully connected");
         }else {
             System.out.println("conection failed");
         }
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// query to get  subject name from data
        Statement s1=con.createStatement();

        ResultSet r1=s1.executeQuery("select * from data ");

        ArrayList<String>  str=new ArrayList<>();
        while(r1.next())
        {
            str.add(r1.getString(2));
        }
        String[] names =str.toArray(new String[0]);


// creating view feebck button

        JButton bt1=new JButton("View Feedback");
        bt1.setBounds(100,150,150,40);
        bt1.setFocusable(false);
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                FrameForBt1 k1=new FrameForBt1(names,con);
            }
        });
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
//button for give feedback

        // query to get  subject name from data
        Statement s2=con.createStatement();

        ResultSet r2=s1.executeQuery("select * from data ");

        ArrayList<String>  str2=new ArrayList<>();
        while(r2.next())
        {
            str2.add(r2.getString(5));
        }
        String[] subjects =str2.toArray(new String[0]);


        JButton bt2=new JButton("Give Feedback");
        bt2.setBounds(330,150,150,40);
        bt2.setFocusable(false);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 FrameForBt2 k2=new FrameForBt2(subjects,con);



            }
        });
        frame.add(bt1);
        frame.add(bt2);
        frame.setVisible(true);
    }
}