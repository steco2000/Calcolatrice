package graphic_control;

import control.CalculatorController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.Main;

import java.io.IOException;

import static java.lang.Float.parseFloat;

public class CalculatorGraphicController {

    @FXML
    private TextField display;

    private float operand1;
    private float operand2;
    private boolean equalPressed;
    private boolean opConcatenation;
    private int opCounter = 0;

    //1 per somma, 2 per sottrazione, 3 per moltiplicazione, 4 per divisione, 5 per calcolo numerico log. naturale
    private int operation;

    //metodo che carica e visualizza la schermata della calcolatrice
    public void displayGUI() throws IOException {
        FXMLLoader windowALoader = new FXMLLoader(Main.class.getResource("View.fxml"));
        Scene scene = new Scene(windowALoader.load(),338,552);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    //evento alla pressione del bottone del logaritmo, il conto viene subito eseguito e mostrato nel display
    public void logButtonEvent() {
        if(display.getText().isEmpty()) return;

        float value = parseFloat(display.getText());
        if(value == 0){
            display.setText("Infinity");
            return;
        }

        operand1 = parseFloat(display.getText());
        display.setText(String.valueOf(doOperation(5,true)));
    }

    /*
    alla pressione del tasto relativo alla divisione si controlla se stiamo concatenando operazioni o no. Primo caso si chiede di calcolare l'operazione gestendo
    questo caso (fromEqual = false), altrimenti si salva l'operando 1 e si attende per l'operando 2
     */
    public void divButtonEvent(ActionEvent actionEvent) {
        if(display.getText().isEmpty()) return;

        if(opCounter == 0){
            operand1 = parseFloat(display.getText());
            operation = 4;
            display.setText("0");
        }
        else{
            display.setText(String.valueOf(doOperation(operation,false)));
            operation = 4;
        }
        opCounter++;
    }

    //stessa cosa del tasto della divisione
    public void multButtonEvent(ActionEvent actionEvent) {
        if(display.getText().isEmpty()) return;

        if(opCounter == 0){
            operand1 = parseFloat(display.getText());
            operation = 3;
            display.setText("0");
        }else{
            display.setText(String.valueOf(doOperation(operation,false)));
            operation = 3;
        }
        opCounter++;
    }

    //stessa cosa del tasto della divisione
    public void subButtonEvent(ActionEvent actionEvent) {
        if(display.getText().isEmpty()) return;

        if(!display.getText().equals("0")) {
            if (opCounter == 0) {
                operand1 = parseFloat(display.getText());
                operation = 2;
                display.setText("0");
            } else {
                display.setText(String.valueOf(doOperation(operation, false)));
                operation = 2;
            }
            opCounter++;
        }else{
            digitAppend("-");
        }
    }

    //stessa cosa del tasto della divisione
    public void plusButtonEvent(ActionEvent actionEvent) {
        if(display.getText().isEmpty()) return;

        if(opCounter == 0){
            operand1 = parseFloat(display.getText());
            operation = 1;
            display.setText("0");
        }else{
            display.setText(String.valueOf(doOperation(operation,false)));
            operation = 1;
        }
        opCounter++;
    }

    /*
    Alla pressione del tasto relativo a una cifra questa viene aggiunta al valore sul display
     */

    public void zeroButtonEvent(ActionEvent actionEvent) {
        digitAppend("0");
    }

    public void oneButtonEvent(ActionEvent actionEvent) {
        digitAppend("1");
    }

    public void twoButtonEvent(ActionEvent actionEvent) {
        digitAppend("2");
    }

    public void threeButtonEvent(ActionEvent actionEvent) {
        digitAppend("3");
    }

    public void fourButtonEvent(ActionEvent actionEvent) {
        digitAppend("4");
    }

    public void fiveButtonEvent(ActionEvent actionEvent) {
        digitAppend("5");
    }

    public void sixButtonEvent(ActionEvent actionEvent) {
        digitAppend("6");
    }

    public void sevenButtonEvent(ActionEvent actionEvent) {
        digitAppend("7");
    }

    public void eightButtonEvent(ActionEvent actionEvent) {
        digitAppend("8");
    }

    public void nineButtonEvent(ActionEvent actionEvent) {
        digitAppend("9");
    }

    public void pointButtonEvent(ActionEvent actionEvent) {
        digitAppend(".");
    }

    //alla pressione dell'uguale eseguo l'operazione
    public void equalButtonEvent(ActionEvent actionEvent) {
        equalPressed = true;
        operand2 = Float.parseFloat(display.getText());
        display.setText(String.valueOf(doOperation(operation,true)));
    }

    /*
    L'aggiunta di una cifra dipende dalla situazione. Se sono in una condizione normale la aggiungo e basta. Se sto concatenando operazioni devo resettare
    il display. Se invece premo lo zero o il punto vuol dire che sto scrivendo un numero decimale <1, quindi appendo e basta
     */
    private void digitAppend(String digit){
        if ((display.getText().equals("0") && !digit.equals(".")) || equalPressed) {
            display.setText(digit);
            equalPressed = false;
        } else {
            if(!opConcatenation) {
                display.setText(display.getText() + digit);
            }else{
                display.setText(digit);
                opConcatenation = false;
            }
        }
    }

    //Il bottone cancel elimina tutto ciò che è salvato
    public void cancelButtonEvent() {
        display.setText("0");
        operand1 = 0;
        operand2 = 0;
        opCounter = 0;
        opConcatenation = false;
    }

    //in base all'id dell'operazione chiamo il giusto metodo nel controller
    private float doOperation(int operation, boolean fromEqual) {
        operand2 = Float.parseFloat(display.getText());
        CalculatorController controller = new CalculatorController();
       float result;
        switch (operation){
            case 1 -> {
                result = controller.sum(operand1,operand2);
            }
            case 2 -> {
                result = controller.sub(operand1,operand2);
            }
            case 3 -> {
                result = controller.multiply(operand1,operand2);
            }
            case 4 -> {
                result = controller.divide(operand1,operand2);
            }
            default -> {
                result = controller.naturalLog(operand1);
            }
        }

        if(!fromEqual) {
            operand1 = result;
            operand2 = 0;
            opConcatenation = true;
        }else{
            opCounter = 0;
            opConcatenation = false;
            equalPressed = true;
        }

        return result;

    }
}
