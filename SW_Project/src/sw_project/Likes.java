/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw_project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.*;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import rounded_label.JLabelRound;

/**
 *
 * @author Dell E7280 Pro
 */
public class Likes extends javax.swing.JFrame {

    /**
     * Creates new form Likes
     */
    int idpost = 0;
    JPanel postbase = new JPanel();

    public Likes() {
        initComponents();
        //getusername();
    }

    public Likes(String idpost) {
        initComponents();
        super.pack();
        super.setLocationRelativeTo(null);
        scrollPane1.add(postbase);
        this.idpost = Integer.parseInt(idpost);
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
            String sql1 = "select * from Likes";
            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);
            while (rs1.next()) {

                if (idpost == rs1.getInt("idpost")) {
                    jhh.setLayout(null);//new FlowLayout());
                    //**********************add the jpanel
                    jhh.setPreferredSize(new Dimension(100, 50));
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
                        if (rs1.getString("userlike").equals(rs2.getString("username"))) {
                            System.out.println("hiii222 like  " + rs1.getString("userlike"));
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
                    String userlike = rs1.getString("userlike");
                    JLabel label2 = new JLabel(rs1.getString("userlike"));
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
                            new otherusers22(userlike).setVisible(true);
                            dispose();
                            firstpage22.mainpage.dispose();//this is an object declare in firstpage22.java
                        }
                    });
                    /////////////////////////////////////////////////////////
                    scrollPane1.add(postbase);

                    jhh.add(label2);
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        scrollPane1 = new java.awt.ScrollPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(244, 216, 19));

        jPanel2.setBackground(new java.awt.Color(18, 33, 139));

        jPanel3.setBackground(new java.awt.Color(26, 162, 163));

        jLabel2.setFont(new java.awt.Font("Goudy Old Style", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Likes");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images0/previous.png"))); // NOI18N
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images0/coml_1.jpg"))); // NOI18N
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Likes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Likes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Likes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Likes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //COMPAR RS GET INT ID POST 
        //IF WAS EQUL GET USER LIKE 
        //IN PANEL WE HAVE TO SHOW USER LIKE 
        //TO GIVE HIS PHOT INEED TO OPEN APPUSER  AND KNOW RS GETSTRING GIVE EMAIL THEN FROM PERSON TABLE WE CAN GET USER PHTO

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Likes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private java.awt.ScrollPane scrollPane1;
    // End of variables declaration//GEN-END:variables
}
