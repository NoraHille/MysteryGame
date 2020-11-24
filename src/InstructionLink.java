public class InstructionLink {

    // this class is the gear of the game, the entire inner logic that perscribes
    // what actions will be performed when a touchField is clicked


    String message = "";
    Item necesarryItem = null;
    String noItemErrorMessage = "You need to use an item here.";
    String wrongItemErrorMessage = "You used the wrong item.";
    boolean deactivateAfterOneAction = true;
    boolean deleteItemUponUse = true;
    boolean active = true;



    public InstructionLink(String message) {
        this.message = message;
    }

    public InstructionLink(String message, Item necesarryItem, String noItemErrorMessage, String wrongItemErrorMessage) {
        this.message = message;
        this.necesarryItem = necesarryItem;
        this.noItemErrorMessage = noItemErrorMessage;
        this.wrongItemErrorMessage = wrongItemErrorMessage;
    }
}
