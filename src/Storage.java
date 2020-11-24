import java.util.ArrayList;
import java.util.List;

public class Storage {

    List<Item> items;

    public Storage(List<Item> items) {
        this.items = items;
    }

    public Storage() {
        this.items = new ArrayList<>();
    }

    public void add(Item item){
        if(items.size()>=10){
            System.out.println("No more Items can be added.");
        }
        else {
            if(!items.contains(item)) {
                items.add(item);
            }
        }
    }

    public void remove(Item item){
        items.remove(item);
    }

    public void pick(Item item){
        for(Item i: items){
            if(i==item) {
                if(i.chosen){
                    i.pick(false);

                }
                else{
                i.pick(true);

                }
            }
            else{
                i.pick(false);
            }



        }

    }

    public void choose(Item item ){ //makes sure the item is not deselected;
        pick(item);

        item.pick(true);
    }

    public void deselectAllItems(){
        for(Item i: items){
            i.pick(false);
        }


    }



}
