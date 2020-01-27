package controllers;

import gates.*;
import model.Gate;
import model.Circuit;
import model.PowerSource;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Created by Mojtaba Jalambadani.
 * Jun 2020
 */


public class DragController implements Config {

    private static final String INPUT_A = "inputA";
    private static final String INPUT_B = "inputB";
    private static final String PUT_A = "A";
    private static final String PUT_B = "B";

    private MenuController menuController;

    private Node currentNode;

    private AnchorPane anchorPane;

    private boolean componentDropped;

    private boolean newComponent;

    private Pane pane;

    private GateProgrammer decoder;



    public DragController(final MenuController menuController){
        this.menuController = menuController;
        this.currentNode = null;
        this.componentDropped = false;
        this.newComponent = false;
        this.decoder = new GateProgrammer(this);
        this.pane = menuController.getCanvasPanel();
        initListeners();
    }


    private void initListeners() {

        menuController.getAndGateButton().setOnDragDetected(event -> handelDrag(event, TYPE_AND_GATE));
        menuController.getNandGateButton().setOnDragDetected(event -> handelDrag(event, TYPE_NAND_GATE));

        menuController.getOrGateButton().setOnDragDetected(event -> handelDrag(event, TYPE_OR_GATE));
        menuController.getNorButtonGate().setOnDragDetected(event -> handelDrag(event, TYPE_NOR_GATE));

        menuController.getNotGateButton().setOnDragDetected(event -> handelDrag(event, TYPE_NOT_GATE));

        menuController.getXorGateButton().setOnDragDetected(event -> handelDrag(event, TYPE_XOR_GATE));
        menuController.getXnorGateButton().setOnDragDetected(event -> handelDrag(event, TYPE_XNOR_GATE));

        menuController.getLEDButton().setOnDragDetected(event -> handelDrag(event, TYPE_LED));


        menuController.getVccButton().setOnDragDetected(event -> handelDrag(event, TYPE_VCC));
        menuController.getGroundButton().setOnDragDetected(event -> handelDrag(event, TYPE_GROUND));


        menuController.getPowerButton().setOnMouseClicked(this::run);
        menuController.getCanvasPanel().setOnMouseDragEntered(this::drawDragging);
        menuController.getCanvasPanel().setOnMouseDragExited(this::canvasDragExited);
        menuController.getCanvasPanel().setOnMouseDragOver(this::DragPosition);
    }


    private Parent getNode(Object object) {
        return ((Node)object).getParent().getParent();
    }

    private Node createGate(String componentType) {
        switch (componentType) {

            case TYPE_AND_GATE :
                return new AndGate();
            case TYPE_NAND_GATE:
                return new NAndGate();


            case TYPE_OR_GATE:
                return new OrGate();
            case TYPE_NOR_GATE:
                return new NorGate();


            case TYPE_NOT_GATE:
                return new NotGate();


            case TYPE_XOR_GATE:
                return new XorGate();
            case TYPE_XNOR_GATE:
                return new XnorGate();



            case TYPE_LED:
                return new LEDGate();


            case TYPE_GROUND:
                return new PowerGate(true);
            case TYPE_VCC:
                return new PowerGate(false);





            default:
                return null;
        }
    }



    public void handelDrag(MouseEvent mouseEvent, String componentType) {
        ((Node)mouseEvent.getSource()).startFullDrag();
        this.currentNode = createGate(componentType);
        this.newComponent = false;
    }


