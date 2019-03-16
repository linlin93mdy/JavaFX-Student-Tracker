/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import student.app.database.Database;
import student.app.model.Student;

/**
 *
 * @author LinnLinn
 */
public class StudentDAO {
    //CRUD

    // Create
    public int saveStudent(Student sd) throws SQLException {
        
        Connection conn = Database.getDatabaseObject().getConnection();
//        String name = sd.getName();
//        String email = sd.getEmail();
//        String gender = sd.getGender();
//        Date dob = sd.getDob();
        String sql = "insert into students (name,email,gender,dob) values (?,?,?,?)";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, sd.getName());
        stat.setString(2, sd.getEmail());
        stat.setString(3, sd.getGender());
        stat.setDate(4, sd.getDob());
        int row = stat.executeUpdate();
        return row;
    }

    // Read
    public Student getStudentById(int id) throws SQLException {
        
        Connection conn = Database.getDatabaseObject().getConnection();
        String sql = "select * from students where id=?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setInt(1, id);
        ResultSet result = stat.executeQuery();
        Student sd = null;
        if (result.next()) {
            String name = result.getString("name");
            String email = result.getString("email");
            String gender = result.getString("gender");
            Date dob = result.getDate("dob");
            sd = new Student(name, email, gender, dob);
        }
        return sd;
    }

    public List<Student> getStudents() throws SQLException {
        
        Connection conn = Database.getDatabaseObject().getConnection();
        String sql = "Select * from students";
        Statement stat = conn.createStatement();
        ResultSet result = stat.executeQuery(sql);
        List<Student> sdList = new LinkedList<>();
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");
            String email = result.getString("email");
            String gender = result.getString("gender");
            Date dob = result.getDate("dob");
            Student sd = new Student(id,name, email, gender, dob);
            sdList.add(sd);
        }
        return sdList;
    }

    public int updateStudent(Student obj) throws SQLException {
        
        Connection conn = Database.getDatabaseObject().getConnection();
        int id = obj.getId();
        String name = obj.getName();
        String email = obj.getEmail();
        String gender = obj.getGender();
        Date dob = obj.getDob();
        String sql = "update students set name=?,email=?,gender=?,dob=? where id=?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, name);
        stat.setString(2, email);
        stat.setString(3, gender);
        stat.setDate(4, dob);
        stat.setInt(5, id);
        return stat.executeUpdate();
    }

    public int deleteStudentById(int id) throws SQLException {
        Connection conn = Database.getDatabaseObject().getConnection();
        String sql = "delete from students where id=?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setInt(1, id);
        return stat.executeUpdate();
    }

}
