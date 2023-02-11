package com.pharmasoft.Controllers.Forms;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.pharmasoft.Utils.Api;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginForm implements Initializable {


    @FXML
    private JFXTextField username_field;

    @FXML
    private JFXPasswordField password_field;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username_field.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)){
                    loginAttempt();
                }
            }
        });
        password_field.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)){
                    loginAttempt();
                }
            }
        });
    }

    public void loginAttempt()  {
        Api login_attempt = new Api();
        try {
            login_attempt.verifyLogin(username_field.getText(),password_field.getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
