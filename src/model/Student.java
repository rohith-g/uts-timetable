package model;

import javafx.beans.property.*;
import javafx.collections.*;

public class Student {
    private University university;
    private StringProperty number = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty attendance = new SimpleStringProperty();
    private BooleanProperty scholarship = new SimpleBooleanProperty();
    private ObservableList<Activity> activities = FXCollections.observableArrayList();

    public Student(University university, String number, String name, String attendance, boolean scholarship) {
        this.university = university;
        this.number.set(number);
        this.name.set(name);
        this.attendance.set(attendance);
        this.scholarship.set(scholarship);
    }

    public University getUniversity() { return university; }
    public String getNumber() { return number.get(); }
    public String getName() { return name.get(); }
    public String getAttendance() { return attendance.get(); }
    public Boolean getScholarship() { return scholarship.get(); }
    public ObservableList<Activity> getActivities() { return activities; }

    public boolean isEnrolledIn(Activity activity) {
        return activities.contains(activity);
    }

    public boolean matches(String number) {
        return getNumber().equals(number);
    }

    public void enrol(Activity activity) {
        Activity act = activity(activity.getSubjectNumber(), activity.getGroup());

        if (isEnrolledIn(act))
            withdraw(act);

        activities.add(activity);
        activity.enrol();
    }

    public void withdraw(Activity activity) {
        activities.remove(activity);
        activity.withdraw();
    }

    private Activity activity(int subjectNumber, String group) {
        for (Activity activity : activities)
            if (activity.matches(subjectNumber, group))
                return activity;
        return null;
    }

    @Override
    public String toString() {
        return getNumber() + " - " + getName();
    }
}
