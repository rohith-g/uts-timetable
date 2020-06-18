package controller;

import java.io.IOException;

import au.edu.uts.ap.javafx.*;
import model.*;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UniversityController extends Controller<University> {
    @FXML private ListView<Student> studentList;
    @FXML private Button addBtn;
    @FXML private Button removeBtn;
    @FXML private Button loginBtn;

    @FXML
    private void initialize() {
        studentList.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> enableButtons(newValue));
    }

    public final University getUniversity() {
        return model;
    }
    
    private Student getStudent() {
        return studentList.getSelectionModel().getSelectedItem();
    }

    private void enableButtons(Student student) {
        if (student != null) {
            removeBtn.setDisable(false);
            loginBtn.setDisable(false);
        } else {
            removeBtn.setDisable(true);
            loginBtn.setDisable(true);
        }
    }

    @FXML
    private void handleAdd(ActionEvent event) throws Exception {
        ViewLoader.showStage(getUniversity(), "/view/add_student.fxml", "Add Student", new Stage());
    }
     
    @FXML
    private void removeStudent(ActionEvent event) {
        getUniversity().remove(getStudent());
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        ViewLoader.showStage(studentList.getSelectionModel().getSelectedItem(), "/view/student.fxml", "Student", new Stage());
    }
}
