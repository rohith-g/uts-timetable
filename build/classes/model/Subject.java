package model;

import javafx.beans.property.*;
import javafx.collections.*;

public class Subject {
    private IntegerProperty number = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private ObservableList<Activity> activities = FXCollections.observableArrayList();

    public Subject(int number, String name) {
        this.number.set(number);
        this.name.set(name);
    }

    public int getNumber() { 
        return number.get(); 
    }
    
    public ObservableList<Activity> getActivities() { 
        return activities; 
    }

    public void addActivity(String group, int number, String day, int start, int duration, String room, int capacity) {
        activities.add(new Activity(this, group, number, day, start, duration, room, capacity));
    }

    public boolean matches(int number) {
        return getNumber() == number;
    }

    @Override
    public String toString() {
        return getNumber() + " " + name.get();
    }
}
