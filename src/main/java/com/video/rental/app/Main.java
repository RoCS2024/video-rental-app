package com.video.rental.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //use FXMLLoaded to load the fxml file in the resources/views
        FXMLLoader loader = new FXMLLoader();

        //set the location of the file
        loader.setLocation(getClass().getResource("/views/MainView.fxml"));

        //create a layout and load the loader in the layout
        HBox mainLayout = new HBox();
        mainLayout = loader.load();

        //Create a scene using the layout
        Scene scene = new Scene(mainLayout);

        //set the scene and show the stage
        stage.setTitle("Video Rental Application");
        stage.setScene(scene);
        stage.show();
    }

}
