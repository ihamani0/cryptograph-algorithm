package com.example.finaleproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainModernController implements Initializable {

    @FXML
    private Button btnAsymmtric;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSymmtric;

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

        //Asymmtric scene
        btnAsymmtric.setOnAction(event -> {
            try {
                AsymmtricScene();
            }catch (IOException e){
                throw new RuntimeException("Exception From change scene Btn classice " +e);
            }
        });

        //Symmtric scene
        btnSymmtric.setOnAction(event -> {
            try {
                SymmtricScene();
            }catch (IOException e){
                throw new RuntimeException("Exception From change scene Btn classice " +e);
            }
        });
    }

    private void BackScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        HelloApplication.stagePrime.setScene(scene);
    }


    private void AsymmtricScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Asymmetric.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }

    private void SymmtricScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Symmetric.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }
}
