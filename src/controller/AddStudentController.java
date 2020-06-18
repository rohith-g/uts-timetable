package controller;

import au.edu.uts.ap.javafx.*;
import model.*;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class AddStudentController extends Controller<University> {     
    @FXML private Button addBtn;
    @FXML private TextField stuNumTf;
    @FXML private TextField stuNameTf;
    @FXML private ToggleGroup attendanceToggle;
    @FXML private CheckBox scholarCBox;
    @FXML private Label errorText;
    
    @FXML
    private void initialize() {
        stuNameTf.textProperty().addListener((observable, oldValue, newValue) -> enableAdd());
        stuNumTf.textProperty().addListener((observable, oldValue, newValue) -> enableAdd());
        attendanceToggle.selectedToggleProperty().addListener((observable, oldValue, newValue) -> enableAdd());
    }
    
    public final University getUniversity() { 
        return model; 
    }
    
    private String getStuNum() {
        return stuNumTf.getText();
    }
    
    private String getStuName() {
        return stuNameTf.getText();
    }
    
    private String getAttendance() {
        return attendanceToggle.getSelectedToggle().getUserData().toString();
    }
    
    private boolean hasScholarship() {
        return scholarCBox.isSelected();
    }


    private void enableAdd() {
        if (attendanceToggle.getSelectedToggle() != null && getStuNum().length() > 0 && getStuName().length() > 0) {
            addBtn.setDisable(false);
        } else {
            addBtn.setDisable(true);
        }
    }
    
    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            getUniversity().addStudent(getStuNum(), getStuName(), getAttendance(), hasScholarship());
            stage.close();
        } catch (Exception exception) {
            errorText.setText(exception.getMessage());
        }
    }
    
    @FXML
    private void handleCancel(ActionEvent event){
        stage.close();
    }
}
