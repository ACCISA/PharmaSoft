package com.pharmasoft.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class BaseController {

    @FXML
    private JFXButton closeBtn;

    public void closeBtnAction(ActionEvent actionEvent) {
        closeBtn.getScene().getWindow().hide();
        System.out.println("[APP] Application Closed");
    }
}
