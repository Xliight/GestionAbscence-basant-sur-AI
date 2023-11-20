package com.example.dao;

import javafx.collections.ObservableList;
import com.example.model.employee;

public interface employeedao {
    public void save(employee e);

    public ObservableList<employee> showlist();
    public ObservableList<employee> showlistbyid(String id);
}
