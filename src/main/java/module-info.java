module MnemoApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires org.fxmisc.richtext;
    requires reactfx;
    requires com.fasterxml.jackson.databind;
    requires fontawesomefx.fontawesome;
    requires de.jensd.fx.fontawesomefx.commons;

    opens MnemoApp to java.base;
    exports MnemoApp;
}