/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_register;

import javax.swing.JFrame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import login_register.loginForm;

/**
 *
 * @author Sara
 */
public class OperationsTest {
    Operations opera;
    public OperationsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        opera=new Operations();
    }
    
    @After
    public void tearDown() {
        opera=null;
    }

    /**
     * Test of isLogin method, of class Operations.
     */
   @Test
    public void testIsLogin() {

        String username = "sara21";
        String password = "sarasara";
        String usertype = "User";
        loginForm  log =null;
        JFrame frame = log;
        boolean expResult = true;
        boolean result = Operations.isLogin(username, password, usertype, frame);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of isRegistered method, of class Operations.
     */
  /*  @Test
    public void testIsRegistered() {
        System.out.println("isRegistered");
        String Rfullname = " Sara Mahmoud";
        String Rgender = "Male";
        String Remail = "saramahmoud15420@gmail.com";
        String Rpath = "C:\\Users\\Sara\\OneDrive\\Pictures\\Screenshots.png";
        JFrame RegisterForm = null;
        JFrame frame = RegisterForm;
        boolean expResult = false;
        boolean result = Operations.isRegistered(Rfullname, Rgender, Remail, Rpath, frame);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of isTaken method, of class Operations.
     */
    /*@Test
    public void testIsTaken() {
        System.out.println("isTaken");
        String username = "";
        JFrame frame = null;
        boolean expResult = false;
        boolean result = Operations.isTaken(username, frame);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of insertUser method, of class Operations.
     */
  /*  @Test
    public void testInsertUser() {
        System.out.println("insertUser");
        String RemailStr = "";
        String username = "";
        String password = "";
        JFrame frame = null;
        Operations.insertUser(RemailStr, username, password, frame);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }/*

    /**
     * Test of resetPassword method, of class Operations.
     */
   /* @Test
    public void testResetPassword() {
        System.out.println("resetPassword");
        String newpass = "";
        String name = "";
        JFrame frame = null;
        Operations.resetPassword(newpass, name, frame);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of checkEmail method, of class Operations.
     */
  /*  @Test
    public void testCheckEmail() {
        System.out.println("checkEmail");
        String Email = "";
        JFrame frame = null;
        Operations instance = new Operations();
        boolean expResult = false;
        boolean result = instance.checkEmail(Email, frame);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of contact method, of class Operations.
     */
   /* @Test
    public void testContact() {
        System.out.println("contact");
        String name = "";
        String email = "";
        String message = "";
        JFrame frame = null;
        Operations.contact(name, email, message, frame);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    } */
    
}
