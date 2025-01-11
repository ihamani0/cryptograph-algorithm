package com.example.finaleproject;

import Algorithem.DSA;
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
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.ResourceBundle;

public class DSAController implements Initializable {


    @FXML
    private ComboBox<String> ComboBox_keySize;

    @FXML
    private TextArea TextFeild_InputText;

    @FXML
    private TextArea TextFeild_PrivateKey;

    @FXML
    private TextArea TextFeild_SignatureText;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClassic;

    @FXML
    private MFXButton btn_generateSignature;


    @FXML
    private Button btn_info;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComboBox_keySize.setItems(FXCollections.observableArrayList("MD5" ,"SHA-1","SHA-256","SHA-384" ,"SHA-512"));
        ComboBox_keySize.setValue("SHA-256");

        btn_generateSignature.setOnAction(event -> {
            String inputText = TextFeild_InputText.getText();
            String algorithmeInstance= ComboBox_keySize.getValue();
            String privateKeyString = TextFeild_PrivateKey.getText();
            if(validate(inputText)){
                try {

                    String Singnature = DSA.SingeMessageCipher(inputText,algorithmeInstance);
                    TextFeild_SignatureText.setText(Singnature);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
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
        alert.setContentText("The Digital Signature Algorithm (DSA) is a widely used public-key cryptography algorithm that provides a method for digital signing and verification  It is commonly employed for ensuring the authenticity and integrity of digital messages or documents DSA uses a pair of asymmetric keys: a private key for signing and a corresponding public key for verification.");


        alert.showAndWait();
    }
}
