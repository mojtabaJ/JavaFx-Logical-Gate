package controllers.gate;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;


/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class GateControllerXnor {
    @FXML
    private ImageView inputA;
    @FXML
    private ImageView inputB;
    @FXML
    private ImageView outputConnector;
    @FXML
    private ImageView xNorGate;


    public ImageView getXnorGate() {
        return xNorGate;
    }

    public ImageView getInputB() {
        return inputB;
    }

    public ImageView getInputA() {
        return inputA;
    }

    public ImageView getOutput() {
        return outputConnector;
    }
}
