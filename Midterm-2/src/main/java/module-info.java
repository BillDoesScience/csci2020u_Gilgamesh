module com.example.midterm2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.xml;

    opens com.example.midterm2 to javafx.fxml;
    exports com.example.midterm2;
}