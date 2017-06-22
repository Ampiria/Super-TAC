import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Guest_Account on 6/6/2017.
 */
public enum Player {
    X("x.png", "X", "xVictory.jpg", "xDefeat.jpg"),
    O("o.png", "O", "oVictory.jpg", "oDefeat.jpg");

    private String username, imagePath, victoryPath, defeatPath;
    private Image image, victory, defeat;
    private String character;

    Player(String path, String character, String victoryPath, String defeatPath){
        this.victoryPath = victoryPath;
        this.defeatPath = defeatPath;
        this.imagePath = path;
        this.character = character;
    }

    private void setUsername(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getCharacter() {
        return character;
    }

    public Image getImage(double screenWidth, double screenHeight) {
        image = new Image(getClass().getResourceAsStream(imagePath), screenWidth * 0.2/6.75, screenHeight * 0.3/6, true, true);
        return image;
    }

    public Image getVictory(double screenWidth, double screenHeight) {
        victory = new Image(getClass().getResourceAsStream(victoryPath), screenWidth * 0.2/6.75, screenHeight * 0.3/6, false, true);
        return victory;
    }

    public Image getDefeat(double screenWidth, double screenHeight) {
        defeat = new Image(getClass().getResourceAsStream(defeatPath), screenWidth, screenHeight, false, true);
        return defeat;
    }
}
