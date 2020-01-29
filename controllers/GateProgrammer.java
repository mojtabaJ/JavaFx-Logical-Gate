package controllers;

import gates.*;
import model.Gate;
import model.Circuit;
import model.GateUnary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class GateProgrammer {
    private DragController ref;
    private List<PowerGate> powerSources;
    public GateProgrammer(DragController ref) {
        this.ref = ref;
        this.powerSources = new ArrayList<>();
    }

    public boolean addPowerSource(final PowerGate powerSource) {
        if(powerSource == null)
            return false;
        if(powerSources.contains(powerSource))
            return false;
        return powerSources.add(powerSource);
    }

    public void run() {
        for (PowerGate powerSourceComponent:
                powerSources) {
            findOutput(powerSourceComponent);
        }
    }

    private void findOutput(final Circuit target) {
        if(target instanceof Circuit) {
            Circuit circuitComponent = (Circuit)target;
            if(target instanceof GateUnary) {
                GateUnary unaryGate = (GateUnary)target;
                boolean value = !unaryGate.getInput();
                unaryGate.setOutput(value);
            } else if(target instanceof Gate) {
                Gate binaryGate = (Gate)target;
                boolean value = false;

                if(binaryGate instanceof AndGate)
                    value = binaryGate.getInputAValue() & binaryGate.getInputBValue();

                else if(binaryGate instanceof OrGate)
                    value = binaryGate.getInputAValue() | binaryGate.getInputBValue();

                else if(binaryGate instanceof NAndGate)
                    value = !(binaryGate.getInputAValue() & binaryGate.getInputBValue());

                else if(binaryGate instanceof NorGate)
                    value = !(binaryGate.getInputAValue() | binaryGate.getInputBValue());

                else if(binaryGate instanceof NotGate)
                    value = !(binaryGate.getInputAValue());

                else if(binaryGate instanceof XorGate)
                    value = (!(binaryGate.getInputAValue() & binaryGate.getInputBValue())) & (binaryGate.getInputAValue() | binaryGate.getInputBValue());

                else if(binaryGate instanceof XnorGate)
                    value = !(!(binaryGate.getInputAValue() & binaryGate.getInputBValue())) & (binaryGate.getInputAValue() | binaryGate.getInputBValue());


                binaryGate.setOutput(value);
            }
            Circuit outputNode = (Circuit)circuitComponent.getOutputNode();
            if(outputNode instanceof LEDGate) {
                LEDGate ledComponent = (LEDGate)outputNode;
                ledComponent.setLed(circuitComponent.getOutputValue());
            } else if(outputNode instanceof Gate) {
                Gate binaryGate = (Gate)outputNode;
                if(circuitComponent.getOutputType().equals("A")) {
                    binaryGate.setInputA(circuitComponent.getOutputValue());
                } else if(circuitComponent.getOutputType().equals("B")) {
                    binaryGate.setInputBValue(circuitComponent.getOutputValue());
                }
                findOutput(binaryGate);
            } else if(outputNode instanceof GateUnary) {
                GateUnary unaryGate = (GateUnary)outputNode;
                unaryGate.setInput(circuitComponent.getOutputValue());
                findOutput(unaryGate);
            }
        }
    }
}
