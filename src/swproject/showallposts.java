/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class showallposts extends javax.swing.JFrame {
int id,deleteitem,q,q1,q2,i;
    /**
     * Creates new form showallposts
     */
    public showallposts() {
        initComponents();
         super.setLocationRelativeTo(null);
        super.setSize(730, 520);
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
     public void upDateDB(){
        try{
       // Class.forName("mysql.jdbc,Drive");
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/database1","root","11923918r");
        String sql="Select * from post";
         PreparedStatement pst=conn.prepareStatement(sql);
           ResultSet rs=pst.executeQuery();
           
           String sql1="Select * from comments";
         PreparedStatement pst1=conn.prepareStatement(sql1);
           ResultSet rs1=pst1.executeQuery();
           
           
           String sql2="Select * from likes";
         PreparedStatement pst2=conn.prepareStatement(sql2);
           ResultSet rs2=pst2.executeQuery();
           
           
           
           
           ResultSetMetaData stData=rs.getMetaData();
           ResultSetMetaData stData1=rs1.getMetaData();
           ResultSetMetaData stData2=rs2.getMetaData();
           
           q=stData.getColumnCount();
             q1=stData1.getColumnCount();
               q2=stData2.getColumnCount();
           DefaultTableModel RecordTable=(DefaultTableModel)posttable.getModel();
           RecordTable.setRowCount(0);
           
           while(rs.next()){
               Vector columnData=new Vector();
               for(i=1;i<=q;i++)
               {
                 columnData.add(rs.getString("idpost"));
                 columnData.add(rs.getString("content"));
                 columnData.add(rs.getString("description"));
                 columnData.add(rs.getString("username"));
                
               }
               RecordTable.addRow(columnData);
           }
           
           while(rs1.next()){
               Vector columnData=new Vector();
               for(i=1;i<=q1;i++)
               {
                   columnData.add(rs1.getString("idcomments"));
                 columnData.add(rs1.getString("username"));
                 columnData.add(rs1.getString("content"));
                  columnData.add(rs1.getString("idpost"));
                
               }
               RecordTable.addRow(columnData);
           }
           
           
           while(rs2.next()){
               Vector columnData=new Vector();
               for(i=1;i<=q2;i++)
               {
                   columnData.add(rs2.getString("idlikes"));
                 columnData.add(rs2.getString("idpost"));
                 columnData.add(rs2.getString("username"));
                
               }
               RecordTable.addRow(columnData);
           }
           
           
           
           
    }
        
        catch (Exception ex){
           //JOptionPane.showMessageDialog(this, e);
           System.out.println("Error :" +ex.getMessage());
       }
        
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        posttable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(349, 121));

        jPanel2.setBackground(new java.awt.Color(26, 162, 163));

        posttable.setBackground(new java.awt.Color(26, 162, 163));
        posttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID_Post", "Content", "Description", "Username"
            }
        ));
        posttable.setGridColor(new java.awt.Color(18, 33, 139));
        jScrollPane1.setViewportView(posttable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(333, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(26, 162, 163));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("View");
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(26, 162, 163));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Delete This Post");
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(26, 162, 163));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Back");
        jButton3.setBorder(null);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try{
      //   Class.forName("mysql.jdbc,Drive");
         Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/database1","root","11923918r");
        String sql="Select * from post";
         PreparedStatement pst=conn.prepareStatement(sql);
           ResultSet rs=pst.executeQuery();
 
           DefaultTableModel model=(DefaultTableModel)posttable.getModel();
          
           model.setRowCount(0);
     
           while(rs.next())
           {
              
                   model.addRow(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
           }
                  
                  
             
              
             // System.out.println("hiii");
        
         }
           
           
           
           
           
          // String sql1="Select count(idcomments) from comments  ";
           
           //where idpost= ' "+rs.getInt(1)+" '
          // PreparedStatement pst1=conn.prepareStatement(sql1);
          // ResultSet rs1=pst1.executeQuery();
           
          //  while(rs1.next()){
          //     model.addRow(new String[]{rs.getString(5)});
               
          // }
          
           //if(rs1.next()){
           //    String count=rs1.getString("count(idcomments)");
             //  model.addRow(new String[]{rs.getString(count)});
           //     System.out.print(count);
               
          //}
       
       
       catch (Exception ex){
           //JOptionPane.showMessageDialog(this, e);
           System.out.println("Error :" +ex.getMessage());
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         DefaultTableModel RecordTable=(DefaultTableModel)posttable.getModel();
        int SelectedRow=posttable.getSelectedRow();
        
        try{
            id=Integer.parseInt(RecordTable.getValueAt(SelectedRow, 0).toString());
            deleteitem=JOptionPane.showConfirmDialog(null,"Confirm if you want to delete this post","Warning",JOptionPane.YES_NO_OPTION);
            
            if(deleteitem==JOptionPane.YES_OPTION)
            {
          //    Class.forName("mysql.jdbc,Drive");  
              Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/database1","root","11923918r");
              String sql=("delete from post where idpost=?");  
              PreparedStatement pst=conn.prepareStatement(sql);
              
              String sql1=("delete from likes where idpost=?"); 
              PreparedStatement pst1=conn.prepareStatement(sql1);
              
              String sql2=("delete from comments where idpost=?"); 
              PreparedStatement pst2=conn.prepareStatement(sql2);
              
              pst.setInt(1, id);
              pst.executeUpdate();
              
              pst1.setInt(1, id);
              pst1.executeUpdate();
              
              pst2.setInt(1, id);
              pst2.executeUpdate();
              
              JOptionPane.showMessageDialog(this, "Delete Successfully!");
          //    upDateDB();
              
            }
               
                    
      
       
       }
       catch (Exception ex){
           
         // System.out.println("Error :" +ex.getMessage());
       }
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
        aboutpost abp=new aboutpost ();
        abp.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(showallposts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(showallposts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(showallposts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(showallposts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new showallposts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable posttable;
    // End of variables declaration//GEN-END:variables
}
