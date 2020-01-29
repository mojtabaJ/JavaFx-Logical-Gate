package controllers.gate;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;


/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class GateControllerOr {
    @FXML
    private ImageView inputA;
    @FXML
    private ImageView inputB;
    @FXML
    private ImageView outputConnector;
    @FXML
    private ImageView orGate;


    public ImageView getOrGate() {
        return orGate;
    }

    public ImageView getInputB() {
        return inputB;
    }

    public ImageView getInputA() {
        return inputA;
    }

    public ImageView getOutputConnector() {
        return outputConnector;
    }
}
