/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Date;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import student.app.dao.StudentDAO;
import student.app.model.Student;

/**
 *
 * @author LinnLinn
 */
public class StudentDAOTest {
    
    public StudentDAOTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
     public void textSave() throws SQLException {
         StudentDAO dao = new StudentDAO();
         Date dob = new Date(System.currentTimeMillis());
         Student sd = new Student("Aye Aye", "ayeaye@gmail.com", "Female", dob);
         assertEquals(1, dao.saveStudent(sd));
                 
     }
     
    // @Test
     public void testGetStudents() throws SQLException{
         StudentDAO dao = new StudentDAO();
         assertEquals(2, dao.getStudents().size());   // list's size 
     }
     
    // @Test
     public void deleteStudent() throws SQLException{
         StudentDAO dao = new StudentDAO();
         assertEquals(1, dao.deleteStudentById(8));
     
     }
}
