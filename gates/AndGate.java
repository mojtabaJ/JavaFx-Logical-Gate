package gates;

import model.Gate;
import controllers.gate.GateControllerAnd;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class AndGate extends Gate {


    private GateControllerAnd gateControllerAnd;


    public AndGate() {
        super(false,false,false, AND_GATE);

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("AndGate.fxml"));
            Parent root = loader.load();

            this.gateControllerAnd = loader.<GateControllerAnd>getController();
            AnchorPane.setTopAnchor(root,0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            this.getChildren().add(root);

        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    public ImageView getAndGate() {
        return gateControllerAnd.getAndGate();
    }

    public ImageView getOutputImage() {
        return gateControllerAnd.getOutputConnector();
    }

    public ImageView getInputAImage() {
        return gateControllerAnd.getInputA();
    }
    public ImageView getInputBImage() {
        return gateControllerAnd.getInputB();
    }

    @Override
    public String toString() {
        return gateControllerAnd.toString();
    }
}
