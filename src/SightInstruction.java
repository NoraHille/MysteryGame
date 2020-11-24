public class SightInstruction extends InstructionLink {

    Sight nextSight = null;



    public SightInstruction(String message) {
        super(message);
        this.deactivateAfterOneAction = false;
    }

    public SightInstruction(String message, Sight nextSight) {
        super(message);
        this.deactivateAfterOneAction = false;
        this.nextSight = nextSight;
    }
}
