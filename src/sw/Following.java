/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import rounded.JLabelRound;
import static sw.Followers1.usernameorigin;

/**
 *
 * @author Dell E7280 Pro
 */
public class Following extends javax.swing.JFrame {

    /**
     * Creates new form Following
     */
    JPanel postbase = new JPanel();
    String username = "";
    static String usernameorigin;//

    

    public Following(String usernameorigin) {
        this.usernameorigin = usernameorigin;
        initComponents();
        super.pack();
        super.setLocationRelativeTo(null);
        scrollPane1.add(postbase);
       // this.username = username;
        giveFollowing();

    }

    public void giveFollowing() {
        JPanel jhh = new JPanel();
        postbase.setLayout(new BoxLayout(postbase, BoxLayout.Y_AXIS));
        Connection conn = null;
        ResultSet rs = null;
        Boolean flag = true;
        
        
        //go to like TABLE
        //WHELE WE HAVE NEXT
        int totalcomment = 0;
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject","root","iNEEDtostudy@202");
            
            
            String sql1 = "select * from Following where username = '"+usernameorigin+"'";
            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);
            
            
            if (rs1.next() == false){
               // System.out.println("user::: no?");
                //we will display a text telling the user to follow people
                    //post from people you follow will be displayed here
                    jhh.setLayout(null);//new FlowLayout());
                    //**********************add the jpanel in which the post will be displayed on
                            jhh.setPreferredSize(new Dimension(this.scrollPane1.getWidth(),this.scrollPane1.getHeight()));
                            jhh.setBackground(new java.awt.Color(255,255,255));//18, 33, 139));//blue
                            postbase.add(jhh);
                            
                            
                            
                    //************label that have the photo 
                    JLabel nofo = new JLabel();
                    nofo.setBounds(10, 50, 70, 70);
                    //nofo.setBackground(Color.red);
                    //nofo.setOpaque(true);
                    
                    Icon icon=new ImageIcon(getClass().getResource("images0/nofo.png"));
                    ImageIcon imgicon=new ImageIcon(getClass().getResource("images0/nofo.png"));
                    Image img=imgicon.getImage();
                    Image imgscale=img.getScaledInstance(nofo.getWidth(), nofo.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon=new ImageIcon(imgscale);
                    nofo.setIcon(scaledIcon);
                    nofo.setOpaque(true);
                    nofo.setBackground(new java.awt.Color(250,250,250));
                    
                    
                    jhh.add(nofo);
                    
                    //************label that tell the user "no posts to show you have not followed anyone yet"
                    JLabel nofotext = new JLabel();
                    nofotext.setBounds(10, 90, 300, 90);
                    nofotext.setText("no comments. add one!");
                    //nofotext.setBackground(Color.YELLOW);
                    nofotext.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
                    nofotext.setForeground(new java.awt.Color(18, 33, 139));
                    nofotext.setBackground(Color.white);
                    nofotext.setOpaque(true);
                    
                    jhh.add(nofotext);
                
                
            }else{
            sql1 = "select * from Following where username = '"+usernameorigin+"'";
            st1 = conn.createStatement();
            rs1 = st1.executeQuery(sql1);
            
            while (rs1.next()) {
                // i need to compare data id post in data base and whta data i have 
                if (rs1.getString("username").equals(usernameorigin)) {
                    totalcomment++;
                  //  System.out.println("following" + rs1.getString("username"));
                    jhh.setLayout(null);//new FlowLayout());
                    //**********************add the jpanel
                    jhh.setPreferredSize(new Dimension(100, 100));
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
                        if (rs1.getString("following").equals(rs2.getString("username"))) {
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
                    String following= rs1.getString("following");
                    JLabel label2 = new JLabel(rs1.getString("following"));
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
                            new otherusers22(usernameorigin,following).setVisible(true);
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
                        jhh.setPreferredSize(new Dimension(10, 5));
                        postbase.add(jhh);
                        jhh = new JPanel();
                        jhh.setVisible(true);
                    }//{if idpost=postid)
                }

            }//while1
        }
            count.setText(String.valueOf(totalcomment));

        } catch (SQLException ex) {
            //Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
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
        count = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(244, 216, 19));

        jPanel2.setBackground(new java.awt.Color(18, 33, 139));

        jPanel3.setBackground(new java.awt.Color(26, 162, 163));

        jLabel2.setFont(new java.awt.Font("Goudy Old Style", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Following List");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sw/images0/previous.png"))); // NOI18N
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 341, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(count, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            java.util.logging.Logger.getLogger(Following.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Following.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Following.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Following.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new Following().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel count;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private java.awt.ScrollPane scrollPane1;
    // End of variables declaration//GEN-END:variables
}
