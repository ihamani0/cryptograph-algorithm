package com.example.finaleproject;

import Algorithem.AES;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URL;
import java.security.Key;
import java.util.Base64;
import java.util.ResourceBundle;

public class AesController implements Initializable {
    @FXML
    private ToggleGroup ModDec;

    @FXML
    private ToggleGroup ModEnc;

    @FXML
    private TextArea TextFeild_ChipherTextInput;

    @FXML
    private TextArea TextFeild_plainText;

    @FXML
    private TextArea TextFeild_plainText1Output;

    @FXML
    private TextArea TextField_Cipher;

    @FXML
    private MFXButton bt_ChoosFile;

    @FXML
    private MFXButton bt_ChoosIV;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClassic;

    @FXML
    private Button btn_Dec;

    @FXML
    private Button btn_Enc;

    @FXML
    private MFXCheckbox cb_saveIV;

    @FXML
    private MFXCheckbox cb_saveKey;

    @FXML
    private Label lbl_error;

    @FXML
    private Label lbl_error_dec;

    @FXML
    private MFXRadioButton rb_Ecb;

    @FXML
    private MFXRadioButton rb_cbc;

    @FXML
    private MFXRadioButton rb_cbc_dec;

    @FXML
    private MFXRadioButton rb_ecb_dec;


    @FXML
    private Button btn_info;

    private static SecretKey keyFromFile;

    private static byte[] IVvec;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //choose File
        bt_ChoosFile.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");


            // Show open file dialog
            File selectedFile = fileChooser.showOpenDialog(HelloApplication.stagePrime);

