/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw_project;

import java.awt.Dimension;
import java.awt.Image;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import rounded_label.JLabelRound;

/**
 *
 * @author Dell E7280 Pro
 */
public class Commentsforprofileuser extends javax.swing.JFrame {

    /**
     * Creates new form Comments
     */
    int idpost = 0;
    String usernameorigin = "";//name of user who want to add comment
    String usernamepost = "";// name of user who own the post
    JPanel postbase = new JPanel();

    public Commentsforprofileuser() {
        initComponents();

    }

    public Commentsforprofileuser(String idpost, String username) {
        initComponents();
        super.pack();
        super.setLocationRelativeTo(null);
        scrollPane1.add(postbase);
        this.idpost = Integer.parseInt(idpost);
        this.usernameorigin = username;
        getusername();

    }

    public void getusername() {
        JPanel jhh = new JPanel();
        postbase.setLayout(new BoxLayout(postbase, BoxLayout.Y_AXIS));
        Connection conn = null;
        ResultSet rs = null;
        Boolean flag = true;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");
            String sql1 = "select * from comments";
            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);
            while (rs1.next()) {

                if (idpost == rs1.getInt("idpost")) {
                    usernamepost = rs1.getString("username");
                    jhh.setLayout(null);//new FlowLayout());
                    //**********************add the jpanel
                    System.out.println("commennttts " + idpost);
                    jhh.setPreferredSize(new Dimension(100, 150));
                    jhh.setBackground(new java.awt.Color(255, 255, 255));//18, 33, 139));//blue
                    postbase.add(jhh);
                    JLabelRound rondlabel = new JLabelRound();
                    rondlabel.setBounds(10, 10, 60, 60);//******(left/right, up/down, width,height)
                    rondlabel.setOpaque(true);
                    rondlabel.setBackground(new java.awt.Color(250, 250, 250));
                    rondlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                    //********************Open appuser table then person table to get his/her photo:
                    String sql2 = "select * from appuser";
                    Statement st2 = conn.createStatement();
                    ResultSet rs2 = st2.executeQuery(sql2);
                    while (rs2.next()) {
                        if (rs1.getString("usercomment").equals(rs2.getString("username"))) {
                            System.out.println("commennttts " + rs1.getString("usercomment"));
                            String sql3 = "select * from person";
                            Statement st3 = conn.createStatement();
                            ResultSet rs3 = st3.executeQuery(sql3);
                            while (rs3.next()) {
                                if (rs2.getString("email").equals(rs3.getString("email"))) {
                                    //*********************** change scale for icon to fit the roundedlabel:
                                    Icon icon2 = new ImageIcon(rs3.getString("image"));
                                    ImageIcon imgicon2 = new ImageIcon(rs3.getString("image"));
                                    Image img2 = imgicon2.getImage();
                                    Image imgscale2 = img2.getScaledInstance(rondlabel.getWidth(), rondlabel.getHeight(), Image.SCALE_SMOOTH);
                                    ImageIcon scaledIcon2 = new ImageIcon(imgscale2);
                                    rondlabel.setIcon(scaledIcon2);

                                }
                            }//while (person table)
                        }//

                    }//while appuser table

                    jhh.add(rondlabel);

                    //********************create jlabel to display username post:
                    String usercomment = rs1.getString("usercomment");
                    JLabel label2 = new JLabel(rs1.getString("usercomment"));
                    label2.setBounds(80, 15, 100, 30);//******(left/right, up/down, width,height)
                    label2.setOpaque(true);
                    label2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    label2.setBackground(new java.awt.Color(255, 255, 255));
                    label2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                    label2.setForeground(new java.awt.Color(26, 162, 163));
                    //   label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    /////////////////////////////////////////////////////////
                    label2.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            new otherusers22(usercomment).setVisible(true);
                            dispose();
                            firstpage22.mainpage.dispose();//this is an object declare in firstpage22.java
                        }
                    });
                    //********************create jlabel to display username post:
                    JLabel label3 = new JLabel(rs1.getString("content"));
                    label3.setBounds(30, 80, 100, 30);//******(left/right, up/down, width,height)
                    label3.setOpaque(true);
                    label3.setBackground(new java.awt.Color(255, 255, 255));
                    label3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                    label3.setForeground(new java.awt.Color(26, 162, 163));
                    /////////////////////////////////////////////////////////

                    jhh.add(label2);
                    jhh.add(label3);

                    JLabel label4 = new JLabel(rs1.getString("idcomments"));
                    label4.setIcon(new ImageIcon(getClass().getResource("/images0/x1.png")));
                    label4.setToolTipText("Hide this comment.");
                    label4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    label4.setBounds(200, 15, 100, 30);//******(left/right, up/down, width,height)
                    label4.setOpaque(true);
                    label4.setBackground(new java.awt.Color(255, 255, 255));
                    label4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                    label4.setForeground(new java.awt.Color(26, 162, 163));
                    /////////////////////////////////////////////////////////
                    //*********** add mouse listner for deleting comments:
                    label4.addMouseListener(mouseListener);
                    jhh.add(label4);
                    jhh.setVisible(true);
                    postbase.add(jhh);

                    //********************create new jpanel:
                    jhh = new JPanel();

                    flag = false;
                    if (!flag) {
                        flag = true;
                        jhh.setBackground(new java.awt.Color(240, 240, 240));
                        jhh.setPreferredSize(new Dimension(10, 15));
                        postbase.add(jhh);
                        jhh = new JPanel();
                        jhh.setVisible(true);
                    }//inner if
                }

            }//while
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.err.println(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    MouseListener mouseListener = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");
                String idcomm = ((JLabel) e.getSource()).getText();
                Statement t = conn.createStatement();
                String sql2 = "delete from comments where idcomments ='" + Integer.parseInt(idcomm) + "'";
                t.executeUpdate(sql2);
                conn.setAutoCommit(false);
                conn.commit();
                conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(Commentsforprofileuser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButtonsend = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButtonback = new javax.swing.JButton();
        scrollPane1 = new java.awt.ScrollPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(244, 216, 19));

        jPanel2.setBackground(new java.awt.Color(18, 33, 139));

        jPanel3.setBackground(new java.awt.Color(26, 162, 163));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Goudy Old Style", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Comments");

        jButtonsend.setFont(new java.awt.Font("Goudy Old Style", 0, 12)); // NOI18N
        jButtonsend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images0/send.png"))); // NOI18N
        jButtonsend.setActionCommand("send");
        jButtonsend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsendActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButtonback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images0/previous.png"))); // NOI18N
        jButtonback.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonback, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonsend, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonsend, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonback, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images0/coml.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonbackActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonbackActionPerformed

    private void jButtonsendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsendActionPerformed
        // TODO add your handling code here:
        try {
            //******************* open connection with mysql:
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");
            String sql2 = "select * from post";
            Statement st2 = conn.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            while (rs2.next()) {

                if (idpost == rs2.getInt("idpost")) {
                    usernamepost = rs2.getString("username");
                    break;
                }
            }

            //********************* insert data into post table:
            String sql1 = "insert into comments (username,content,idpost,usercomment) values (?,?,?,?)";
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setString(1, usernamepost);
            pstmt1.setString(2, jTextArea1.getText());
            pstmt1.setInt(3, idpost);
            pstmt1.setString(4, usernameorigin);
            pstmt1.executeUpdate();
            jTextArea1.setText("");
            //getusername();

        } catch (SQLException ex) {
            Logger.getLogger(Commentsforprofileuser.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonsendActionPerformed

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
            java.util.logging.Logger.getLogger(Commentsforprofileuser.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Commentsforprofileuser.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Commentsforprofileuser.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Commentsforprofileuser.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Commentsforprofileuser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonback;
    private javax.swing.JButton jButtonsend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private java.awt.ScrollPane scrollPane1;
    // End of variables declaration//GEN-END:variables
}
