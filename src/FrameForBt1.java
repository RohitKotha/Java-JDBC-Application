import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FrameForBt1 {
    public FrameForBt1(String[] names, Connection con)
    {
        JFrame f1=new JFrame("f1frame");
        //f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
        f1.setLayout(null);
        f1.setResizable(false);
        f1.setBounds(0,0,600,400);

        JLabel lb1=new JLabel("Select Name");
        lb1.setBounds(230,100,200,50);
        lb1.setFont(new Font("cosmic MS",Font.BOLD,20));


//combo box for view feedback
        JComboBox bx1=new JComboBox(names);
        bx1.setBounds(200,150,200,40);
        bx1.setFont(new Font("cosmic MS",Font.BOLD,20));
        bx1.setSelectedItem(null);
        //adding actionlisterner for bx1
        bx1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f3=new JFrame("records");
                f3.setBounds(0,0,600,400);
                f3.setVisible(true);
                f3.setResizable(false);
                f3.setLayout(null);
                try{
                    //getting records of sir
                    String sir= ""+bx1.getSelectedItem();
                    String quary ="select * from data where name=?;";
                    PreparedStatement p1= con.prepareStatement(quary);
                    p1.setString(1,sir);
                    // creating textarea for records of sir


                    JTextArea t1=new JTextArea();
                    t1.setFont(new Font("cosmic MS",Font.BOLD,20));
                    t1.setBounds(75,100,400,230);

                    JLabel lbb=new JLabel("Rating of "+bx1.getSelectedItem()+" sir ");
                    lbb.setFont(new Font("cosmic Ms",Font.BOLD,30));
                    lbb.setBounds(100,40,400,40);


                    ResultSet r2=p1.executeQuery();
                    while(r2.next()) {
                            t1.append(
                                "Id     : "+r2.getString(1) +"\n"+"College: " +r2.getString(3)+"\n"+"subject: "+r2.getString(5) +"\n"+"below Average ratings are : "+ r2.getString(6) +"\n"+"average ratings  are: "+ r2.getString(7)+"\n"+ "good ratings are : "+ r2.getString(8) +"\n"+"excellents ratings are: "+ r2.getString(9) +"\n"+"total: "+ r2.getString(10));
                    }
                    t1.setEditable(false);
                    f3.add(lbb);
                    f3.add(t1);

                }
                catch(SQLException ex)
                {
                    throw new RuntimeException(ex);
                }


            }
        });
        f1.add(lb1);
        f1.add(bx1);


    }
}
