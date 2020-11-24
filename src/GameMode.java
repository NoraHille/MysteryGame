
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class GameMode {


    List<Sight> sights = new ArrayList<>();
    List<Connection> connections = new ArrayList<>();

    public GameMode(String gameName) {

        switch (gameName){

            case "firstTry":
                List<String> listi = new ArrayList<>();
                listi.add("Banner");
                Sight banner = new Sight("Banner",listi);


                List<String> listy = new ArrayList<>();
                listy.add("Group");
                InstructionLink messageInstruc = new InstructionLink("Hi, I am Luna! Thanks for the beautiful pet!");
                Item kraken = new Item("Kraken");
                messageInstruc.necesarryItem = kraken;

                SightInstruction sightInstruction = new SightInstruction("Hey! This is a picture I drew!", banner);

                TouchField luna = new TouchField(new Point2D(122, 195),messageInstruc );
                TouchField enuca = new TouchField(new Point2D(815, 265),sightInstruction, 80);
                List<TouchField> touchFieldList = new ArrayList<>();
                touchFieldList.add(luna);
                touchFieldList.add(enuca);
                Sight group = new Sight("Group", listy,touchFieldList);


                List<String> list = new ArrayList<>();
                list.add("Weird");



                ItemInstruction i = new ItemInstruction("you found a Kraken!", kraken);
                SightInstruction s = new SightInstruction("You meet a group", group);

                TouchField tf0 = new TouchField(new Point2D(523, 200), s);
                TouchField tf1 = new TouchField(408, 647);
                TouchField tf2 = new TouchField(new Point2D(850,400),i );

                System.out.println(i.getClass());


                List<TouchField> tfList = new ArrayList<>();
                tfList.add(tf0);
                tfList.add(tf1);
                tfList.add(tf2);




                Sight weird = new Sight("Weird", list, tfList);
                weird.previousSight = weird;
                sights.add(weird);
                sights.add(group);
                sights.add(banner);

                List<String> lis = new ArrayList<>();
                lis.add("Circle");

                Sight c = new Sight("Circle", lis, null);
                connections.add(new Connection(c, group));

                sights.add(c);
                break;
        }

    }
}
