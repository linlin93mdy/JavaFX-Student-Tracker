/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.app.model;

import java.sql.Date;

/**
 *
 * @author LinnLinn
 */
public class Student {

    private int id;
    private String name;
    private String email;
    private String gender;
    private Date dob;

    public Student() {
    }

    public Student(String name, String email, String gender, Date dob) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
    }

    public Student(int id, String name, String email, String gender, Date dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

}
