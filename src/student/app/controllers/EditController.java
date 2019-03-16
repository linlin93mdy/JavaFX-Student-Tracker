/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.app.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import student.app.dao.StudentDAO;
import student.app.model.Student;

/**
 * FXML Controller class
 *
 * @author LinnLinn
 */
public class EditController implements Initializable {

    @FXML
    private TextField nameFieldofEdit;
    @FXML
    private TextField emailFieldofEdit;
    @FXML
    private RadioButton maleRadioOfEdit;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton femaleRadioOfEdit;
    @FXML
    private DatePicker dobPickerOfEdit;
    @FXML
    private Button saveBtnOfEdit;

    private int studentId;
    private StudentDAO studentDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maleRadioOfEdit.setUserData("Male");
        femaleRadioOfEdit.setUserData("Female");
        studentDAO = new StudentDAO();
    }

    @FXML
    private void saveOfEdit(ActionEvent event) {
        String name = nameFieldofEdit.getText();
        String email = emailFieldofEdit.getText();
        String userdata = (String) gender.getSelectedToggle().getUserData();
        LocalDate dob = dobPickerOfEdit.getValue();

        if (name.isEmpty() || email.isEmpty() || dob == null) {
            alertErrorBox("Please fill out all required fields.....");
            return;
        }
        Date sqlDate = Date.valueOf(dob);
        try {
            // localDate - to - sqlDate
            Student sd = new Student(studentId, name, email, userdata, sqlDate);
            studentDAO.updateStudent(sd);
            Stage currentStage = (Stage) nameFieldofEdit.getScene().getWindow();
            currentStage.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStudentData(Student selectStudent) {
        this.studentId = selectStudent.getId();
        nameFieldofEdit.setText(selectStudent.getName());
        emailFieldofEdit.setText(selectStudent.getEmail());
        if (selectStudent.getGender().equals("Male")) {
            maleRadioOfEdit.setSelected(true);
        } else {
            femaleRadioOfEdit.setSelected(true);
        }
        Date sqlDate = selectStudent.getDob();
        LocalDate local = sqlDate.toLocalDate();
        dobPickerOfEdit.setValue(local);

    }

    private void alertErrorBox(String txt) {
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.setHeaderText(null);
        alert1.setContentText(txt);
        alert1.show();
    }

    private void alertInfoBox(String txt) {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setHeaderText(null);
        alert1.setContentText(txt);
        alert1.show();
    }

}
