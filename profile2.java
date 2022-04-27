
package softwareproject;

import java.awt.Color;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class profile2 extends javax.swing.JFrame {
    
JPanel postbase = new JPanel();
    /**
     * Creates new form profile2
     */
    public profile2() {
        initComponents();
        photo.setIcon(new ImageIcon(getClass().getResource("image/wa0.jpeg")));
        
        scrollPane2.add(postbase);
        setResizable(false);
        setLocationRelativeTo(null);
        makepanel();
        dbconnect();
    }
//////////////////////////////////////////////////////////////////////////////////////////////////
    public void dbconnect(){
    
       // String url = "jdbc:mysql://localhost:3306/db1?useSSL=true";
       // String user = "softwareproject";
       // String pass = "iNEEDtostudy@202";
        
       Connection con = null;
       ResultSet rs = null;
       PreparedStatement ps = null;
       
           try{
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject","root","iNEEDtostudy@202");
           ps = con.prepareStatement("select * from post where idpost = ?");
           ps.setString(1,"1");
           rs = ps.executeQuery();
           }catch(Exception ex){
              JOptionPane.showMessageDialog(null, ex.getMessage());
               System.err.println(ex);
           }
        
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////
    public void makepanel(){
        
        JPanel jhh =new JPanel(); 
        postbase.setLayout(new BoxLayout(postbase, BoxLayout.Y_AXIS));
        for(int i=0; i<100; i++){//less than posts saved in database
        
        
         
        if(i%2 == 0){
            jhh.setLayout(null);//new FlowLayout());
            //add the jpanel in witch the post will be displayed on
            jhh.setPreferredSize(new Dimension(50, 250));
            jhh.setBackground(new java.awt.Color(18, 33, 139));
         
            
            //add the jlable that will have the image 
          //  JLabel l = new JLabel("here please", SwingConstants.CENTER);
            JLabel l = new JLabel("", new ImageIcon(getClass().getResource("image/S_I_1.png")), SwingConstants.CENTER);//JLabel("Text"); //JLabel(Icon i)	Creates a JLabel instance with the specified image.
          
            
          //  Dimension size = l.getPreferredSize();
            l.setBounds(20, 20, 300,210); //size.width, size.height);
            l.setOpaque(true);
            l.setBackground(new java.awt.Color(250,250,250));
       
            jhh.add( l );
         
         
         
         
        }
        
        else{
           
             jhh.setBackground(new java.awt.Color(240,240,240));
             jhh.setPreferredSize(new Dimension(10, 15));
             
        }
           
           
         postbase.add(jhh);  
         jhh =new JPanel(); 
         jhh.setVisible(true);
          }//for end
      }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        base = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        scrollPane2 = new java.awt.ScrollPane();
        photo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        base.setBackground(new java.awt.Color(255, 255, 255));

        scrollPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        photo.setBackground(new java.awt.Color(255, 255, 255));
        photo.setForeground(new java.awt.Color(255, 255, 255));
        photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        photo.setOpaque(true);

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
        );
        baseLayout.setVerticalGroup(
            baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseLayout.createSequentialGroup()
                .addContainerGap(214, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(profile2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(profile2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(profile2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(profile2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new profile2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel base;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel photo;
    private java.awt.ScrollPane scrollPane2;
    // End of variables declaration//GEN-END:variables
}
