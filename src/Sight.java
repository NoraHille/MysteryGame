import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sight {

    String title;
    List<String> Layers;
    List<TouchField> touchFields;
    int currentLayer = 0;
    Sight previousSight = null;


    public Sight(String title, List<String> layers, List<TouchField> touchPoints) {
        this.title = title;
        Layers = layers;
        this.touchFields = touchPoints;
    }

    public  Sight(String title,List<String> layers ){
        this.title = title;
        Layers = layers;
        touchFields = new ArrayList<>();

    }

}










