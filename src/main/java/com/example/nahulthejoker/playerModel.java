package com.example.nahulthejoker;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.SQLException;

public class playerModel {

    private StringProperty id = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty dept = new SimpleStringProperty();
    private StringProperty pos = new SimpleStringProperty();
    private StringProperty val = new SimpleStringProperty();


    public playerModel(String id, String name, String dept, String pos, String val) {
        this.id.set(id);
        this.name.set(name);
        this.dept.set(dept);
        this.pos.set(pos);
        this.val.set(val);
    }

    public playerModel() {
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDept() {
        return dept.get();
    }

    public StringProperty deptProperty() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept.set(dept);
    }

    public String getPos() {
        return pos.get();
    }

    public StringProperty posProperty() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos.set(pos);
    }

    public String getVal() {
        return val.get();
    }

    public StringProperty valProperty() {
        return val;
    }

    public void setVal(String val) {
        this.val.set(val);
    }




}
