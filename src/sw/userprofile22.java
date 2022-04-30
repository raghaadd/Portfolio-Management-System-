/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author LENOVO
 */
public class userprofile22 extends javax.swing.JFrame {

    JPanel postbase = new JPanel();
    String usernameorigin ="asma";
    
    /**
     * Creates new form userprofile22
     */
    public userprofile22() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        close.setIcon(new ImageIcon(getClass().getResource("images0/x1.png")));
        min.setIcon(new ImageIcon(getClass().getResource("images0/min1.png")));
        back.setIcon(new ImageIcon(getClass().getResource("images0/back3.png")));
        addpost.setIcon(new ImageIcon(getClass().getResource("images0/add3.png")));
        explore.setIcon(new ImageIcon(getClass().getResource("images0/explore0.png")));
        contact.setIcon(new ImageIcon(getClass().getResource("images0/contact.png")));
        out.setIcon(new ImageIcon(getClass().getResource("images0/out.png")));
        edit.setIcon(new ImageIcon(getClass().getResource("images0/edit.png")));
        
        scrollPane1.add(postbase);
       // String usenamepost = "asma";
        setResizable(false);
        makepanel();
        //dbconnect();
    }

    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public Connection dbconnect(String sql1){
    
       //we have to open post table then compare username that we have with username in post table:
         Connection conn = null;
         ResultSet rs = null;
         PreparedStatement ps = null;
       //  String usernameorigin="asma";//saja123"; //get this value from login page
         String usenamepost=null;
         
        // int count=0;
           try{
           conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject","root","iNEEDtostudy@202");
         //  String sql1="select * from post";//DELETE FROM table_name WHERE condition of which row(s) to delete;
           
           Statement st1=conn.createStatement();
           st1.executeQuery(sql1);
           
           
           conn.close();}
           catch(Exception ex){
              JOptionPane.showMessageDialog(null, ex.getMessage());
               System.err.println(ex);
           }
           
           
        return conn;
        
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////
    public void makepanel(){
        
        JPanel jhh =new JPanel(); 
        postbase.setLayout(new BoxLayout(postbase, BoxLayout.Y_AXIS));  
        
        //we have to open post table then compare username that we have with username in post table:
         Connection conn = null;
         ResultSet rs = null;
         PreparedStatement ps = null;
       //  String usernameorigin="asma";//saja123"; //get this value from login page
         String usenamepost=null;
         String idpost;
        // int count=0;
           try{
           conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject","root","iNEEDtostudy@202");
           String sql1="select * from post";
           Statement st1=conn.createStatement();
           ResultSet rs1=st1.executeQuery(sql1);
           Boolean flag=true;
           
           
           while(rs1.next())
           {
               
               usenamepost=rs1.getString("username");
               System.out.println("user::: "+usenamepost);
               name.setText(usenamepost);
             //  Object usernameorigin = null;
               
              idpost=rs1.getString("idpost");
               
             
               if(usenamepost.equals(usernameorigin))
               {
                   String content=rs1.getString("content");
                   //make a lable that have all the content here ////////////////////////////////////////////////////////////////////
                   
                   System.out.println("content::: "+content);
                        
            jhh.setLayout(null);//new FlowLayout());
            //add the jpanel in which the post will be displayed on
            jhh.setPreferredSize(new Dimension(50, 240));
            jhh.setBackground(new java.awt.Color(255,255,255));//blue
         
              postbase.add(jhh);  
         
            //add the jlable that will have the image 
          //  JLabel l = new JLabel("here please", SwingConstants.CENTER);
          
           //Icon icon=new ImageIcon(content);
       // ImageIcon imgicon=new ImageIcon(content);
       // Image img=imgicon.getImage();
       
        JLabel l=new JLabel();
        l.setBounds(15, 15, 300,210); //size.width, size.height);
        
        //*********************** change scale for icon to fit the label:
        Icon icon=new ImageIcon(content);
        ImageIcon imgicon=new ImageIcon(content);
        Image img=imgicon.getImage();
        Image imgscale=img.getScaledInstance(l.getWidth(), l.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon=new ImageIcon(imgscale);
        l.setIcon(scaledIcon);
        l.setOpaque(true);
        l.setBackground(new java.awt.Color(250,250,250));
        jhh.add( l );
        
        
        //**************************the delete button///////////////////////////////////////////////////////////////////////////
        JButton jbuttondelete=new JButton("Delete");
        jbuttondelete.setForeground(new java.awt.Color(255,255,255));//text color
        jbuttondelete.setFont(new java.awt.Font("iCiel Baliho Script", 0, 18));//text type
        
        jbuttondelete.setName(idpost);
        jbuttondelete.setBounds(810,200, 100,29);//buttom size
        jbuttondelete.setBackground(new java.awt.Color(172,49,48));//button color
        jbuttondelete.setContentAreaFilled(false);
        jbuttondelete.setBorderPainted(false);
        jbuttondelete.setOpaque(true);
        
        jbuttondelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        /////////////////////////////////////////////////////////
        //******eventlistener******
        jbuttondelete.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            String idpost = ((JButton) e.getSource()).getName();
            
            System.out.println("Clicked!"+idpost);
     //   System.out.println("Clicked edit!"+idpost);
        deletepost();
        }});
        /////////////////////////////////////////////////////////
        
        jhh.add(jbuttondelete);
        
        //**************************the edit button///////////////////////////////////////////////////////////////////////////
        JButton jbuttonedit=new JButton("Edit");
        jbuttonedit.setForeground(new java.awt.Color(255,255,255));//text color
        jbuttonedit.setFont(new java.awt.Font("iCiel Baliho Script", 0, 18));//text type
        
        jbuttonedit.setName(idpost);
        jbuttonedit.setBounds(810,165,100,29);//buttom size
        jbuttonedit.setBackground(new java.awt.Color(18,33,139));//button color
        jbuttonedit.setContentAreaFilled(false);
        jbuttonedit.setBorderPainted(false);
        jbuttonedit.setOpaque(true);
        
        jbuttonedit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        /////////////////////////////////////////////////////////
        //******eventlistener******
        jbuttonedit.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            String idpost = ((JButton) e.getSource()).getName();
            
            System.out.println("Clicked!"+idpost);
     //   System.out.println("Clicked edit!"+idpost);
        editpost();
        }});
        /////////////////////////////////////////////////////////
        
        
        jhh.add(jbuttonedit);
        
        
        //create new jpanel:
        jhh =new JPanel(); 
        jhh.setVisible(true);
        flag=false;
        //JLabel l = new JLabel("", new ImageIcon(getClass().getResource("content")), SwingConstants.CENTER);//JLabel("Text"); //JLabel(Icon i)	Creates a JLabel instance with the specified image.
          
            
          //  Dimension size = l.getPreferredSize();
          
            
             if(!flag)
             { flag=true;
             jhh.setBackground(new java.awt.Color(240,240,240));
             jhh.setPreferredSize(new Dimension(10, 15));
             postbase.add(jhh);  
             jhh =new JPanel(); 
             jhh.setVisible(true);
           
             }
         //flag=false;
               
               }//if
           }//while
           
          conn.close();
           }catch(Exception ex){
              JOptionPane.showMessageDialog(null, ex.getMessage());
               System.err.println(ex);
           }
           

      }//end function
    
    
     ////////////////////////////////////////////////////////////////////
    public void deletepost(){
    System.out.println("Clicked delete inside fun!");
    
       String sql1="select * from post";//DELETE FROM table_name WHERE condition of which row(s) to delete;
        
    }//end function
    ////////////////////////////////////////////////////////////////////
    public void editpost(){
    System.out.println("Clicked edit inside fun!");
       
        
    }//end function
     ////////////////////////////////////////////////////////////////////
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        min = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabelRound1 = new rounded.JLabelRound();
        out = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        explore = new javax.swing.JLabel();
        edit = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        name = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        addpost = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        scrollPane1 = new java.awt.ScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(9, 16, 69));

        min.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        min.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minMouseClicked(evt);
            }
        });

        close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });

        back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(244, 216, 19));

        jPanel3.setBackground(new java.awt.Color(48, 171, 172));

        jPanel4.setBackground(new java.awt.Color(244, 216, 19));

        jPanel5.setBackground(new java.awt.Color(48, 171, 172));

        jPanel6.setBackground(new java.awt.Color(244, 216, 19));

        jPanel7.setBackground(new java.awt.Color(48, 171, 172));

        jPanel8.setBackground(new java.awt.Color(48, 171, 172));

        jPanel9.setBackground(new java.awt.Color(48, 171, 172));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/mainpage.jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jLabelRound1.setBackground(new java.awt.Color(0, 0, 0));
        jLabelRound1.setText("jLabelRound1");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        out.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                outMouseClicked(evt);
            }
        });

        contact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        explore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        explore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        edit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editMouseClicked(evt);
            }
        });

        name.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        name.setForeground(new java.awt.Color(18, 33, 139));
        name.setText("@username");
        name.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        name.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText(" bio");

        addpost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addpost.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addpost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addpostMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addpost, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(explore, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(out, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(out, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                        .addComponent(contact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(explore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addpost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1259, Short.MAX_VALUE)
                        .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(min, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(close, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        // TODO add your handling code here:
        // close
        this.dispose();
    }//GEN-LAST:event_closeMouseClicked

    private void minMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minMouseClicked
        // TODO add your handling code here:
        // min
        this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minMouseClicked

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new mainpage22().setVisible(true);
    }//GEN-LAST:event_backMouseClicked

    private void outMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_outMouseClicked
        // TODO add your handling code here:
        // close
        this.dispose();
    }//GEN-LAST:event_outMouseClicked

    private void editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseClicked
        // TODO add your handling code here:
        // close
        this.dispose();
       // new addpost22().setVisible(true);
    }//GEN-LAST:event_editMouseClicked

    private void nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new userprofile22().setVisible(true);
    }//GEN-LAST:event_nameMouseClicked

    private void addpostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addpostMouseClicked
        // TODO add your handling code here:
        // close
        this.dispose();
        new addpost22().setVisible(true);
    }//GEN-LAST:event_addpostMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(userprofile22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userprofile22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userprofile22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userprofile22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userprofile22().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addpost;
    private javax.swing.JLabel back;
    private javax.swing.JLabel close;
    private javax.swing.JLabel contact;
    private javax.swing.JLabel edit;
    private javax.swing.JLabel explore;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private rounded.JLabelRound jLabelRound1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel min;
    private javax.swing.JLabel name;
    private javax.swing.JLabel out;
    private java.awt.ScrollPane scrollPane1;
    // End of variables declaration//GEN-END:variables
}
