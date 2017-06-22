import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Guest_Account on 6/9/2017.
 */
public class TitleScreen extends Scene {
    public TitleScreen(final Parent root, final double width, final double height, final ObservableList list, final Stage stage) throws FileNotFoundException {
        super(root);
        ImageView titleScreen = new ImageView(new Image(getClass().getResourceAsStream("titlescreen.png")));
        titleScreen.setX(0);
        titleScreen.setY(0);
        titleScreen.setFitWidth(width);
        titleScreen.setFitHeight(height * 1.11);
        list.add(titleScreen);

        Button localPlay = new Button();
        localPlay.setLayoutX(width * 0.065);
        localPlay.setLayoutY(height * 0.415);
        localPlay.setPrefSize(width * 0.19, height * 0.11);
        localPlay.setStyle("-fx-background-color: transparent");
        list.add(localPlay);

        localPlay.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    Group newRoot = new Group();
                    ObservableList newList = newRoot.getChildren();
                    stage.setScene(new SuperTicTacToe(newRoot, newList, width, height, stage));
                    stage.setFullScreen(true);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        Button onlinePlay = new Button();
        onlinePlay.setLayoutX(width * 0.065);
        onlinePlay.setLayoutY(height * 0.57);
        onlinePlay.setPrefSize(width * 0.22, height * 0.11);
        onlinePlay.setStyle("-fx-background-color: transparent");
        list.add(onlinePlay);

        onlinePlay.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "This Feature is not yet active");
                alert.show();
            }
        });

        Button credits = new Button();
        credits.setLayoutX(width * 0.065);
        credits.setLayoutY(height * 0.72);
        credits.setPrefSize(width * 0.175, height * 0.11);
        credits.setStyle("-fx-background-color: transparent");
        list.add(credits);
    }
}
