import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Guest_Account on 6/6/2017.
 */
public enum BoardPositions {
    TOPLEFT(0, 0, "/tl.png"),
    TOPMIDDLE(1, 0, "/tm.png"),
    TOPRIGHT(2, 0, "/tr.png"),
    MIDDLELEFT(0, 1, "/ml.png"),
    CENTER(1, 1, "/c.png"),
    MIDDLERIGHT(2, 1, "/mr.png"),
    BOTTOMLEFT(0, 2, "/bl.png"),
    BOTTOMMIDDLE(1, 2, "/bm.png"),
    BOTTOMRIGHT(2, 2, "/br.png");

    private int x;
    private int y;
    private Player occupant;
    private String abbreviation;
    private Image image;

    BoardPositions(int x, int y, String abbr){
        this.x = x;
        this.y = y;
        abbreviation = abbr;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player getOccupant() {
        return occupant;
    }

    public void setOccupant(Player occupant) {
        this.occupant = occupant;
    }

    public Image getAbbreviation() {
        image = new Image(getClass().getResourceAsStream(abbreviation));
        return image;
    }
}
