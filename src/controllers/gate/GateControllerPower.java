package controllers.gate;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;


/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class GateControllerPower {

    @FXML
    private ImageView powerSource;

    @FXML
    private ImageView outputConnector;

    @FXML
    public void initialize() {
    }

    public ImageView getPowerSourceImage() {
        return this.powerSource;
    }

    public ImageView getOutputConnector() {
        return this.outputConnector;
    }
}
