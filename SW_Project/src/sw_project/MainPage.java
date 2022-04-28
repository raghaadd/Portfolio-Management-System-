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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import rounded_label.JLabelRound;

public class MainPage extends javax.swing.JFrame {

    JPanel postbase = new JPanel();
    String usernameorigin = "saja123"; //get this value from login page

    /**
     * Creates new form profile2
     */
    public MainPage() {
        initComponents();
        photo.setIcon(new ImageIcon(getClass().getResource("/image/wa0.jpeg")));
        scrollPane2.add(postbase);
        setResizable(false);
        setLocationRelativeTo(null);
        personalphoto();
        makepanel();

    }

    public void personalphoto() {
        //*************************set username for jlabelusername:
        jLabelusername.setText("@" + usernameorigin);
        jLabelusername.addMouseListener(mouseListener2);
        //*******************************we have to open following table then compare username that we have with username in follwoing table then bring posts from post table:
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String usenamepost = null;
        String usenamefollowing = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");
            String sql1 = "select * from appuser";
            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);
            while (rs1.next()) {
                if (usernameorigin.equals(rs1.getString("username"))) {
                    String sql2 = "select * from person";
                    Statement st2 = conn.createStatement();
                    ResultSet rs2 = st2.executeQuery(sql2);
                    while (rs2.next()) {
                        if (rs1.getString("email").equals(rs2.getString("email"))) {
                            //*********************** change scale for icon to fit the roundedlabel:
                            Icon icon2 = new ImageIcon(rs2.getString("image"));
                            System.out.println("hi: " + rs2.getString("image"));
                            ImageIcon imgicon2 = new ImageIcon(rs2.getString("image"));
                            Image img2 = imgicon2.getImage();
                            Image imgscale2 = img2.getScaledInstance(jLabelRound1.getWidth(), jLabelRound1.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon2 = new ImageIcon(imgscale2);
                            jLabelRound1.setIcon(scaledIcon2);
                        }//if
                    }//while

                }//if

            }//while
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
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
            String sql1 = "select * from following";
            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);
            Boolean flag = true;
            while (rs1.next()) {
                usenamefollowing = rs1.getString("username");
                if (usenamefollowing.equals(usernameorigin)) {
                    //***********************open post table and bring posts 
                    String following = rs1.getString("following");
                    String sql2 = "select * from post";
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
                            String sql3 = "select * from post_hashtag";
                            Statement st3 = conn.createStatement();
                            ResultSet rs3 = st3.executeQuery(sql3);
                            while (rs3.next()) {
                                String idpost2 = String.valueOf(rs3.getInt("idpost"));
                                if (idpost.equals(idpost2)) {
                                    String idhastag1 = String.valueOf(rs3.getInt("idhashtag"));
                                    //*********open hashtag:
                                    String sql4 = "select * from hashtag";
                                    Statement st4 = conn.createStatement();
                                    ResultSet rs4 = st4.executeQuery(sql4);
                                    while (rs4.next()) {
                                        String idhastag2 = String.valueOf(rs4.getInt("idhashtag"));
                                        if (idhastag2.equals(idhastag1)) {
                                            keyword = rs4.getString("keyword");
                                        }//if idhashtag==idhashtag
                                    }//while
                                }//if (idpost==idpost

                            }//while
                            jhh.setLayout(null);//new FlowLayout());
                            //**********************add the jpanel in which the post will be displayed on
                            jhh.setPreferredSize(new Dimension(150, 500));
                            jhh.setBackground(new java.awt.Color(18, 33, 139));//blue
                            postbase.add(jhh);
                            JLabel label1 = new JLabel(idpost);
                            label1.setBounds(200, 60, 450, 280); //size.width, size.height);//******(left/right, up/down, width,height)
                            //*********************** change scale for icon to fit the label:
                            Icon icon = new ImageIcon(content);
                            ImageIcon imgicon = new ImageIcon(content);
                            Image img = imgicon.getImage();
                            Image imgscale = img.getScaledInstance(label1.getWidth(), label1.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon = new ImageIcon(imgscale);
                            label1.setIcon(scaledIcon);
                            label1.setOpaque(true);
                            label1.setBackground(new java.awt.Color(250, 250, 250));
                            label1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            jhh.add(label1);
                            //******************** add mouse listner in order to like the post:
                            label1.addMouseListener(mouseListener);
                            //********************* create view likes button:
                            JButton jbuttonlike = new JButton();
                            jbuttonlike.setBounds(250, 450, 150, 29);//******(left/right, up/down, width,height)
                            jbuttonlike.setOpaque(true);
                            jbuttonlike.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                            jbuttonlike.setForeground(new java.awt.Color(18, 33, 139));
                            jbuttonlike.setText("View Likes");
                            jbuttonlike.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
                            jbuttonlike.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            jhh.add(jbuttonlike);
                            jbuttonlike.addActionListener(actionListener);

                            //********************* create add comment button:
                            JButton jbuttoncomment = new JButton();
                            jbuttoncomment.setBounds(420, 450, 150, 29);//******(left/right, up/down, width,height)
                            jbuttoncomment.setOpaque(true);
                            jbuttoncomment.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                            jbuttoncomment.setForeground(new java.awt.Color(18, 33, 139));
                            jbuttoncomment.setText("Add Comments");
                            jbuttoncomment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
                            jbuttoncomment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            jhh.add(jbuttoncomment);
                            jbuttoncomment.addActionListener(actionListener);
                            //********************create rounded jlabel to dispaly user photo on it:
                            JLabelRound rondlabel = new JLabelRound();
                            rondlabel.setBounds(200, 5, 50, 50);//******(left/right, up/down, width,height)
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
                            //********************create jlabel to display username post:
                            JLabel label2 = new JLabel(following);
                            label2.setBounds(255, 15, 100, 30);//******(left/right, up/down, width,height)
                            label2.setOpaque(true);
                            label2.setBackground(new java.awt.Color(250, 250, 250));
                            label2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                            label2.setForeground(new java.awt.Color(18, 33, 139));
                            label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                            jhh.add(label2);

                            //********************add jtextarea to add the desc.
                            JTextArea textarea1 = new JTextArea(description + "\n" + keyword);
                            textarea1.setBounds(198, 345, 455, 100);//******(left/right, up/down, width,height)
                            textarea1.setOpaque(true);
                            textarea1.setBackground(new java.awt.Color(250, 250, 250));
                            textarea1.setEditable(false);
                            textarea1.setColumns(20);
                            textarea1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                            textarea1.setForeground(new java.awt.Color(18, 33, 139));
                            textarea1.setRows(5);
                            textarea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(18, 33, 139), 3));
                            textarea1.setSelectionColor(new java.awt.Color(244, 216, 19));
                            jhh.add(textarea1);
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
            if (e.getClickCount() == 2) {
                //******************** first we have to know which label makes the action:
                String idpost = ((JLabel) e.getSource()).getText();
                //******************* Then we have to open likes table:(we have id post and username)
                try {
                    //******************* open connection with mysql:
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");

                    //********************* insert data into post table:
                    String sql1 = "insert into likes (username,idpost) values (?,?)";
                    PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                    pstmt1.setString(1, usernameorigin);
                    pstmt1.setString(2, idpost);
                    pstmt1.executeUpdate();
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
                MainPage mainpage=new MainPage();
                mainpage.dispose();
                profile2 profile=new profile2();
                profile.setVisible(true);

            }//if

        }

    };

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        base = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        scrollPane2 = new java.awt.ScrollPane();
        photo = new javax.swing.JLabel();
        jLabelRound1 = new rounded_label.JLabelRound();
        jLabelusername = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        base.setBackground(new java.awt.Color(255, 255, 255));

        scrollPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        photo.setBackground(new java.awt.Color(255, 255, 255));
        photo.setForeground(new java.awt.Color(255, 255, 255));
        photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        photo.setOpaque(true);

        jLabelRound1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user32.png"))); // NOI18N

        jLabelusername.setBackground(new java.awt.Color(255, 255, 255));
        jLabelusername.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabelusername.setForeground(new java.awt.Color(18, 33, 139));
        jLabelusername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelusername.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton1.setBackground(new java.awt.Color(18, 33, 139));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add Post");

        jButton2.setBackground(new java.awt.Color(18, 33, 139));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Contact Us");

        jButton3.setBackground(new java.awt.Color(18, 33, 139));
        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Log Out");

        jButton4.setBackground(new java.awt.Color(18, 33, 139));
        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Expolre");

        javax.swing.GroupLayout baseLayout = new javax.swing.GroupLayout(base);
        base.setLayout(baseLayout);
        baseLayout.setHorizontalGroup(
            baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(baseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(photo, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(baseLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelusername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(158, 158, 158)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        baseLayout.setVerticalGroup(
            baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(baseLayout.createSequentialGroup()
                        .addComponent(jLabelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelusername, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(photo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel base;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private rounded_label.JLabelRound jLabelRound1;
    private javax.swing.JLabel jLabelusername;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel photo;
    private java.awt.ScrollPane scrollPane2;
    // End of variables declaration//GEN-END:variables
}
