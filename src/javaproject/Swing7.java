package JavaProject;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Swing7 implements ActionListener
{
    JFrame j1,j2,j3,j4,j5;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
    JTextField t1,t3,t4,t5,t6;
    JPasswordField t2;
    JTextArea a1,a2,a3;
    JButton b,b1,b2,b3,b4,b5;
    String uname, passwd,uid,text,text1;
    
    Swing7()
    {        
        j1 = new JFrame("LogIn");
        j2 = new JFrame("Blog_update");
        j3 = new JFrame("Update_status");
        j4 = new JFrame("Anonymous_Blogger.com");
        j5 = new JFrame("SignUp");
        l1 = new JLabel("User Name : ");
        l1.setBounds(50,96,100,30);
        l2 = new JLabel("Password : ");
        l2.setBounds(50,146,100,30);
        l6 = new JLabel("User Id : ");
        l6.setBounds(50,20,100,30);
        l8 = new JLabel("User Name : ");
        l8.setBounds(50,60,100,30);
        l9 = new JLabel("Password : ");
        l9.setBounds(50,100,100,30);
        l10 = new JLabel("Blog :- ");
        l10.setBounds(50,140,100,30);
        l7 = new JLabel("User Id : ");
        l7.setBounds(50,196,100,30);
        l3 = new JLabel("Blog Entry : ");
        l3.setBounds(50,200,100,30);
        a1 = new JTextArea("Write blog here");
        a1.setBounds(50,95,300,200);
        a1.setLineWrap(true);
        a2 = new JTextArea();
        a2.setBounds(150,70,200,200);
        a2.setLineWrap(true);
        a2.setEditable(false);
        a3 = new JTextArea();
        a3.setBounds(150,150,200,160);
        a3.setLineWrap(true);        
        
        l4 = new JLabel();
        l4.setBounds(150,100,150,30);
        t1 = new JTextField(15);
        t1.setBounds(150,100,150,30);        
        t2 = new JPasswordField(15);
        t2.setBounds(150,150,150,30);
        t3 = new JTextField(15);
        t3.setBounds(150,200,100,30);
        t4 = new JTextField(15);
        t4.setBounds(150,20,100,30);
        t5 = new JTextField(15);
        t5.setBounds(150,63,100,30);
        t6 = new JTextField(15);
        t6.setBounds(150,103,100,30);
        l5 = new JLabel();
        l5.setBounds(150,150,150,30);        
        
        b = new JButton("Enter");
        b.setBounds(150,250,100,30);
        b.addActionListener(this);
        b3 = new JButton("Login");
        b3.setBounds(50,30,100,30);
        b3.addActionListener(this);
        b4 = new JButton("Signup");
        b4.setBounds(180,30,100,30);
        b4.addActionListener(this);
        b5 = new JButton("Submit");
        b5.setBounds(150,330,100,30);
        b5.addActionListener(this);
        
        j1.add(l1);
        j1.add(l2);
        j1.add(l7);
        j1.add(t1);
        j1.add(t2);
        j1.add(t3);
        j1.add(b);
        j1.setSize(420,420);
        j1.setLayout(null);
        j1.setVisible(false); 
                
        j4.add(b3);
        j4.add(b4);
        j4.setSize(350,120);
        j4.setLayout(null);
        j4.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        uname = t1.getText();
        passwd = t2.getText();
        uid = t3.getText();
        Connection c = null;
        String url = "jdbc:mysql://localhost:3306/blog";
        if(e.getSource() == b || e.getSource() == b3)
        {
            try
            {
                j4.setVisible(false);
                j1.setVisible(true); 
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver Loaded");
                c = DriverManager.getConnection(url,"root","som@mysql123");
                System.out.println("Connection Established");
                Statement stmt = c.createStatement();                                   
                String sql = ("select u_pass,u_id,u_name,blog from info where u_id='" + uid + "'");                
                ResultSet re = stmt.executeQuery(sql);
                while(re.next())
                {      
                    j1.setTitle("ShowBlog");
                    l1.setBounds(50,20,100,30);
                    l2.setBounds(50,40,100,30);
                    l3.setBounds(50,60,100,30);
                    l4.setBounds(150,20,150,30);
                    l5.setBounds(150,40,150,30);                    
                    l1.setText("User Id         : ");
                    l2.setText("User Name  : ");
                    l3.setText("Blog Entry  : ");
                    b1 = new JButton("Edit");    
                    j1.add(b1);
                    b1.addActionListener(this);
                    b1.setBounds(150,280,100,30);                    
                    b.setBounds(150,450,100,30);
                    j1.remove(b);
                    j1.remove(t1);
                    j1.remove(t2);
                    j1.remove(t3);
                    j1.remove(l7);
                    j1.add(l3);
                    j1.add(l4);
                    j1.add(l5);
                    j1.add(a2);
                    l4.setText(re.getString(2));
                    l5.setText(re.getString(3));
                    a2.setText(re.getString(4));                 
                    if (re.getString(1).equals(passwd)==false || re.getString(2).equals(uid)==false)
                    {
                       l4.setText("NULL");
                       l5.setText("NULL");
                       l3.setText("Error            : ");
                       a2.setText("Wrong password or U_ID");                       
                       j1.remove(b1);                       
                    }                    
                }                
                c.close();                
            }
            catch(SQLException e1)
            {
                System.out.println("connection not established "+e1);
            }
            catch(ClassNotFoundException e2)
            {
                System.out.println("Driver not loaded");
            }
        }
        else if(e.getSource() == b1)
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");                
                c = DriverManager.getConnection(url,"root","som@mysql123");
                Statement stmt = c.createStatement();                
                String sql1 = ("select u_name,blog from info where u_id='" + uid + "'");                
                ResultSet r1 = stmt.executeQuery(sql1);
                while(r1.next())
                {
                    a1.setText(a2.getText());
                    j2.setSize(400,400);
                    j2.setLayout(null);
                    j2.setVisible(true);
                    j1.setVisible(false);
                    j2.add(l1);
                    j2.add(l2);
                    l2.setBounds(50,60,100,30);
                    l1.setText("Username      : ");
                    l2.setText("Update Blog :- ");
                    j2.add(l4);
                    l4.setText(r1.getString(1));
                    j2.add(a1);
                    b2 = new JButton("Update");    
                    j2.add(b2);
                    b2.addActionListener(this);
                    b2.setBounds(150,320,100,30);                    
                }              
            }
            catch(SQLException e1)
            {
                System.out.println("connection not established"+e1);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Swing7.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
        else if(e.getSource() == b2)
        {
             try
            {                
                text = a1.getText();
                Class.forName("com.mysql.cj.jdbc.Driver");                
                c = DriverManager.getConnection(url,"root","som@mysql123");
                Statement stmt = c.createStatement();                
                String sql2 = ("update info set blog='"+text+"' where u_id='" + uid + "'");                
                stmt.executeUpdate(sql2);
                j3.setSize(400,120);
                j3.setLayout(null);
                j3.setVisible(true);
                j2.setVisible(false);
                j3.add(l1);
                l1.setBounds(50,17,300,30);
                l1.setText("Record update successful !!!");                              
            }
            catch(SQLException e1)
            {
                System.out.println("connection not established"+e1);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Swing7.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(e.getSource() == b4)
        {         
            j5.add(l6);
            j5.add(l8);
            j5.add(l9);
            j5.add(l10);
            j5.add(t4);
            j5.add(t5);
            j5.add(t6);
            j5.add(a3);
            j5.add(b5);
            j5.setSize(420,420);
            j5.setLayout(null);
            j4.setVisible(false);
            j5.setVisible(true);         
        }
        
        else if(e.getSource() == b5)
        {
            uid = t4.getText();
            uname = t5.getText();
            passwd = t6.getText();
            text1 = a3.getText();
            try {                
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(url,"root","som@mysql123");
            Statement stmt = c.createStatement();                
            String sql3 = ("insert into info values('"+uid+"','"+uname+"','"+passwd+"','"+text1+"')");
            stmt.executeUpdate(sql3);
            j3.setSize(400,120);
            j3.setLayout(null);
            j3.setVisible(true);
            j5.setVisible(false);
            j3.add(l1);
            l1.setBounds(50,17,300,30);
            l1.setText("Record update successful !!!"); 
            }  
            catch (ClassNotFoundException ex) {
                Logger.getLogger(Swing7.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                j3.setSize(400,120);
                j3.setLayout(null);
                j3.setVisible(true);
                j5.setVisible(false);
                j3.add(l1);
                l1.setBounds(50,17,300,30);
                l1.setText("Duplicate entry detected, change u_id");                
                Logger.getLogger(Swing7.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String args[])
    {
        Swing7 ob = new Swing7();
    }    
}