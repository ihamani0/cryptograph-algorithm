package com.example.finaleproject;

import Algorithem.ViginerCipher;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViginerController implements Initializable {


    @FXML
    private TextArea TextField_CipherInput;

    @FXML
    private TextArea TextField_PlainOutput;

    @FXML
    private TextArea TextField_cipher;

    @FXML
    private MFXTextField TextField_key;

    @FXML
    private MFXTextField TextField_keyDec;

    @FXML
    private TextArea TextField_plain;

    @FXML
    private Button btnBack;

    @FXML
    private Button btn_AffineSecen;

    @FXML
    private Button btn_CesareSecen;

    @FXML
    private Button btn_HillSecen;

    @FXML
    private Button btn_cryptr;

    @FXML
    private Button btn_decrypt;

    @FXML
    private Button btn_info;

    @FXML
    private Label lbl_error;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Encrypte
        btn_cryptr.setOnAction(event -> {
            lbl_error.setText(" ");
            TextField_cipher.setStyle("-fx-border-color:#FFF");

            String plainText = TextField_plain.getText().toUpperCase();
            String key = TextField_key.getText().toUpperCase();

            if(validate(plainText) && validate(key)){
                String encrypteText = ViginerCipher.Encrypte(plainText,key);
                TextField_cipher.setText(encrypteText);
                TextField_cipher.setStyle("-fx-border-color:green");
            }else{
                lbl_error.setText("Field is empty");
                lbl_error.setStyle("-fx-text-fill: red");
            }

        });

        //decrypte
        btn_decrypt.setOnAction(event -> {
            lbl_error.setText(" ");
            TextField_plain.setStyle("-fx-border-color:#FFF");

            String cipherText = TextField_CipherInput.getText().toUpperCase();
            String key = TextField_keyDec.getText().toUpperCase();

            if(validate(cipherText)&& validate(key)){
                String decrypteText = ViginerCipher.Dencrypte(cipherText,key);
                TextField_PlainOutput.setText(decrypteText);
                TextField_PlainOutput.setStyle("-fx-border-color:green");

            }else{
                lbl_error.setText("Field is empty");
                lbl_error.setStyle("-fx-text-fill: red");
            }
        });

        btnBack.setOnAction( event -> {
            try{
                BackScene();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Back " +e);
            }

        });

        //btnHill
        btn_HillSecen.setOnAction( event -> {
            try{
                HillSecen();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Back " +e);
            }

        });
        //btnCesare
        btn_AffineSecen.setOnAction( event -> {
            try{
                AffinSecen();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Back " +e);
            }

        });
        //btnViginer
        btn_CesareSecen.setOnAction( event -> {
            try{
                CesareSecen();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Back " +e);
            }

        });

        //btn info
        btn_info.setOnAction(e -> showAlert());
    }

    private void BackScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Classic.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }

    //Hill Secen
    private void HillSecen() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Hill.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }
    //Viginer Secen
    private void CesareSecen() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Cesare.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }
    //Secen Cesare
    private void AffinSecen() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Affine.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }


    private boolean validate(String text){

        if(text == null || text.trim().isEmpty())
        {
            return false;
        }

        return true;
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information about cipher");
        alert.setHeaderText("Information");

        alert.setContentText(" Vigenere cipher is a method of encrypting alphabetic text by using a simple form of polyalphabetic substitution Instead of using a single shift for the entire message the Vigenere cipher uses a keyword to determine different shift values for different parts of the message The encryption process involves repeating the keyword to match  the length of the plaintext and then shifting each letter in the message according to the corresponding letter in the keyword.");


        alert.showAndWait();
    }

}
