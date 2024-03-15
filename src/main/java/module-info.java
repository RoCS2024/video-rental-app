module com.video.rental.app {

    requires javafx.controls;
    requires javafx.fxml;
    requires video.rental.application;
    requires java.sql;
    requires org.apache.logging.log4j.slf4j2.impl;

    opens com.video.rental.app to javafx.fxml;
    opens com.video.rental.app.controllers.main to javafx.fxml;
    opens com.video.rental.app.controllers.dashboard to javafx.fxml;
    opens com.video.rental.app.controllers.item to javafx.fxml;

    exports com.video.rental.app;
    exports com.video.rental.app.controllers.main;
    exports com.video.rental.app.controllers.dashboard;
    exports com.video.rental.app.controllers.item;
}