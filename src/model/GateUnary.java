package model;

import javafx.scene.shape.Line;

/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class GateUnary extends Circuit implements GateName {

    private Line outputLine;

    private Line inputLine;

    private int gateTypeId;

    public GateUnary(final boolean input, final boolean output, final int gateTypeId) {
        super(input, output);
        this.gateTypeId = gateTypeId;
    }
    public GateUnary(final int gateTypeId) {
        this(false, false, gateTypeId);
    }
    public GateUnary() {
        this(NOT_GATE);
    }


    public void setInput(boolean input) {
        super.setInputA(input);
    }

    public boolean getInput() {
        return super.getInputAValue();
    }

    public Line getOutputLine() {
        return outputLine;
    }

    public void setOutputLine(Line outputLine) {
        this.outputLine = outputLine;
    }

    public Line getInputLine() {
        return inputLine;
    }

    public void setInputLine(Line inputLine) {
        this.inputLine = inputLine;
    }

    public int getGateType() {
        return gateTypeId;
    }

    public void setGateType(int gateTypeIdId) {
        this.gateTypeId = gateTypeIdId;
    }
}
