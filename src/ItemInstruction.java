public class ItemInstruction extends InstructionLink {

    Item foundItem = null;



    public ItemInstruction(String message, Item foundItem) {
        super(message);
        this.foundItem = foundItem;
    }
}
