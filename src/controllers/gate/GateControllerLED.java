package controllers.gate;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class GateControllerLED {
    @FXML
    private ImageView ledImage,inputConnector;

    public ImageView getLedImage() {
        return ledImage;
    }

    public ImageView getInputConnector() {
        return inputConnector;
    }
}
