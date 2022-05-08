/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw_project;

import java.awt.Color;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import rounded_label.JLabelRound;

/**
 *
 * @author LENOVO
 */
public class mainpage22 extends javax.swing.JFrame {

    /**
     * Creates new form mainpage22
     */
    JPanel postbase = new JPanel();
    String usernameorigin = "saja123"; //get this value from login page
    boolean likeflag = false;//*************** to know if the user has been liked the post or not
    boolean saveflag = false;//*************** to know if the user has been saved the post or not

    public mainpage22() {
        initComponents();
        // photo.setIcon(new ImageIcon(getClass().getResource("/image/wa0.jpeg")));
        scrollPane2.add(postbase);
        setResizable(false);
        personalphoto();
        makepanel();
        this.setLocationRelativeTo(null);

        close.setIcon(new ImageIcon(getClass().getResource("/images0/x1.png")));
        min.setIcon(new ImageIcon(getClass().getResource("/images0/min1.png")));
        back.setIcon(new ImageIcon(getClass().getResource("/images0/back3.png")));
        addpost.setIcon(new ImageIcon(getClass().getResource("/images0/add3.png")));
        explore.setIcon(new ImageIcon(getClass().getResource("/images0/explore0.png")));
        contact.setIcon(new ImageIcon(getClass().getResource("/images0/contact.png")));
        out.setIcon(new ImageIcon(getClass().getResource("/images0/out.png")));
        profile.setIcon(new ImageIcon(getClass().getResource("/images0/pro.png")));
        //   main.setIcon(new ImageIcon(getClass().getResource("images0/mainpage.jpeg")));
    }

