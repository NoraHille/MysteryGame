public class ConnectionInstruction extends SightInstruction{

    public ConnectionInstruction(String message) {
        super(message);
    }

    public ConnectionInstruction(String message, Sight nextSight) {
        super(message, nextSight);
    }
}
