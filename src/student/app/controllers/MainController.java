/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.app.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import student.app.dao.StudentDAO;
import student.app.model.Student;

/**
 *
 * @author LinnLinn
 */
public class MainController implements Initializable {

    private Label myLabel;
    @FXML
    private MenuItem about;
    @FXML
    private ToggleGroup gender;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private DatePicker dobPicker;
    @FXML
    private Button saveBtn;

    private StudentDAO studentDAO;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> idCol;
    @FXML
    private TableColumn<Student, String> nameCol;
    @FXML
    private TableColumn<Student, String> emailCol;
    @FXML
    private TableColumn<Student, String> genderCol;
    @FXML
    private TableColumn<Student, Date> dobCol;
    @FXML
    private MenuItem editItem;
    @FXML
    private MenuItem deleteItem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        maleRadio.setUserData("male");
        maleRadio.setSelected(true);
        femaleRadio.setUserData("female");
        studentDAO = new StudentDAO();

        initColoum();
        loadTableData();

    }

    public void setTitle(String txt) {
//            myLabel.setText(txt);
    }

    @FXML
    private void showAboutWindow(ActionEvent event) throws IOException {
        Parent layout = FXMLLoader.load(getClass().getResource("/student/app/view/about.fxml"));
        Scene scene = new Scene(layout);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(menuBar.getScene().getWindow());
        stage.show();
        
    }

    @FXML
    private void saveStudent(ActionEvent event) {
        String name = nameField.getText();
        String email = emailField.getText();
        String userdata = (String) gender.getSelectedToggle().getUserData();
        LocalDate dob = dobPicker.getValue();
        
        if (name.isEmpty() || email.isEmpty() || dob == null) {
            alertErrorBox("Please fill out all required fields.....");
            return;
        }
        // localDate - to - sqlDate
        Date sqlDate = Date.valueOf(dob);
        
        try {
            
            Student sd = new Student(name, email, userdata, sqlDate);
            studentDAO.saveStudent(sd);
            alertInfoBox("Student saved..");
            loadTableData();
            clearForm();

        } catch (SQLException ex) {
            System.out.println("Error.");
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showEditWindow(ActionEvent event) throws IOException {

        Student selectStudent = studentTable.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/student/app/view/edit.fxml"));
        Parent root = loader.load();
        EditController controller = loader.getController();
        controller.setStudentData(selectStudent);
        Scene scene = new Scene(root);
        Stage editStage = new Stage();
        editStage.setScene(scene);
        editStage.initModality(Modality.WINDOW_MODAL);
        editStage.initOwner(studentTable.getScene().getWindow());
        editStage.showAndWait();
        loadTableData();

    }

    @FXML
    private void deleteStudent(ActionEvent event) {
        // get Selected Student
        Student selectStudent = studentTable.getSelectionModel().getSelectedItem();

        if (selectStudent == null) {
            alertErrorBox("Please select Student.....");
            return;
        }
        // delete from student
        try {
            studentDAO.deleteStudentById(selectStudent.getId());
            studentTable.getItems().remove(selectStudent);
            alertInfoBox("Finished delete");
            //    loadTableData();    // this is my logic
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initColoum() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
    }

    private void loadTableData() {
        List<Student> students;
        try {
            students = studentDAO.getStudents();
            studentTable.getItems().setAll(students);       // setAll --> add data after remove exiting data
            //        studentTable.getItems().addAll(students);       // addAll --> add data with exiting data
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clearForm() {
        nameField.clear();
        emailField.clear();
        dobPicker.getEditor().clear();
        // dobPicker.setValue(null);

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
