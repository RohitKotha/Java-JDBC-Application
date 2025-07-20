import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class FrameForBt2 {
      public  FrameForBt2(String[] s, Connection con)
      {
          JFrame f2=new JFrame("f1frame");
          //f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          f2.setVisible(true);
          f2.setLayout(null);
          f2.setBounds(0,0,600,400);
          f2.setResizable(false);

          JLabel lb2=new JLabel("Select a subject");
          lb2.setBounds(220,100,200,50);
          lb2.setFont(new Font("Cosmic MS", Font.BOLD,20));

          JComboBox bxt2=new JComboBox(s);
          bxt2.setBounds(200,150,200,40);
          bxt2.setSelectedItem(null);
          f2.add(bxt2);
          f2.add(lb2);
          bxt2.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  JFrame f4=new JFrame("f4");
                  f4.setResizable(false);
                  f4.setBounds(0,0,600,400);

                  try {
                      String sub = "" + bxt2.getSelectedItem();
                      String query="select * from data where subject=?;";
                      PreparedStatement p2= con.prepareStatement(query);
                      p2.setString(1,sub);
                      ResultSet r3=p2.executeQuery();
                      String sirName="";
                      while(r3.next())
                      {
                          sirName=""+r3.getString(2);
                      }
                      //System.out.println(sirName);
                      sirName="Give feedback for "+sirName;
                      JLabel lb=new JLabel(sirName);
                      lb.setBounds(100,10,300,30);
                      lb.setFont(new Font("cosmic Ms",Font.BOLD,20));

                      f4.add(lb);

                  } catch (SQLException ex) {
                      throw new RuntimeException(ex);
                  }


                  JRadioButton r1=new JRadioButton("Below average");
                  r1.setBounds(150,60,200,30);
                  r1.setFont(new Font("cosmic MS",Font.BOLD,20));
                  r1.setFocusable(false);

                  JRadioButton r2=new JRadioButton("Average ");
                  r2.setBounds(150,120,200,30);
                  r2.setFont(new Font("cosmic MS",Font.BOLD,20));

                  r2.setFocusable(false);

                  JRadioButton r3=new JRadioButton("Good");
                  r3.setBounds(150,180,200,30);
                  r3.setFocusable(false);
                  r3.setFont(new Font("cosmic MS",Font.BOLD,20));

                  JRadioButton r4=new JRadioButton("Excellent");
                  r4.setBounds(150,240,200,30);
                  r4.setFocusable(false);
                  r4.setFont(new Font("cosmic MS",Font.BOLD,20));

                  JButton bt=new JButton("SUBMIT");
                  bt.setBounds(250,300,100,40);
                  bt.setFocusable(false);
                  bt.addActionListener(new ActionListener() {
                      @Override
                      public void actionPerformed(ActionEvent e) {
                          int n = 0;
                          if (r1.isSelected()) {
                              n = 1;
                          } else if (r2.isSelected()) {
                              n = 2;
                          } else if (r3.isSelected()) {
                              n = 3;

                          } else if (r4.isSelected()) {
                              n = 4;
                          }
                          if (n == 1) {
                              try {
                                  String sub = "" + bxt2.getSelectedItem();
                                  String qry = "update data set belowAverage=belowAverage+1 where subject=?;";
                                  PreparedStatement pr1 = con.prepareStatement(qry);
                                  pr1.setString(1, sub);
                                  pr1.executeUpdate();
                                  JOptionPane.showMessageDialog(null, "Thankyou for feedback", "Message", 1);
                                  f4.dispose();

                              } catch (SQLException ex) {
                                  throw new RuntimeException(ex);
                              }
                          } else if (n == 2)
                          {
                              try {
                                  String sub = "" + bxt2.getSelectedItem();
                                  String qry = "update data set average=average+1 where subject=?;";
                                  PreparedStatement pr1 = con.prepareStatement(qry);
                                  pr1.setString(1, sub);
                                  pr1.executeUpdate();
                                  JOptionPane.showMessageDialog(null, "Thankyou for feedback", "Message", 1);
                                  f4.dispose();

                              } catch (SQLException ex) {
                                  throw new RuntimeException(ex);
                              }

                          }else if (n == 3)
                          {
                              try {
                                  String sub = "" + bxt2.getSelectedItem();
                                  String qry = "update data set good=good+1 where subject=?;";
                                  PreparedStatement pr1 = con.prepareStatement(qry);
                                  pr1.setString(1, sub);
                                  pr1.executeUpdate();
                                  JOptionPane.showMessageDialog(null, "Thankyou for feedback", "Message", 1);
                                  f4.dispose();

                              } catch (SQLException ex) {
                                  throw new RuntimeException(ex);
                              }

                          }
                          else if (n == 4)
                          {
                              try {
                                  String sub = "" + bxt2.getSelectedItem();
                                  String qry = "update data set excellent =excellent+1  where subject=?;";
                                  PreparedStatement pr1 = con.prepareStatement(qry);
                                  pr1.setString(1, sub);
                                  pr1.executeUpdate();
                                  JOptionPane.showMessageDialog(null, "Thankyou for feedback", "Message", 1);
                                  f4.dispose();

                              } catch (SQLException ex) {
                                  throw new RuntimeException(ex);
                              }

                          }
                      }
                  });



                  ButtonGroup g=new ButtonGroup();
                  g.add(r1);
                  g.add(r2);
                  g.add(r3);
                  g.add(r4);

                  f4.add(r1);
                  f4.add(r2);
                  f4.add(r3);
                  f4.add(r4);
                  f4.add(bt);
                  f4.setLayout(null);
                  f4.setVisible(true);
              }
          });

          f2.setVisible(true);

      }
}
