/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw_project;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Msys
 */
public class userinfo22Test {

    public userinfo22Test() {
    }

    /**
     * Test of info method, of class userinfo22.
     */
    /**
     * Test of getFileExtension method, of class userinfo22.
     */

    /**
     * Test of userinfotest method, of class userinfo22.
     */
    @Test
    public void testUserinfotest() {
        System.out.println("userinfotest");
        String username = "saja123";
        String email = "saja@gmail.com";
        String name = "sajaaaa";
        String filepath = "C:/Users/Msys/OneDrive/Desktop/102/Photoa/2.jpeg";
        String bio = "good nightt";
        userinfo22 instance = new userinfo22();
        String expResult = "insert successfully";
        String result = instance.userinfotest(username, email, name, filepath, bio);
        assertEquals(expResult, result);
    }

    //******* name is empty
    @Test
    public void testUserinfotest2() {
        System.out.println("userinfotest2");
        String username = "saja123";
        String email = "saja@gmail.com";
        String name = "";
        String filepath = "C:/Users/Msys/OneDrive/Desktop/102/Photoa/2.jpeg";
        String bio = "good nightt";
        userinfo22 instance = new userinfo22();
        String expResult = "somethig wrong happen";
        String result = instance.userinfotest(username, email, name, filepath, bio);
        assertEquals(expResult, result);
    }

    //******* file mismatch
    @Test
    public void testUserinfotest3() {
        System.out.println("userinfotest3");
        String username = "saja123";
        String email = "saja@gmail.com";
        String name = "sjsj";
        String filepath = "C:/Users/Msys/OneDrive/Desktop/102/Photoa/90s.mp4";
        String bio = "good nightt";
        userinfo22 instance = new userinfo22();
        String expResult = "somethig wrong happen";
        String result = instance.userinfotest(username, email, name, filepath, bio);
        assertEquals(expResult, result);
    }

    //********************* name with just numbers:
    @Test
    public void testUserinfotest4() {
        System.out.println("userinfotest4");
        String username = "saja123";
        String email = "saja@gmail.com";
        String name = "11";
        String filepath = "C:/Users/Msys/OneDrive/Desktop/102/Photoa/90s.mp4";
        String bio = "good nightt";
        userinfo22 instance = new userinfo22();
        String expResult = "somethig wrong happen";
        String result = instance.userinfotest(username, email, name, filepath, bio);
        assertEquals(expResult, result);
    }

    /**
     * Test of main method, of class userinfo22.
     */
}
