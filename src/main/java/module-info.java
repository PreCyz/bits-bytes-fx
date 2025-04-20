module pawg.it.bitsbytesfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires atlantafx.base;

    opens pawg.it.bitsbytesfx to javafx.fxml;
    opens pawg.it.bitsbytesfx.animations to javafx.fxml;
    opens pawg.it.bitsbytesfx.transformation to javafx.fxml;
    opens pawg.it.bitsbytesfx.other to javafx.fxml;

    exports pawg.it.bitsbytesfx;
    exports pawg.it.bitsbytesfx.animations;
    exports pawg.it.bitsbytesfx.transformation;
    exports pawg.it.bitsbytesfx.other;
}