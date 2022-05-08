
package sw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
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
import static sw.Followers1.usernameorigin;
import static sw.mainpage22.usernameorigin;
import static sw.userprofile22.usernameorigin;

/**
 *
 * @author LENOVO
 */
public class saved22 extends javax.swing.JFrame {

    
    JPanel postbase = new JPanel();
    static String usernameorigin ;//="asma";
    boolean likeflag = false;//*************** to know if the user has been liked the post or not
    boolean saveflag = false;//*************** to know if the user has been saved the post or not

    @FXML
    MediaPlayer player = null;
   
    public saved22(String usernameorigin) {
        this.usernameorigin = usernameorigin;
       
        initComponents();
        player = null;
        
        this.setLocationRelativeTo(null);
        
        close.setIcon(new ImageIcon(getClass().getResource("images0/x1.png")));
        close.setToolTipText("Exit.");
        
        min.setIcon(new ImageIcon(getClass().getResource("images0/min1.png")));
        min.setToolTipText("Minimize.");
        
        back.setIcon(new ImageIcon(getClass().getResource("images0/back3.png")));
        back.setToolTipText("Go back.");
        
        scrollPane1.add(postbase);
        //String usenamepost = "asma";
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
        
        
        
        
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject","root","iNEEDtostudy@202");
            //String sql1 = "select * from saved where username = '"+usernameorigin+"'";
           // String sql1 = "select * from following where username = '"+usernameorigin+"'";
             String sql1 = "select * from savepost where username = '" + usernameorigin + "'";
            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);
            Boolean flag = true;
            
