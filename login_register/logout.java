/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_register;

import javax.swing.JFrame;

/**
 *
 * @author Sara
 */
public class logout {
    public static void logout(JFrame context,loginForm loginScreen){
        loginSession.isLogged=false;
        context.setVisible(false);
        loginScreen.setVisible(true);
    }
    
}
