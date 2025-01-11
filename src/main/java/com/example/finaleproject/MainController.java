package com.example.finaleproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnClassic;

    @FXML
    private Button btnModren;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //For button back
        btnBack.setOnAction( event -> {
            try {
                BackScene();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Back " +e);
            }
        });

        //for button classice
        btnClassic.setOnAction(event -> {
            try {
                ClassiceScene();
            }catch (IOException e){
                throw new RuntimeException("Exception From change scene Btn classice " +e);
            }
        });

        btnModren.setOnAction(event -> {
            try {
                ModernScene();
            }catch (IOException e){
                throw new RuntimeException("Exception From change scene Btn classice " +e);
            }
        });
    }

    private void ClassiceScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Classic.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }

    private void ModernScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainModern.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }


    private void BackScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        HelloApplication.stagePrime.setScene(scene);
    }
}