            if (selectedFile != null) {
                // Now you can read bytes from the selected file
                try {

                        AesController.keyFromFile=readKeyFromFile(selectedFile);

                        if(keyFromFile != null){
                            bt_ChoosFile.setStyle("-fx-background-color:#FFAC00");
                        }else{
                            bt_ChoosFile.setStyle("-fx-background-color:#f18383");
                        }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //choose File Iv
        bt_ChoosIV.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");

            // Show open file dialog
            File selectedFile = fileChooser.showOpenDialog(HelloApplication.stagePrime);

            if (selectedFile != null) {
                // Now you can read bytes from the selected file
                try {

                    AesController.IVvec=readIVFromFile(selectedFile);

                    if(keyFromFile != null){
                        bt_ChoosIV.setStyle("-fx-background-color:#FFAC00");
                    }else{
                        bt_ChoosIV.setStyle("-fx-background-color:#f18383");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //encrypte
            btn_Enc.setOnAction(event -> {
                    lbl_error.setText("");
                    String PlainText=TextFeild_plainText.getText();

                try{
                    if(validate(PlainText)){
                        if(rb_Ecb.isSelected()){
                            String EncrypteData = AES.encrypt(PlainText) ;
                            //String EncDataString = Base64.getEncoder().encodeToString(EncrypteData);

                            //save Key in File (option)
                            if(cb_saveKey.isSelected()){
                                SecretKey KeyFromClass=AES.key;

                                byte[] KeyToFile = KeyFromClass.getEncoded();


                                FileChooser fileChooser = new FileChooser();
                                fileChooser.setTitle("Save File");

                                // Show save file dialog
                                fileChooser.setInitialFileName("Key.txt"); // Set default file name

                                // This will open the save file dialog
                                File selectedFile = fileChooser.showSaveDialog(HelloApplication.stagePrime);

                                if (selectedFile != null) {
                                    // Now you can write content to the selected file
                                    writeContentToFile(selectedFile,KeyToFile);
                                }
                            }
                            //end save in File
                            //set Text in Text field
                            TextField_Cipher.setText(EncrypteData);


                        }

                        else if(rb_cbc.isSelected()){

                            String EncrypteData = AES.encryptCbc(PlainText) ;
                            if(cb_saveKey.isSelected()){
                                SecretKey KeyFromClass=AES.key;

                                byte[] KeyToFile = KeyFromClass.getEncoded();

                                FileChooser fileChooser = new FileChooser();
                                fileChooser.setTitle("Save File");

                                // Show save file dialog
                                fileChooser.setInitialFileName("Key.txt"); // Set default file name

                                // This will open the save file dialog
                                File selectedFile = fileChooser.showSaveDialog(HelloApplication.stagePrime);

                                if (selectedFile != null) {
                                    // Now you can write content to the selected file
                                    writeContentToFile(selectedFile,KeyToFile);
                                }
                            }
                            if(cb_saveIV.isSelected()){
                                byte[] ivFromClass = AES.IV;
                                FileChooser fileChooser = new FileChooser();
                                fileChooser.setTitle("Save File");

                                // Show save file dialog
                                fileChooser.setInitialFileName("IV_vector.txt"); // Set default file name

                                // This will open the save file dialog
                                File selectedFile = fileChooser.showSaveDialog(HelloApplication.stagePrime);

                                if (selectedFile != null) {
                                    // Now you can write content to the selected file
                                    writeContentToFile(selectedFile,ivFromClass);
                                }
                            }
                            TextField_Cipher.setText(EncrypteData);


                        }else{
                            //error
                            lbl_error.setText("Plese Select Mode [cbc - ecb ]");
                        }
                    }else {
                        lbl_error.setText("Please Input the Fill THe Text field");
                    }

                }catch (Exception e){
                    System.out.println("Expction encryptr : " + e);
                }
            });

        //Decrypte
            btn_Dec.setOnAction(event -> {
                lbl_error_dec.setText("");
                String cipherText=TextFeild_ChipherTextInput.getText();

                try
                {
                    if(validate(cipherText)){
                        if(rb_Ecb.isSelected()){

                           String DecrypteData = AES.decrypte(cipherText,  AesController.keyFromFile);

                            TextFeild_plainText1Output.setText(DecrypteData);

                        }else if(rb_cbc.isSelected()){
                            String DecrypteData = AES.decrypteCbc(cipherText, AesController.keyFromFile,AesController.IVvec);

                            TextFeild_plainText1Output.setText(DecrypteData);
                        }else{
                            //error
                            lbl_error_dec.setText("Please Select Mode [cbc - ecb ]");
                        }
                    }else {
                        lbl_error_dec.setText("Please Input the Fill THe Text field");
                }

                }
                catch (Exception e){
                    System.out.println("Expction decryptr : " + e);
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


    //write in File
    private void writeContentToFile(File file , byte[] Key) {
        try (FileOutputStream writer = new FileOutputStream(file)) {
            // Replace this line with your actual content to be written to the file
            writer.write(Key);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }
    }

    //read from file

    private  SecretKey readKeyFromFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);

        byte[] keyBytes = new byte[(int) file.length()];

        if (inputStream.read(keyBytes) == -1) {
            throw new IOException("Failed to read content from file: " + file.getAbsolutePath());
        }
        return new SecretKeySpec(keyBytes, "AES");
    }

    //read Iv From FIle
    private static byte[] readIVFromFile(File file) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] iv = new byte[16]; // Adjust the size based on the IV size (e.g., 16 bytes for AES)
            int bytesRead = inputStream.read(iv);

            if (bytesRead != iv.length) {
                throw new IOException("Invalid IV size in the file.");
            }

            return iv;
        }
    }



    //classic secen

    //Validate function
    private boolean validate(String Text){
        if(Text == null || Text.trim().isEmpty()){
            return false;
        }
        return true;
    }

    private void ClassicScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Classic.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }
    //Back secen
    private void BackScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainModern.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        HelloApplication.stagePrime.setScene(scene);
    }


    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information about cipher");
        alert.setHeaderText("Information");
        alert.setContentText("AES (Advanced Encryption Standard) is a widely used symmetric encryption algorithm that is designed to provide a high level of security. It operates on blocks of data and supports key sizes of 128, 192, or 256 bits. AES has become the standard for encrypting sensitive data, such as in securing communication over the internet and protecting stored data.");

        alert.showAndWait();
    }

}
