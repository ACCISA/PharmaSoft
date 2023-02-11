package com.pharmasoft;

import com.pharmasoft.Entities.Session;
import com.pharmasoft.Entities.Window;
import com.pharmasoft.Utils.Api;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;//s
import javafx.stage.StageStyle;

import java.io.IOException;

public class Start extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        Window background = new Window("src/main/resources/com/pharmasoft/background.fxml",false,true);
        Window start = new Window("src/main/resources/com/pharmasoft/Forms/login-form.fxml", false, true);
        start.Open();
//        Api a = new Api();
//        a.verifyLogin("3","4");
    }

    public static void main(String[] args) {
        launch();
    }
}