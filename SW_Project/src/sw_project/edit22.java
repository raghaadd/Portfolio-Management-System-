/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw_project;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author LENOVO
 */
public class edit22 extends javax.swing.JFrame {

    /**
     * Creates new form edit22
     */
    String filename="";
    public edit22() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        close.setIcon(new ImageIcon(getClass().getResource("/images0/x1.png")));
        min.setIcon(new ImageIcon(getClass().getResource("/images0/min1.png")));
      //  main.setIcon(new ImageIcon(getClass().getResource("images0/mainpage.jpeg")));
        back.setIcon(new ImageIcon(getClass().getResource("/images0/back3.png")));
      //  my_update("1", "asma");
    }

    
    public void my_update(String postid, String name) {
		
            //    jTextFieldhashtag.setText();
             //   jTextAreadesc.setText(pdescription);
               
                // TODO add your handling code here:
        
                fake.setText(postid);
                try{
            //******************* open connection with mysql:
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");

            //********************* insert data into post table:
            //System.out.println("filepath new:: "+filename);
            String sql8="select * from post where idpost ="+postid+" and username = '"+name+"'";
            Statement st8 = conn.createStatement();
            ResultSet rs8 = st8.executeQuery(sql8);
            if(rs8.next()){
            String postcontent = rs8.getString("content");
            filename = postcontent;
            
            if((!postcontent.equals("")) && postcontent!=null){
                           //************display image************
                           //*********************** change scale for icon to fit the label:
                           //Icon icon6=new ImageIcon(content);
                            ImageIcon imgicon6=new ImageIcon(postcontent);
                            Image img6=imgicon6.getImage();
                            Image imgscale6=img6.getScaledInstance(jLabelsefile.getWidth(), jLabelsefile.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon6=new ImageIcon(imgscale6);
                            jLabelsefile.setIcon(scaledIcon6);
                            jLabelsefile.setOpaque(true);
                            jLabelsefile.setBackground(new java.awt.Color(250,250,250));
                           
                            System.out.println("user::: image found");
                       }
                       else{
                           //************display noimage icon********
                            ImageIcon imgicon6=new ImageIcon(getClass().getResource("/images0/noimage.png"));
                            Image img6=imgicon6.getImage();
                            Image imgscale6=img6.getScaledInstance(jLabelsefile.getWidth(), jLabelsefile.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon6=new ImageIcon(imgscale6);
                            jLabelsefile.setIcon(scaledIcon6);
                            jLabelsefile.setOpaque(true);
                            jLabelsefile.setBackground(new java.awt.Color(250,250,250));
                           System.out.println("user::: no image set one");
                       }
            
            
            String postdescription = rs8.getString("description");
            
            this.jTextAreadesc.setText(postdescription);
           // String posthashtag = rs8.getString("username");
            }
            //jTextFieldhashtag.setText();
           // jTextAreadesc.setText(postdescription);
       
           
            String sql9="select * from post_hashtag where idpost ='"+postid+"'";
            Statement st9 = conn.createStatement();
            ResultSet rs9 = st9.executeQuery(sql9);
            
            if(rs9.next()){
            String hashid = rs9.getString("idhashtag");
            
            
            String sql10="select * from hashtag where idhashtag ='"+hashid+"'";
            Statement st10 = conn.createStatement();
            ResultSet rs10 = st10.executeQuery(sql10);
            if(rs10.next()){
            String hashkeyword = rs10.getString("keyword");
            this.jTextFieldhashtag.setText(hashkeyword);}
            }
            
        

        //******************** close addpost frame then close connection:
        conn.close();

        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
            System.out.println(e);
            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, e);
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

        base = new javax.swing.JPanel();
        close = new javax.swing.JLabel();
        min = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabelsefile = new javax.swing.JLabel();
        jLabelchoosefile = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jTextFieldhashtag = new javax.swing.JTextField();
        jButtonupload = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreadesc = new javax.swing.JTextArea();
        jButtoncancel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        fake = new javax.swing.JLabel();
        fake2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        base.setBackground(new java.awt.Color(9, 16, 69));

        close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });

        min.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        min.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minMouseClicked(evt);
            }
        });

        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(244, 216, 19));

        jPanel2.setBackground(new java.awt.Color(18, 33, 139));

        jPanel3.setBackground(new java.awt.Color(48, 171, 172));

        jPanel4.setBackground(new java.awt.Color(18, 33, 139));

        jPanel5.setBackground(new java.awt.Color(48, 171, 172));

        jPanel6.setBackground(new java.awt.Color(48, 171, 172));

        jPanel8.setBackground(new java.awt.Color(48, 171, 172));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(680, 206));

        jLabelsefile.setBackground(new java.awt.Color(255, 255, 255));
        jLabelsefile.setOpaque(true);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelsefile, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelsefile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabelchoosefile.setBackground(new java.awt.Color(255, 255, 255));
        jLabelchoosefile.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabelchoosefile.setForeground(new java.awt.Color(18, 33, 139));
        jLabelchoosefile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelchoosefile.setText("Click here to add new file");
        jLabelchoosefile.setOpaque(true);
        jLabelchoosefile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelchoosefileMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelchoosefile, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelchoosefile, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel11.setPreferredSize(new java.awt.Dimension(73, 42));

        jTextFieldhashtag.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextFieldhashtag.setForeground(new java.awt.Color(18, 33, 139));
        jTextFieldhashtag.setText("#Hashtag");
        jTextFieldhashtag.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldhashtagFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldhashtagFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextFieldhashtag)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jTextFieldhashtag, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButtonupload.setBackground(new java.awt.Color(244, 216, 19));
        jButtonupload.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButtonupload.setText("Edit");
        jButtonupload.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonupload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonuploadActionPerformed(evt);
            }
        });

        jTextAreadesc.setColumns(20);
        jTextAreadesc.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextAreadesc.setForeground(new java.awt.Color(18, 33, 139));
        jTextAreadesc.setRows(5);
        jTextAreadesc.setText("Enter your Description\n");
        jTextAreadesc.setSelectionColor(new java.awt.Color(244, 216, 19));
        jTextAreadesc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextAreadescFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextAreadescFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreadesc);

        jButtoncancel.setBackground(new java.awt.Color(244, 216, 19));
        jButtoncancel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButtoncancel.setText("Cancel");
        jButtoncancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtoncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtoncancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jButtoncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jButtonupload, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtoncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonupload, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/mainpage.jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        fake.setBackground(new java.awt.Color(9, 16, 69));
        fake.setForeground(new java.awt.Color(9, 16, 69));
        fake.setOpaque(true);

        fake2.setBackground(new java.awt.Color(9, 16, 69));
        fake2.setForeground(new java.awt.Color(9, 16, 69));
        fake2.setBorder(null);
        fake2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        fake2.setDisabledTextColor(new java.awt.Color(9, 16, 69));
        fake2.setSelectedTextColor(new java.awt.Color(9, 16, 69));
        fake2.setSelectionColor(new java.awt.Color(9, 16, 69));

        javax.swing.GroupLayout baseLayout = new javax.swing.GroupLayout(base);
        base.setLayout(baseLayout);
        baseLayout.setHorizontalGroup(
            baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(baseLayout.createSequentialGroup()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(240, 240, 240)
                        .addComponent(fake2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fake, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 549, Short.MAX_VALUE)
                        .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        baseLayout.setVerticalGroup(
            baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fake, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fake2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        new userprofile22().setVisible(true);
    }//GEN-LAST:event_backMouseClicked

    private void jButtonuploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonuploadActionPerformed
        // TODO add your handling code here:
        try{
            //******************* open connection with mysql:
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");

            //********************* insert data into post table:
            System.out.println("filepath new:: "+filename);
            String id = fake.getText();
            String sql1="update post set content =?,description='"+jTextAreadesc.getText()+"',username='asma' where idpost ='"+id+"'";
            PreparedStatement pstmt1=conn.prepareStatement(sql1);
            pstmt1.setString(1, filename);
            pstmt1.executeUpdate();

        //******************** open hashtag table and post hashtag:
        if(!jTextFieldhashtag.getText().equals("#Hashtag") && !jTextFieldhashtag.getText().equals(""))
        {
            //**************** insert data into hashtag table:
            String sql2="update hashtag set keyword ='"+jTextFieldhashtag.getText()+"'";
            PreparedStatement pstmt2=conn.prepareStatement(sql2);
            pstmt2.executeUpdate();

            //*************** open post and hashtag tables to get postid and hashtagid:
            Statement st1=conn.createStatement();
            String sql3="select * from post";
            ResultSet rs1=st1.executeQuery(sql3);
            String idpost=null;
            while(rs1.next()){
                idpost=String.valueOf(rs1.getInt("idpost"));
            }

            Statement st2=conn.createStatement();
            String sql4="select * from hashtag";
            ResultSet rs2=st2.executeQuery(sql4);
            String idhastag=null;
            while(rs2.next()){
                idhastag=String.valueOf(rs2.getInt("idhashtag"));
            }

            //********************* insert data into post_hashtag table:
            String sql5="update post_hashtag set idpost = '"+idpost+"',idhashtag='"+idhastag+"'";
            PreparedStatement pstmt3=conn.prepareStatement(sql5);
            pstmt3.executeUpdate();

        }

       
        //******************** close addpost frame then close connection:
        conn.close();
        this.dispose();
        new userprofile22().setVisible(true);

        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
            System.out.println(e);
            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }//GEN-LAST:event_jButtonuploadActionPerformed

    private void jButtoncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtoncancelActionPerformed
        // TODO add your handling code here:
        // close
        this.dispose();
        new userprofile22().setVisible(true);
    }//GEN-LAST:event_jButtoncancelActionPerformed

    private void jTextAreadescFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAreadescFocusGained
        // TODO add your handling code here:
        if(jTextAreadesc.getText().trim().equals("Enter your Description"))
            jTextAreadesc.setText("");
    }//GEN-LAST:event_jTextAreadescFocusGained

    private void jTextAreadescFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAreadescFocusLost
        // TODO add your handling code here:
        if(jTextAreadesc.getText().trim().equals(""))
            jTextAreadesc.setText("Enter your Description");
    }//GEN-LAST:event_jTextAreadescFocusLost

    private void jLabelchoosefileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelchoosefileMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File file=chooser.getSelectedFile();
        filename=file.getAbsolutePath();
        System.out.println("file path: "+filename);
        jLabelchoosefile.setText("");
        //*********************** change scale for icon to fit the label:
        Icon icon=new ImageIcon(filename);
        ImageIcon imgicon=new ImageIcon(filename);
        Image img=imgicon.getImage();
        Image imgscale=img.getScaledInstance(jLabelsefile.getWidth(), jLabelsefile.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon=new ImageIcon(imgscale);
        jLabelsefile.setIcon(scaledIcon);
    }//GEN-LAST:event_jLabelchoosefileMouseClicked

    private void jTextFieldhashtagFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldhashtagFocusGained
        // TODO add your handling code here:
        if(jTextFieldhashtag.getText().trim().equals("#Hashtag")){
            
            jTextFieldhashtag.setText("");
            
        }
    }//GEN-LAST:event_jTextFieldhashtagFocusGained

    private void jTextFieldhashtagFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldhashtagFocusLost
        // TODO add your handling code here:
        if(jTextFieldhashtag.getText().trim().equals(""))
            jTextFieldhashtag.setText("#Hashtag");
    }//GEN-LAST:event_jTextFieldhashtagFocusLost

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
            java.util.logging.Logger.getLogger(edit22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(edit22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(edit22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(edit22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new edit22().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JPanel base;
    private javax.swing.JLabel close;
    private javax.swing.JLabel fake;
    private javax.swing.JTextField fake2;
    private javax.swing.JButton jButtoncancel;
    private javax.swing.JButton jButtonupload;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelchoosefile;
    private javax.swing.JLabel jLabelsefile;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreadesc;
    private javax.swing.JTextField jTextFieldhashtag;
    private javax.swing.JLabel min;
    // End of variables declaration//GEN-END:variables
}
