import com.sun.org.apache.bcel.internal.generic.Instruction;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.FileNotFoundException;

public class MysteryGame extends Application {

    //Logic components

    MysteryGameModel model;



    // Visual components

    final int defaultWindowHeight = 700;
    final int defaultWindowWidth = 1000;

    final double storageIconSize = 50;
    final double spaceBetweenVisulaItemAndFrame = 20;

    final double storageHeight = defaultWindowHeight / 7;
    final double StorageWidth = defaultWindowWidth * 0.8;
    final double storageStrokeWidth = 10;
    final double itemSize = 60;
    final double heightallowance = 10; // might be caused be bar...?


    Button closeButton;
    Rectangle storageRec;
    Group storageGroup;
    Group storageDisplayalGroup;
    Group touchFieldCircleGroup;
    Button messageCloseButton;
    Label messageLabel;
    HBox messageHBox;
    Group messageGroup;
    Group arrowGroup;
    ImageView imgView;
    StackPane box;
    Scene scene;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        // set up game

        model = new MysteryGameModel("firstTry");

        // set up basic visual components

        imgView = new ImageView();
        box = new StackPane();
        touchFieldCircleGroup = new Group();
        storageDisplayalGroup = new Group();
        messageGroup = new Group();
        arrowGroup = new Group();

        box.getChildren().add(imgView);
        box.getChildren().add(touchFieldCircleGroup);
        box.getChildren().add(arrowGroup);
        box.getChildren().add(storageDisplayalGroup);
        box.getChildren().add(messageGroup);


        createFrameRectangleForGroup(touchFieldCircleGroup);
        createFrameRectangleForGroup(storageDisplayalGroup);
        createFrameRectangleForGroup(arrowGroup);


        // create TouchfieldCircles

        displaySightAndLayer(0, 0);


        // display Scene


        scene = new Scene(box, defaultWindowWidth, defaultWindowHeight);

        primaryStage.setTitle("Mystery");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


        // lets prepare the storageDisplayelGroup

        createStorageVisuals();

        // and make the messageGroup

