module com.example.assignment2_graphics {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.xml;

    opens com.example.assignment2_graphics to javafx.fxml;
    exports com.example.assignment2_graphics;
}