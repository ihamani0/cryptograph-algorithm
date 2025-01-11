package com.example.finaleproject;

import Algorithem.AffineCipher;
import Algorithem.CesareCipher;
import Algorithem.MyExcption;
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

public class AffineController implements Initializable {

    @FXML
    private TextArea TextField_CipherInput;

    @FXML
    private TextArea TextField_PlainOutput;

    @FXML
    private TextArea TextField_cipher;

    @FXML
    private MFXTextField TextField_key1;

    @FXML
    private MFXTextField TextField_key2;

    @FXML
    private TextArea TextField_plain;

    @FXML
    private Button btnBack;

    @FXML
    private Button btn_CesareSecen;

    @FXML
    private Button btn_HillSecen;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

        //Encrypte button
        btn_cryptr.setOnAction( event -> {

            lbl_error.setText("");
            TextField_plain.setStyle("-fx-border-color:  #6696E8");
            TextField_key1.setStyle("-fx-border-color:  #6696E8");


            //
            try{
                if(validate(TextField_plain.getText() , TextField_key1.getText() , TextField_key2.getText()) && validateKeyLen( TextField_key1.getText() , TextField_key2.getText()) ){

                    TextField_cipher.setText(AffineCipher.Encrypte(
                                    TextField_plain.getText().toUpperCase() ,
                                    TextField_key1.getText().toUpperCase(),
                                    TextField_key2.getText().toUpperCase()
                            )
                    );

                }else{
                    lbl_error.setText("Please Verify the Field ");
                    lbl_error.setStyle("-fx-text-fill: red");
                    TextField_plain.setStyle("-fx-border-color: red");
                }
            }catch (Exception e){
                TextField_key1.setStyle("-fx-border-color:  red");
                lbl_error.setText(e.getMessage());
                lbl_error.setStyle("-fx-text-fill: red");
            }

        });
        //Decrypte button

        btn_decrypt.setOnAction( event -> {
            lbl_error.setText("");
            TextField_cipher.setStyle("-fx-border-color:  #6696E8");

            if(validate(TextField_CipherInput.getText() , TextField_key1.getText() , TextField_key2.getText()) && validateKeyLen( TextField_key1.getText() , TextField_key2.getText())){

                TextField_PlainOutput.setText(AffineCipher.Dencrypte(
                        TextField_CipherInput.getText().toUpperCase() ,
                        TextField_key1.getText().toUpperCase(),
                        TextField_key2.getText().toUpperCase()
                ));

            }else{
                lbl_error.setText("Please Verify the Field ");
                lbl_error.setStyle("-fx-text-fill: red");
                TextField_CipherInput.setStyle("-fx-border-color: red");
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

    //validation Input
    private boolean validate(String Text , String k1 , String k2){
        if(Text == null || Text.trim().isEmpty()){
            return false;
        }
        if(k1 == null || k2 == null || k1.trim().isEmpty() || k2.trim().isEmpty()){
            return false;
        }

        if(!Text.matches("[a-zA-Z]+") ){
            return false;
        }
        return true;
    }

    private boolean validateKeyLen(String k1 , String k2){
        if(k1.length() != 1 ){
            return false;
        }
        if(k2.length() != 1 ){
            return false;
        }
        return true;
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


    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information about cipher");
        alert.setHeaderText("Information");
        alert.setContentText("The Affine cipher is a type of monoalphabetic substitution cipher where each letter in an alphabet is mapped to its numeric equivalent, encrypted using a simple mathematical function, and converted back to a letter, The encryption function is of the form   \"E(x)=(ax+b) mod M \" where a and b are the key components, and M is the size of alphabit");


        alert.showAndWait();
    }
}
