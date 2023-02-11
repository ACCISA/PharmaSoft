package com.pharmasoft.Controllers.Menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMenu implements Initializable {

    @FXML
    private VBox backgroundBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double x = screenBounds.getMaxX();
        double y = screenBounds.getMaxY();
        double a = x-(0.3*x);
        double a2 = a/2;
        backgroundBox.setPadding(new Insets(10,a2,10,a2));
    }

}
