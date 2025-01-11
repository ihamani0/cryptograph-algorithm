package com.example.finaleproject;

import Algorithem.HillCipher;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HillController implements Initializable {
    @FXML
    private TextArea TextField_CipherInput;

    @FXML
    private TextArea TextField_PlainOutput;

    @FXML
    private TextArea TextField_cipher;

    @FXML
    private MFXTextField TextField_key;

    @FXML
    private TextArea TextField_plain;

    @FXML
    private Button btnBack;

    @FXML
    private Button btn_AffineSecen;

    @FXML
    private Button btn_CesareSecen;

    @FXML
    private Button btn_ViginerSecen;

    @FXML
    private Button btn_cryptr;

    @FXML
    private Button btn_decrypt;

    @FXML
    private Button btn_info;

    @FXML
    private Label lbl_error;

    @FXML
    private MFXRadioButton rb_2x2;

    @FXML
    private MFXRadioButton rb_3x3;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        //Encrypte
        btn_cryptr.setOnAction(event -> {
            lbl_error.setText("");
            TextField_plain.setStyle("-fx-border-color:  #6696E8");
            TextField_key.setStyle("-fx-border-color:  #6696E8");
            lbl_error.setText("");

            String PlainText = TextField_plain.getText().toUpperCase();
            String key = TextField_key.getText().toUpperCase();

            if( validate(PlainText , key) ){
                    try{
                         if(rb_2x2.isSelected()){
                                String cipherText = HillCipher.Encrypt2X2(PlainText, key);
                                TextField_cipher.setText(cipherText);
                        }else {
                                String cipherText = HillCipher.Encrypt3x3(PlainText, key);
                                TextField_cipher.setText(cipherText);

                        }
                    }catch (Exception e){
                        TextField_key.setStyle("-fx-border-color:  red");
                        lbl_error.setText(e.getMessage());
                        lbl_error.setStyle("-fx-text-fill: red");
                    }
            }else{
                lbl_error.setText("Please Verify the Field ");
                lbl_error.setStyle("-fx-text-fill: red");
                TextField_plain.setStyle("-fx-border-color: red");
            }
        });

        //Decrypte
        btn_decrypt.setOnAction(event -> {
            String cipherText = TextField_CipherInput.getText().toUpperCase();
            String key =TextField_key.getText().toUpperCase();
            if(validate( cipherText , key)){
                try{
                    if(rb_2x2.isSelected()){
                        String Plain = HillCipher.Decrypte2x2(cipherText,key);

                        TextField_PlainOutput.setText(Plain);
                    }else {
                        String Plain = HillCipher.Dencrypt3x3(cipherText,key);
                        TextField_PlainOutput.setText(Plain);

                    }
                }catch (Exception e){
                    TextField_key.setStyle("-fx-border-color:  red");
                    lbl_error.setText(e.getMessage());
                    lbl_error.setStyle("-fx-text-fill: red");
                }
            }else{
                lbl_error.setText("Please Verify the Field ");
                lbl_error.setStyle("-fx-text-fill: red");
                TextField_plain.setStyle("-fx-border-color: red");
            }
        });

        //btn Affine
        btn_AffineSecen.setOnAction( event -> {
            try{
                AffineSecen();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Back " +e);
            }

        });
        //btnCesare
        btn_CesareSecen.setOnAction( event -> {
            try{
                CesareSecen();
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
        //btnBack
        btnBack.setOnAction( event -> {
            try{
                BackScene();
            } catch (IOException e) {
                throw new RuntimeException("Exception From change scene Btn Back " +e);
            }

        });

        //btn info
        btn_info.setOnAction( e -> showAlert() );
    }


    //Affine Secen
    private void AffineSecen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Affine.fxml"));
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
    private void CesareSecen() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Cesare.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }
    //backSecene
    private void BackScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Classic.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }

    private boolean validate(String Text , String k){
        if(Text == null || Text.trim().isEmpty()){
            return false;
        }
        if(k == null  || k.trim().isEmpty() ){
            return false;
        }

        if(!Text.matches("[a-zA-Z]+") ){
            return false;
        }
        return true;
    }

    //btn info
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information about cipher");
        alert.setHeaderText("Information");
        alert.setContentText("The Hill cipher is a polygraphic substitution cipher that operates on blocks of letters (instead of individual letters) and uses linear algebra for encryption and decryption It involves representing each letter as a number and performing matrix multiplication on blocks of these numbers  The key matrix determines how the letters are transformed.");

        alert.showAndWait();
    }
}
