public class MysteryGameModel {



    Storage storage;
    int currentSightindex;
    String gameMode;
    GameMode mode;

    public MysteryGameModel(String gameMode) {

        mode = new GameMode(gameMode);
        this.gameMode = gameMode;
        storage = new Storage();
        currentSightindex = 0;



        Item circleItem =new Item("Circle");
        storage.add(circleItem);
        Item weirdItem = new Item("Weird");
        storage.add(weirdItem);
        //storage.pick(weirdItem);
        //storage.add(new Item("Kraken"));
        //storage.add(new Item("Weird2"));
        storage.add(new Item("Banner"));
    }
}
