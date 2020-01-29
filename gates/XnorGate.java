package gates;

import model.Gate;
import controllers.gate.GateControllerXnor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class XnorGate extends Gate {

    private GateControllerXnor gateControllerXnor;

    public XnorGate() {
        super(false,false,false, XNOR_GATE);

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(this.getClass().getResource("XnorGate.fxml"));

            Parent root = fxmlLoader.load();

            this.gateControllerXnor = fxmlLoader.<GateControllerXnor>getController();

            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public ImageView getXnorGate() {
        return this.gateControllerXnor.getXnorGate();
    }
    public ImageView getInputAImage() {
        return this.gateControllerXnor.getInputA();
    }
    public ImageView getInputBImage() {
        return this.gateControllerXnor.getInputB();
    }
    public ImageView getOutput() {
        return this.gateControllerXnor.getOutput();
    }
}
