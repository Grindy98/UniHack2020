module UniHack2020 {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;

    opens gui;
    opens gui.login;
    opens gui.user;
}