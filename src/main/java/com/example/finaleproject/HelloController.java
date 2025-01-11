package com.example.finaleproject;



import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    @FXML
    private MFXButton btnStart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnStart.setOnAction( event -> {
            try {
                changeScene();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Start " +e);
            }
        });
    }

    private void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);

    }
}