    public void personalphoto() {
        //*************************set username for jlabelusername:
        jLabelusername.setText("@" + usernameorigin);
        //*******************************we have to open following table then compare username that we have with username in follwoing table then bring posts from post table:
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String usenamepost = null;
        String usenamefollowing = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");

            String sql5 = "select * from appuser where username ='" + usernameorigin + "'";
            Statement st5 = conn.createStatement();
            ResultSet rs5 = st5.executeQuery(sql5);

            if (rs5.next()) {

                //*******photo
                String mail = rs5.getString("email");
                String sql6 = "select * from person where email='" + mail + "'";
                Statement st6 = conn.createStatement();
                ResultSet rs6 = st6.executeQuery(sql6);

                if (rs6.next()) {//there is photo
                    String image = rs6.getString("image");

                    if ((!image.equals("")) && (!image.equals(null))) {// image!=null){
                        //************display image************
                        //*********************** change scale for icon to fit the label:
                        //Icon icon6=new ImageIcon(content);
                        ImageIcon imgicon6 = new ImageIcon(image);
                        Image img6 = imgicon6.getImage();
                        Image imgscale6 = img6.getScaledInstance(jLabelRound1.getWidth(), jLabelRound1.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon6 = new ImageIcon(imgscale6);
                        jLabelRound1.setIcon(scaledIcon6);
                        jLabelRound1.setOpaque(true);
                        jLabelRound1.setBackground(new java.awt.Color(250, 250, 250));

                        System.out.println("user::: you have come here? havent you! ");
                    } else {
                        //************display noimage icon********
                        ImageIcon imgicon6 = new ImageIcon(getClass().getResource("/images0/noimage.png"));
                        Image img6 = imgicon6.getImage();
                        Image imgscale6 = img6.getScaledInstance(jLabelRound1.getWidth(), jLabelRound1.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon6 = new ImageIcon(imgscale6);
                        jLabelRound1.setIcon(scaledIcon6);
                        jLabelRound1.setOpaque(true);
                        jLabelRound1.setBackground(new java.awt.Color(250, 250, 250));
                        System.out.println("user::: guess not");
                    }

                }//photo if
                else {//there is no photo

                }

            }//bio if
            else {/*there is no user*/
            }

        } catch (SQLException ex) {
            Logger.getLogger(mainpage22.class.getName()).log(Level.SEVERE, null, ex);

        }

    }//end of function
    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void makepanel() {

        JPanel jhh = new JPanel();
        postbase.setLayout(new BoxLayout(postbase, BoxLayout.Y_AXIS));
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String usenamepost = null;
        String usenamefollowing = null;

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");
            String sql1 = "select * from following where username = '" + usernameorigin + "'";
            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);
            Boolean flag = true;

            while (rs1.next()) {

                usenamefollowing = rs1.getString("username");
                if (usenamefollowing.equals(usernameorigin)) {

                    //***********************open post table and bring posts 
                    String following = rs1.getString("following");
                    String sql2 = "select * from post where username = '" + following + "'";
                    Statement st2 = conn.createStatement();
                    ResultSet rs2 = st2.executeQuery(sql2);

                    while (rs2.next()) {

                        usenamepost = rs2.getString("username");
                        if (following.equals(usenamepost)) {
                            String content = rs2.getString("content");
                            String description = rs2.getString("description");
                            String idpost = String.valueOf(rs2.getInt("idpost"));
                            String keyword = "";

                            //***********************open post_hashtag table then hashtag to get the keywords:
                            String sql3 = "select * from post_hashtag where idpost = '" + idpost + "'";
                            Statement st3 = conn.createStatement();
                            ResultSet rs3 = st3.executeQuery(sql3);
                            while (rs3.next()) {
                                String idpost2 = String.valueOf(rs3.getInt("idpost"));
                                if (idpost.equals(idpost2)) {
                                    String idhastag1 = String.valueOf(rs3.getInt("idhashtag"));
                                    System.out.println("hashhhhhhhh: " + idhastag1);

                                    //*********open hashtag:
                                    String sql4 = "select * from hashtag where idhashtag ='" + idhastag1 + "'";
                                    Statement st4 = conn.createStatement();
                                    ResultSet rs4 = st4.executeQuery(sql4);
                                    while (rs4.next()) {
                                        String idhastag2 = String.valueOf(rs4.getInt("idhashtag"));
                                        if (idhastag2.equals(idhastag1)) {
                                            keyword = rs4.getString("keyword");
                                            System.out.println("keyyy: " + idhastag1);

                                        }//if idhashtag==idhashtag
                                    }//while
                                }//if (idpost==idpost

                            }//while

                            jhh.setLayout(null);//new FlowLayout());

                            //**********************add the jpanel in which the post will be displayed on
                            jhh.setPreferredSize(new Dimension(this.scrollPane2.getWidth(), 450));
                            jhh.setBackground(new java.awt.Color(255, 255, 255));//18, 33, 139));//blue
                            postbase.add(jhh);

                            //**********************************************************************************************
                            //********************create rounded jlabel to dispaly user photo on it:
                            JLabelRound rondlabel = new JLabelRound();
                            rondlabel.setBounds(10, 10, 60, 60);//******(left/right, up/down, width,height)
                            rondlabel.setOpaque(true);
                            rondlabel.setBackground(new java.awt.Color(250, 250, 250));
                            rondlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                            //********************Open appuser table then person table to get his/her photo:
                            String sql4 = "select * from appuser";
                            Statement st4 = conn.createStatement();
                            ResultSet rs4 = st4.executeQuery(sql4);
                            while (rs4.next()) {
                                String appuser_username = rs4.getString("username");
                                if (appuser_username.equals(usenamepost)) {

                                    String email = rs4.getString("email");
                                    String sql5 = "select * from person";
                                    Statement st5 = conn.createStatement();
                                    ResultSet rs5 = st5.executeQuery(sql5);
                                    while (rs5.next()) {
                                        if (email.equals(rs5.getString("email"))) {
                                            System.out.println("hi: " + rs5.getString("email"));
                                            //*********************** change scale for icon to fit the roundedlabel:
                                            Icon icon2 = new ImageIcon(rs5.getString("image"));
                                            System.out.println("hi: " + rs5.getString("image"));
                                            ImageIcon imgicon2 = new ImageIcon(rs5.getString("image"));
                                            Image img2 = imgicon2.getImage();
                                            Image imgscale2 = img2.getScaledInstance(rondlabel.getWidth(), rondlabel.getHeight(), Image.SCALE_SMOOTH);
                                            ImageIcon scaledIcon2 = new ImageIcon(imgscale2);
                                            rondlabel.setIcon(scaledIcon2);

                                        }
                                    }//while (person table
                                }//if(appusername==following)

                            }//while appuser table

                            jhh.add(rondlabel);
                            //**********************************************************************************************

                            //********************create jlabel to display username post:
                            JLabel label2 = new JLabel(following);
                            label2.setBounds(80, 15, 100, 30);//******(left/right, up/down, width,height)
                            label2.setOpaque(true);
                            label2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            label2.setBackground(new java.awt.Color(255, 255, 255));
                            label2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                            label2.setForeground(new java.awt.Color(18, 33, 139));
                            //   label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                            /////////////////////////////////////////////////////////
                            label2.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent e) {
                                    //   String idpost = ((JButton) e.getSource()).getName();
                                    new otherusers22(idpost).setVisible(true);
                                    dispose();
                                }
                            });
                            /////////////////////////////////////////////////////////

                            jhh.add(label2);

                            //**********************************************************************************************
                            //********************display content:
                            JLabel label1 = new JLabel(idpost);
                            label1.setBounds(10, 70, 350, 300); //size.width, size.height);//******(left/right, up/down, width,height)
                            //*********************** change scale for icon to fit the label:
                            Icon icon = new ImageIcon(content);
                            ImageIcon imgicon = new ImageIcon(content);
                            Image img = imgicon.getImage();
                            Image imgscale = img.getScaledInstance(label1.getWidth(), label1.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon = new ImageIcon(imgscale);
                            label1.setIcon(scaledIcon);
                            label1.setOpaque(true);
                            label1.setBackground(new java.awt.Color(250, 250, 250));
                            jhh.add(label1);

                            //******************** add mouse listner in order to like the post:
                            //label1.addMouseListener(mouseListener);
                            //**********************************************************************************************
                            //********************add jtextarea to add the desc.
                            JTextArea textarea1 = new JTextArea(description + "\n" + keyword);
                            textarea1.setLineWrap(true);
                            textarea1.setWrapStyleWord(true);
                            textarea1.setBounds(365, 70, 330, 300);//******(left/right, up/down, width,height)
                            textarea1.setOpaque(true);
                            textarea1.setBackground(new java.awt.Color(255, 255, 255));
                            textarea1.setEditable(false);
                            textarea1.setColumns(20);
                            textarea1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                            textarea1.setForeground(new java.awt.Color(0, 0, 0));//18, 33, 139));
                            textarea1.setRows(5);
                            textarea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(18, 33, 139), 2));
                            // textarea1.setSelectionColor(new java.awt.Color(244, 216, 19));
                            jhh.add(textarea1);

                            //**********************************************************************************************
                            //********************* create view likes button:
                            JLabel like = new JLabel(idpost);
                            like.setBounds(10, 375, 70, 70);
                            like.setOpaque(true);

                            like.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            like.setHorizontalAlignment(SwingConstants.CENTER);
                            like.setVerticalAlignment(SwingConstants.CENTER);

                            //*********************** change scale for icon to fit the label:
                            //********************* open likes table verfiy if user like this post or not:
                            String sql6 = "select * from likes";

                            Statement st6 = conn.createStatement();
                            ResultSet rs6 = st6.executeQuery(sql6);
                            ImageIcon imgicon1 = new ImageIcon(getClass().getResource("/images0/emptyh.png"));
                            while (rs6.next()) {
                                if (String.valueOf(rs6.getInt("idpost")).equals(idpost)) {
                                    if (rs6.getString("username").equals(following)) {
                                        if (rs6.getString("userlike").equals(usernameorigin)) {
                                            System.out.println("well here we go...");
                                            imgicon1 = new ImageIcon(getClass().getResource("/images0/like.png"));
                                            likeflag = true;
                                        }
                                    }
                                }

                            }

                            //imgicon1 = new ImageIcon(getClass().getResource("/images0/emptyh.png"));
                            Image img1 = imgicon1.getImage();
                            Image imgscale1 = img1.getScaledInstance(like.getWidth(), like.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon1 = new ImageIcon(imgscale1);
                            like.setIcon(scaledIcon1);
                            like.setOpaque(true);
                            like.setBackground(new java.awt.Color(255, 255, 255));
                            like.setToolTipText("Like.");
                            //******************** add mouse listner in order to like the post:
                            like.addMouseListener(mouseListener);
                            //  like.addMouseListener(mouseListener);

                            jhh.add(like);
                            //********************************view likeeee
                            JLabel viewlike = new JLabel(idpost);
                            viewlike.setBounds(90, 375, 70, 70);
                            viewlike.setOpaque(true);

                            viewlike.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            viewlike.setHorizontalAlignment(SwingConstants.CENTER);
                            viewlike.setVerticalAlignment(SwingConstants.CENTER);
                            ImageIcon imgicon4= new ImageIcon(getClass().getResource("/images0/emptyh.png"));
                            Image img4 = imgicon4.getImage();
                            Image imgscale4 = img4.getScaledInstance(viewlike.getWidth(), viewlike.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon4 = new ImageIcon(imgscale4);
                            viewlike.setIcon(scaledIcon4);
                            viewlike.setOpaque(true);
                            viewlike.setBackground(new java.awt.Color(255, 255, 255));
                            viewlike.setToolTipText("view Like.");
                            //******************** add mouse listner in order to like the post:
                          viewlike.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent e) {
                                    //   String idpost = ((JButton) e.getSource()).getName();
                                    new Likes(idpost).setVisible(true);
                                    
                                }
                            });

