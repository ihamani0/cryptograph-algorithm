package com.example.finaleproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClassicController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCesare;


    @FXML
    private Button btnAffineSecen;

    @FXML
    private Button btnHillSecen;

    @FXML
    private Button btnModren;

    @FXML
    private Button btnViginerSecen;



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

        //Afffine secen
        btnAffineSecen.setOnAction( event -> {
            try {
                AffineScene();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Affine " +e);
            }
        });

        //Cesare secen
        btnCesare.setOnAction(event -> {
            try {
                CesareScene();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Cesare " +e);
            }
        });

        //Hill secen
        btnHillSecen.setOnAction(event -> {
            try {
                HillScene();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Cesare " +e);
            }
        });

        //viginer secen
        btnViginerSecen.setOnAction(event -> {
            try {
                ViginerScene();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Cesare " +e);
            }
        });

        //Modren scene
        btnModren.setOnAction(event -> {
            try {
                ModernScene();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Cesare " +e);
            }
        });


    }

    private void BackScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        HelloApplication.stagePrime.setScene(scene);
    }

    private void CesareScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Cesare.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }

    private void AffineScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Affine.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }

    private void HillScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Hill.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }

    private void ViginerScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Viginer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }

    private void ModernScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainModern.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }


}
