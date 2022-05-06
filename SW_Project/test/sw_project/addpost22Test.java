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
public class addpost22Test {

    public addpost22Test() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Before all");
    }

    /**
     * Test of getFileExtension method, of class addpost22.
     */
//    @Test
//    public void testGetFileExtension() {
//        System.out.println("getFileExtension");
//        String fullName = "C:/Users/Msys/OneDrive/Desktop/102/Photoa/2.jpeg";
//        String expResult = "jpeg";
//        String result = addpost22.getFileExtension(fullName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
///********************* evrything goes right
    @Test
    public void testinsert() {
        System.out.println("test insert1");
        String filepath = "C:/Users/Msys/OneDrive/Desktop/102/Photoa/3.jpeg";
        String keyword = "#art";
        String desc = "this is my first test";
        String username = "duha123";
        addpost22 addpost = new addpost22();
        String expResult = "insert successfully";
        String result = addpost.addpostfromjunit(filepath, keyword, desc, username);
        assertEquals(expResult, result);

    }
    //*************** if the user does not write hashtag
     @Test
    public void testinsert2() {
        System.out.println("test insert2");
        String filepath = "C:/Users/Msys/OneDrive/Desktop/102/Photoa/2.jpeg";
        String keyword = "#Hashtag";
        String desc = "this is my first test";
        String username = "duha123";
        addpost22 addpost = new addpost22();
        String expResult = "insert successfully";
        String result = addpost.addpostfromjunit(filepath, keyword, desc, username);
        assertEquals(expResult, result);

    }
    //******* if user not found in appuser table
    @Test
    public void testinsert3() {
        System.out.println("test insert3");
        String filepath = "C:/Users/Msys/OneDrive/Desktop/102/Photoa/2.jpeg";
        String keyword = "#Hashtag";
        String desc = "this is my first test";
        String username = "nn123";
        addpost22 addpost = new addpost22();
        String expResult = "somethig wrong happen";
        String result = addpost.addpostfromjunit(filepath, keyword, desc, username);
        assertEquals(expResult, result);

    }
    //*************** type of file not as required:
    @Test
    public void testinsert4() {
        System.out.println("test insert4");
        String filepath = "C:/Users/Msys/OneDrive/Desktop/102/Photoa/90s.mp3";
        String keyword = "#good";
        String desc = "this is my first test";
        String username = "sabreen123";
        addpost22 addpost = new addpost22();
        String expResult = "somethig wrong happen";
        String result = addpost.addpostfromjunit(filepath, keyword, desc, username);
        assertEquals(expResult, result);

    }

    /**
     * Test of main method, of class addpost22.
     */

}