                            jhh.add(viewlike);
                            //**********************************************************************************************
                            //********************* create add comment button:
                            JLabel comm = new JLabel();
                            comm.setBounds(170, 375, 70, 70);
                            comm.setOpaque(true);

                            comm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            comm.setHorizontalAlignment(SwingConstants.CENTER);
                            comm.setVerticalAlignment(SwingConstants.CENTER);

                            //*********************** change scale for icon to fit the label:
                            //Icon icon2=new ImageIcon(getClass().getResource("images0/heart.png"));
                            ImageIcon imgicon2 = new ImageIcon(getClass().getResource("/images0/comm1.png"));
                            Image img2 = imgicon2.getImage();
                            Image imgscale2 = img2.getScaledInstance(like.getWidth(), like.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon2 = new ImageIcon(imgscale2);
                            comm.setIcon(scaledIcon2);
                            comm.setOpaque(true);
                            comm.setBackground(new java.awt.Color(250, 250, 250));
                            comm.setToolTipText("Comment.");
                            comm.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent e) {
                                    //   String idpost = ((JButton) e.getSource()).getName();
                                    new Comments(idpost,usernameorigin).setVisible(true);
                                    
                                }
                            });
                            //  comm.addMouseListener(mouseListener);

                            jhh.add(comm);

                            //**********************************************************************************************
                            //********************* create save button:
                            JLabel save = new JLabel(idpost);
                            save.setBounds(630, 375, 70, 70);//(170, 375, 70, 70);
                            save.setOpaque(true);

                            save.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            save.setHorizontalAlignment(SwingConstants.CENTER);
                            save.setVerticalAlignment(SwingConstants.CENTER);

                            //*********************** change scale for icon to fit the label:
                            //ImageIcon imgicon3 = new ImageIcon(getClass().getResource("/images0/saved.png"));
                            //********************* open likes table verfiy if user like this post or not:
                            String sql7 = "select * from savepost";
                            Statement st7 = conn.createStatement();
                            ResultSet rs7 = st7.executeQuery(sql7);
                            ImageIcon imgicon3 = new ImageIcon(getClass().getResource("/images0/unsaved.png"));
                            while (rs7.next()) {
                                if (String.valueOf(rs7.getInt("idpost")).equals(idpost)) {
                                    if (rs7.getString("username").equals(usernameorigin)) {
                                        System.out.println("well here we go22...");
                                        imgicon3 = new ImageIcon(getClass().getResource("/images0/saved.png"));
                                        saveflag = true;

                                    }
                                }

                            }
                            Image img3 = imgicon3.getImage();
                            Image imgscale3 = img3.getScaledInstance(like.getWidth(), like.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon3 = new ImageIcon(imgscale3);
                            save.setIcon(scaledIcon3);
                            save.setOpaque(true);
                            save.setBackground(new java.awt.Color(250, 250, 250));
                            save.setToolTipText("save.");
                            save.addMouseListener(mouseListenersave);
                            jhh.add(save);

                            //**********************************************************************************************
                            //********************create new jpanel:
                            jhh = new JPanel();
                            jhh.setVisible(true);
                            flag = false;
                            if (!flag) {
                                flag = true;
                                jhh.setBackground(new java.awt.Color(240, 240, 240));
                                jhh.setPreferredSize(new Dimension(10, 15));
                                postbase.add(jhh);
                                jhh = new JPanel();
                                jhh.setVisible(true);
                            }//inner if
                        }//end if

                    }

                }//if
            }//while

            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.err.println(ex);

        }//catch
    }//end function

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println(actionEvent.getActionCommand());

        }
    };

    MouseListener mouseListener = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {

            likeflag = false;
            if (e.getClickCount() == 2) {
                //******************** first we have to know which label makes the action:
                String idpost = ((JLabel) e.getSource()).getText();
                JLabel like = (JLabel) e.getSource();
                //******************* Then we have to open likes table:(we have id post and username)
                try {

                    //******************* open connection with mysql:
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");
                    String sql2 = "select * from post ORDER BY date DESC";
                    Statement st2 = conn.createStatement();
                    ResultSet rs2 = st2.executeQuery(sql2);
                    String userpost = "";
                    while (rs2.next()) {
                        if (idpost.equals(String.valueOf(rs2.getInt("idpost")))) {
                            userpost = rs2.getString("username");

                        }

                    }
                    //********************* open likes table verfiy if user like this post or not:
                    String sql6 = "select * from likes";

                    Statement st6 = conn.createStatement();
                    ResultSet rs6 = st6.executeQuery(sql6);
                    ImageIcon imgicon1 = new ImageIcon(getClass().getResource("/images0/like.png"));
                    while (rs6.next()) {
                        if (String.valueOf(rs6.getInt("idpost")).equals(idpost)) {
                            if (rs6.getString("username").equals(userpost)) {
                                if (rs6.getString("userlike").equals(usernameorigin)) {
                                    System.out.println("well here we go...");
                                    //************** delete like:
                                    String query = "delete from likes where username = ? AND idpost=?";
                                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                                    preparedStmt.setString(1, userpost);
                                    preparedStmt.setString(2, idpost);
                                    preparedStmt.execute();
                                    imgicon1 = new ImageIcon(getClass().getResource("/images0/emptyh.png"));
                                    likeflag = true;
                                }
                            }
                        }

                    }
                    if (!likeflag) {
                        //********************* insert data into post table:
                        String sql1 = "insert into likes (username,idpost,userlike) values (?,?,?)";
                        PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                        pstmt1.setString(1, userpost);
                        pstmt1.setString(2, idpost);
                        pstmt1.setString(3, usernameorigin);
                        pstmt1.executeUpdate();
                    }
                    Image img1 = imgicon1.getImage();
                    Image imgscale1 = img1.getScaledInstance(like.getWidth(), like.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon1 = new ImageIcon(imgscale1);
                    like.setIcon(scaledIcon1);
                    //like.setOpaque(true);
                    //like.setBackground(new java.awt.Color(255, 255, 255));
                    //like.setToolTipText("Like.");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    System.err.println(ex);
                }
            }//end if
  
        }
    };

    /////////////////////////*********************************///////////////////////////////////
    MouseListener mouseListenersave = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {

            saveflag = false;
            if (e.getClickCount() == 1) {
                //******************** first we have to know which label makes the action:
                String idpost = ((JLabel) e.getSource()).getText();
                JLabel save = (JLabel) e.getSource();
                //******************* Then we have to open likes table:(we have id post and username)
                try {

                    //******************* open connection with mysql:
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");

                    //********************* open save table verfiy if user like this post or not:
                    String sql6 = "select * from savepost";

                    Statement st6 = conn.createStatement();
                    ResultSet rs6 = st6.executeQuery(sql6);
                    ImageIcon imgicon1 = new ImageIcon(getClass().getResource("/images0/saved.png"));
                    while (rs6.next()) {
                        if (String.valueOf(rs6.getInt("idpost")).equals(idpost)) {
                            if (rs6.getString("username").equals(usernameorigin)) {

                                System.out.println("well here we go222...");
                                //************** delete save:
                                String query = "delete from savepost where username = ? AND idpost=?";
                                PreparedStatement preparedStmt = conn.prepareStatement(query);
                                preparedStmt.setString(1, usernameorigin);
                                preparedStmt.setString(2, idpost);
                                preparedStmt.execute();
                                imgicon1 = new ImageIcon(getClass().getResource("/images0/unsaved.png"));
                                saveflag = true;

                            }
                        }

                    }
                    if (!saveflag) {
                        //********************* insert data into post table:
                        String sql1 = "insert into savepost (idpost,username) values (?,?)";
                        PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                        pstmt1.setString(1, idpost);
                        pstmt1.setString(2, usernameorigin);
                        pstmt1.executeUpdate();
                    }
                    Image img1 = imgicon1.getImage();
                    Image imgscale1 = img1.getScaledInstance(save.getWidth(), save.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon1 = new ImageIcon(imgscale1);
                    save.setIcon(scaledIcon1);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    System.err.println(ex);
                }
            }
        }
    };

    MouseListener mouseListener2 = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1) {
                mainpage22 mainpage = new mainpage22();
                mainpage.dispose();
                userprofile22 profile = new userprofile22();
                profile.setVisible(true);

            }//if

        }

    };

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        base = new javax.swing.JPanel();
        min = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabelRound1 = new rounded_label.JLabelRound();
        out = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        explore = new javax.swing.JLabel();
        addpost = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabelusername = new javax.swing.JLabel();
        profile = new javax.swing.JLabel();
        scrollPane2 = new java.awt.ScrollPane();
        jLabelRound3 = new rounded_label.JLabelRound();
        jLabelRound2 = new rounded_label.JLabelRound();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        base.setBackground(new java.awt.Color(9, 16, 69));

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

        jPanel1.setBackground(new java.awt.Color(9, 16, 69));

        jPanel2.setBackground(new java.awt.Color(244, 216, 19));

        jPanel3.setBackground(new java.awt.Color(18, 33, 139));

        jPanel4.setBackground(new java.awt.Color(244, 216, 19));

        jPanel5.setBackground(new java.awt.Color(18, 33, 139));

        jPanel6.setBackground(new java.awt.Color(244, 216, 19));

        jPanel7.setBackground(new java.awt.Color(18, 33, 139));

        jPanel8.setBackground(new java.awt.Color(18, 33, 139));

        jPanel9.setBackground(new java.awt.Color(18, 33, 139));

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
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jLabelRound1.setText("jLabelRound1");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addComponent(jLabelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addComponent(jLabelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

        addpost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addpost.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addpost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addpostMouseClicked(evt);
            }
        });

        jLabelusername.setText("@username");
        jLabelusername.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelusername.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelusername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelusernameMouseClicked(evt);
            }
        });

        profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabelusername, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                        .addComponent(profile, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addpost, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(explore, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(out, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelusername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(addpost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(explore, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(contact, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(out, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(profile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabelRound3.setText("jLabelRound3");
        scrollPane2.add(jLabelRound3);

        jLabelRound2.setText("jLabelRound2");
        scrollPane2.add(jLabelRound2);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(scrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout baseLayout = new javax.swing.GroupLayout(base);
        base.setLayout(baseLayout);
        baseLayout.setHorizontalGroup(
            baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, baseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(baseLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        baseLayout.setVerticalGroup(
            baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(min, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(base, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void outMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_outMouseClicked
        // TODO add your handling code here:
        // close
        this.dispose();
    }//GEN-LAST:event_outMouseClicked

    private void addpostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addpostMouseClicked
        // TODO add your handling code here:
        // close
        this.dispose();
        new addpost22().setVisible(true);
    }//GEN-LAST:event_addpostMouseClicked

    private void jLabelusernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelusernameMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new userprofile22().setVisible(true);
    }//GEN-LAST:event_jLabelusernameMouseClicked

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new mainpage22().setVisible(true);
    }//GEN-LAST:event_backMouseClicked

    private void profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileMouseClicked
        // TODO add your handling code here:
        new userprofile22().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_profileMouseClicked

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
            java.util.logging.Logger.getLogger(mainpage22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainpage22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainpage22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainpage22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainpage22().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addpost;
    private javax.swing.JLabel back;
    private javax.swing.JPanel base;
    private javax.swing.JLabel close;
    private javax.swing.JLabel contact;
    private javax.swing.JLabel explore;
    private javax.swing.JLabel jLabel3;
    private rounded_label.JLabelRound jLabelRound1;
    private rounded_label.JLabelRound jLabelRound2;
    private rounded_label.JLabelRound jLabelRound3;
    private javax.swing.JLabel jLabelusername;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
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
    private javax.swing.JLabel out;
    private javax.swing.JLabel profile;
    private java.awt.ScrollPane scrollPane2;
    // End of variables declaration//GEN-END:variables
}
