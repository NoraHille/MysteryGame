public class Item {

    String name;
    boolean chosen;


    public Item(String name) {
        this.name = name;
        chosen = false;
    }


    public void pick(boolean bool){
        chosen = bool;
    }

}
