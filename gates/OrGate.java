package gates;

import model.Gate;
import controllers.gate.GateControllerOr;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class OrGate extends Gate {

    private GateControllerOr orGateController;

    public OrGate() {
        super(false,false,false, OR_GATE);

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(this.getClass().getResource("OrGate.fxml"));

            Parent root = fxmlLoader.load();

            this.orGateController = fxmlLoader.<GateControllerOr>getController();

            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public ImageView getOrGate() {
        return this.orGateController.getOrGate();
    }
    public ImageView getInputAImage() {
        return this.orGateController.getInputA();
    }
    public ImageView getInputBImage() {
        return this.orGateController.getInputB();
    }
    public ImageView getOutputConnector() {
        return this.orGateController.getOutputConnector();
    }
}
