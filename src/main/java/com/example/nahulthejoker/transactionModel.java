package com.example.nahulthejoker;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class transactionModel extends playerModel {

    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty current_amount = new SimpleStringProperty();

    public transactionModel(String id, String name, String dept, String pos, String val, String status, String current_amount) {
        super(id, name, dept, pos, val);
        this.status.set(status);
        this.current_amount.set(current_amount);
    }

    public transactionModel() {
        super();
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getCurrent_amount() {
        return current_amount.get();
    }

    public StringProperty current_amountProperty() {
        return current_amount;
    }

    public void setCurrent_amount(String current_amount) {
        this.current_amount.set(current_amount);
    }
}
