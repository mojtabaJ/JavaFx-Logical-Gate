package gates;

import model.Gate;
import controllers.gate.GateControllerXor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class XorGate extends Gate {

    private GateControllerXor gateControllerXor;

    public XorGate() {
        super(false,false,false, XOR_GATE);

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(this.getClass().getResource("XorGate.fxml"));

            Parent root = fxmlLoader.load();

            this.gateControllerXor = fxmlLoader.<GateControllerXor>getController();

            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public ImageView getXorGate() {
        return this.gateControllerXor.getXorGate();
    }
    public ImageView getInputAImage() {
        return this.gateControllerXor.getInputA();
    }
    public ImageView getInputBImage() {
        return this.gateControllerXor.getInputB();
    }
    public ImageView getOutput() {
        return this.gateControllerXor.getOutputConnector();
    }
}