            if (rs1.next() == false){
                //if user have no post 
                 //we will display a text telling the user to follow people
                    //post from people you follow will be displayed here
                    jhh.setLayout(null);//new FlowLayout());
                    //**********************add the jpanel in which the post will be displayed on
                            jhh.setPreferredSize(new Dimension(this.scrollPane1.getWidth(),this.scrollPane1.getHeight()));
                            jhh.setBackground(new java.awt.Color(255,255,255));//18, 33, 139));//blue
                            postbase.add(jhh);
                            
                            
                            
                    //************label that have the photo 
                    JLabel nofo = new JLabel();
                    nofo.setBounds(160, 200, 100, 100);
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
                    nofotext.setBounds(260, 200, 800, 100);
                    nofotext.setText("no posts to show.");
                    //nofotext.setBackground(Color.YELLOW);
                    nofotext.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
                    nofotext.setForeground(new java.awt.Color(18, 33, 139));
                    nofotext.setBackground(Color.white);
                    nofotext.setOpaque(true);
                    
                    jhh.add(nofotext);
               
            }else{
                
            sql1 = "select * from savepost where username = '" + usernameorigin + "'";
            st1 = conn.createStatement();
            rs1 = st1.executeQuery(sql1);
            flag = true;
            
            while (rs1.next()) {
                
                    
                    //***********************open post table and bring posts 
                    String idpostsaved = rs1.getString("idpost");
                    String sql2 = "select * from post where idpost = '" + idpostsaved + "'";
                  //  String savedposts = rs1.getString("username");
                   // String sql2 = "select * from post where username = '"+usernameorigin+"'";
                   // String savedposts = rs1.getString("idpost");
                   // String sql2 = "select * from post where idpost = '"+savedposts+"'";
                    
                    Statement st2 = conn.createStatement();
                    ResultSet rs2 = st2.executeQuery(sql2);
                    
                    
                    while (rs2.next()) {
                        
                            String content = rs2.getString("content");
                            String description = rs2.getString("description");
                            String idpost = String.valueOf(rs2.getInt("idpost"));
                            String usenamepost = rs2.getString("username");
                            String keyword = "";
                            
                            
                    //***********************open post_hashtag table then hashtag to get the keywords:
                    String sql3 = "select * from post_hashtag where idpost = '" + idpost + "'";
                    Statement st3 = conn.createStatement();
                    ResultSet rs3 = st3.executeQuery(sql3);
                    while (rs3.next()) {
                        String idpost2 = String.valueOf(rs3.getInt("idpost"));

                        if (idpost.equals(idpost2)) {
                            String idhastag1 = String.valueOf(rs3.getInt("idhashtag"));

                            //*********open hashtag:
                            String sql4 = "select * from hashtag where idhashtag ='" + idhastag1 + "'";
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
                                           // System.out.println("hi: " + rs5.getString("email"));
                                            //*********************** change scale for icon to fit the roundedlabel:
                                            Icon icon2 = new ImageIcon(rs5.getString("image"));
                                           // System.out.println("hi: " + rs5.getString("image"));
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
                            JLabel label2 = new JLabel(usenamepost);
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
                                  new otherusers22(usernameorigin,usenamepost).setVisible(true);
                                  dispose();
                               }
                         });
                         /////////////////////////////////////////////////////////
                         
                            jhh.add(label2);
                            
       
                            
       
 //*******************************************************************************************
 //*******************************************************************************************
 //*******************************************************************************************
 //*******************************************************************************************
 //*******************************************************************************************
 //********************display content:                   
 //*********new**************
                            
        JLabel l = new JLabel(idpost);
        l.setBounds(10, 70, 350, 300); //size.width, size.height);//******(left/right, up/down, width,height)
                            
                            
 
 
       //**********************content display ************************
       if(content != null && !content.equals("")){
          // System.out.println("content");
           
           
        int type = (content.length()-3);
        String subtype = content.substring(type);
       // System.out.println(subtype); 
        
        if(subtype.toLowerCase().equals("mp4")){
        //***************display video**********************
        
        
        
        try{
           
    final JFXPanel VFXPanel = new JFXPanel();
    File video_source = new File(content);
    Media m = new Media(video_source.toURI().toString());
    Platform.setImplicitExit(false);
    
    
        player = new MediaPlayer(m);
        onSeekComplete(player);
        player.stop();
        
       
    //player.play();
    //player.setAutoPlay(true);
    //player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setVolume(50);
   
    
    
    MediaView viewer = new MediaView(player);
    StackPane root = new StackPane();
    Scene scene = new Scene(root);

   
    // center video position
    javafx.geometry.Rectangle2D screen = Screen.getPrimary().getVisualBounds();
    viewer.setX((screen.getWidth() - l.getWidth()) / 2);
    viewer.setY((screen.getHeight() - l.getHeight()) / 2);

    // resize video based on screen size
    DoubleProperty width = viewer.fitWidthProperty();
    DoubleProperty height = viewer.fitHeightProperty();
    //width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
    //height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
    width.setValue(350);
    height.setValue(300);
    viewer.setPreserveRatio(true);

    // add video to stackpane
    root.getChildren().add(viewer);

    VFXPanel.setScene(scene);
    //player.play();
    l.setLayout(new BorderLayout());
    l.add(VFXPanel, BorderLayout.CENTER);
    l.setName(idpost);
 
    
    
    //****************video control***************
   
    //************************************************************play icon
        JLabel b=new JLabel();
        b.setBounds(18, 85, 30,30);
        b.setBackground(Color.WHITE);
        b.setName(idpost);
        //*********************** change scale for icon to fit the label:
        Icon icon=new ImageIcon(getClass().getResource("images0/play.png"));
        ImageIcon imgicon=new ImageIcon(getClass().getResource("images0/play.png"));
        Image img=imgicon.getImage();
        Image imgscale=img.getScaledInstance(b.getWidth(), b.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon=new ImageIcon(imgscale);
        b.setIcon(scaledIcon);
        b.setOpaque(true);
        
        
        /////////////////////////////////////////////////////////
        b.addMouseListener(new MouseAdapter() {
              public void mouseClicked(MouseEvent e) {
                  
                 // String idpost = ((JLabel) e.getSource()).getName();
                 // System.out.println("content  "+idpost);
                  
                  player.play();
                  
              }});
        /////////////////////////////////////////////////////////
        jhh.add(b);
    
        
        
    //************************************************************stop icon
        JLabel b1=new JLabel();
        b1.setBounds(50, 85, 30,30);
        b1.setBackground(Color.WHITE);
        b1.setName(idpost);
        //*********************** change scale for icon to fit the label:
        Icon icon1=new ImageIcon(getClass().getResource("images0/stopplay.png"));
        ImageIcon imgicon1=new ImageIcon(getClass().getResource("images0/stopplay.png"));
        Image img1=imgicon1.getImage();
        Image imgscale1=img1.getScaledInstance(b1.getWidth(), b1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1=new ImageIcon(imgscale1);
        b1.setIcon(scaledIcon1);
        b1.setOpaque(true);
        
        
        /////////////////////////////////////////////////////////
        b1.addMouseListener(new MouseAdapter() {
              public void mouseClicked(MouseEvent e) {
                  player.stop();
              }});
        /////////////////////////////////////////////////////////
        jhh.add(b1);
    
    
    
    
    //************************************************************mute icon
        JLabel b2=new JLabel();
        b2.setBounds(82, 85, 30,30);
        b2.setBackground(Color.WHITE);
        b2.setName(idpost);
        //*********************** change scale for icon to fit the label:
        Icon icon2=new ImageIcon(getClass().getResource("images0/muteplay.png"));
        ImageIcon imgicon2=new ImageIcon(getClass().getResource("images0/muteplay.png"));
        Image img2=imgicon2.getImage();
        Image imgscale2=img2.getScaledInstance(b2.getWidth(), b2.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2=new ImageIcon(imgscale2);
        b2.setIcon(scaledIcon2);
        b2.setOpaque(true);
        
        
        /////////////////////////////////////////////////////////
        b2.addMouseListener(new MouseAdapter() {
              public void mouseClicked(MouseEvent e) {
                  player.setMute(true);
              }});
        /////////////////////////////////////////////////////////
        jhh.add(b2);
        jhh.add(l);
        player.stop();
          
        } 
       catch(Exception eee){
           JOptionPane.showMessageDialog(null, eee);
       }
        
        }
        else{
               
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
        }
        
       }
       else{
          // System.out.println("no content");
           //*********************** change scale for icon to fit the label:
        Icon icon=new ImageIcon(getClass().getResource("images0/addc.png"));;
        ImageIcon imgicon=new ImageIcon(getClass().getResource("images0/addc.png"));;
        Image img=imgicon.getImage();
        Image imgscale=img.getScaledInstance(l.getWidth(), l.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon=new ImageIcon(imgscale);
        l.setIcon(scaledIcon);
        l.setOpaque(true);
        l.setBackground(new java.awt.Color(250,250,250));
        l.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
      
        
        jhh.add( l );
        
           
       }
           
       
        
                
 //*******************************************************************************************
 //*******************************************************************************************
 //*******************************************************************************************
 //*******************************************************************************************
 //*******************************************************************************************
        
                            
                            
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
                            
                            JLabel like = new JLabel(idpost);
                            like.setBounds(10, 375, 70, 70);
                            like.setOpaque(true);
                           
                            like.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            like.setHorizontalAlignment(SwingConstants.CENTER);
                            like.setVerticalAlignment(SwingConstants.CENTER);
                          
                          
                          //*********************** change scale for icon to fit the label:
                          
                          //********************* open likes table verfiy if user like this post or not:
                            String sql6 = "select * from likes where idpost ='"+idpost+"'";

                            Statement st6 = conn.createStatement();
                            ResultSet rs6 = st6.executeQuery(sql6);
                            ImageIcon imgicon1 = new ImageIcon(getClass().getResource("images0/emptyh.png"));
                            while (rs6.next()) {
                                
                                    if (rs6.getString("username").equals(usenamepost)) {
                                        if (rs6.getString("userlike").equals(usernameorigin)) {
                                           // System.out.println("well here we go..."+idpost);
                                            imgicon1 = new ImageIcon(getClass().getResource("images0/like.png"));
                                            likeflag = true;
                                        }
                                    }
                                

                            }

                          
                           // ImageIcon imgicon1=new ImageIcon(getClass().getResource("images0/emptyh.png"));
                            Image img1=imgicon1.getImage();
                            Image imgscale1=img1.getScaledInstance(like.getWidth(), like.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon1=new ImageIcon(imgscale1);
                            like.setIcon(scaledIcon1);
                            like.setOpaque(true);
                            like.setBackground(new java.awt.Color(255,255,255));
                            like.setToolTipText("Like.");
                            
                           //******************** add mouse listner in order to like the post:
                           /////////////////////////////////////////////////////////
                         
                            like.addMouseListener(mouseListener);
                          
                            jhh.add(like);
                            
                            //********************************view likeeee
                            JLabel viewlike = new JLabel(idpost);
                            viewlike.setBounds(90, 375, 70, 70);
                            viewlike.setOpaque(true);

                            viewlike.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            viewlike.setHorizontalAlignment(SwingConstants.CENTER);
                            viewlike.setVerticalAlignment(SwingConstants.CENTER);
                            ImageIcon imgicon4= new ImageIcon(getClass().getResource("images0/see.png"));
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
                                    new Likes(usernameorigin,idpost ).setVisible(true);
                                    
                                }
                            });

                            jhh.add(viewlike);
                          
                            
                            
                            
       //**********************************************************************************************
                            //********************* create add comment button:
                            JLabel comm = new JLabel(idpost);
                            comm.setBounds(165, 375, 70, 70);
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
                          
                            comm.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent e) {
                                    //   String idpost = ((JButton) e.getSource()).getName();
                                    new Comments(idpost,usernameorigin).setVisible(true);
                                    
                                }
                            });
                          
                            jhh.add(comm);
                            
                            
                            
       //**********************************************************************************************
                            //********************* create save button:
                            JLabel save = new JLabel(idpost);
                            save.setBounds(660, 375, 70, 70);//(170, 375, 70, 70);
                            save.setOpaque(true);
                            
                            save.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            save.setHorizontalAlignment(SwingConstants.CENTER);
                            save.setVerticalAlignment(SwingConstants.CENTER);
                          
                          
                          //*********************** change scale for icon to fit the label:
                          //Icon icon2=new ImageIcon(getClass().getResource("images0/heart.png"));
                           // ImageIcon imgicon3=new ImageIcon(getClass().getResource("images0/saved.png"));
                           //********************* open likes table verfiy if user like this post or not:
                            String sql7 = "select * from savepost";
                            Statement st7 = conn.createStatement();
                            ResultSet rs7 = st7.executeQuery(sql7);
                            ImageIcon imgicon3 = new ImageIcon(getClass().getResource("images0/unsaved.png"));
                            while (rs7.next()) {
                                if (String.valueOf(rs7.getInt("idpost")).equals(idpost)) {
                                    if (rs7.getString("username").equals(usernameorigin)) {
                                       // System.out.println("well here we go22...");
                                        imgicon3 = new ImageIcon(getClass().getResource("images0/saved.png"));
                                        saveflag = true;

                                    }
                                }

                            }
                           
                            Image img3=imgicon3.getImage();
                            Image imgscale3=img3.getScaledInstance(like.getWidth(), like.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon3=new ImageIcon(imgscale3);
                            save.setIcon(scaledIcon3);
                            save.setOpaque(true);
                            save.setBackground(new java.awt.Color(250,250,250));
                            save.setToolTipText("save.");
                            save.addMouseListener(mouseListenersave);
                          
                            jhh.add(save);
                            
                            
                            
       //**********************************************************************************************
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

            }
            }//while
            
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.err.println(ex);
            
        }//catch
    }//end function
   
   public void onSeekComplete(final MediaPlayer mp) {
    player = mp;
}
   
   MouseListener mouseListener = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            
            likeflag = false;
            if (e.getClickCount() == 1) {
                //******************** first we have to know which label makes the action:
                String idpost = ((JLabel) e.getSource()).getText();
                JLabel like = (JLabel) e.getSource();
                System.out.println("idpostt:: " + idpost);
               
                //******************* Then we have to open likes table:(we have id post and username)
                try {
                    
                    //******************* open connection with mysql:
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject","root","iNEEDtostudy@202");
                    String sql2 = "select * from post ";
                    Statement st2 = conn.createStatement();
                    ResultSet rs2 = st2.executeQuery(sql2);
                    String userpost = "asma";
                    while (rs2.next()) {
                        if (idpost.equals("idpost")) {
                            userpost = rs2.getString("username");
                        }

                    }
                    
                    //********************* open likes table verfiy if user like this post or not:
                    String sql6 = "select * from likes";

                    Statement st6 = conn.createStatement();
                    ResultSet rs6 = st6.executeQuery(sql6);
                    ImageIcon imgicon1 = new ImageIcon(getClass().getResource("images0/like.png"));
                    while (rs6.next()) {
                        if (rs6.getString("idpost").equals(idpost)) {
                            if (rs6.getString("username").equals(userpost)) {
                                if (rs6.getString("userlike").equals(usernameorigin)) {
                                    System.out.println("well here we go...");
                                    //************** delete like:
                                    String query = "delete from likes where username = ? AND idpost=?";
                                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                                    preparedStmt.setString(1, userpost);
                                    preparedStmt.setString(2, idpost);
                                    preparedStmt.execute();
                                    imgicon1 = new ImageIcon(getClass().getResource("images0/emptyh.png"));
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
                    }//end if !like flag
                    
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
            }
            
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
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject","root","iNEEDtostudy@202");

                    //********************* open save table verfiy if user like this post or not:
                    String sql6 = "select * from savepost";

                    Statement st6 = conn.createStatement();
                    ResultSet rs6 = st6.executeQuery(sql6);
                    ImageIcon imgicon1 = new ImageIcon(getClass().getResource("images0/saved.png"));
                    while (rs6.next()) {
                        if (String.valueOf(rs6.getInt("idpost")).equals(idpost)) {
                            if (rs6.getString("username").equals(usernameorigin)) {

                                //System.out.println("well here we go222...");
                                //************** delete save:
                                String query = "delete from savepost where username = ? AND idpost=?";
                                PreparedStatement preparedStmt = conn.prepareStatement(query);
                                preparedStmt.setString(1, usernameorigin);
                                preparedStmt.setString(2, idpost);
                                preparedStmt.execute();
                                imgicon1 = new ImageIcon(getClass().getResource("images0/unsaved.png"));
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
               // mainpage22 mainpage = new mainpage22(usernameorigin);
                dispose();
                userprofile22 profile = new userprofile22(usernameorigin);
                profile.setVisible(true);

            }//if

        }

    };

    
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
        // close
        if(player == null){
            
            this.dispose();
        }
        else{
            this.player.stop();
            this.dispose();}
        
    }//GEN-LAST:event_closeMouseClicked

    private void minMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minMouseClicked
        // TODO add your handling code here:
        // min
        if(player == null)
            this.setExtendedState(JFrame.ICONIFIED);
        else{
            this.player.stop();
            this.setExtendedState(JFrame.ICONIFIED);
        
        }
    }//GEN-LAST:event_minMouseClicked

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:
        if(player == null){
            new mainpage22(usernameorigin).setVisible(true);
            this.dispose();
        }
        else{
            this.pack();
            this.player.stop();
            new mainpage22(usernameorigin).setVisible(true);
            this.dispose();
        }
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
