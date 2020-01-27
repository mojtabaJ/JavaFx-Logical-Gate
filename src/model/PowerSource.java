package model;

import javafx.scene.shape.Line;


/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class PowerSource extends Circuit {


    private Line outputLine;

    private Circuit nextComponent;

    public PowerSource(final boolean output) {
        super(false,output);
    }

    public PowerSource() {
        this(false);
    }

    public void togglePower() {
        setOutput(!getOutputValue());
    }

    public void setOutputLine(Line outputLine) {
        this.outputLine = outputLine;
    }

    public void setNextComponent(Circuit nextComponent) {
        this.nextComponent = nextComponent;
    }

    public Line getOutputLine() {
        return outputLine;
    }

    public Circuit getNextComponent() {
        return nextComponent;
    }
}
