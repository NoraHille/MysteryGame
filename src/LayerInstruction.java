public class LayerInstruction extends InstructionLink {

    int indexOfNextLayer = -1;

    public LayerInstruction(String message, int indexOfNextLayer) {
        super(message);
        this.indexOfNextLayer = indexOfNextLayer;
    }
}
