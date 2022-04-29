/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rounded;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Msys
 */
public class JLabelRound extends JLabel{
     public JLabelRound(){
        number="";
        intComponent();
    }
    
    public JLabelRound(String info){
     number=info;
     intComponent();
             
    }
     public JLabelRound(ImageIcon info){
     icono=info;   
     intComponent();
    }
     private void intComponent()
     {
         setText(number);
         setHorizontalAlignment(CENTER);
         setIcon(icono);
         setOpaque(true);
         setBorder(border);
         setPreferredSize(new Dimension(100,100));
     }
    private String number="";
    private ImageIcon icono=null;
    private final borderround border=new borderround();
    
    
}
