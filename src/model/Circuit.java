package model;

import javafx.scene.layout.AnchorPane;


/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class Circuit extends AnchorPane {


    private boolean inputAValue;
    private boolean outputValue;

    private Circuit inputANode;
    private Circuit outputNode;
    private String outputType;



    public Circuit(final boolean inputAValue, final boolean outputValue) {
        this.inputAValue = inputAValue;
        this.outputValue = outputValue;
    }

    public Circuit(final boolean inputAValue) {
        this(inputAValue, false);
    }

    public Circuit() {
        this(false);
    }

    public boolean hasInputANode() {
        return !(inputANode == null);
    }

    public boolean hasOutputNode() {
        return !(outputNode == null);
    }



    public void setOutput(boolean output) {
        this.outputValue = output;
    }
    public void setInputA(boolean inputA) {
        this.inputAValue = inputA;
    }
    public void setInputANode(Circuit inputANode) {
        this.inputANode = inputANode;
    }
    public void setOutputNode(Circuit outputNode) {
        this.outputNode = outputNode;
    }
    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public boolean getInputAValue() {
        return this.inputAValue;
    }
    public boolean getOutputValue() {
        return this.outputValue;
    }
    public Circuit getInputANode() {
        return inputANode;
    }
    public Circuit getOutputNode() {
        return outputNode;
    }
     public String getOutputType() {
        return outputType;
    }
}

