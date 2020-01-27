package gates;

import model.Circuit;
import controllers.gate.GateControllerLED;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

import java.io.IOException;



/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class LEDGate extends Circuit {


    private Line inputLine;

    private GateControllerLED controller;

    public LEDGate() {
        super(false);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("LEDGate.fxml"));
            Parent root = fxmlLoader.load();
            this.controller = fxmlLoader.<GateControllerLED>getController();
            this.getChildren().add(root);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public void toggleInput() {
        setInputA(!getInputAValue());
    }


    public void setInputLine(Line inputLine) {
        this.inputLine = inputLine;
    }

    public Line getInputLine() {
        return inputLine;
    }


    public void setLed(final boolean input) {
        super.setInputA(input);
        if(input) {
            this.controller.getLedImage().setImage(new Image("/icons/lamp_on.png"));
        } else {
            this.controller.getLedImage().setImage(new Image("/icons/lamp_off.png"));
        }
    }

    public ImageView getLEDImage() {
        return this.controller.getLedImage();
    }
    public ImageView getInputImage() {
        return this.controller.getInputConnector();
    }

}