    public void drawDragging(MouseDragEvent dragEvent) {
        try {
            if (!this.pane.getChildren().contains(currentNode))
                this.pane.getChildren().add(currentNode);
            if (this.currentNode instanceof PowerGate)
                this.decoder.addPowerSource((PowerGate) this.currentNode);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void DragPosition(MouseEvent mouseEvent) {
        this.setPosition(mouseEvent, currentNode);
    }


    private void run(MouseEvent mouseEvent) {
        this.decoder.run();
    }


    public void canvasDragExited(MouseDragEvent dragEvent) {
        if(!newComponent)
        addListenersToNode((AnchorPane) currentNode);
        this.newComponent = false;
        this.currentNode = null;
    }


    public void componentDragDetected(MouseEvent mouseEvent) {
        Object object = mouseEvent.getSource();
        if(object instanceof Node) {
            Node component = ((Node)object);
            getNode(component).startFullDrag();
            this.currentNode = component.getParent().getParent();
            this.newComponent = true;
        }
    }


    public void outputDragDetected(MouseEvent mouseEvent) {
        Node component = ((Node)mouseEvent.getSource());
        Dragboard dragboard = component.getParent().getParent().startDragAndDrop(TransferMode.MOVE);
        ClipboardContent clipboardContent = new ClipboardContent();

        double x = component.getLayoutX() + component.getParent().getParent().getLayoutX() + component.getLayoutBounds().getWidth()/2;
        double y = component.getLayoutY() + component.getParent().getParent().getLayoutY() + component.getLayoutBounds().getHeight()/2;

        this.currentNode = createLine(x,y,x,y);
        this.anchorPane = (AnchorPane) getNode(mouseEvent.getSource());

        mapLineTo(getNode(mouseEvent.getSource()),(Line)this.currentNode, ((Node) mouseEvent.getSource()).getId());


        Object object = mouseEvent.getSource();
        if(object instanceof ImageView)
            ((ImageView)object).setImage(new Image("icons/connector-positive.png"));

        clipboardContent.putString("String");
        dragboard.setContent(clipboardContent);
        setDragView(dragboard);
    }


    public void inputDragEntered(DragEvent dragEvent) {
        if(this.currentNode instanceof Line) {

            setLinePosition((Node)dragEvent.getSource(), (Line)this.currentNode);
            if(!this.pane.getChildren().contains(this.currentNode))
                this.pane.getChildren().add(this.currentNode);
        }
    }


    public void inputDragOver(DragEvent dragEvent) {
        if(this.currentNode instanceof Line) {
            mapLineTo(getNode(dragEvent.getSource()), (Line)this.currentNode, ((Node)dragEvent.getSource()).getId());
            mapNodeTo(anchorPane, getNode(dragEvent.getSource()),((Node) dragEvent.getSource()).getId());

            Object object = dragEvent.getSource();
            if(object instanceof ImageView) {
                ((ImageView)object).setImage(new Image("icons/connector-positive.png"));
            }
            ((Line)this.currentNode).setStroke(Color.web("#1e1e1e"));


            setLinePosition((Node)dragEvent.getSource(),(Line)this.currentNode);
            if(!this.pane.getChildren().contains(this.currentNode))
                this.pane.getChildren().add(this.currentNode);
            dragEvent.acceptTransferModes(TransferMode.MOVE);
        }
    }


    public void inputDragExited(DragEvent dragEvent) {
    }


    private void setPosition(final MouseEvent mouseEvent, final Node node) {
        if(node instanceof AnchorPane) {
            AnchorPane anchorPane = (AnchorPane)node;
            double layoutX = mouseEvent.getX() - anchorPane.getWidth() / 2;
            double layoutY = mouseEvent.getY() - anchorPane.getHeight() / 2;
            anchorPane.setLayoutX(layoutX);
            anchorPane.setLayoutY(layoutY);
            if(node instanceof LEDGate) {
                LEDGate ledComponent = (LEDGate)node;
                adjustLine(ledComponent.getInputLine(),layoutX,layoutY,ledComponent.getInputImage(),true);
            } else if(node instanceof AndGate) {
                AndGate andGate = (AndGate)node;
                adjustLine(andGate.getOutputLine(),layoutX,layoutY,andGate.getOutputImage(),false);
                adjustLine(andGate.getInputALine(),layoutX,layoutY,andGate.getInputAImage(),true);
                adjustLine(andGate.getInputBLine(),layoutX,layoutY,andGate.getInputBImage(),true);
            } else if(node instanceof OrGate) {
                OrGate orGate = (OrGate)node;
                adjustLine(orGate.getOutputLine(),layoutX,layoutY,orGate.getOutputConnector(),false);
                adjustLine(orGate.getInputALine(),layoutX, layoutY,orGate.getInputAImage(),true);
                adjustLine(orGate.getInputBLine(),layoutX,layoutY,orGate.getInputBImage(),true);
            } else if(node instanceof NAndGate){
                NAndGate nandGate = (NAndGate) node;
                adjustLine(nandGate.getOutputLine(),layoutX,layoutY,nandGate.getOutputImage(),false);
                adjustLine(nandGate.getInputALine(),layoutX,layoutY,nandGate.getInputAImage(),true);
                adjustLine(nandGate.getInputBLine(),layoutX,layoutY,nandGate.getInputBImage(),true);
            } else if(node instanceof PowerGate) {
                PowerGate powerSourceComponent = (PowerGate)node;
                adjustLine(powerSourceComponent.getOutputLine(),layoutX,layoutY,powerSourceComponent.getOutputConnector(),false);
            } else if(node instanceof NorGate) {
                NorGate norGate = (NorGate) node;
                adjustLine(norGate.getOutputLine(),layoutX,layoutY,norGate.getOutputImage(),false);
                adjustLine(norGate.getInputALine(),layoutX,layoutY,norGate.getInputAImage(),true);
                adjustLine(norGate.getInputBLine(),layoutX,layoutY,norGate.getInputBImage(),true);
            } else if(node instanceof NotGate) {
                NotGate notGate = (NotGate) node;
                adjustLine(notGate.getOutputLine(),layoutX,layoutY,notGate.getOutputImage(),false);
                adjustLine(notGate.getInputALine(),layoutX,layoutY,notGate.getInputA(),true);
            } else if(node instanceof XorGate) {
                XorGate xorGate = (XorGate) node;
                adjustLine(xorGate.getOutputLine(),layoutX,layoutY,xorGate.getOutput(),false);
                adjustLine(xorGate.getInputALine(),layoutX,layoutY,xorGate.getInputAImage(),true);
                adjustLine(xorGate.getInputBLine(),layoutX,layoutY,xorGate.getInputBImage(),true);
            } else if(node instanceof XnorGate) {
                XnorGate xnorGate = (XnorGate) node;
                adjustLine(xnorGate.getOutputLine(),layoutX,layoutY,xnorGate.getOutput(),false);
                adjustLine(xnorGate.getInputALine(),layoutX,layoutY,xnorGate.getInputAImage(),true);
                adjustLine(xnorGate.getInputBLine(),layoutX,layoutY,xnorGate.getInputBImage(),true);
            }
        }
    }

    private void adjustLine(final Line lineToAdjust, final double x, final double y, final ImageView targetToAdjustWith, final boolean isEnd) {

        if(lineToAdjust == null)
            return;

        if(isEnd) {

            lineToAdjust.setEndX(x + targetToAdjustWith.getLayoutX() + targetToAdjustWith.getLayoutBounds().getWidth()/2);
            lineToAdjust.setEndY(y + targetToAdjustWith.getLayoutY() + targetToAdjustWith.getLayoutBounds().getHeight()/2);

        } else {

            lineToAdjust.setStartX(x + targetToAdjustWith.getLayoutX() + targetToAdjustWith.getLayoutBounds().getWidth()/2);
            lineToAdjust.setStartY(y + targetToAdjustWith.getLayoutY() + targetToAdjustWith.getLayoutBounds().getHeight()/2);
        }

    }

    private void setDragView(Dragboard dragboard) {
        dragboard.setDragView(new Image("icons/drag-image.png"));
    }

    private void addListenersToNode(AnchorPane anchorPane) {
        if(anchorPane == null) {
            return;
        }

        if(anchorPane instanceof AndGate) {
            AndGate andGate = (AndGate)anchorPane;
            andGate.getAndGate().setOnDragDetected(this::componentDragDetected);
            andGate.getOutputImage().setOnDragDetected(this::outputDragDetected);
            andGate.getInputAImage().setOnDragOver(this::inputDragOver);
            andGate.getInputAImage().setOnDragEntered(this::inputDragEntered);
            andGate.getInputAImage().setOnDragExited(this::inputDragExited);
            andGate.getInputBImage().setOnDragOver(this::inputDragOver);
            andGate.getInputBImage().setOnDragEntered(this::inputDragEntered);
            andGate.getInputBImage().setOnDragExited(this::inputDragExited);

        } else if(anchorPane instanceof OrGate) {

            OrGate orGate = (OrGate)anchorPane;
            orGate.getOrGate().setOnDragDetected(this::componentDragDetected);
            orGate.getOutputConnector().setOnDragDetected(this::outputDragDetected);
            orGate.getInputAImage().setOnDragEntered(this::inputDragEntered);
            orGate.getInputAImage().setOnDragOver(this::inputDragOver);
            orGate.getInputAImage().setOnDragExited(this::inputDragExited);
            orGate.getInputBImage().setOnDragEntered(this::inputDragEntered);
            orGate.getInputBImage().setOnDragExited(this::inputDragExited);
            orGate.getInputBImage().setOnDragOver(this::inputDragOver);

        }else if(anchorPane instanceof LEDGate) {

            LEDGate led = (LEDGate)anchorPane;
            led.getLEDImage().setOnDragDetected(this::componentDragDetected);
            led.getInputImage().setOnDragEntered(this::inputDragEntered);
            led.getInputImage().setOnDragOver(this::inputDragOver);
            led.getInputImage().setOnDragExited(this::inputDragExited);

        } else if(anchorPane instanceof NAndGate) {

            NAndGate nAndGate = (NAndGate) anchorPane;
            nAndGate.getNandGate().setOnDragDetected(this::componentDragDetected);
            nAndGate.getOutputImage().setOnDragDetected(this::outputDragDetected);
            nAndGate.getInputAImage().setOnDragOver(this::inputDragOver);
            nAndGate.getInputAImage().setOnDragEntered(this::inputDragEntered);
            nAndGate.getInputAImage().setOnDragExited(this::inputDragExited);
            nAndGate.getInputBImage().setOnDragOver(this::inputDragOver);
            nAndGate.getInputBImage().setOnDragEntered(this::inputDragEntered);
            nAndGate.getInputBImage().setOnDragExited(this::inputDragExited);

        } else if(anchorPane instanceof PowerGate) {

            PowerGate powerSourceComponent = (PowerGate)anchorPane;
            powerSourceComponent.getPowerSourceImage().setOnDragDetected(this::componentDragDetected);
            powerSourceComponent.getOutputConnector().setOnDragDetected(this::outputDragDetected);

        } else if(anchorPane instanceof NorGate) {

            NorGate norGateComponent = (NorGate) anchorPane;
            norGateComponent.getNorGate().setOnDragDetected(this::componentDragDetected);
            norGateComponent.getOutputImage().setOnDragDetected(this::outputDragDetected);
            norGateComponent.getInputAImage().setOnDragOver(this::inputDragOver);
            norGateComponent.getInputAImage().setOnDragEntered(this::inputDragEntered);
            norGateComponent.getInputAImage().setOnDragExited(this::inputDragExited);
            norGateComponent.getInputBImage().setOnDragOver(this::inputDragOver);
            norGateComponent.getInputBImage().setOnDragEntered(this::inputDragEntered);
            norGateComponent.getInputBImage().setOnDragExited(this::inputDragExited);

        } else if(anchorPane instanceof NotGate) {

            NotGate notGate = (NotGate) anchorPane;
            notGate.getNotGate().setOnDragDetected(this::componentDragDetected);
            notGate.getOutputImage().setOnDragDetected(this::outputDragDetected);
            notGate.getInputA().setOnDragOver(this::inputDragOver);
            notGate.getInputA().setOnDragEntered(this::inputDragEntered);
            notGate.getInputA().setOnDragExited(this::inputDragExited);

        } else if(anchorPane instanceof XorGate) {

            XorGate xorGate = (XorGate) anchorPane;
            xorGate.getXorGate().setOnDragDetected(this::componentDragDetected);
            xorGate.getOutput().setOnDragDetected(this::outputDragDetected);
            xorGate.getInputAImage().setOnDragOver(this::inputDragOver);
            xorGate.getInputAImage().setOnDragEntered(this::inputDragEntered);
            xorGate.getInputAImage().setOnDragExited(this::inputDragExited);
            xorGate.getInputBImage().setOnDragOver(this::inputDragOver);
            xorGate.getInputBImage().setOnDragEntered(this::inputDragEntered);
            xorGate.getInputBImage().setOnDragExited(this::inputDragExited);

        }else if(anchorPane instanceof XorGate) {

            XnorGate xnorGate = (XnorGate) anchorPane;
            xnorGate.getXnorGate().setOnDragDetected(this::componentDragDetected);
            xnorGate.getOutput().setOnDragDetected(this::outputDragDetected);
            xnorGate.getInputAImage().setOnDragOver(this::inputDragOver);
            xnorGate.getInputAImage().setOnDragEntered(this::inputDragEntered);
            xnorGate.getInputAImage().setOnDragExited(this::inputDragExited);
            xnorGate.getInputBImage().setOnDragOver(this::inputDragOver);
            xnorGate.getInputBImage().setOnDragEntered(this::inputDragEntered);
            xnorGate.getInputBImage().setOnDragExited(this::inputDragExited);

        }
    }

    private  Line createLine(final double startX, final double startY, final double endX, final double endY) {
        Line line = new Line(startX,startY,endX,endY);
        line.setStrokeWidth(3);
        line.setStroke(LINE_COLOR);
        return line;
    }

    private void setLinePosition(final Node target, final Line line) {

        double x = target.getLayoutX() + target.getParent().getParent().getLayoutX() + target.getLayoutBounds().getWidth()/2;
        double y = target.getLayoutY() + target.getParent().getParent().getLayoutY() + target.getLayoutBounds().getHeight()/2;

        line.setEndX(x);
        line.setEndY(y);
    }

    private void mapLineTo(final Object target, final Line line, final String fxID) {

        if(target instanceof LEDGate) {

            LEDGate ledComponent = (LEDGate) target;

            if(ledComponent.getInputLine() != null)
                this.pane.getChildren().remove(ledComponent.getInputLine());
            ledComponent.setInputLine(line);

        } else if(target instanceof Gate) {

            Gate binaryGate = (Gate) target;
            switch (fxID) {
                case INPUT_A :
                    if(binaryGate.getInputALine() != null)
                        this.pane.getChildren().remove(binaryGate.getInputALine());
                    binaryGate.setInputALine(line);
                    break;

                case INPUT_B :
                    if(binaryGate.getInputBLine() != null)
                        this.pane.getChildren().remove(binaryGate.getInputBLine());
                    binaryGate.setInputBLine(line);
                    break;

                case "outputConnector" :
                    if(binaryGate.getOutputLine() != null)
                        this.pane.getChildren().remove(binaryGate.getOutputLine());
                    binaryGate.setOutputLine(line);
                    break;
            }

        } else if(target instanceof PowerSource) {

            PowerSource powerSource = (PowerSource)target;
            if(powerSource.getOutputLine() != null)
                this.pane.getChildren().remove(powerSource.getOutputLine());
            powerSource.setOutputLine(line);

        }
    }

    public void mapNodeTo(final Parent outputNode, final Parent inputNode, final String fxId) {


        if(outputNode instanceof LEDGate ||
                inputNode instanceof PowerGate ||
                outputNode == null ||
                inputNode == null  ||
                !(outputNode instanceof Circuit) ||
                !(inputNode instanceof Circuit)) {
            return;
        }

        Circuit outputComponent = (Circuit)outputNode;
        ((Circuit) outputNode).setOutputNode((Circuit) inputNode);

        if(inputNode instanceof Gate) {
            Gate binaryGate = (Gate)inputNode;
            if(fxId.equals(INPUT_A)) {
                binaryGate.setInputANode(outputComponent);
                outputComponent.setOutputType(PUT_A);
            } else if(fxId.equals(INPUT_B)) {
                binaryGate.setInputBNode(outputComponent);
                outputComponent.setOutputType(PUT_B);
            }
        } else if(inputNode instanceof LEDGate) {
            LEDGate ledComponent = (LEDGate)inputNode;
            ledComponent.setInputANode(outputComponent);
            outputComponent.setOutputType(PUT_A);
        }

    }


}