        createMessageGroup();


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                setMessageLabel("");
            }
        });

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getX() + ": " + event.getY());
            }
        });


    }


    // Visual set up methods


    public void setImage(String layerName) {

        System.out.println(" Try to open: Images/" + layerName + ".jpg");
        Image image = new Image("Images/" + layerName + ".jpg");

        //imgView = new ImageView();
        imgView.setImage(image);
        imgView.setFitWidth(defaultWindowWidth);
        imgView.setFitHeight(defaultWindowHeight);

        Rectangle2D viewportRect = new Rectangle2D(0, 0, image.getWidth(), image.getHeight());
        imgView.setViewport(viewportRect);


    }

    public void createMessageGroup() {
        createFrameRectangleForGroup(messageGroup);


        messageCloseButton = new Button(" x ");
        messageCloseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                messageGroup.setVisible(false);
                messageGroup.setDisable(true);
            }
        });
        messageCloseButton.setFocusTraversable(false);


        messageLabel = new Label("This is a test fjdskalllllllllllllllllll \n fdjasssssssssssssssssssssss");
        messageLabel.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 15));
        messageLabel.setTextFill(Color.BLACK);
        messageLabel.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        messageLabel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.FULL)));


        messageHBox = new HBox(messageCloseButton, messageLabel);
        messageHBox.setLayoutY(spaceBetweenVisulaItemAndFrame);
        messageHBox.setLayoutX(spaceBetweenVisulaItemAndFrame);
        messageHBox.setSpacing(10);
        messageGroup.getChildren().add(messageHBox);
        messageGroup.setVisible(false);
        messageGroup.setDisable(true);

    }

    public void createStorageVisuals() {


        Rectangle storageIcon = new Rectangle(0, 0, storageIconSize, storageIconSize);
        storageIcon.setFill(Color.rgb(40, 25, 25));
        storageIcon.setStroke(Color.BLACK);
        storageIcon.setStrokeWidth(10);
        storageIcon.setStrokeType(StrokeType.INSIDE);
        storageIcon.setX(spaceBetweenVisulaItemAndFrame);
        storageIcon.setY(scene.getHeight() - storageIcon.getHeight() - spaceBetweenVisulaItemAndFrame - heightallowance);
        box.getChildren().add(storageIcon);


        storageIcon.setOnMouseClicked(event -> openStorage(true));

        storageDisplayalGroup.getChildren().add(storageIcon);


        storageGroup = new Group();


        storageRec = new Rectangle(0, 0, StorageWidth, storageHeight);
        storageRec.setFill(Color.rgb(40, 25, 25));
        storageRec.setStroke(Color.BLACK);
        storageRec.setStrokeWidth(storageStrokeWidth);
        storageRec.setStrokeType(StrokeType.INSIDE);
        storageRec.setX(spaceBetweenVisulaItemAndFrame);
        storageRec.setY(scene.getHeight() - storageRec.getHeight() - spaceBetweenVisulaItemAndFrame - heightallowance);
        storageGroup.getChildren().add(storageRec);


        storageDisplayalGroup.getChildren().add(storageGroup);


        closeButton = new Button(" x ");
        closeButton.setOnAction(event -> openStorage(false));
        closeButton.setTranslateY(scene.getHeight() - 130);
        closeButton.setTranslateX(20);
        closeButton.setBackground(new Background(new BackgroundFill(Color.rgb(70, 40, 30), CornerRadii.EMPTY, Insets.EMPTY)));
        closeButton.setTextFill(Color.BLACK);

        storageGroup.getChildren().add(closeButton);
        storageGroup.setVisible(false);


    }

    /**
     * creates a see through and deactivated Rectangle that solely displays a black frame around the entire scene
     * <p>
     * This is necessary in order to be sure all the nodes
     * that are added after the frame rectangle are placed in the right position.
     * We force the Group to have the size of our scene, so nothing gets shifted around.
     *
     * @param group Group the Rectangle should be added to
     */

    public void createFrameRectangleForGroup(Group group) {
        Rectangle frame = new Rectangle(0, 0, defaultWindowWidth, defaultWindowHeight);
        frame.setStrokeType(StrokeType.INSIDE);
        frame.setStroke(Color.BLACK);
        frame.setStrokeWidth(10);
        frame.setFill(Color.rgb(1, 1, 1, 0));
        frame.setDisable(true);
        group.getChildren().add(frame);
    }

    // update methods:

    private void displaySight(Sight sight){
        displaySight(model.mode.sights.indexOf(sight));

    }

    private void displaySight(int sightIndex) {
        displaySightAndLayer(sightIndex, model.mode.sights.get(sightIndex).currentLayer);
    }

    private void displayLayer(int layerIndex) {
        model.mode.sights.get(model.currentSightindex).currentLayer = layerIndex;
        setImage(model.mode.sights.get(model.currentSightindex).Layers.get(layerIndex));
    }


    private void displaySightAndLayer(int sightIndex, int layerIndex) {

        setImage(model.mode.sights.get(sightIndex).Layers.get(layerIndex));

        model.mode.sights.get(sightIndex).currentLayer = layerIndex;
        model.currentSightindex = sightIndex;


        touchFieldCircleGroup.getChildren().clear();
        createFrameRectangleForGroup(touchFieldCircleGroup);



        if(model.mode.sights.get(sightIndex).touchFields!= null) {


            for (TouchField tf : model.mode.sights.get(sightIndex).touchFields) {

                Circle c = new Circle(tf.radius);
                c.setOnMouseClicked(event -> startEvent(tf.instruc));
                touchFieldCircleGroup.getChildren().add(c);
                c.setTranslateX(tf.location.getX());

                c.setTranslateY(tf.location.getY());

                c.setFill(Color.rgb(1, 1, 1, 0));
            }
        }


        arrowGroup.getChildren().clear();
        createFrameRectangleForGroup(arrowGroup);

        if(model.mode.sights.get(sightIndex).previousSight!= null && model.mode.sights.get(sightIndex).previousSight!= model.mode.sights.get(sightIndex)){





            Rectangle r = new Rectangle(0,0,20,20);
            r.setX(defaultWindowWidth*0.5);
            r.setY(defaultWindowHeight*0.9);
            r.setFill(Color.YELLOW);
            r.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {

                                        SightInstruction s = new SightInstruction("", model.mode.sights.get(sightIndex).previousSight);
                                        model.mode.sights.get(sightIndex).previousSight = null;
                                        performClassSpecificInstructionLinkAction(s);
                                    }
                                });


            arrowGroup.getChildren().add(r);
        }

        if(model.mode.connections.size()>0){
            for(Connection c: model.mode.connections){
                if(c.hasSight(model.mode.sights.get(sightIndex))){

                    Rectangle r = new Rectangle(0,0,20,20);
                    r.setFill(Color.YELLOW);

                    if(c.getTypeOfSight(model.mode.sights.get(sightIndex)) == ConnectionType.LEFT) {
                        System.out.println("I was here!");
                        r.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                ConnectionInstruction cs = new ConnectionInstruction("", c.leftSight);
                                performClassSpecificInstructionLinkAction(cs);
                            }
                        });

                        r.setX(defaultWindowWidth * 0.02);
                        r.setY(defaultWindowHeight * 0.5);
                    }
                    else{
                        r.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                ConnectionInstruction cs = new ConnectionInstruction("", c.rightSight);
                                performClassSpecificInstructionLinkAction(cs);

                            }
                        });

                        r.setX(defaultWindowWidth * 0.96);
                        r.setY(defaultWindowHeight * 0.5);
                        r.setFill(Color.RED);

                    }

                    arrowGroup.getChildren().add(r);
                }
            }

        }


    }


    public void openStorage(boolean bool) {
        System.out.println("update called");

        storageGroup.getChildren().removeAll(storageGroup.getChildren());
        storageGroup.setVisible(bool);

        if (bool) {

            double spaceBetweenItems = ((storageHeight - itemSize) / 2);


            double xValueOfItemView = storageRec.getX() + ((storageHeight - itemSize) / 2);
            storageGroup.getChildren().add(storageRec);
            if (model.storage.items.size() == 0) {
                storageRec.setWidth((itemSize + spaceBetweenItems) + spaceBetweenItems);
                Label emptyLabel1 = new Label("STORAGE");
                Label emptyLabel2 = new Label("EMPTY");
                VBox vBox = new VBox(emptyLabel1, emptyLabel2);
                for (int i = 0; i < vBox.getChildren().size(); i++) {
                    Label l = (Label) vBox.getChildren().get(i);
                    l.setAlignment(Pos.CENTER);
                    l.setPrefWidth(itemSize);
                    l.setMaxWidth(itemSize);
                    l.setMinWidth(itemSize);
                    l.setTextFill(Color.rgb(70, 40, 30));
                    l.setTextAlignment(TextAlignment.CENTER);
                    l.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 12));

                }
                vBox.setLayoutX(xValueOfItemView);
                vBox.setLayoutY(storageRec.getY() + spaceBetweenItems + ((itemSize - vBox.getHeight()) / 2));
                storageGroup.getChildren().add(vBox);
            } else {
                storageRec.setWidth(model.storage.items.size() * (itemSize + spaceBetweenItems) + spaceBetweenItems);
            }


            for (Item i : model.storage.items) {
                ImageView itemView = new ImageView();

                Image image = new Image("Images/" + i.name + ".jpg");

                //imgView = new ImageView();
                itemView.setImage(image);

                Rectangle2D viewportRect = new Rectangle2D(0, 0, image.getWidth(), image.getHeight());
                itemView.setViewport(viewportRect);
                itemView.setX(xValueOfItemView);
                itemView.setY(storageRec.getY() + spaceBetweenItems);

                itemView.setFitWidth(itemSize);
                itemView.setFitHeight(itemSize);


                if (i.chosen) {
                    Rectangle chosenFrame = new Rectangle(0, 0, itemSize, itemSize);
                    chosenFrame.setStrokeType(StrokeType.OUTSIDE);
                    chosenFrame.setStrokeWidth(itemSize * 0.05);
                    chosenFrame.setStroke(Color.YELLOW);
                    chosenFrame.setX(itemView.getX());
                    chosenFrame.setY(itemView.getY());
                    storageGroup.getChildren().add(chosenFrame);
                }


                itemView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        model.storage.pick(i);
                        openStorage(true);
                    }
                });

                storageGroup.getChildren().add(itemView);

                xValueOfItemView += itemSize + ((storageHeight - itemSize) / 2);

            }

            storageGroup.getChildren().add(closeButton);
        }
    }

    public void startEvent(InstructionLink instruc) {
        if(instruc.active) {
            if(instruc.deactivateAfterOneAction){
                instruc.active = false;
            }
            Item chosenItem = null;

            for (Item i : model.storage.items) {
                if (i.chosen) {
                    chosenItem = i;
                    break;
                }

            }


            if (instruc.necesarryItem == chosenItem || instruc.necesarryItem == null) {

                performClassSpecificInstructionLinkAction(instruc);
                if(instruc.necesarryItem == chosenItem && instruc.deleteItemUponUse){
                    model.storage.items.remove(instruc.necesarryItem);
                    openStorage(true);
                }
                if (! instruc.message.equals("")) {
                    setMessageLabel(instruc.message);
                } else {
                    setMessageLabel("Touchpoint detected");

                }

            } else {
                if (chosenItem == null) {
                    setMessageLabel(instruc.noItemErrorMessage);
                } else {
                    setMessageLabel(instruc.wrongItemErrorMessage);
                }
            }
        }
        else{
            setMessageLabel("There is nothing else to do here.");
        }
    }





    public void setMessageLabel(String message) {

        if (message.equals("")) {
            messageGroup.setVisible(false);
            messageGroup.setDisable(true);
        } else {

            messageGroup.setVisible(true);
            messageGroup.setDisable(false);
            messageLabel.setText(message);
        }

//
//        FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), messageHBox);
//        fadeTransition.setFromValue(1);
//        fadeTransition.setDelay(Duration.millis(2000));
//        fadeTransition.setByValue(1);
//        fadeTransition.setToValue(0);
//        fadeTransition.setCycleCount(1);
//        fadeTransition.setAutoReverse(true);
//        fadeTransition.play();
//        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                messageGroup.setDisable(true);
//                messageGroup.setVisible(false);
//            }
//        });
//
//        messageLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                fadeTransition.setToValue(1);
//
//            }
//        });
//
//        messageLabel.setOnMouseEntered(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                fadeTransition.playFromStart();
//                messageGroup.setVisible(true);
//            }
//        });


    }

    public void performClassSpecificInstructionLinkAction(InstructionLink instruc) {
        boolean deletePreviousSight = true;
        switch (instruc.getClass().toString()) {
            case "class ItemInstruction":
                ItemInstruction itemInstruction = (ItemInstruction) instruc;
                model.storage.add(itemInstruction.foundItem);
                model.storage.choose(itemInstruction.foundItem);
                openStorage(true);
                break;
            case "class LayerInstruction":
                LayerInstruction layerInstruction = (LayerInstruction) instruc;
                displayLayer(layerInstruction.indexOfNextLayer);
                break;

            case "class ConnectionInstruction":
                deletePreviousSight = false;

            case "class SightInstruction":
                setMessageLabel("");
                SightInstruction sightInstruction = (SightInstruction) instruc;
                Sight currentSight = model.mode.sights.get(model.currentSightindex);
                if(sightInstruction.nextSight.previousSight == null && deletePreviousSight){
                    sightInstruction.nextSight.previousSight = currentSight;
                }
                displaySight(sightInstruction.nextSight);
                break;
//            case "class ConnectionInstruction":
//                setMessageLabel("");
//                ConnectionInstruction connectionInstruction = (ConnectionInstruction) instruc;
//                displaySight(connectionInstruction.nextSight);
//                break;





        }


    }
}
