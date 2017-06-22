import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.*;

/**
 * Created by Guest_Account on 6/6/2017.
 */
public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double SCREEN_WIDTH = screenBounds.getWidth();
        double SCREEN_HEIGHT = screenBounds.getHeight();

        Group root = new Group();
        ObservableList list = root.getChildren();

        Scene main = new TitleScreen(root, SCREEN_WIDTH, SCREEN_HEIGHT, list, primaryStage);
        main.setFill(Color.WHITE);

        primaryStage.setTitle("Super Tic-Tac-Toe");
        primaryStage.setScene(main);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
