package com.pharmasoft.Entities;


import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Window {//


    private final int width = 300;
    private final int height = 350;
    private String fxml;
    private String title = "PharmaSoft";
    private String icon;
    private String styleSheet = "style.css";
    private double x = 0;
    private double y = 0;
    private Stage stage;
    private boolean isDraggable;
    private boolean isFullscreen;
    private ArrayList<Stage> allStage;
    private String style = "src/main/resources/com/pharmasoft/Styles/styles.css";
    public static Window cur_window;
    public Scene scene;
    public Window( String fxml, String style){
        this.style = style;

        this.fxml = fxml;
        this.icon = "Images/pharma_logo.jpg"; // TODO
        CreateWindow();
    }

    public Window(String fxml,boolean isDraggable, boolean isFullscreen){
        this.fxml = fxml;
        this.isDraggable = isDraggable;
        this.isFullscreen = isFullscreen;
        if (isFullscreen){
            Window.cur_window = this;
        }
        CreateWindow();
    }

    public Window( String fxml, String title, String icon){

        this.fxml = fxml;
        this.title = title;
        this.icon = icon;
        CreateWindow();
    }

    public static void Close(JFXButton component){
        component.getScene().getWindow().hide();
        System.out.println("[APP] Application has been closed");

    }

    private ImageView image(){
        Image image = new Image("");
        ImageView background = new ImageView(image);
        return background;
    }

    private void CreateWindow(){
        this.stage = new Stage();

        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        File f = new File(fxml);
        String abs = f.getAbsolutePath();
        int fxmlLength = fxml.length();
        int absLength = abs.length();
        String sourcePath = abs.substring(0,absLength-fxmlLength);
        if (this.isFullscreen){
            stage.setMaximized(true);
        }
        stage.getIcons().add(new Image(sourcePath+"src/main/resources/com/pharmasoft/Images/pharma_logo.jpg"));
        try{

            URL fxmlLocation = getClass().getClassLoader().getResource(fxml);
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            URL url = new File(fxml).toURI().toURL();
            Parent root = FXMLLoader.load(url);
            scene = new Scene(root,width,height);
            scene.setFill(Color.TRANSPARENT);

            if (isDraggable){
                scene.setOnMousePressed(mouseEvent -> {
                    x = mouseEvent.getSceneX();
                    y = mouseEvent.getSceneY();
                });

                scene.setOnMouseDragged(mouseEvent -> {
                    stage.setX(mouseEvent.getScreenX() - x);
                    stage.setY(mouseEvent.getScreenY() - y);
                });
            }
            stage.setTitle(title);
            stage.setScene(scene);

            ScrollPane scroll = new ScrollPane();


            URL urlCSS = new File(style).toURI().toURL();
            scene.getStylesheets().add(urlCSS.toExternalForm());
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeRoot(String new_fxml){

        URL fxmlLocation = getClass().getClassLoader().getResource(new_fxml);
        FXMLLoader loader = new FXMLLoader(fxmlLocation);

        try {
            URL url = new File(new_fxml).toURI().toURL();
            Parent root = FXMLLoader.load(url);
            scene.setRoot(root);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Open(){
        stage.show();
        System.out.println("[APP] Opened " +fxml);
    }

    public void Hide(){
        stage.hide();
    }

    public static String backgroundPath;
    public static void SetBackground(String filePath){

        File f = new File(filePath);
        System.out.println("fpath: " + filePath);
        String abs = f.getAbsolutePath();
        backgroundPath = abs;

        backgroundPath = backgroundPath.replaceAll("\\\\","/");
        System.out.println("[APP] Background Set to " + backgroundPath.replaceAll("\\\\","/"));
    }


}
