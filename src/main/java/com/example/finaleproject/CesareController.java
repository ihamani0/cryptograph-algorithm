package com.example.finaleproject;

import Algorithem.CesareCipher;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CesareController implements Initializable {

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
    private Button btn_AffinSecen;

    @FXML
    private Button btn_HillSecen;

    @FXML
    private Button btn_ViginerSecen;

    @FXML
    private Button btn_cryptr;

    @FXML
    private Button btn_decrypt;

    @FXML
    private Label lbl_error;

    @FXML
    private Button btn_info;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_cryptr.setOnAction( event -> {

                    lbl_error.setText("");
                    TextField_plain.setStyle("-fx-border-color:  #6696E8");

                    if(validate(TextField_plain.getText()) && validate(TextField_key.getText()) && TextField_key.getText().length() == 1 ){

                        String PlainText = TextField_plain.getText().toUpperCase();
                        String Key = TextField_key.getText().toUpperCase();

                        String cipherText = CesareCipher.encrypte( PlainText ,  Key );
                        TextField_cipher.setText(cipherText);

                    }else{
                        lbl_error.setText("Please Verify the Field ");
                        lbl_error.setStyle("-fx-text-fill: red");
                        TextField_plain.setStyle("-fx-border-color: red");
                    }
        });

        btn_decrypt.setOnAction( event -> {
            lbl_error.setText("");
            TextField_cipher.setStyle("-fx-border-color:  #6696E8");

            if(validate(TextField_CipherInput.getText()) && validate(TextField_keyDec.getText()) && TextField_keyDec.getText().length() == 1 ){

                String ciphreText = TextField_CipherInput.getText().toUpperCase();
                String Key = TextField_keyDec.getText().toUpperCase();
                String PlainText = CesareCipher.Decrypte(ciphreText,Key);
                TextField_PlainOutput.setText(PlainText);

            }else{
                lbl_error.setText("Please Verify the Field ");
                lbl_error.setStyle("-fx-text-fill: red");
                TextField_cipher.setStyle("-fx-border-color: red");
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
        btn_AffinSecen.setOnAction( event -> {
            try{
                AffinSecen();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Back " +e);
            }

        });
        //btnViginer
        btn_ViginerSecen.setOnAction( event -> {
            try{
                ViginerSecen();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Back " +e);
            }

        });

        //btn info
        btn_info.setOnAction(event -> {
            showAlert();
        });
    }

    private boolean validate(String Text){
        if(Text == null || Text.trim().isEmpty()){
            return false;
        }
        if(!Text.matches("[a-zA-Z]+")){
            return false;
        }
        return true;
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
    private void ViginerSecen() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Viginer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }
    //Secen Cesare
    private void AffinSecen() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Affine.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information about cipher");
        alert.setHeaderText("Information");
        alert.setContentText("The Caesar cipher is a basic substitution cipher where each letter in the plaintext is shifted a certain number of places down or up the alphabet. It's named after Julius Caesar, who is historically known to have used this encryption method.");


        alert.showAndWait();
    }
}
