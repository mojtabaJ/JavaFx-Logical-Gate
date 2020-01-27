package controllers.gate;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;


/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class GateControllerNot {

    @FXML
    private ImageView inputA;

    @FXML
    private ImageView outputConnector;

    @FXML
    private ImageView notGate;


    public ImageView getNotGate() {
        return notGate;
    }

    public ImageView getInputA() {
        return inputA;
    }

    public ImageView getOutputConnector() {
        return outputConnector;
    }
}
