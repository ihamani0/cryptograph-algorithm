package com.example.finaleproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SymmetricController implements Initializable {

    @FXML
    private Button btnAes;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClassic;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Back secen
        btnBack.setOnAction( event -> {
            try {
                BackScene();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Back " +e);
            }
        });

        //Classic secen
        btnClassic.setOnAction(event -> {
            try {
                ClassicScene();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Cesare " +e);
            }
        });

        //Aes secen
        btnAes.setOnAction(event -> {
            try {
                AesScene();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Cesare " +e);
            }
        });
    }

    private void BackScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainModern.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }

    private void ClassicScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Classic.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }

    private void AesScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Aes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }


}
