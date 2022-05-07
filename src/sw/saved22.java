
package sw;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import rounded.JLabelRound;

/**
 *
 * @author LENOVO
 */
public class saved22 extends javax.swing.JFrame {

    
    JPanel postbase = new JPanel();
    static String usernameorigin ;//="asma";
    
    
   
    public saved22(String usernameorigin) {
        this.usernameorigin = usernameorigin;
       
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        close.setIcon(new ImageIcon(getClass().getResource("images0/x1.png")));
        close.setToolTipText("Exit.");
        
        min.setIcon(new ImageIcon(getClass().getResource("images0/min1.png")));
        min.setToolTipText("Minimize.");
        
        back.setIcon(new ImageIcon(getClass().getResource("images0/back3.png")));
        back.setToolTipText("Go back.");
        
        scrollPane1.add(postbase);
        String usenamepost = "asma";
        setResizable(false);
        makepanel();
        
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
   public void makepanel() {
        
       
        JPanel jhh = new JPanel();
        postbase.setLayout(new BoxLayout(postbase, BoxLayout.Y_AXIS));
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String usenamepost = null;
        
        
        
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject","root","iNEEDtostudy@202");
            //String sql1 = "select * from saved where username = '"+usernameorigin+"'";
            String sql1 = "select * from following where username = '"+usernameorigin+"'";
            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);
            Boolean flag = true;
            
            
            while (rs1.next()) {
                
                    
                    //***********************open post table and bring posts 
                    String savedposts = rs1.getString("username");
                    String sql2 = "select * from post where username = '"+usernameorigin+"'";
                   // String savedposts = rs1.getString("idpost");
                   // String sql2 = "select * from post where idpost = '"+savedposts+"'";
                    
                    Statement st2 = conn.createStatement();
                    ResultSet rs2 = st2.executeQuery(sql2);
                    
                    
                    while (rs2.next()) {
                        
                            String content = rs2.getString("content");
                            String description = rs2.getString("description");
                            String idpost = String.valueOf(rs2.getInt("idpost"));
                            String keyword = "";
                            
                            
                            //***********************open post_hashtag table then hashtag to get the keywords:
                            String sql3 = "select * from post_hashtag where idpost = '"+idpost+"'";
                            Statement st3 = conn.createStatement();
                            ResultSet rs3 = st3.executeQuery(sql3);
                            while (rs3.next()) {
                                String idpost2 = String.valueOf(rs3.getInt("idpost"));
                                
                                if (idpost.equals(idpost2)) {
                                    String idhastag1 = String.valueOf(rs3.getInt("idhashtag"));
                                    
                                    
                                    //*********open hashtag:
                                    String sql4 = "select * from hashtag where idhashtag ='"+idhastag1+"'";
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
                            jhh.setPreferredSize(new Dimension(this.scrollPane1.getWidth(),440));
                            jhh.setBackground(new java.awt.Color(255,255,255));//18, 33, 139));//blue
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
                            JLabel label2 = new JLabel(savedposts);
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
                            label1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            jhh.add(label1);
                            
                            //******************** add mouse listner in order to like the post:
                          //  label1.addMouseListener(mouseListener);

                            
                            
                            
       //**********************************************************************************************
                            //********************add jtextarea to add the desc.
                            JTextArea textarea1 = new JTextArea(description + "\n" + keyword);
                            textarea1.setLineWrap(true);
                            textarea1.setWrapStyleWord(true);
                            textarea1.setBounds(365, 70, 370, 300);//******(left/right, up/down, width,height)
                            textarea1.setOpaque(true);
                            textarea1.setBackground(new java.awt.Color(255, 255, 255));
                            textarea1.setEditable(false);
                            textarea1.setColumns(20);
                            textarea1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                            textarea1.setForeground(new java.awt.Color(0,0,0));//18, 33, 139));
                            textarea1.setRows(5);
                            textarea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(18, 33, 139), 2));
                           // textarea1.setSelectionColor(new java.awt.Color(244, 216, 19));
                            jhh.add(textarea1);
                            
                            
                            
                            
       //**********************************************************************************************
                            //********************* create view likes button:
                            
                            
                            
                            JLabel like = new JLabel();
                            like.setBounds(10, 375, 70, 70);
                            like.setOpaque(true);
                           
                            like.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            like.setHorizontalAlignment(SwingConstants.CENTER);
                            like.setVerticalAlignment(SwingConstants.CENTER);
                          
                          
                          //*********************** change scale for icon to fit the label:
                            ImageIcon imgicon1=new ImageIcon(getClass().getResource("images0/emptyh.png"));
                            Image img1=imgicon1.getImage();
                            Image imgscale1=img1.getScaledInstance(like.getWidth(), like.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon1=new ImageIcon(imgscale1);
                            like.setIcon(scaledIcon1);
                            like.setOpaque(true);
                            like.setBackground(new java.awt.Color(255,255,255));
                            like.setToolTipText("Like.");
                          //  like.addMouseListener(mouseListener);
                          
                            jhh.add(like);
                            
                            
                           // JButton jbuttonlike = new JButton();
                           // jbuttonlike.setBounds(190, 450, 150, 29);//******(left/right, up/down, width,height)
                           // jbuttonlike.setOpaque(true);
                           // jbuttonlike.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                           // jbuttonlike.setForeground(new java.awt.Color(18, 33, 139));
                           // jbuttonlike.setText("View Likes");
                           // jbuttonlike.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
                           // jbuttonlike.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                           // jhh.add(jbuttonlike);
                           // jbuttonlike.addActionListener(actionListener);

                            
                            
                            
       //**********************************************************************************************
                            //********************* create add comment button:
                            JLabel comm = new JLabel();
                            comm.setBounds(90, 375, 70, 70);
                            comm.setOpaque(true);
                            
                            comm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            comm.setHorizontalAlignment(SwingConstants.CENTER);
                            comm.setVerticalAlignment(SwingConstants.CENTER);
                          
                          
                          //*********************** change scale for icon to fit the label:
                          //Icon icon2=new ImageIcon(getClass().getResource("images0/heart.png"));
                            ImageIcon imgicon2=new ImageIcon(getClass().getResource("images0/comm1.png"));
                            Image img2=imgicon2.getImage();
                            Image imgscale2=img2.getScaledInstance(like.getWidth(), like.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon2=new ImageIcon(imgscale2);
                            comm.setIcon(scaledIcon2);
                            comm.setOpaque(true);
                            comm.setBackground(new java.awt.Color(250,250,250));
                            comm.setToolTipText("Comment.");
                          //  comm.addMouseListener(mouseListener);
                          
                            jhh.add(comm);
                            
                            
                            
                          //  JButton jbuttoncomment = new JButton();
                          //  jbuttoncomment.setBounds(360, 450, 150, 29);//******(left/right, up/down, width,height)
                          //  jbuttoncomment.setOpaque(true);
                          //  jbuttoncomment.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                          //  jbuttoncomment.setForeground(new java.awt.Color(18, 33, 139));
                          //  jbuttoncomment.setText("Add Comments");
                          //  jbuttoncomment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
                          //  jbuttoncomment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                          //  jhh.add(jbuttoncomment);
                          //  jbuttoncomment.addActionListener(actionListener);
                            
                            
       //**********************************************************************************************
                            //********************* create save button:
                            JLabel save = new JLabel();
                            save.setBounds(630, 375, 70, 70);//(170, 375, 70, 70);
                            save.setOpaque(true);
                            
                            save.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            save.setHorizontalAlignment(SwingConstants.CENTER);
                            save.setVerticalAlignment(SwingConstants.CENTER);
                          
                          
                          //*********************** change scale for icon to fit the label:
                          //Icon icon2=new ImageIcon(getClass().getResource("images0/heart.png"));
                            ImageIcon imgicon3=new ImageIcon(getClass().getResource("images0/saved.png"));
                            Image img3=imgicon3.getImage();
                            Image imgscale3=img3.getScaledInstance(like.getWidth(), like.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon3=new ImageIcon(imgscale3);
                            save.setIcon(scaledIcon3);
                            save.setOpaque(true);
                            save.setBackground(new java.awt.Color(250,250,250));
                            save.setToolTipText("Comment.");
                          //  comm.addMouseListener(mouseListener);
                          
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
                        

                    }

               
            }//while
            
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.err.println(ex);
            
        }//catch
    }//end function

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        base = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        scrollPane1 = new java.awt.ScrollPane();
        close = new javax.swing.JLabel();
        min = new javax.swing.JLabel();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        base.setBackground(new java.awt.Color(9, 16, 69));

        jPanel1.setBackground(new java.awt.Color(244, 216, 19));

        jPanel2.setBackground(new java.awt.Color(18, 33, 139));

        jPanel3.setBackground(new java.awt.Color(244, 216, 19));

        jPanel4.setBackground(new java.awt.Color(18, 33, 139));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/mainpage.jpeg"))); // NOI18N

        jPanel5.setBackground(new java.awt.Color(18, 33, 139));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });

        min.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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

        javax.swing.GroupLayout baseLayout = new javax.swing.GroupLayout(base);
        base.setLayout(baseLayout);
        baseLayout.setHorizontalGroup(
            baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(baseLayout.createSequentialGroup()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        baseLayout.setVerticalGroup(
            baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, baseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(min, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
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
        this.dispose();
    }//GEN-LAST:event_closeMouseClicked

    private void minMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minMouseClicked
        // TODO add your handling code here:
        // min
        this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minMouseClicked

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:
        new userprofile22(usernameorigin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

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
            java.util.logging.Logger.getLogger(saved22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(saved22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(saved22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(saved22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new saved22(usernameorigin).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JPanel base;
    private javax.swing.JLabel close;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel min;
    private java.awt.ScrollPane scrollPane1;
    // End of variables declaration//GEN-END:variables
}
