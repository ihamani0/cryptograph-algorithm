package com.example.finaleproject;


import Algorithem.RSA;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.ResourceBundle;

public class RSAController implements Initializable {
    @FXML
    private ComboBox<Integer> ComboBox_keySize;
    @FXML
    private ToggleGroup Key;

    @FXML
    private TextArea TextFeild_PrivateKey;

    @FXML
    private TextArea TextFeild_PublicKey;

    @FXML
    private TextArea TextField_Input;

    @FXML
    private TextArea TextField_Output;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClassic;

    @FXML
    private Button btn_info;

    @FXML
    private MFXButton btn_generateKey;

    @FXML
    private MFXButton btn_submit;

    @FXML
    private MFXRadioButton rb_PrivateKey;

    @FXML
    private MFXRadioButton rb_publicKey;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComboBox_keySize.setItems(FXCollections.observableArrayList(512 , 1024 , 2048));
        ComboBox_keySize.setValue(512);

        //event to generate key pair
        btn_generateKey.setOnAction(event -> {
            int Size =  ComboBox_keySize.getValue();

            RSA.KeySize=Size;
            KeyPair keyPair =  RSA.generateRSAKeyPair();

            // Display public key in the public key text area
            PublicKey publicKey = keyPair.getPublic();
            String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            TextFeild_PublicKey.setText(publicKeyString);

            // Display private key in the private key text area
            PrivateKey privateKey = keyPair.getPrivate();
            String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            TextFeild_PrivateKey.setText(privateKeyString);

        });

        //event to encrypte / decrypte
        btn_submit.setOnAction(event -> {

            if(rb_publicKey.isSelected()){
                            String plainText = TextField_Input.getText() ;
                            String PublicKey = TextFeild_PublicKey.getText();
                        if(validate(plainText) && validate(PublicKey)){
                                try {
                                    String encryptData = RSA.encrypt(plainText,PublicKey);
                                    TextField_Output.setText(encryptData);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                        }
            }else if (rb_PrivateKey.isSelected()){
                        String CipherText = TextField_Input.getText() ;
                        String PrivateKey = TextFeild_PrivateKey.getText();
                        if(validate(CipherText) && validate(PrivateKey)){
                            try {
                                String decryptData = RSA.decrypte(CipherText,PrivateKey);
                                TextField_Output.setText(decryptData);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
            }else {

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


    //Validation input
    //Validate function
    private boolean validate(String Text){
        if(Text == null || Text.trim().isEmpty()){
            return false;
        }
        return true;
    }

    //btn info
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information about cipher");
        alert.setHeaderText("Information");
        alert.setContentText("RSA (Rivest–Shamir–Adleman) is a widely used asymmetric cryptographic algorithm that enables secure communication and digital signatures It involves two keys: a public key used for encryption  and a private key used for decryption The security of RSA is  based on the difficulty of factoring the product of two large prime numbers.");

        alert.showAndWait();
    }

}
