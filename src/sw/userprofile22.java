/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import static javafx.beans.binding.Bindings.length;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static javafx.scene.input.KeyCode.R;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javax.swing.text.View;
import static sw.mainpage22.usernameorigin;

/**
 *
 * @author LENOVO
 */
public class userprofile22 extends javax.swing.JFrame{

    
    
    
    JPanel postbase = new JPanel();
    static String usernameorigin ;///="asma";
    String id = null;
    
    boolean likeflag = false;//*************** to know if the user has been liked the post or not
    boolean saveflag = false;//*************** to know if the user has been saved the post or not
    
    
    @FXML
    MediaPlayer player = null;
   // String idpost;
   
    
    

    public userprofile22(String usernameorigin) {
        this.usernameorigin = usernameorigin;
        // System.out.println("no "+usernameorigin);
        initComponents();
       
        player = null;
        
        this.setLocationRelativeTo(null);
        
        close.setIcon(new ImageIcon(getClass().getResource("images0/x1.png")));
        close.setToolTipText("Exit.");
        
        min.setIcon(new ImageIcon(getClass().getResource("images0/min1.png")));
        min.setToolTipText("Minimize.");
        
        back.setIcon(new ImageIcon(getClass().getResource("images0/back3.png")));
        back.setToolTipText("Go back.");
        
        addpost.setIcon(new ImageIcon(getClass().getResource("images0/add3.png")));
        addpost.setToolTipText("To add post page.");
        
        explore.setIcon(new ImageIcon(getClass().getResource("images0/explore0.png")));
        explore.setToolTipText("To explore page.");
        
        contact.setIcon(new ImageIcon(getClass().getResource("images0/contact.png")));
        contact.setToolTipText("Contact us.");
        
        out.setIcon(new ImageIcon(getClass().getResource("images0/out.png")));
        out.setToolTipText("Sign out.");
        
        edit.setIcon(new ImageIcon(getClass().getResource("images0/edit.png")));
        edit.setToolTipText("Settings.");
        
        fing.setIcon(new ImageIcon(getClass().getResource("images0/fing.png")));
        
        fer.setIcon(new ImageIcon(getClass().getResource("images0/fer.png")));
        
        saved.setIcon(new ImageIcon(getClass().getResource("images0/saved.png")));
        saved.setToolTipText("saved.");
        
        scrollPane1.add(postbase);
       // String usenamepost = "asma";
        setResizable(false);
        makepanel();
        
    }
    
    
//////////////////////////////////////////////////////////////////////////////////////////////////
    public void makepanel(){
        pack();
       // System.out.println("no "+usernameorigin);
        
        JPanel jhh =new JPanel(); 
        postbase.setLayout(new BoxLayout(postbase, BoxLayout.Y_AXIS));  
        
         //********************we have to open post table then compare username that we have with username in post table:
         Connection conn = null;
         ResultSet rs = null;
         PreparedStatement ps = null;
         String usenamepost=null;
         
        
        
        
           try{
           conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject","root","iNEEDtostudy@202");
           
           
           
           
            //***********************getbio and photo******************************
               String sql5="select * from appuser where username='"+usernameorigin+"'";
               Statement st5=conn.createStatement();
               ResultSet rs5=st5.executeQuery(sql5);
               if(rs5.next()){
                   
                   //********bio
                   String setbio = rs5.getString("bio");
                   
                   if(setbio!=null)
                      this.bio.setText(setbio);
                   else
                      this.bio.setText("Write a bio");
                   
                   usenamepost=rs5.getString("username");
                // System.out.println("user::: "+usenamepost);
                // name.setText(usenamepost);
                    name.setText(usernameorigin);
                   
                   
                   //*******photo
                   String mail = rs5.getString("email");
                   String sql6="select * from person where email='"+mail+"'";
                   Statement st6=conn.createStatement();
                   ResultSet rs6=st6.executeQuery(sql6);
                   
                    if(rs6.next()){//there is photo
                       String image = rs6.getString("image");
                       
                       if((!image.equals("")) &&(!image.equals(null)) && (!image.equals("Image path,"))){// image!=null){
                           //************display image************
                           //*********************** change scale for icon to fit the label:
                           //Icon icon6=new ImageIcon(content);
                            ImageIcon imgicon6=new ImageIcon(image);
                            Image img6=imgicon6.getImage();
                            Image imgscale6=img6.getScaledInstance(userimage.getWidth(), userimage.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon6=new ImageIcon(imgscale6);
                            userimage.setIcon(scaledIcon6);
                            userimage.setOpaque(true);
                            userimage.setBackground(new java.awt.Color(250,250,250));
                           
                           // System.out.println("user::: you have come here? havent you! profile ");
                       }
                       else{
                           //************display noimage icon********
                            ImageIcon imgicon6=new ImageIcon(getClass().getResource("images0/noimage.png"));
                            Image img6=imgicon6.getImage();
                            Image imgscale6=img6.getScaledInstance(userimage.getWidth(), userimage.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon6=new ImageIcon(imgscale6);
                            userimage.setIcon(scaledIcon6);
                            userimage.setOpaque(true);
                            userimage.setBackground(new java.awt.Color(250,250,250));
                         //  System.out.println("user::: guess not profile");
                       }
                      
                      
                   }//photo if
                    else{//there is no photo
                        
                    }
                   
               }//bio if
               else{/*there is no user*/}
               
               
               //******************tooltip:
                    fing.setToolTipText("followingcount");
                    fer.setToolTipText("followerscount");
                    fer.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    fing.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    
                    
                    //************************** add mouse listner to open list of follwing and follower:
                    fing.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            //   String idpost = ((JButton) e.getSource()).getName();
                            new Following(usernameorigin).setVisible(true);

                        }
                    });
                    fer.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            //   String idpost = ((JButton) e.getSource()).getName();
                            new Followers1(usernameorigin).setVisible(true);

                        }
                    });
                    
                    
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////          
         ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
           ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
           String sql1="select * from post where username ='"+usernameorigin+"' ORDER BY date DESC";
           Statement st1=conn.createStatement();
           ResultSet rs1=st1.executeQuery(sql1);
           
           Boolean flag=true;
           
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
              sql1="select * from post where username ='"+usernameorigin+"' ORDER BY date DESC";
              st1=conn.createStatement();
              rs1=st1.executeQuery(sql1);
              
           
              flag=true;
           while(rs1.next()){
               
               String idpost = rs1.getString("idpost");
             //  idpost=rs1.getString("idpost");
             
               if(usenamepost.equals(usernameorigin))
               {
                   String content=rs1.getString("content");
                   
                  // System.out.println("content::: "+content);
                   
                  
                  //*************************hashtags work*******************************
                  String keyword = "";
                            
                            
                            //***********************open post_hashtag table then hashtag to get the keywords:
                            String sql3 = "select * from post_hashtag where idpost = '"+idpost+"'";
                            Statement st3 = conn.createStatement();
                            ResultSet rs3 = st3.executeQuery(sql3);
                            while (rs3.next()) {
                                String idpost2 = rs3.getString("idpost");
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
           
               //*************************hashtags work end*******************************
               
               
              
               
               
               
            //******************************************************************
            jhh.setLayout(null);//new FlowLayout());
            //add the jpanel in which the post will be displayed on
            jhh.setSize(new Dimension(this.scrollPane1.getWidth(), 250));
            
            jhh.setPreferredSize(new Dimension(this.scrollPane1.getWidth(), 250));
            jhh.setBackground(new java.awt.Color(255,255,255));//blue
         
            postbase.add(jhh);  
         
             
 //*******************************************************************************************
 //*******************************************************************************************
 //*******************************************************************************************
 //*******************************************************************************************
 //*******************************************************************************************
 
        JLabel l=new JLabel(idpost);
        l.setName(idpost);
        l.setBounds(15, 15, 290,220); //size.width, size.height);
        
 
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
    width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
    height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
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
        b.setBounds(18, 10, 30,30);
        b.setBackground(Color.WHITE);
        b.setName(idpost);
        //*********************** change scale for icon to fit the label:
        Icon icon=new ImageIcon(getClass().getResource("images0/addc.png"));
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
        b1.setBounds(50, 10, 30,30);
        b1.setBackground(Color.WHITE);
        b1.setName(idpost);
        //*********************** change scale for icon to fit the label:
        Icon icon1=new ImageIcon(getClass().getResource("images0/addc.png"));
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
        b2.setBounds(82, 10, 30,30);
        b2.setBackground(Color.WHITE);
        b2.setName(idpost);
        //*********************** change scale for icon to fit the label:
        Icon icon2=new ImageIcon(getClass().getResource("images0/addc.png"));
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
            
              //if the content is a photo 
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
           //if no content photo was found
           System.out.println("no content");
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
        
        
        /////////////////////////////////////////////////////////
        l.addMouseListener(new MouseAdapter() {
              public void mouseClicked(MouseEvent e) {
                  String idpost = ((JLabel) e.getSource()).getName();
                  edit22 editobj = new edit22(usernameorigin);
                  editobj.my_update(idpost, usernameorigin);//Execute the method my_update to pass str
	          editobj.setVisible(true); // Open the Second.java window
	          dispose();
              
              }});
        /////////////////////////////////////////////////////////
        
        jhh.add( l );
        
           
       } 
 //*******************************************************************************************
 //*******************************************************************************************
 //*******************************************************************************************
 //*******************************************************************************************
 //*******************************************************************************************
        
        
        
        
        //**************************the description display*************************
        //JLabel des=new JLabel();
        JTextPane des = new JTextPane();
       //   JTextArea des = new JTextArea();

        //des.setLineWrap(true);
        //des.setWrapStyleWord(true);
        des.setBounds(330, 20, 380, 140);//330, 240, 300,210); //x, y, width, height);
        des.setBackground(new java.awt.Color(255,255,255));//200,200,200));
        
        String description=rs1.getString("description");
        des.setText(description);
        
       // JScrollPane scroll = new JScrollPane(des);
       // scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       // scroll.setBounds(330, 20, 465, 150);
      
       
        des.setForeground(new java.awt.Color(0,0,0));//text color
        des.setFont(new java.awt.Font("iCiel Baliho Script", 0, 18));//text type
        des.setBorder(null);
        des.setEditable(false);
        des.setOpaque(true);
       
        jhh.add(des); //scroll );
        
        
        
        
        
                            
        //**********************************************************************************************
        //********************* create view likes button:
        //**************************like icon*************************
        
        JLabel like=new JLabel(idpost);
        like.setBounds(330, 165, 70, 70);
        like.setOpaque(true);
        
        like.setCursor(new Cursor(Cursor.HAND_CURSOR));
        like.setHorizontalAlignment(SwingConstants.CENTER);
        like.setVerticalAlignment(SwingConstants.CENTER);
        
        //*********************** change scale for icon to fit the label:
        
                          //********************* open likes table verfiy if user like this post or not:
                            String sql66 = "select * from likes where idpost ='"+idpost+"'";

                            Statement st66 = conn.createStatement();
                            ResultSet rs66 = st66.executeQuery(sql66);
                            ImageIcon imgicon66 = new ImageIcon(getClass().getResource("images0/emptyh.png"));
                            while (rs66.next()) {
                                
                                    if (rs66.getString("username").equals(following)) {
                                        if (rs66.getString("userlike").equals(usernameorigin)) {
                                           // System.out.println("well here we go..."+idpost);
                                            imgicon66 = new ImageIcon(getClass().getResource("images0/like.png"));
                                            likeflag = true;
                                        }
                                    }
                                

                            }

                          // ImageIcon imgicon1=new ImageIcon(getClass().getResource("images0/emptyh.png"));
                            Image img1=imgicon66.getImage();
                            Image imgscale66=img1.getScaledInstance(like.getWidth(), like.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon66=new ImageIcon(imgscale66);
                            like.setIcon(scaledIcon66);
                            like.setOpaque(true);
                            like.setBackground(new java.awt.Color(255,255,255));
                            like.setToolTipText("Like.");
                            
                           //******************** add mouse listner in order to like the post:
                           /////////////////////////////////////////////////////////
                         
                            like.addMouseListener(mouseListener);
                          
                            jhh.add(like);
                            
                            
                            //********************************view likeeee
                            JLabel viewlike = new JLabel(idpost);
                            viewlike.setBounds(400, 165, 70, 70);
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
                                    new Likes(idpost).setVisible(true);
                                    
                                }
                            });

                            jhh.add(viewlike);
                          
        
        
       
        //**************************comment icon*************************
        //Commentsforprofileuser
        //**********************************************************************************************
                            //********************* create add comment button:
                            JLabel comm = new JLabel(idpost);
                            comm.setBounds(470, 165, 70, 70);
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
                            comm.setBackground(new java.awt.Color(255,255,255));
                            comm.setToolTipText("Comment.");
                          //  comm.addMouseListener(mouseListener);
                          
                            comm.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent e) {
                                    //   String idpost = ((JButton) e.getSource()).getName();
                                    new Commentsforprofileuser(idpost,usernameorigin).setVisible(true);
                                    
                                }
                            });
                          
                            jhh.add(comm);
                            
                            
                            
       //**********************************************************************************************
        
        
        //**************************the delete button*************************
       // JButton jbuttondelete=new JButton("Delete");
        JLabel jbuttondelete = new JLabel();
        
        jbuttondelete.setFont(new java.awt.Font("iCiel Baliho Script", 0, 18));//text type
        
        jbuttondelete.setName(idpost);
        jbuttondelete.setBounds(670,165, 70,70);//buttom size
        jbuttondelete.setBackground(new java.awt.Color(172,49,48));//button color
        
        jbuttondelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        //*********************** change scale for icon to fit the label:
        //Icon icon2=new ImageIcon(getClass().getResource("images0/heart.png"));
        ImageIcon imgicon11=new ImageIcon(getClass().getResource("images0/deletepo.png"));
        Image img11=imgicon11.getImage();
        Image imgscale11=img11.getScaledInstance(jbuttondelete.getWidth(), jbuttondelete.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon11=new ImageIcon(imgscale11);
        jbuttondelete.setIcon(scaledIcon11);
        jbuttondelete.setOpaque(true);
        jbuttondelete.setBackground(new java.awt.Color(250,250,250));
        jbuttondelete.setToolTipText("delete post.");
        
        /////////////////////////////////////////////////////////
        jbuttondelete.addMouseListener(new MouseAdapter() {
              public void mouseClicked(MouseEvent e) {
                  String idpost = ((JLabel) e.getSource()).getName();
                  deletepost(idpost);}});
        /////////////////////////////////////////////////////////
        
        jhh.add(jbuttondelete);
        
        
        
        
        //**************************the edit button///////////////////////////////////////////////////////////////////////////
        //JButton jbuttonedit=new JButton("Edit");
        JLabel jbuttonedit = new JLabel();
        
        jbuttonedit.setFont(new java.awt.Font("iCiel Baliho Script", 0, 18));//text type
        
        jbuttonedit.setName(idpost);
        jbuttonedit.setBounds(600,165,70,70);//buttom size
        jbuttonedit.setBackground(new java.awt.Color(18,33,139));//button color
        
        jbuttonedit.setOpaque(true);
        
        jbuttonedit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        //*********************** change scale for icon to fit the label:
        //Icon icon2=new ImageIcon(getClass().getResource("images0/heart.png"));
        ImageIcon imgicon22=new ImageIcon(getClass().getResource("images0/editpo.png"));
        Image img22=imgicon22.getImage();
        Image imgscale22=img22.getScaledInstance(jbuttonedit.getWidth(), jbuttonedit.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon22=new ImageIcon(imgscale22);
        jbuttonedit.setIcon(scaledIcon22);
        jbuttonedit.setOpaque(true);
        jbuttonedit.setBackground(new java.awt.Color(250,250,250));
        jbuttonedit.setToolTipText("edit post.");
        
        
        /////////////////////////////////////////////////////////
        jbuttonedit.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            String idpost = ((JLabel) e.getSource()).getName();
            edit22 editobj = new edit22(usernameorigin);
            editobj.my_update(idpost, usernameorigin);//Execute the method my_update to pass str
	    editobj.setVisible(true); // Open the Second.java window
	    dispose(); // Close 
         // System.out.println("Clicked!"+idpost);
        }});
        /////////////////////////////////////////////////////////
        
        jhh.add(jbuttonedit);
        
        
        
        
        
        //*****************************************************************************
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
           }//end else 
           
          conn.close();
           }catch(Exception ex){
              JOptionPane.showMessageDialog(null, ex.getMessage());
               System.err.println(ex);
           }
           
      }//end function
    
    
    
    
    
    
    
     
    public void deletepost(String id){
   // System.out.println("Clicked delete inside fun!");
    
      
            Connection con = null;
            try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject","root","iNEEDtostudy@202");
            
            Statement t = con.createStatement();
            
            //like delete
            String sql2 = "delete from likes where idpost ='" + id + "'";
            t.executeUpdate(sql2);
            
            //commemtes delete
            String sql3 = "delete from comments where idpost ='" + id + "'";
            t.executeUpdate(sql3);
            
            //post delete
            String sql="delete from post where idpost ='"+id+"'";
            t.executeUpdate(sql);
            
            
            
            con.setAutoCommit(false);
            con.commit();
            con.close();
            
            new userprofile22(usernameorigin).setVisible(true);
            this.dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
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
        jPanel12 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        userimage = new rounded.JLabelRound();
        name = new javax.swing.JLabel();
        bio = new javax.swing.JLabel();
        following = new javax.swing.JLabel();
        fer = new javax.swing.JLabel();
        ercount = new javax.swing.JLabel();
        followers = new javax.swing.JLabel();
        ingcount = new javax.swing.JLabel();
        out = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        explore = new javax.swing.JLabel();
        addpost = new javax.swing.JLabel();
        saved = new javax.swing.JLabel();
        edit = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        fing = new javax.swing.JLabel();
        scrollPane1 = new java.awt.ScrollPane();
        jLabel3 = new javax.swing.JLabel();

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

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        userimage.setBackground(new java.awt.Color(250, 250, 250));

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

        bio.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        bio.setText(" bio");
        bio.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        following.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        following.setForeground(new java.awt.Color(18, 33, 139));
        following.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        following.setText("followers");

        fer.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        ercount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ercount.setText("count");

        followers.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        followers.setForeground(new java.awt.Color(18, 33, 139));
        followers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        followers.setText("following");

        ingcount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ingcount.setText("count");

        out.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                outMouseClicked(evt);
            }
        });

        contact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contactMouseClicked(evt);
            }
        });

        explore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        explore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        addpost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addpost.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addpost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addpostMouseClicked(evt);
            }
        });

        saved.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saved.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saved.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                savedMouseClicked(evt);
            }
        });

        edit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(userimage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(saved, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(explore, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addpost, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(out, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(bio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(following, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ercount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(followers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ingcount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24))))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(out, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                .addComponent(contact, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(edit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addpost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(explore, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saved, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(followers)
                                    .addComponent(following))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fer, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                    .addComponent(fing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ingcount, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ercount, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addComponent(bio, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(userimage, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/mainpage.jpeg"))); // NOI18N
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseClicked
        // TODO add your handling code here:
        // close
        // this.dispose();

        if(player == null){
            userinfo22 editobj = new userinfo22(usernameorigin);
            editobj.info(usernameorigin);//Execute the method my_update to pass str
            editobj.setVisible(true);

            this.dispose();
        }
        else{
           this.player.stop();
       // new userinfo22().setVisible(true);
           userinfo22 editobj = new userinfo22(usernameorigin);
           editobj.info(usernameorigin);//Execute the method my_update to pass str
           editobj.setVisible(true);

           this.dispose(); 
        }
        
        // new userinfo22().setVisible(true);
        // new addpost22().setVisible(true);
    }//GEN-LAST:event_editMouseClicked

    private void savedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_savedMouseClicked
        // TODO add your handling code here:
        if(player == null){
            new saved22(usernameorigin).setVisible(true);
            this.dispose();
            }
        else{
            this.player.stop();
            new saved22(usernameorigin).setVisible(true);
            this.dispose();}
    }//GEN-LAST:event_savedMouseClicked

    private void addpostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addpostMouseClicked
        // TODO add your handling code here:
        // close
        if(player == null){
            new addpost22(usernameorigin).setVisible(true);
            this.dispose();
        }
        else{
            this.player.stop();
            new addpost22(usernameorigin).setVisible(true);
            this.dispose();
        }
        
    }//GEN-LAST:event_addpostMouseClicked

    private void outMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_outMouseClicked
        // TODO add your handling code here:
        // close
        if(player == null){
            new loginForm().setVisible(true);
            this.dispose();
        }
        else{
            this.player.stop();
            new loginForm().setVisible(true);
            this.dispose();
        }
        
    }//GEN-LAST:event_outMouseClicked

    private void nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameMouseClicked
        // TODO add your handling code here:
        if(player == null){
            new userprofile22(usernameorigin).setVisible(true);
            this.dispose();
        }
        else{
            this.player.stop();
            new userprofile22(usernameorigin).setVisible(true);
            this.dispose();
        }
        
    }//GEN-LAST:event_nameMouseClicked

    private void contactMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactMouseClicked
        // TODO add your handling code here:
        if(player == null){
            new contactForm(usernameorigin).setVisible(true);
            this.dispose();
        }
        else{
            this.pack();
            this.player.stop();
            new contactForm(usernameorigin).setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_contactMouseClicked

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
                new userprofile22(usernameorigin).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addpost;
    private javax.swing.JLabel back;
    private javax.swing.JLabel bio;
    private javax.swing.JLabel close;
    private javax.swing.JLabel contact;
    private javax.swing.JLabel edit;
    private javax.swing.JLabel ercount;
    private javax.swing.JLabel explore;
    private javax.swing.JLabel fer;
    private javax.swing.JLabel fing;
    private javax.swing.JLabel followers;
    private javax.swing.JLabel following;
    private javax.swing.JLabel ingcount;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel min;
    private javax.swing.JLabel name;
    private javax.swing.JLabel out;
    private javax.swing.JLabel saved;
    private java.awt.ScrollPane scrollPane1;
    private rounded.JLabelRound userimage;
    // End of variables declaration//GEN-END:variables
}
