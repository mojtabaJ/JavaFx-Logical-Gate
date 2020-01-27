package gates;

import model.Gate;
import controllers.gate.GateControllerNor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class NorGate extends Gate {
    private GateControllerNor norGateController;


    public NorGate() {
        super(false,false,false, NOR_GATE);

        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(this.getClass().getResource("NorGate.fxml"));
            Parent root = loader.load();

            this.norGateController = loader.<GateControllerNor>getController();
            AnchorPane.setTopAnchor(root,0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        } catch (IOException ioe) {
        }
    }

    public ImageView getNorGate() {
        return norGateController.getNorGate();
    }

    public ImageView getOutputImage() {
        return norGateController.getOutputConnector();
    }

    public ImageView getInputAImage() {
        return norGateController.getInputA();
    }
    public ImageView getInputBImage() {
        return norGateController.getInputB();
    }
}
