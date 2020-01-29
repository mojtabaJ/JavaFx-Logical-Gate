package gates;

import model.Gate;
import controllers.gate.GateControllerNAnd;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class NAndGate extends Gate {
    private GateControllerNAnd nandGateController;

    public NAndGate() {
        super(false,false,false, NAND_GATE);
        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(this.getClass().getResource("NandGate.fxml"));
            Parent root = loader.load();

            this.nandGateController = loader.<GateControllerNAnd>getController();
            AnchorPane.setTopAnchor(root,0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        } catch (IOException ioe) {
        }
    }

    public ImageView getNandGate() {
        return nandGateController.getNandGate();
    }

    public ImageView getOutputImage() {
        return nandGateController.getOutputConnector();
    }

    public ImageView getInputAImage() {
        return nandGateController.getInputA();
    }
    public ImageView getInputBImage() {
        return nandGateController.getInputB();
    }
}
