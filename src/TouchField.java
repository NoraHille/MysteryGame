import javafx.geometry.Point2D;

public class TouchField {



    Point2D location;
    InstructionLink instruc = new InstructionLink("There is nothing of interest here.");
    double radius = 30;

    public TouchField(Point2D location, InstructionLink instruc) {
        this.location = location;
        this.instruc = instruc;
    }



    public TouchField(Point2D location) {
        this.location = location;
    }

    public TouchField(int x, int y){this.location = new Point2D(x,y);};

    public TouchField(Point2D location, InstructionLink instruc, double radius) {
        this.location = location;
        this.instruc = instruc;
        this.radius = radius;
    }

    public void setDisabled(){
        instruc = new InstructionLink("Everything that could be done was done here.");
    }
}