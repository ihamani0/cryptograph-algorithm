package com.example.finaleproject;

import Algorithem.Hash;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class HashController implements Initializable {

    @FXML
    private ComboBox<String> ComboBox_keySize;

    @FXML
    private TextArea TextFeild_HashText;

    @FXML
    private TextArea TextFeild_InputText;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClassic;

    @FXML
    private MFXButton btn_generateHash;


    @FXML
    private Button btn_info;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ComboBox_keySize.setItems(FXCollections.observableArrayList("MD5" ,"SHA-1","SHA-256","SHA-384" ,"SHA-512"));
        ComboBox_keySize.setValue("SHA-256");


        //Hash
        btn_generateHash.setOnAction(  event -> {
            String InputText = TextFeild_InputText.getText();
            String algorithmInstance = ComboBox_keySize.getValue();
            if(validate(InputText)){
                try {
                    String hash = Hash.Hash(InputText,algorithmInstance);
                    TextFeild_HashText.setText(hash);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }

            }else{
                //lbl error
            }
        });

        //Calssic secen
        btnClassic.setOnAction(event -> {
            System.out.println("clicked");
            try {
                ClassicScene();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Cesare " +e);
            }
        });
        //back secen
        btnBack.setOnAction( event -> {
            System.out.println("clicked");
            try{
                BackScene();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Back " +e);
            }
        });

        //btn info
        btn_info.setOnAction( e -> showAlert() );
    }

    private void ClassicScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Classic.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }
    //Back secen
    private void BackScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Asymmetric.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }

    private boolean validate(String Text){
        if(Text == null || Text.trim().isEmpty()){
            return false;
        }
        return true;
    }


    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information about cipher");
        alert.setHeaderText("Information");
        alert.setContentText("A hash function is a mathematical algorithm that takes input data (or a \"message\") and produces a fixed-size string of characters, which is typically a hash value or hash code. The key properties of a hash function include generating the same hash for the same input and making it computationally infeasible to derive the original input from its hash.");

        alert.showAndWait();
    }
}
