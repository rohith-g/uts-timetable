package controller;

import au.edu.uts.ap.javafx.*;
import javafx.beans.property.*;
import model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.*;

public class StudentController extends Controller<Student> { 
    @FXML private Text scholarshipTxt;
    @FXML private Text attendanceTxt;
    @FXML private Button withdrawBtn;   
    @FXML private Button enrolBtn;   
    @FXML private TableView<Activity> activitiesTable;
    @FXML private TableView<Activity> enrolTable;
    @FXML private ComboBox<Subject> subjectChoice;
    
    @FXML
    private void initialize() {
        scholarshipTxt.setText(setScholarshipTxt());        
        attendanceTxt.setText(setAttendanceTxt());
        activitiesTable.setItems(getStudent().getActivities());
        activitiesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> enableWithdraw());
        subjectChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> populateActivities(newValue));
        enrolTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> enableEnrol(newValue));
        
        enrolTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            clearWithdrawSelection(newSelection);
        });

        activitiesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            clearEnrolSelection(newSelection);
        });
    }
 
    private void clearEnrolSelection(Activity activity) {
        if (activity != null) {
            enrolTable.getSelectionModel().clearSelection();
            enrolBtn.setDisable(true);
        }
    }
    
    private void clearWithdrawSelection(Activity activity) {
        if (activity != null) {
            activitiesTable.getSelectionModel().clearSelection();
            withdrawBtn.setDisable(true);
        }
    }
    
    private String setScholarshipTxt() {
        if(getStudent().getScholarship()) {
            return "Yes";
        } else {
            return "No";
        }
    }
    
    private String setAttendanceTxt() {
        if(getStudent().getAttendance().equals("ft")) {
            return "Full Time";
        } else {
            return "Part Time";
        }
    }
    
    private void enableWithdraw() {
        withdrawBtn.setDisable(false);
    }

    private void enableEnrol(Activity activity) {
        if(activity != null) {
            if(activity.canEnrol()) {
                if(!getStudent().isEnrolledIn(activity)) {
                    enrolBtn.setDisable(false);
                } else {
                    enrolBtn.setDisable(true);
                }
            }
        }
    }

    private void populateActivities(Subject subject) {
        enrolTable.setItems(subject.getActivities());
    }

    public final Student getStudent() { 
        return model; 
    }

    public final University getUniversity() {
        return getStudent().getUniversity();
    }

    @FXML
    public void handleEnrol(ActionEvent event) {
        Activity enrolActivity = enrolTable.getSelectionModel().getSelectedItem();
        getStudent().enrol(enrolActivity);
        enrolBtn.setDisable(true);
    }

    @FXML
    public void handleWithdraw(ActionEvent event) {
        Activity withdrawActivity = activitiesTable.getSelectionModel().getSelectedItem();
        getStudent().withdraw(withdrawActivity);
        activitiesTable.getSelectionModel().clearSelection();
        withdrawBtn.setDisable(true);
    }
    
    @FXML
    public void handleLogout(ActionEvent event) {
        stage.close();
    }
}
