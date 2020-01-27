package gates;

import model.Gate;
import controllers.gate.GateControllerNot;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class NotGate extends Gate {


    private GateControllerNot gateControllerNot ;


    public NotGate() {
        super(false,false,false, NOT_GATE);

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("NotGate.fxml"));
            Parent root = loader.load();

            this.gateControllerNot = loader.<GateControllerNot>getController();
            AnchorPane.setTopAnchor(root,0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);

        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    public ImageView getNotGate() {
        return gateControllerNot.getNotGate();
    }

    public ImageView getOutputImage() {
        return gateControllerNot.getOutputConnector();
    }

    public ImageView getInputA() {
        return gateControllerNot.getInputA();
    }


}
