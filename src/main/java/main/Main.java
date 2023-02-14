package main;

import graphic_control.CalculatorGraphicController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage primaryStage;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        setPrimaryStage(stage);
        primaryStage.setResizable(false);
        CalculatorGraphicController controller = new CalculatorGraphicController();
        controller.displayGUI();
    }

    private static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

}
