module com.example.finaleproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires MaterialFX;

    opens com.example.finaleproject to javafx.fxml;
    exports com.example.finaleproject;
}