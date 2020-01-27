package controllers;

import javafx.scene.paint.Color;


/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public interface Config {

    String TYPE_AND_GATE = "GATE_AND";
    String TYPE_NAND_GATE = "GATE_N_AND";

    String TYPE_OR_GATE = "GATE_OR";
    String TYPE_NOR_GATE = "GATE_N_OR";

    String TYPE_NOT_GATE = "GATE_NOT";

    String TYPE_XOR_GATE = "GATE_X_OR";
    String TYPE_XNOR_GATE = "GATE_X_NOR";

    String TYPE_LED = "LED";
    String TYPE_VCC = "VCC";
    String TYPE_GROUND = "GROUND";


    Color LINE_COLOR = Color.web("#515151");

}
