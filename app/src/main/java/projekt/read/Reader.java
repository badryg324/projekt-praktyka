/*package projekt.read;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Reader {

    String pom;
    String title;
    String director;
    String hall;
    String date;

    ArrayList<HBox> boxes = new ArrayList<HBox>();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/movieList.txt"), StandardCharsets.UTF_8));


    public ArrayList<HBox> addList(){
        try {
            while ((pom = bufferedReader.readLine()) != null) {

                title = pom.substring(0, pom.indexOf(';'));
                pom = pom.substring(pom.indexOf(';')+1);
                director = pom.substring(0, pom.indexOf(';'));
                pom = pom.substring(pom.indexOf(';')+1);
                hall = pom.substring(0, pom.indexOf(';'));
                pom = pom.substring(pom.indexOf(';')+1);
                date = pom.substring(0, pom.indexOf(';'));

                HBox list = new HBox();
                list.getChildren().addAll(new Button(" * ").setOnAction(event -> viewModel.addHallReference()), new Label(title), new Label(director), new Label(hall), new Label(date), new Button("Delete"));
                boxes.add(list);
            }
            bufferedReader.close();
        } catch(IOException e){
            System.out.println("problem");
        }

        return boxes;
    }
}
*/