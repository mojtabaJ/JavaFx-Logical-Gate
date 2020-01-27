package gates;

import model.PowerSource;
import controllers.gate.GateControllerPower;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class PowerGate extends PowerSource {

    private GateControllerPower controller;

    public PowerGate(boolean isGround) {
        super(true);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("PowerGate.fxml"));
            Parent root = fxmlLoader.load();
            this.controller = fxmlLoader.<GateControllerPower>getController();
            this.getChildren().add(root);
            if(isGround){
                initGround();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void initGround() {
        this.setOutput(false);
        this.controller.getPowerSourceImage().setImage(new Image("icons/panel/grand.png"));

    }


    public ImageView getPowerSourceImage() {
        return this.controller.getPowerSourceImage();
    }

    public ImageView getOutputConnector() {
        return this.controller.getOutputConnector();
    }
}
