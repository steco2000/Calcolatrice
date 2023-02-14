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
        primaryStage = stage;
        primaryStage.setResizable(false);
        CalculatorGraphicController controller = new CalculatorGraphicController();
        controller.displayGUI();
    }

}
