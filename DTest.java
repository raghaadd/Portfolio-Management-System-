/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swproject.S;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import swproject.S$D;

/**
 *
 * @author DELL
 */
public class DTest {
    
    public DTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class S$D.
     */
   
   
    /**
     * Test of searchfromjunit method, of class S$D.
     */
    @Test
    public void testSearchfromjunit() {
        System.out.println("searchfromjunit");
        String user = "samah";
        S$D instance = new S$D();
        String expResult = "search successfully";
        String result = instance.searchfromjunit(user);
        assertEquals(expResult, result);
        
    }
     @Test
    public void testSearchfromjunit1() {
        System.out.println("searchfromjunit");
        String user = "rand";
        S$D instance = new S$D();
        String expResult = "something wrong happen";
        String result = instance.searchfromjunit(user);
        assertEquals(expResult, result);
        
    }
    
     @Test
    public void testSearchfromjunit2() {
        System.out.println("searchfromjunit");
        String user = "1";
        S$D instance = new S$D();
        String expResult = "something wrong happen";
        String result = instance.searchfromjunit(user);
        assertEquals(expResult, result);
        
    }
      @Test
    public void testSearchfromjunit3() {
        System.out.println("searchfromjunit");
        String user = " ";
        S$D instance = new S$D();
        String expResult = "something wrong happen";
        String result = instance.searchfromjunit(user);
        assertEquals(expResult, result);
        
    }
    
}
